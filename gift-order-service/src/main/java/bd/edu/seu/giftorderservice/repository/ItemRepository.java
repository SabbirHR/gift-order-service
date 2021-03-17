package bd.edu.seu.giftorderservice.repository;

import bd.edu.seu.giftorderservice.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item,String> {
}
