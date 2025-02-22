package lk.ijse.pos.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.model.ItemDTO;
import lk.ijse.pos.tm.ItemTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ItemController implements Initializable {

    @FXML
    private Button btnDeleteItem;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSaveItem;

    @FXML
    private Button btnUpdateItem;

    @FXML
    private TableColumn<ItemTM, String> colItemId;

    @FXML
    private TableColumn<ItemTM, String> colName;

    @FXML
    private TableColumn<ItemTM, Double> colPrice;

    @FXML
    private TableColumn<ItemTM, Integer> colQuantity;

    @FXML
    private Label lblItemId;

    @FXML
    private TableView<ItemTM> tblItem;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQuantity;

    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOType.ITEM);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            refreshPage();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load data!").show();
        }
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> itemDTOS = itemBO.getAll();
        ObservableList<ItemTM> itemTMS = FXCollections.observableArrayList();

        for (ItemDTO itemDTO : itemDTOS) {
            ItemTM itemTM = new ItemTM(
                    itemDTO.getItemId(),
                    itemDTO.getItemName(),
                    itemDTO.getQuantity(),
                    itemDTO.getPrice()
            );

            itemTMS.add(itemTM);
        }

        tblItem.setItems(itemTMS);
    }

    private void loadNextItemId() throws SQLException, ClassNotFoundException {
        String nextItemId = itemBO.generateNewID();
        lblItemId.setText(nextItemId);

    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextItemId();
        loadTableData();
        resetStyles();

        txtName.setText("");
        txtQuantity.setText("");
        txtPrice.setText("");
        tblItem.getSelectionModel().clearSelection();

        btnSaveItem.setDisable(false);
        btnDeleteItem.setDisable(true);
        btnUpdateItem.setDisable(true);
    }

    private void resetStyles() {
        txtName.setStyle(txtName.getStyle() + "; -fx-border-color: #7367F0;");
        txtQuantity.setStyle(txtQuantity.getStyle() + "; -fx-border-color: #7367F0;");
        txtPrice.setStyle(txtPrice.getStyle() + "; -fx-border-color: #7367F0;");
    }

    @FXML
    void btnSaveItemOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String itemId = lblItemId.getText();
        String name = txtName.getText();
        String quantityString = txtQuantity.getText();
        String priceString = txtPrice.getText();

        String quantityPattern = "^[0-9]+$";
        String pricePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name != null;
        boolean isValidQuantity = quantityString.matches(quantityPattern);
        boolean isValidPrice = priceString.matches(pricePattern);

        System.out.println(isValidQuantity + " / " + quantityString);

        resetStyles();

        if (!isValidName) {
            txtName.setStyle(txtName.getStyle() + "; -fx-border-color: red;");
        }

        if (!isValidQuantity) {
            txtQuantity.setStyle(txtQuantity.getStyle() + "; -fx-border-color: red;");
        }

        if (!isValidPrice) {
            txtPrice.setStyle(txtPrice.getStyle() + "; -fx-border-color: red;");
        }

        if (isValidName && isValidQuantity && isValidPrice) {
            resetStyles();

            int quantity = Integer.parseInt(quantityString);
            double price = Double.parseDouble(priceString);

            ItemDTO itemDTO = new ItemDTO(itemId, name, quantity, price);

            boolean isSaved = itemBO.save(itemDTO);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Item saved successfully!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to save item!").show();
            }
        }
    }

    @FXML
    void btnDeleteItemOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String itemId = lblItemId.getText();

        if (itemId != null) {
            // Confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this item?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {

                itemBO.delete(itemId);
                 new Alert(Alert.AlertType.INFORMATION, "Item deleted successfully!").show();
                 refreshPage();

            }
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    void btnUpdateItemOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String itemId = lblItemId.getText();
        String name = txtName.getText();
        String quantityString = txtQuantity.getText();
        String priceString = txtPrice.getText();

        String quantityPattern = "^[0-9]+$";
        //String pricePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";
        String pricePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name != null;
        boolean isValidQuantity = quantityString.matches(quantityPattern);
        boolean isValidPrice = priceString.matches(pricePattern);

        resetStyles();

        if (!isValidName) {
            txtName.setStyle(txtName.getStyle() + "; -fx-border-color: red;");
        }

        if (!isValidQuantity) {
            txtQuantity.setStyle(txtQuantity.getStyle() + "; -fx-border-color: red;");
        }

        if (!isValidPrice) {
            txtPrice.setStyle(txtPrice.getStyle() + "; -fx-border-color: red;");
        }

        if (isValidName && isValidQuantity && isValidPrice) {
            resetStyles();

            int quantity = Integer.parseInt(quantityString);
            double price = Double.parseDouble(priceString);

                        ItemDTO updatedItemDTO = new ItemDTO(itemId, name, quantity, price);

             itemBO.update(updatedItemDTO);
             new Alert(Alert.AlertType.INFORMATION, "Item updated successfully!").show();
             refreshPage();



        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        ItemTM itemTM = tblItem.getSelectionModel().getSelectedItem();
        if (itemTM != null) {
            btnSaveItem.setDisable(true);
            btnDeleteItem.setDisable(false);
            btnUpdateItem.setDisable(false);

            lblItemId.setText(itemTM.getItemId());
            txtName.setText(itemTM.getName());
            txtQuantity.setText(String.valueOf(itemTM.getQuantity()));
            txtPrice.setText(String.valueOf(itemTM.getPrice()));
        }
    }

}
