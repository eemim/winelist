package hh.sof3as3.WineList.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface WineRepository extends CrudRepository<Wine, Long> {
	
	List<Wine> findByNameContainingIgnoreCase(String name);
	
	List<Wine> findByCountryContainingIgnoreCase(String country);
	
	List<Wine> findByType(Type type);
	
	List<Wine> findByNameContainingIgnoreCaseAndCountryContainingIgnoreCase(String name, String country);
	
	List<Wine> findByNameContainingIgnoreCaseAndType(String name, Type type);
	
	List<Wine> findByCountryContainingIgnoreCaseAndType(String country, Type type);
	
}
