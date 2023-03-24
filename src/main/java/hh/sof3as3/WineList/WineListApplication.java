package hh.sof3as3.WineList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof3as3.WineList.WineListApplication;
import hh.sof3as3.WineList.domain.Wine;
import hh.sof3as3.WineList.domain.WineRepository;

@SpringBootApplication
public class WineListApplication {
	private static final Logger log = LoggerFactory.getLogger(WineListApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WineListApplication.class, args);
	}

	@Bean
	public CommandLineRunner demos(WineRepository wineRepo) {

		return (args) -> {
			
			wineRepo.save(new Wine("Ruppertsberger Gold Imperial Off-Dry Riesling", 13.99, "Pirteä & hedelmäinen", "Saksa",
					2020, 5, "Loistavaa esimerkiksi jouluna lohen kanssa"));
			wineRepo.save(new Wine("Tommasi Valpolicella", 13.99, "Marjaisa & raikas", "Italia", 2022, 4, 
					"Kevyt ja marjaisa peruspunaviini. Sopii tuhdimman pitsan tai pastan kanssa, ei siedä kuitenkaan tulisuutta."));
			

			List<Wine> wines = (List<Wine>) wineRepo.findAll();
			for (Wine wine : wines) {
				log.info(wine.toString());
			}
		};
	}

}
