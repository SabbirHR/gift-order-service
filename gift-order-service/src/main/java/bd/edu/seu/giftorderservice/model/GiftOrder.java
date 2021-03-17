package bd.edu.seu.giftorderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GiftOrder {
    @Id
    private String id;
    private String paymentId;
    private String address;
    private String phoneNumber;
    private LocalDate deliveryTime;
    private double totalCost;

    @OneToMany
    private List<Item> itemList;
    public GiftOrder(String id,String paymentId,String address,String phoneNumber,LocalDate deliveryTime,double totalCost) {
        this.id = id;
        this.paymentId= paymentId;
        this.paymentId=paymentId;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.deliveryTime=deliveryTime;
        this.totalCost=totalCost;
    }
    public void addItem(Item item){
        if(itemList == null)
            itemList = new ArrayList();

        itemList.add(item);
    }
}
