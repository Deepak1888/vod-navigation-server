package com.globant.vod.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.vod.model.Video;

/**
 * @author rohitkumar.patel
 */
@Repository
public class VideoDao {

    @Value("${index.name.video}")
    private String INDEX;

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    private ObjectMapper objectMapper = null;

    public List<IndexResponse> insertVideo(List<Video> videos) {
        List<IndexResponse> result = new ArrayList<IndexResponse>();
        for (Video video : videos) {
            objectMapper = new ObjectMapper();
            video.setId(UUID.randomUUID().toString());
            video.setCreatedDate(System.currentTimeMillis());
            Map dataMap = objectMapper.convertValue(video, Map.class);
            IndexRequest indexRequest = new IndexRequest(INDEX, INDEX, video.getId()).source(dataMap);
            try {
                IndexResponse response = restHighLevelClient.index(indexRequest);
                result.add(response);
            } catch (

            ElasticsearchException e) {
                e.getDetailedMessage();
            } catch (java.io.IOException ex) {
                ex.getLocalizedMessage();
            }
        }
        return result;
    }

    public Map<String, Object> getVideoById(String id) {
        GetRequest getRequest = new GetRequest(INDEX);// , "", id);
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getRequest);
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        return sourceAsMap;
    }

    public List<Video> fetchVideosOfCategory(String category) {
        List<Video> sourceAsMap = new ArrayList<Video>();
        SearchRequest searchRequest = new SearchRequest(INDEX);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchQuery("isActive", true));
        if (!category.equalsIgnoreCase("all")) {
            boolQuery.must(QueryBuilders.matchQuery("categoryList", category));
        }
        sourceBuilder.query(boolQuery);
        sourceBuilder.from(0);
        sourceBuilder.size(100);
        System.out.println("Query =" + sourceBuilder.toString());
        searchRequest.source(sourceBuilder);
        Optional<SearchResponse> checkNull = null;
        SearchResponse searchResponse = null;
        try {
            checkNull = Optional.ofNullable(restHighLevelClient.search(searchRequest));
            if (checkNull.isPresent()) {
                searchResponse = restHighLevelClient.search(searchRequest);
            } else {
                return sourceAsMap;
            }
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        } catch (ElasticsearchStatusException elasticsearchStatusException) {
            return sourceAsMap;
        }
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
            objectMapper = new ObjectMapper();
            Video video = objectMapper.convertValue(hit.getSourceAsMap(), Video.class);
            sourceAsMap.add(video);
        }
        return sourceAsMap;
    }

}
