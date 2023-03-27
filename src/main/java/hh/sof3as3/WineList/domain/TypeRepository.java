package hh.sof3as3.WineList.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, Long> {
	List<Type> findByNameIgnoreCase(String name);

}
