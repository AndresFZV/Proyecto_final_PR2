module co.edu.uniquindio.marketplace.marketplace {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports co.edu.uniquindio.marketplace.marketplace.app;
    opens co.edu.uniquindio.marketplace.marketplace.app to javafx.fxml;

    exports co.edu.uniquindio.marketplace.marketplace.viewcontroller;
    opens co.edu.uniquindio.marketplace.marketplace.viewcontroller to javafx.fxml;
    opens co.edu.uniquindio.marketplace.marketplace to javafx.fxml;
}
