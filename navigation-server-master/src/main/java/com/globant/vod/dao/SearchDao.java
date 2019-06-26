package com.globant.vod.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.elasticsearch.ElasticsearchStatusException;
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
public class SearchDao {

    @Value("${index.name.video}")
    private String INDEX;

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    private ObjectMapper objectMapper = null;

    public List<Video> search(com.globant.vod.model.SearchRequest request) {
        List<Video> sourceAsMap = new ArrayList<Video>();
        SearchRequest searchRequest = new SearchRequest(INDEX);
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery().minimumShouldMatch(1);

        boolQuery
                .should(QueryBuilders
                        .matchQuery("title", request.getQuery())
                        .fuzziness(1)
                        .prefixLength(2)
                        .boost(2));
        boolQuery
                .should(QueryBuilders
                        .matchQuery("description", request.getQuery())
                        .fuzziness(1)
                        .prefixLength(2)
                        .boost(1));
        boolQuery
                .should(QueryBuilders.matchQuery("tags", request.getQuery()).fuzziness(1).prefixLength(2).boost(2));
        boolQuery.must(QueryBuilders.matchQuery("isActive", true));

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(boolQuery);
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
