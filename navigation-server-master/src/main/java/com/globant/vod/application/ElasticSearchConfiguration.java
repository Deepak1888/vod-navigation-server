
package com.globant.vod.application;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rohitkumar.patel
 */
@Configuration
public class ElasticSearchConfiguration extends AbstractFactoryBean<RestHighLevelClient> {

	private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchConfiguration.class);
//    @Value("${spring.data.elasticsearch.cluster-nodes}")
//    private String clusterNodes;
//    @Value("${spring.data.elasticsearch.cluster-name}")
//    private String clusterName;
	@Value("${elasticsearch.host}")
	private String elasticSearchHost;
	@Value("${elasticsearch.port}")
	private Integer elasticSearchPort;
	private RestHighLevelClient restHighLevelClient;

	@Override
	public void destroy() {
		try {
			if (restHighLevelClient != null) {
				restHighLevelClient.close();
			}
		} catch (final Exception e) {
			LOG.error("Error closing ElasticSearch client: ", e);
		}
	}

	@Override
	public Class<RestHighLevelClient> getObjectType() {
		return RestHighLevelClient.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	@Override
	public RestHighLevelClient createInstance() {
		return buildClient();
	}

	private RestHighLevelClient buildClient() {
        try {
            restHighLevelClient = new RestHighLevelClient(
                    RestClient
                            .builder(
                            		new HttpHost(elasticSearchHost,elasticSearchPort,"http")));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return restHighLevelClient;
    }
}
