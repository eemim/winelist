package hh.sof3as3.WineList;

import java.math.BigDecimal;
import java.util.Arrays;
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
import hh.sof3as3.WineList.domain.User;
import hh.sof3as3.WineList.domain.UserRepository;
import hh.sof3as3.WineList.domain.Wine;
import hh.sof3as3.WineList.domain.WineRepository;

@SpringBootApplication
public class WineListApplication {
	private static final Logger log = LoggerFactory.getLogger(WineListApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WineListApplication.class, args);
	}

	@Bean
	public CommandLineRunner demos(WineRepository wineRepo, TypeRepository typeRepo, FoodRepository foodRepo, UserRepository userRepo) {

		return (args) -> {

			Type type1 = typeRepo.save(new Type("Punaviini"));
			Type type2 = typeRepo.save(new Type("Valkoviini"));
			Type type3 = typeRepo.save(new Type("Kuohuviini"));
			Type type4 = typeRepo.save(new Type("Roséviini"));

			Food food1 = new Food("Lohi");
			Food food2 = new Food("Pasta");
			Food food3 = new Food("Pizza");
			Food food4 = new Food("Nauta");
			Food food5 = new Food("Sika");
			Food food6 = new Food("Äyriäiset");
			Food food7 = new Food("Nautiskelu");
			Food food8 = new Food("Kana");


			Wine wine1 = new Wine("Ruppertsberger Gold Imperial Off-Dry Riesling", type2, BigDecimal.valueOf(13.99), "Pirteä & hedelmäinen",
					"Saksa", 2020, 5, "Loistavaa esimerkiksi jouluna lohen kanssa. Kokeile myös lohisalaatin parina.");
			Wine wine2 = new Wine("Tommasi Valpolicella", type1, BigDecimal.valueOf(13.99), "Marjaisa & raikas", "Italia", 2022, 4,
					"Kevyt ja marjaisa peruspunaviini. Sopii tuhdimman pitsan tai pastan kanssa, ei siedä kuitenkaan tulisuutta.");
			Wine wine3 = new Wine("Altolandon Rayuelo", type1, BigDecimal.valueOf(18.90), "Mehevä & hilloinen", "Espanja", 2019, 3, "Mukavan täyteläinen "
					+ "alkuviini, joka sopii esimerkiksi mediumkypsän lihan kanssa. Hinta kuitenkin melko korkea laatuun nähden.");
			Wine wine4 = new Wine("Zenata Ripassa Valpolicella Superiore", type1, BigDecimal.valueOf(23.98), "Mehevä & hilloinen",
					"Italia", 2018, 5, "Erinomaisen pehmeä ja harmoninen, keskitäyteläinen ja keskitanniininen punaviini. Antaa vastinetta hinnalleen. "
							+ "Sopii erityisesti tummien liharuokien ja pataruokien kanssa, mutta myös tuhdimman pastan kaveriksi.");
			Wine wine5 = new Wine("Torres Digno Cabernet Sauvignon Rosé", type4, BigDecimal.valueOf(11.99), "Kuiva", "Chile", 2021,
					4, "Avoin, puhdaspiirteisen raikas, hyvin kypsän marjainen ja luonteikas tuoksu. Tuoksun aromit tarkasti "
							+ "toistava maku on kuiva, mutta erittäin marjainen. Sopii loistavasti vaalean lihan, pastan tai ruokaisan salaatin kera.");
			Wine wine6 = new Wine("Lindauer Special Reserve Blanc de Blancs Brut", type3, BigDecimal.valueOf(16.99), "Runsas & paahteinen", 
					"Uusi-Seelanti", 2020, 5, "Hedelmäinen, paahteinen, kermainen ja ryhdikkään hapokas kuohuviini, joka parittuu "
							+ "mainiosti vaalealihaisen kalan tai äyriäisten kanssa.");
			
			wine1.getFoods().add(food1);
			wine2.getFoods().add(food2);
			wine2.getFoods().add(food3);
			wine3.getFoods().add(food4);
			wine4.getFoods().add(food2);
			wine4.getFoods().add(food4);
			wine4.getFoods().add(food7);
			wine5.getFoods().add(food2);
			wine5.getFoods().add(food7);
			wine5.getFoods().add(food8);
			wine6.getFoods().add(food6);

			foodRepo.saveAll(Arrays.asList(food1, food2, food3, food4, food5, food6, food7, food8));
			wineRepo.saveAll(Arrays.asList(wine1, wine2, wine3, wine4, wine5, wine6));

			
			User user1 = new User("user", "$2a$10$A4TLhJTs75Bw6aX2Xkd8TulID70tQKBOFuv/5J4/AmCOypFO5MxN6", "USER");
			User user2 = new User("admin", "$2a$10$wra58GVDaLEQ9fKnRBmEW.5W9h1itpdv5ea5JZzMGwICJfk3tvsxS", "ADMIN");
			userRepo.save(user1);
			userRepo.save(user2);

			List<Wine> wines = (List<Wine>) wineRepo.findAll();
			for (Wine wine : wines) {
				log.info(wine.toString());

			}
		};
	}

}
