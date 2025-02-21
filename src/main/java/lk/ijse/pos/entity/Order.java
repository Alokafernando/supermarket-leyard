package lk.ijse.pos.entity;

import lombok.*;

import java.sql.Date;
import java.util.ArrayList;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class Order {

    private String orderId;
    private String customerId;
    private Date orderDate;

    private ArrayList<OrderDetails> orderDetailsDTOS;
}
