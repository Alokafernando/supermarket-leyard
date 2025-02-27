package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.dao.custom.impl.ItemDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOType {
        CUSTOMER, ITEM;
    }

    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            default:
                return null;
        }
    }
}
