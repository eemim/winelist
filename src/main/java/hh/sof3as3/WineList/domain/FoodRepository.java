package hh.sof3as3.WineList.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<Food, Long> {
	 List<Food> findByNameIgnoreCase(String name);

}
