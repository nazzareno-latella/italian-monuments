package it.nlatella.italianmonuments.data.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import it.nlatella.italianmonuments.data.entity.Monument;

public interface MonumentRepository extends ElasticsearchRepository<Monument, Integer> {

	List<Monument> findByMunicipality(String municipality);

	List<Monument> findByMunicipalityContaining(String municipality);

	List<Monument> findByProvince(String province);

	List<Monument> findByProvinceContaining(String province);

	List<Monument> findByRegion(String region);

	List<Monument> findByRegionContaining(String region);

	List<Monument> findByName(String name);

	List<Monument> findByNameContaining(String name);

	List<Monument> findByType(String type);

	List<Monument> findByTypeContaining(String type);
}
