package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.model.ItemDTO;
import lk.ijse.pos.tm.CustomerTM;
import lk.ijse.pos.tm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAo = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDAOS = new ArrayList<>();
        ArrayList<Item> items = itemDAo.getAll();
        for (Item item : items) {
            itemDAOS.add(new ItemDTO(item.getItemId(), item.getItemName(), item.getQuantity(), item.getPrice()));
        }
        return itemDAOS;
    }

    @Override
    public boolean save(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAo.save(new Item(item.getItemId(), item.getItemName(), item.getQuantity(), item.getPrice()));
    }

    @Override
    public void update(ItemDTO item) throws SQLException, ClassNotFoundException {
        itemDAo.update(new Item(item.getItemName(), item.getQuantity(), item.getPrice(), item.getItemId()));
    }

    @Override
    public void delete(String itemId) throws SQLException, ClassNotFoundException {
        itemDAo.delete(itemId);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return itemDAo.generateNewID();
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        itemDAo.generateReport();
    }

    @Override
    public ItemTM findById(String selectedItemId) throws SQLException, ClassNotFoundException {
        return itemDAo.findById(selectedItemId);
    }

    @Override
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        return itemDAo.getAllItemIds();
    }
}
