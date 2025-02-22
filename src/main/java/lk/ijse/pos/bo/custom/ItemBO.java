package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.model.CustomerDTO;
import lk.ijse.pos.model.ItemDTO;
import lk.ijse.pos.tm.CustomerTM;
import lk.ijse.pos.tm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean save(ItemDTO item) throws SQLException, ClassNotFoundException;
    void update(ItemDTO item) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
    ItemTM findById(String selectedItemId) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException;

}
