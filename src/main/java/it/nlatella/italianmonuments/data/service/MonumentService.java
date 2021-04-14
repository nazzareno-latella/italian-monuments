package it.nlatella.italianmonuments.data.service;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder.Type;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import it.nlatella.italianmonuments.data.entity.Monument;
import it.nlatella.italianmonuments.data.repository.MonumentRepository;

@Service
public class MonumentService {

	private static final String MONUMENT_INDEX = "monumentindex";

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;

	@Autowired
	private MonumentRepository monumentRepository;

	public long count() {
		return monumentRepository.count();
	}

	public Iterable<Monument> findAll() {
		return monumentRepository.findAll();
	}

	public List<Monument> findByMunicipality(final String municipality) {
		return monumentRepository.findByMunicipality(municipality);
	}

	public List<Monument> findByMunicipalityContaining(final String municipality) {
		return monumentRepository.findByMunicipalityContaining(municipality);
	}

	public List<Monument> findByProvince(final String province) {
		return monumentRepository.findByProvince(province);
	}

	public List<Monument> findByProvinceContaining(final String province) {
		return monumentRepository.findByProvinceContaining(province);
	}

	public List<Monument> findByRegion(final String region) {
		return monumentRepository.findByRegion(region);
	}

	public List<Monument> findByRegionContaining(final String region) {
		return monumentRepository.findByRegionContaining(region);
	}

	public List<Monument> findByName(final String name) {
		return monumentRepository.findByName(name);
	}

	public List<Monument> findByNameContaining(final String name) {
		return monumentRepository.findByNameContaining(name);
	}

	public List<Monument> findByType(final String type) {
		return monumentRepository.findByType(type);
	}

	public List<Monument> findByTypeContaining(final String type) {
		return monumentRepository.findByTypeContaining(type);
	}

	public void saveAll(final List<Monument> monuments) {
		elasticsearchOperations.indexOps(Monument.class).refresh();
		monumentRepository.saveAll(monuments);
	}

	public void save(final Monument monument) {
		monumentRepository.save(monument);
	}

	public void deleteAll() {
		monumentRepository.deleteAll();
	}

	public void delete(final Monument monument) {
		monumentRepository.delete(monument);
	}

	public List<Monument> search(final String searchQuery, final boolean lucky) {
		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		// 1. Create query on multiple fields
		QueryBuilder queryBuilder1 = QueryBuilders.multiMatchQuery(searchQuery).field("name", 70)
				.field("municipality", 20).field("province", 20).field("region", 20).type(Type.PHRASE).boost(100);
		BoolQueryBuilder queryBuilder = boolQueryBuilder.should(queryBuilder1);

		if (!lucky) {
			QueryBuilder queryBuilder2 = QueryBuilders.multiMatchQuery(searchQuery).field("name", 70)
					.field("municipality", 10).field("province", 10).field("region", 10).type(Type.MOST_FIELDS)
					.minimumShouldMatch("100%").boost(50);

			QueryBuilder queryBuilder3 = QueryBuilders.multiMatchQuery(searchQuery).field("name", 70)
					.field("municipality", 10).field("province", 10).field("region", 10).type(Type.MOST_FIELDS)
					.minimumShouldMatch("50%").boost(25);

			queryBuilder = queryBuilder.should(queryBuilder2).should(queryBuilder3);
		}

		Query query = new NativeSearchQueryBuilder().withFilter(queryBuilder).build();

		// 2. Execute search
		SearchHits<Monument> monumentHits = elasticsearchOperations.search(query, Monument.class,
				IndexCoordinates.of(MONUMENT_INDEX));

		// 3. Map searchHits to monument list
		List<Monument> monumentMatches = new ArrayList<Monument>();
		monumentHits.forEach(searchHit -> {
			monumentMatches.add(searchHit.getContent());
			System.out.println(searchHit.getContent().getName() + " [" + searchHit.getScore() + "]");
		});
		return monumentMatches;
	}

	public List<String> suggest(final String suggestQuery) {
		QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("name", "*" + suggestQuery.toLowerCase() + "*");

		Query query = new NativeSearchQueryBuilder().withFilter(queryBuilder).withPageable(PageRequest.of(0, 10))
				.build();

		SearchHits<Monument> searchSuggestions = elasticsearchOperations.search(query, Monument.class,
				IndexCoordinates.of(MONUMENT_INDEX));

		List<String> suggestions = new ArrayList<String>();

		searchSuggestions.getSearchHits().forEach(searchHit -> {
			suggestions.add(searchHit.getContent().getName());
		});
		return suggestions;
	}
}
