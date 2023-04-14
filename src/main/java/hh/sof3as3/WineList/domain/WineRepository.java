package hh.sof3as3.WineList.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface WineRepository extends CrudRepository<Wine, Long> {
	
//	List<Wine> findByNameContainingIgnoreCaseOrCountryContainingIgnoreCaseOrType(String name, String country, Type type);
//	List<Wine> findByType(Type type);
	
	List<Wine> findByNameContainingIgnoreCase(String name);
	List<Wine> findByCountryContainingIgnoreCase(String country);
	List<Wine> findByType(Type type);
	List<Wine> findByNameContainingIgnoreCaseAndCountryContainingIgnoreCase(String name, String country);
	List<Wine> findByNameContainingIgnoreCaseAndType(String name, Type type);
	List<Wine> findByCountryContainingIgnoreCaseAndType(String country, Type type);
	
//	List <Wine> findByCountryContainingIgnoreCase(String country);
	

}
