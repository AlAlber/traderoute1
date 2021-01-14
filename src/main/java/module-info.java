module com.traderoute {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.traderoute to javafx.fxml;
    exports com.traderoute;
}