package bd.edu.seu.giftorderservice;

import bd.edu.seu.giftorderservice.model.GiftOrder;
import bd.edu.seu.giftorderservice.model.Item;
import bd.edu.seu.giftorderservice.repository.GiftOrderRepository;
import bd.edu.seu.giftorderservice.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

@SpringBootTest
class GiftOrderServiceApplicationTests {

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private GiftOrderRepository giftOrderRepository;
	@Test
	void contextLoads() {
	}
	@Test
	void createItem(){
		Item item=new Item("item1","watch",2,600.0);
		Item saveditem = itemRepository.save(item);

		System.out.println(saveditem);
	}
	@Test
	void createGiftOrder(){
		GiftOrder giftOrder=new GiftOrder("o1","12333","banani","019", LocalDate.of(2020, Month.AUGUST,12),650.0);
		Item item=new Item("item1","watch",2,600.0);
		itemRepository.save(item);
		giftOrder.addItem(item);
		GiftOrder savedGiftOrder = giftOrderRepository.save(giftOrder);
		System.out.println(savedGiftOrder);
	}

}
