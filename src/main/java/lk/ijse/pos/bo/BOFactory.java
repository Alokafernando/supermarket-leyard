package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.bo.custom.impl.CustomerBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    public BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }

    public enum BOType{
        CUSTOMER;
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerBOImpl();
            default:
                return null;
        }
    }

}
