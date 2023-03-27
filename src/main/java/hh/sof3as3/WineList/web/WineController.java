package hh.sof3as3.WineList.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.sof3as3.WineList.domain.FoodRepository;
import hh.sof3as3.WineList.domain.TypeRepository;
import hh.sof3as3.WineList.domain.Wine;
import hh.sof3as3.WineList.domain.WineRepository;

@Controller
public class WineController {
	@Autowired
	private WineRepository wineRepo;
	@Autowired
	private TypeRepository typeRepo;
	@Autowired
	private FoodRepository foodRepo;

	// Näytetään viinit
	@GetMapping("/winelist")
	public String getWines(Model model) {
		model.addAttribute("wines", wineRepo.findAll());

		return "winelist";
	}

	// Lisätään viini
	@GetMapping("/addwine")
	public String addWine(Model model) {
		model.addAttribute("wine", new Wine());
		model.addAttribute("types", typeRepo.findAll());
		model.addAttribute("foods", foodRepo.findAll());
		
		return "addwine";
	}

	@PostMapping(value = "/savewine")
	public String save(Wine wine) {
		wineRepo.save(wine);
		
		return "redirect:/winelist";
	}

	// Poista viini
	@GetMapping("/delete/{id}")
	public String deleteWine(@PathVariable("id") Long id) {
		wineRepo.deleteById(id);
		
		return "redirect:/winelist";
	}
	
	// Muokkaa
	@GetMapping("/edit/{id}")
	public String editWine(@PathVariable("id") Long id, Model model) {
		model.addAttribute("wine", wineRepo.findById(id));
		model.addAttribute("types", typeRepo.findAll());
		
		return "editwine";
	}

}
