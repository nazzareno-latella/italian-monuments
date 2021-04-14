package it.nlatella.italianmonuments.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "it.nlatella.italianmonuments.config.data.repository")
public class ElasticsearchConfiguration extends AbstractElasticsearchConfiguration {

	@Override
	@Bean
	public RestHighLevelClient elasticsearchClient() {
		String host = "localhost";
		int port = 9200;
		String username = "username";
		String password = "password";

		String databaseUrl = System.getenv("BONSAI_URL");
		if (databaseUrl != null) {
			URI dbUri;
			try {
				dbUri = new URI(System.getenv("BONSAI_URL"));
			} catch (URISyntaxException e) {
				final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
						.connectedTo(host + ':' + port).usingSsl().withBasicAuth(username, password).build();

				return RestClients.create(clientConfiguration).rest();
			}

			host = dbUri.getHost();
			port = dbUri.getPort();
			username = dbUri.getUserInfo().split(":")[0];
			password = dbUri.getUserInfo().split(":")[1];
		}

		final ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(host + ':' + port)
				.usingSsl().withBasicAuth(username, password).build();

		return RestClients.create(clientConfiguration).rest();
	}
}
