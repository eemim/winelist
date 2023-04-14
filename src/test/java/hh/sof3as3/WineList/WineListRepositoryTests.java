package hh.sof3as3.WineList;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof3as3.WineList.domain.Food;
import hh.sof3as3.WineList.domain.FoodRepository;
import hh.sof3as3.WineList.domain.Type;
import hh.sof3as3.WineList.domain.TypeRepository;
import hh.sof3as3.WineList.domain.User;
import hh.sof3as3.WineList.domain.UserRepository;
import hh.sof3as3.WineList.domain.Wine;
import hh.sof3as3.WineList.domain.WineRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class WineListRepositoryTests {
	
	@Autowired
	private FoodRepository foodRepo;
	
	@Autowired
	private TypeRepository typeRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private WineRepository wineRepo;
	
	//FOODREPO TESTIT
	
	@Test // Ruoka nimen perusteella
	public void findByNameShouldReturnFood() {
		List<Food> foods = foodRepo.findByNameIgnoreCase("nauta");
		
		assertThat(foods).hasSize(1);
	}
	
	@Test // Poistetaan ruoka
	public void deleteByIdShouldReturnEmpty() {
		Food food = foodRepo.findById(Long.valueOf(1)).get();
		foodRepo.delete(food);
		Optional<Food> deletedFood = foodRepo.findById(Long.valueOf(1));
		
		assertThat(deletedFood).isEmpty();
	}
	
	@Test // Lisätään ruoka
	public void addFoodShouldReturnNotNull() {
		Food food = new Food("Risotto");
		foodRepo.save(food);
		
		assertThat(food.getFoodid()).isNotNull();
	}
	
	// TYPEREPO TESTIT
	
	@Test // Tyyppi nimen perusteella
	public void findByNameShouldReturnType() {
		List<Type> types = typeRepo.findByNameIgnoreCase("valkoviini");
		
		assertThat(types).hasSize(1);
	}
	
	// USERREPO TESTIT
	
	@Test // Rooli käyttäjänimen perusteella
	public void findByUsernameShouldReturnRole() {
		User user = userRepo.findByUsername("admin");
		
		assertThat(user.getId()).isNotNull();
		assertThat(user.getRole()).isEqualTo("ADMIN");
	}
	
	// WINEREPO TESTIT
	
	@Test // Viini nimen perusteella
	public void findByNameShouldReturnWine() {
		List<Wine> wines = wineRepo.findByNameContainingIgnoreCase("tommasi valpolicel");
		
		assertThat(wines).hasSize(1);
		assertThat(wines.get(0).getType().getName()).isEqualTo("Punaviini");
	}
	
	@Test // Viini maan perusteella
	public void findByCountryShouldReturnWine() {
		List<Wine> wines = wineRepo.findByCountryContainingIgnoreCase("saks");
		
		assertThat(wines).hasSize(1);
		assertThat(wines.get(0).getName()).containsIgnoringCase("riesling");
	}
	
	@Test // Viini tyypin perusteella
	public void findByTypeShouldReturnWine() {
		Type type = typeRepo.findById(Long.valueOf(4)).get();
		List<Wine> wines = wineRepo.findByType(type);
		
		assertThat(wines).hasSize(1);
		assertThat(wines.get(0).getName()).containsIgnoringCase("torres digno");
	}
	
	@Test // Viini nimen ja maan perusteella
	public void findByNameAndCountryShouldReturnWine() {
		List<Wine> wines = wineRepo.findByNameContainingIgnoreCaseAndCountryContainingIgnoreCase("ol", "itali");
		
		assertThat(wines).hasSize(2);
		assertThat(wines.get(1).getName()).containsIgnoringCase("zenata");
	}
	
	@Test // Viini nimen ja tyypin perusteella
	public void findByNameAndTypeShouldReturnWine() {
		Type type = typeRepo.findById(Long.valueOf(1)).get();
		List<Wine> wines = wineRepo.findByNameContainingIgnoreCaseAndType("tommasi", type);
		
		assertThat(wines).hasSize(1);
		assertThat(wines.get(0).getCountry()).containsIgnoringCase("italia");
	}
	
	@Test // Viini maan ja tyypin perusteella
	public void findByCountryAndTypeShouldReturnWine() {
		Type type = typeRepo.findById(Long.valueOf(1)).get();
		List<Wine> wines = wineRepo.findByCountryContainingIgnoreCaseAndType("espa", type);
		
		assertThat(wines).hasSize(1);
		assertThat(wines.get(0).getName()).containsIgnoringCase("rayuelo");
	}

}
