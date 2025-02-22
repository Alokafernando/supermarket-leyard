package lk.ijse.pos.entity;

import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Item {
    private String itemId;
    private String itemName;
    private int quantity;
    private double price;


}
