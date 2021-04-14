package it.nlatella.italianmonuments.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.nlatella.italianmonuments.data.entity.Monument;
import it.nlatella.italianmonuments.data.service.MonumentService;

@Controller
public class SearchController {

	private MonumentService monumentService;

	@Autowired
	public SearchController(MonumentService monumentService) {
		this.monumentService = monumentService;
	}

	@GetMapping("/monuments")
	public void monuments(Model model) {

	}

	@GetMapping("/search")
	public ModelAndView search(@RequestParam String q, @RequestParam boolean lucky) {
		ModelAndView modelAndView = new ModelAndView("search");
		modelAndView.addObject("q", q);

		long start = System.currentTimeMillis();
		List<Monument> monuments = monumentService.search(q, lucky);
		long end = System.currentTimeMillis();
		float seconds = (end - start) / 1000F;

		modelAndView.addObject("size", monuments.size());
		modelAndView.addObject("seconds", seconds);

		modelAndView.addObject("monuments", monuments);
		return modelAndView;
	}

}
