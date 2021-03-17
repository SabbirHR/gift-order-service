package bd.edu.seu.giftorderservice.controller;

import bd.edu.seu.giftorderservice.exception.ResourceAlreadyExistsException;
import bd.edu.seu.giftorderservice.exception.ResourceDoesNotExistsException;
import bd.edu.seu.giftorderservice.exception.ResourceNotFoundException;
import bd.edu.seu.giftorderservice.model.Item;
import bd.edu.seu.giftorderservice.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("api/item")
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("")
    public ResponseEntity<List<Item>> getItems() {
        List<Item> itemList = itemService.findAll();
        return ResponseEntity.ok(itemList);
    }
    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItem(@PathVariable String itemId) {
        try {
            Item item=itemService.findByItemId(itemId);
            return ResponseEntity.ok(item);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        try {
            Item createdGiftShop = itemService.create(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGiftShop);
        } catch (ResourceAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> deleteByItemId(@PathVariable String itemId) {
        try {
            Boolean deleteByItemId = itemService.deleteByItemId(itemId);
            return ResponseEntity.ok(itemId);
        } catch (ResourceDoesNotExistsException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
