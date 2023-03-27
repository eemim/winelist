package hh.sof3as3.WineList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof3as3.WineList.WineListApplication;
import hh.sof3as3.WineList.domain.Food;
import hh.sof3as3.WineList.domain.FoodRepository;
import hh.sof3as3.WineList.domain.Type;
import hh.sof3as3.WineList.domain.TypeRepository;
import hh.sof3as3.WineList.domain.Wine;
import hh.sof3as3.WineList.domain.WineRepository;

@SpringBootApplication
public class WineListApplication {
	private static final Logger log = LoggerFactory.getLogger(WineListApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WineListApplication.class, args);
	}

	@Bean
	public CommandLineRunner demos(WineRepository wineRepo, TypeRepository typeRepo, FoodRepository foodRepo) {

		return (args) -> {
			
			Type type1 = typeRepo.save(new Type("Punaviini"));
			Type type2 = typeRepo.save(new Type("Valkoviini"));
			Type type3 = typeRepo.save(new Type("Kuohuviini"));
			Type type4 = typeRepo.save(new Type("Roséviini"));
			
			Food food1 = new Food("Lohi");
			Food food2 = new Food("Pasta");
			Food food3 = new Food("Pizza");
			
//			Food food1 = foodRepo.save(new Food("Pasta"));
//			Food food2 = foodRepo.save(new Food("Pizza"));
//			Food food3 = foodRepo.save(new Food("Lohi"));
			
			Wine wine1 = new Wine("Ruppertsberger Gold Imperial Off-Dry Riesling", type2, 13.99, "Pirteä & hedelmäinen", "Saksa",
					2020, 5, "Loistavaa esimerkiksi jouluna lohen kanssa. Kokeile myös lohisalaatin kanssa.");
			Wine wine2 = new Wine("Tommasi Valpolicella", type1, 13.99, "Marjaisa & raikas", "Italia", 2022, 4, 
					"Kevyt ja marjaisa peruspunaviini. Sopii tuhdimman pitsan tai pastan kanssa, ei siedä kuitenkaan tulisuutta.");
			

			
			foodRepo.save(food1);
			foodRepo.save(food2);
			foodRepo.save(food3);
			
			wine1.addFood(food1); // kokeiltu myös jos addFood() Wine modelissa 
			wine2.getFoods().add(food3);
			wine2.getFoods().add(food2);

			
//			food1.getWines().add(wine1);
//			food2.getWines().add(wine2);
//			food3.getWines().add(wine2);
			
			wineRepo.save(wine1);
			wineRepo.save(wine2);
			
		//	wineRepo.save(new Wine("Ruppertsberger Gold Imperial Off-Dry Riesling", type2, 13.99, "Pirteä & hedelmäinen", "Saksa",
		//			2020, null, 5, "Loistavaa esimerkiksi jouluna lohen kanssa. Kokeile myös lohisalaatin kanssa."));
		//	wineRepo.save(new Wine("Tommasi Valpolicella", type1, 13.99, "Marjaisa & raikas", "Italia", 2022, 4, 
		//			"Kevyt ja marjaisa peruspunaviini. Sopii tuhdimman pitsan tai pastan kanssa, ei siedä kuitenkaan tulisuutta."));
			

			List<Wine> wines = (List<Wine>) wineRepo.findAll();
			for (Wine wine : wines) {
				log.info(wine.toString());
				
			}
		};
	}

}
