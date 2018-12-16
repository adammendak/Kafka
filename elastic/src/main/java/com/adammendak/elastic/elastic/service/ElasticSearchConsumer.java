package com.adammendak.elastic.elastic.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class ElasticSearchConsumer {

    private RestHighLevelClient restHighLevelClient;

    public ElasticSearchConsumer(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = createElasticClient();
    }

    public void insertTestData() {

        String testJson = "{ \"created_at\": \"z serwisu\", \"text\": \"testowy text\" }";

        IndexRequest indexRequest = new IndexRequest("twitter", "tweet")
                .source(testJson, XContentType.JSON);

        try {
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            String id = indexResponse.getId();
            log.info("elastic document id: " + id);
            restHighLevelClient.close();
        } catch (IOException e) {
            log.error("error :" + e.getMessage());
        }
    }

    private RestHighLevelClient createElasticClient() {
        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }

}
