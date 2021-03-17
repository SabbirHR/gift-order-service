package bd.edu.seu.giftorderservice.service;

import bd.edu.seu.giftorderservice.exception.ResourceAlreadyExistsException;
import bd.edu.seu.giftorderservice.exception.ResourceDoesNotExistsException;
import bd.edu.seu.giftorderservice.exception.ResourceNotFoundException;
import bd.edu.seu.giftorderservice.model.Item;
import bd.edu.seu.giftorderservice.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    public List<Item> findAll(){
        List<Item> itemList=new ArrayList<>();
        itemRepository.findAll().forEach(itemList::add);
        return itemList;
    }
    public Item findByItemId(String itemId) throws ResourceNotFoundException {
        Item item = itemRepository
                .findById(itemId)
                .orElseThrow(ResourceNotFoundException::new);
        return item;
    }
    public Item create(Item item) throws ResourceAlreadyExistsException {
        if (itemRepository.existsById(item.getItemId())) {
            throw new ResourceAlreadyExistsException();
        } else {
            Item savedItem = itemRepository.save(item);
            return savedItem;
        }
    }
    public Boolean deleteByItemId(String itemId) throws ResourceDoesNotExistsException {
        Optional<Item> item = itemRepository.findById(itemId);
        item.ifPresent(giftShop1 -> itemRepository.deleteById(itemId));
        item.orElseThrow(() -> new ResourceDoesNotExistsException(itemId + ""));
        return true;
    }
}
