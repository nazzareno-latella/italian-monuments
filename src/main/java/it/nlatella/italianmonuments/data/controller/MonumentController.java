package it.nlatella.italianmonuments.data.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.nlatella.italianmonuments.data.entity.Monument;
import it.nlatella.italianmonuments.data.service.MonumentService;

@RestController
@RequestMapping(value = "/monument")
public class MonumentController {

	@Autowired
	private MonumentService monumentService;

	@GetMapping
	public Iterable<Monument> findAll() {
		return monumentService.findAll();
	}

	@GetMapping(params = { "municipality" })
	public List<Monument> findByMunicipalityContaining(@RequestParam(required = true) final String municipality) {
		return monumentService.findByMunicipalityContaining(municipality);
	}

	@GetMapping(params = { "province" })
	public List<Monument> findByProvinceContaining(@RequestParam(required = true) final String province) {
		return monumentService.findByProvinceContaining(province);
	}

	@GetMapping(params = { "region" })
	public List<Monument> findByRegionContaining(@RequestParam(required = true) final String region) {
		return monumentService.findByRegionContaining(region);
	}

	@GetMapping(params = { "name" })
	public List<Monument> findByNameContaining(@RequestParam(required = true) final String name) {
		return monumentService.findByNameContaining(name);
	}

	@GetMapping(params = { "type" })
	public List<Monument> findByTypeContaining(@RequestParam(required = true) final String type) {
		return monumentService.findByTypeContaining(type);
	}

	@PostMapping
	public void save(@RequestBody final Monument monument) {
		monumentService.save(monument);
	}

	@DeleteMapping
	public void deleteAll() {
		monumentService.deleteAll();
	}

	@GetMapping(params = { "searchQuery" })
	public List<Monument> search(@RequestParam(required = true) final String searchQuery,
			@RequestParam(required = true) final boolean lucky) {
		return monumentService.search(searchQuery, lucky);
	}

	@GetMapping(params = { "suggestQuery" })
	public List<String> suggest(@RequestParam(required = true) final String suggestQuery) {
		return monumentService.suggest(suggestQuery);
	}
}
