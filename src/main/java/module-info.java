module tan.oumarket {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires fontawesomefx;

    opens tan.oumarket to javafx.fxml;
    exports tan.oumarket;
    exports tan.pojo;
<<<<<<< HEAD
    exports tan.sevices;
=======
    exports tan.services;
>>>>>>> son
}

