package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.SQLUtil;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.tm.ItemTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {


    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from Item");

        ArrayList<Item> items = new ArrayList<>();

        while (rst.next()) {
            items.add(new Item(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getInt(4)));
        }

        return items;

    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into item values (?, ?, ?, ?)",
                entity.getItemId(), entity.getItemName(), entity.getQuantity(), entity.getPrice());
    }

    @Override
    public void update(Item entity) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("update item set name=?, quantity=?, price=? where item_id=?",
                entity.getItemName(), entity.getQuantity(), entity.getPrice(), entity.getItemId());
    }

    @Override
    public void delete(String itemId) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("delete from item where item_id=?", itemId);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select item_id from item order by item_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("I%03d", newIdIndex);
        }
        return "I001";
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        //empty
    }

    @Override
    public ArrayList<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select item_id from item");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }

        return customerIds;
    }

    @Override
    public ItemTM findById(String selectedItemId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from item where item_id=?", selectedItemId);

        if (rst.next()) {
            return new ItemTM(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getDouble(4));
        }

        return null;
    }

    @Override
    public boolean reduceQty(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "update item set quantity = quantity - ? where item_id = ?",
                entity.getQuantity(), entity.getItemId());
    }
}
