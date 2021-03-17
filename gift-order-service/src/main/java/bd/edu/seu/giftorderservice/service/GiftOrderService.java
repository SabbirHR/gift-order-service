package bd.edu.seu.giftorderservice.service;

import bd.edu.seu.giftorderservice.exception.ResourceAlreadyExistsException;
import bd.edu.seu.giftorderservice.exception.ResourceDoesNotExistsException;
import bd.edu.seu.giftorderservice.exception.ResourceNotFoundException;
import bd.edu.seu.giftorderservice.model.GiftOrder;
import bd.edu.seu.giftorderservice.repository.GiftOrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GiftOrderService {
    private GiftOrderRepository giftOrderRepository;

    public GiftOrderService(GiftOrderRepository giftOrderRepository) {
        this.giftOrderRepository = giftOrderRepository;
    }
    public List<GiftOrder> findAll(){
        List<GiftOrder> giftOrderList=new ArrayList<>();
        giftOrderRepository.findAll().forEach(giftOrderList::add);
        return giftOrderList;
    }

    public GiftOrder findById(String id) throws ResourceNotFoundException {
        GiftOrder giftOrder = giftOrderRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return giftOrder;
    }
    public GiftOrder create(GiftOrder giftOrder) throws ResourceAlreadyExistsException {
        if (giftOrderRepository.existsById(giftOrder.getId())) {
            throw new ResourceAlreadyExistsException();
        } else {
            GiftOrder savedGiftOrder = giftOrderRepository.save(giftOrder);
            return savedGiftOrder;
        }
    }
    public Boolean deleteByid(String id) throws ResourceDoesNotExistsException {
        Optional<GiftOrder> giftOrder = giftOrderRepository.findById(id);
        giftOrder.ifPresent( giftOrder1-> giftOrderRepository.deleteById(id));giftOrder.orElseThrow(() -> new ResourceDoesNotExistsException(id + ""));
        return true;
    }
}
