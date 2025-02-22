package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private AnchorPane content;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        navigateTo("/view/CustomerView.fxml");
    }

    @FXML
    void navigateToCustomerPage(ActionEvent event) {
        navigateTo("/view/CustomerView.fxml");
    }

    @FXML
    void navigateToItemPage(ActionEvent event) {
        navigateTo("/view/ItemView.fxml");
    }

    @FXML
    void navigateToOrdersPage(ActionEvent event) {
        navigateTo("/view/OrdersView.fxml");
    }

    public void navigateTo(String fxmlPath) {
        try {
            content.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));

            load.prefWidthProperty().bind(content.widthProperty());
            load.prefHeightProperty().bind(content.heightProperty());


            content.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }
}
