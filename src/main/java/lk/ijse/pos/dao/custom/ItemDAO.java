package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.tm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item> {
    ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException;
    ItemTM findById(String selectedItemId) throws SQLException, ClassNotFoundException;
    boolean reduceQty(OrderDetails orderDetailsDTO) throws SQLException, ClassNotFoundException;
}
