module org.example.imageprocessoops {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires opencv;

    opens org.example.imageprocessoops to javafx.fxml;
    exports org.example.imageprocessoops;
}