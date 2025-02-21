module lk.ijse.pos.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires com.jfoenix;
    requires net.sf.jasperreports.core;
    requires java.mail;

    opens lk.ijse.pos.tm to javafx.base;

    opens lk.ijse.pos.controller to javafx.fxml;

    exports lk.ijse.pos;

    exports lk.ijse.pos.tm;
}