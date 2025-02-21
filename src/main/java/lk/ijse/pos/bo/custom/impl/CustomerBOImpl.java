package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.model.CustomerDTO;
import lk.ijse.pos.tm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll();

        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(customer.getId(), customer.getName(), customer.getNic(), customer.getEmail(), customer.getPhone()));
        }

        return customerDTOS;
    }

    @Override
    public boolean save(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer( customer.getId(), customer.getName(), customer.getNic(), customer.getEmail(), customer.getPhone()));
    }

    @Override
    public void update(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        customerDAO.update(new Customer(customer.getName(), customer.getNic(), customer.getEmail(), customer.getPhone(), customer.getId()));
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        customerDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        customerDAO.generateReport();
    }

    @Override
    public CustomerTM findById(String selectedCusId) throws SQLException, ClassNotFoundException {
        return customerDAO.findById(selectedCusId);
    }

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllCustomerIds();
    }
}
