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
import hh.sof3as3.WineList.domain.FoodRepository;
import hh.sof3as3.WineList.domain.Type;
import hh.sof3as3.WineList.domain.TypeRepository;
import hh.sof3as3.WineList.domain.Wine;
import hh.sof3as3.WineList.domain.WineRepository;
import jakarta.validation.Valid;

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
		model.addAttribute("types", typeRepo.findAll());
		
		return "winelist";
	}

	// Lisätään viini
	
	@GetMapping("/addwine")
	@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
	public String addWine(Model model) {
		model.addAttribute("wine", new Wine());
		model.addAttribute("types", typeRepo.findAll());
		model.addAttribute("foods", foodRepo.findAll());
		
		return "addwine";
	}

	@PostMapping(value = "/savewine")
	public String saveWine(@Valid @ModelAttribute Wine wine, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("types", typeRepo.findAll());
			model.addAttribute("foods", foodRepo.findAll());
			return "addwine";
		} else {
			
		wineRepo.save(wine);
		return "redirect:/winelist";
		}
	}

	// Poista viini
	
	@GetMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteWine(@PathVariable("id") Long id) {
		wineRepo.deleteById(id);
		
		return "redirect:/winelist";
	}
	
	// Muokkaa viiniä
	
	@GetMapping("/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editWine(@PathVariable("id") Long id, Model model) {
		model.addAttribute("wine", wineRepo.findById(id));
		model.addAttribute("types", typeRepo.findAll());
		model.addAttribute("foods", foodRepo.findAll());
		
		return "editwine";
	}
	
	// Etsi viini (nimen osa, maa tai tyyppi)
	
	@GetMapping("/winelist/search")
	public String wineSearch(@RequestParam(name="nameKeyword", required=false) String name, 
							 @RequestParam(name="countryKeyword", required=false) String country,
							 @RequestParam(name="type", required=false) Type type,
							 Model model) {
	    
		
	    // Ehtolauseet, jotta saadaan haku tehtyä kahden parametrin avulla
		/** (typeRepo.findAll() mukana siksi, että ensimmäisen haun jälkeen searchbar saa Type-repon tiedot ja yhden haun jälkeen
		 * voisi tehdä vielä tarkentavan haun, mutta en ehtinyt toteuttamaan. Nyt se on mukana vain siksi, että näyttäisi
		 * tyhmältä jos Type-tiedot katoaisivat ensimmäisen haun jälkeen.
		 */
		
		if (name != null && !name.isEmpty() && country != null && !country.isEmpty() ) {
	    	model.addAttribute("wines", wineRepo.findByNameContainingIgnoreCaseAndCountryContainingIgnoreCase(name, country));
	    	model.addAttribute("types", typeRepo.findAll());
		}
		else if(name != null && !name.isEmpty() && type != null) {
			model.addAttribute("wines", wineRepo.findByNameContainingIgnoreCaseAndType(name, type));
			model.addAttribute("types", typeRepo.findAll());
		}
		else if (country != null && !country.isEmpty() && type != null) {
			model.addAttribute("wines", wineRepo.findByCountryContainingIgnoreCaseAndType(country, type));
			model.addAttribute("types", typeRepo.findAll());
		}
		
		// Haut yhden parametrin avulla
		
		else if (name != null && !name.isEmpty()) {
	        model.addAttribute("wines", wineRepo.findByNameContainingIgnoreCase(name));
	        model.addAttribute("types", typeRepo.findAll());
	    } 
		else if (country != null && !country.isEmpty()) {
	    	model.addAttribute("wines", wineRepo.findByCountryContainingIgnoreCase(country));
	    	model.addAttribute("types", typeRepo.findAll());
	    } 
		else if (type != null) {
	    	model.addAttribute("wines", wineRepo.findByType(type));
	    	model.addAttribute("types", typeRepo.findAll());
	    } 
		else {    
	    	model.addAttribute("wines", wineRepo.findAll());
	    }

	    return "winelist";
	}
	
	
	
	// REST services
	
		// REST kaikki viinit
	
		@GetMapping(value = "/wines")
		public @ResponseBody List<Wine> getWinesRest(){
			return (List<Wine>) wineRepo.findAll();
		}
		
		// REST viini IDllä
		
		@GetMapping(value = "wines/{id}")
		public @ResponseBody Optional<Wine> getOneWineRest(@PathVariable("id")Long wineid){
			return wineRepo.findById(wineid);
		}
		
		// REST tallenna viini
		
		@PostMapping(value = "/wines")
		public @ResponseBody Wine saveWineRest(@RequestBody Wine wine) {
			return wineRepo.save(wine);
		}

}
