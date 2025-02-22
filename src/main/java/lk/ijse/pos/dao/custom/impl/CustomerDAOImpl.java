package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.tm.CustomerTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {


    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from customer");

        ArrayList<Customer> customerDTOS = new ArrayList<>();

        while (rst.next()) {
            Customer customerDTO = new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));

            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "insert into customer values (?,?,?,?,?)",
                entity.getId(), entity.getName(), entity.getNic(), entity.getEmail(), entity.getPhone());
    }

    @Override
    public void update(Customer entity) throws SQLException, ClassNotFoundException {
         SQLUtil.execute("update customer set name=?, nic=?, email=?, phone=? where customer_id=?",
                entity.getName(), entity.getNic(), entity.getEmail(), entity.getPhone(), entity.getId());
    }

    @Override
    public void delete(String customerId) throws SQLException, ClassNotFoundException {
         SQLUtil.execute("delete from customer where customer_id=?", customerId);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select customer_id from customer order by customer_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("C%03d", newIdIndex);
        }
        return "C001";
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        // empty
    }

    @Override
    public CustomerTM findById(String selectedCusId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from customer where customer_id=?", selectedCusId);

        if (rst.next()) {
            return new CustomerTM(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException {
        ResultSet rst = SQLUtil.execute("select customer_id from customer");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }

        return customerIds;
    }
}
