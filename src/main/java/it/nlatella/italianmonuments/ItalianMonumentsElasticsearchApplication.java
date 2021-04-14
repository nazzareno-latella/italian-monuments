package it.nlatella.italianmonuments;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.opencsv.bean.CsvToBeanBuilder;

import it.nlatella.italianmonuments.data.entity.Monument;
import it.nlatella.italianmonuments.data.service.MonumentService;

@SpringBootApplication
@ServletComponentScan
public class ItalianMonumentsElasticsearchApplication {

	@Autowired
	private MonumentService monumentService;

	public static void main(String[] args) {
		SpringApplication.run(ItalianMonumentsElasticsearchApplication.class, args);
	}

	@PostConstruct
	public void buildIndex() {
		if (monumentService.count() == 0L) {
			System.out.println("Building the Monument Search Index...");
			monumentService.saveAll(prepareDataset());
			System.out.println("...done!");
		}
	}

	private List<Monument> prepareDataset() {
		Resource resource = new ClassPathResource("open_data/italian_monuments_open_data.csv");

		try {
			return new CsvToBeanBuilder<Monument>(new FileReader(resource.getFile(), Charset.forName("UTF-8")))
					.withType(Monument.class).withSkipLines(1).withSeparator(';').build().parse();
		} catch (IllegalStateException | IOException e) {
			return null;
		}
	}
}
