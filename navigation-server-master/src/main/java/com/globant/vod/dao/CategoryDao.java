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
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globant.vod.model.Category;

/**
 * @author rohitkumar.patel
 */
@Repository
public class CategoryDao {

    @Value("${index.name.category}")
    private String INDEX;

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    private ObjectMapper objectMapper = null;

    public Category insertCategory(Category category) {
        objectMapper = new ObjectMapper();
        category.setId(UUID.randomUUID().toString());
        Map dataMap = objectMapper.convertValue(category, Map.class);
        IndexRequest indexRequest = new IndexRequest(INDEX, INDEX, category.getId())
                .source(dataMap);
        try {
            IndexResponse response = restHighLevelClient.index(indexRequest);
        } catch (ElasticsearchException e) {
            e.getDetailedMessage();
        } catch (java.io.IOException ex) {
            ex.getLocalizedMessage();
        }
        return category;
    }

    public Map<String, Object> getcategoryById(String id) {
        GetRequest getRequest = new GetRequest(INDEX, INDEX, id);
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getRequest);
        } catch (java.io.IOException e) {
            e.getLocalizedMessage();
        }
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        return sourceAsMap;
    }

    public List<Category> getAllCategories() {
        List<Category> sourceAsMap = new ArrayList<Category>();
        SearchRequest searchRequest = new SearchRequest(INDEX);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
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
            Category category = objectMapper.convertValue(hit.getSourceAsMap(), Category.class);
            sourceAsMap.add(category);
        }
        return sourceAsMap;
    }

}
