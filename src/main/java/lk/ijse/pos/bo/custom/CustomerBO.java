package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.model.CustomerDTO;
import lk.ijse.pos.tm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;
    boolean save(CustomerDTO customer) throws SQLException, ClassNotFoundException;
    void update(CustomerDTO customer) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
    CustomerTM findById(String selectedCusId) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
}
