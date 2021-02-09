module com.traderoute {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
//    requires adalid.alfa;

    opens com.traderoute to javafx.fxml;
    exports com.traderoute;
}