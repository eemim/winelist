package hh.sof3as3.WineList.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface WineRepository extends CrudRepository<Wine, Long> {
	List<Wine> findByNameIgnoreCase(String name);

}
