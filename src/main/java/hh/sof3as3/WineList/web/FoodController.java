package hh.sof3as3.WineList.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof3as3.WineList.domain.Food;
import hh.sof3as3.WineList.domain.FoodRepository;
import hh.sof3as3.WineList.domain.WineRepository;
import jakarta.validation.Valid;

@Controller
public class FoodController {
	
	@Autowired
	private FoodRepository foodRepo;
	
	
	// Näytetään ruoat
	
	@GetMapping("/foodlist")
	public String getFoods(Model model) {
		model.addAttribute("foods", foodRepo.findAll());
		
		return "foodlist";
	}
	
	// Lisäätään ruoka
	
	@GetMapping("/addfood")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public String addFood(Model model) {
		model.addAttribute("food", new Food());
		
		return "addfood";
	}
	
	@PostMapping("/savefood")
	public String saveFood(@Valid @ModelAttribute Food food, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "addfood";
		} else {
			
			foodRepo.save(food);
			return "redirect:/foodlist";
			
		}

	}
	
	// Poistetaan ruoka
	
	@GetMapping("/delete/food/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteFood(@PathVariable Long id, Model model) {
		foodRepo.deleteById(id);
		
		return "redirect:/foodlist";
	}
	
	
	//REST services
		
		// REST kaikki ruoat
	
		@GetMapping(value = "/foods")
		public @ResponseBody List<Food> getFoodsRest(){
			return(List<Food>) foodRepo.findAll();
		}
		
		// REST ruoka IDllä
		
		@GetMapping(value = "/foods/{id}")
		public @ResponseBody Optional<Food> getOneFoodRest(@PathVariable("id")Long foodid){
			return foodRepo.findById(foodid);
		}
		
		// REST tallenna ruoka
		
		@PostMapping(value = "/foods")
		public @ResponseBody Food saveFoodRest(@RequestBody Food food) {
			return foodRepo.save(food);
		}

}
