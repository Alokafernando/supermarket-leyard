package lk.ijse.pos.model;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {
    private String itemId;
    private String itemName;
    private int quantity;
    private double price;
}
