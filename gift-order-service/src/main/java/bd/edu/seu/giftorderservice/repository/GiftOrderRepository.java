package bd.edu.seu.giftorderservice.repository;

import bd.edu.seu.giftorderservice.model.GiftOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftOrderRepository extends CrudRepository<GiftOrder,String> {
}
