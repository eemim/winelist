package hh.sof3as3.WineList.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.sof3as3.WineList.domain.Food;
import hh.sof3as3.WineList.domain.FoodRepository;

@Controller
public class FoodController {
	
	@Autowired
	private FoodRepository foodRepo;
	
	//näytetään ruoat
	@GetMapping("/foodlist")
	public String getFoods(Model model) {
		model.addAttribute("foods", foodRepo.findAll());
		
		return "foodlist";
	}
	
	//lisäätään ruoka
	@GetMapping("/addfood")
	public String addFood(Model model) {
		model.addAttribute("food", new Food());
		
		return "addfood";
	}
	
	@PostMapping("/savefood")
	public String saveFood(@ModelAttribute Food food) {
		foodRepo.save(food);
		
		return "redirect:/foodlist";
	}
	
	//poistetaan ruoka
	@GetMapping("/delete/food/{id}")
	public String deleteFood(@PathVariable Long id, Model model) {
		foodRepo.deleteById(id);
		
		return "redirect:/foodlist";
	}
	

}
