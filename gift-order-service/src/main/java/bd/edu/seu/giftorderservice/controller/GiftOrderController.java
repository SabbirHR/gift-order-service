package bd.edu.seu.giftorderservice.controller;

import bd.edu.seu.giftorderservice.exception.ResourceAlreadyExistsException;
import bd.edu.seu.giftorderservice.exception.ResourceNotFoundException;
import bd.edu.seu.giftorderservice.model.GiftOrder;
import bd.edu.seu.giftorderservice.model.Item;
import bd.edu.seu.giftorderservice.service.GiftOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("api/giftOrder")
public class GiftOrderController {
    private GiftOrderService giftOrderService;

    public GiftOrderController(GiftOrderService giftOrderService) {
        this.giftOrderService = giftOrderService;
    }
    @GetMapping("")
    public ResponseEntity<List<GiftOrder>> getGiftShops() {
        List<GiftOrder> giftOrderList = giftOrderService.findAll();
        return ResponseEntity.ok(giftOrderList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GiftOrder> getGiftOrder(@PathVariable String id) {
        try {
            GiftOrder giftOrder = giftOrderService.findById(id);
            return ResponseEntity.ok(giftOrder);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<GiftOrder> createGiftShop(@RequestBody GiftOrder giftShop) {
        try {
            GiftOrder createdGiftOrder = giftOrderService.create(giftShop);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGiftOrder);
        } catch (ResourceAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
