package hh.sof3as3.WineList;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof3as3.WineList.web.FoodController;
import hh.sof3as3.WineList.web.WineController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WineListApplicationTests {
	
	@Autowired
	private WineController wineController;
	
	@Autowired
	private FoodController foodController;

	@Test
	void contextLoads() throws Exception {
		assertThat(wineController).isNotNull();
		assertThat(foodController).isNotNull();
	}

}
