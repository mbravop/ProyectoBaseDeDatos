module donacion.donacionsangre {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens donacion.donacionsangre to javafx.fxml;
    opens donacion.donacionsangre.modelo to javafx.base;
    exports donacion.donacionsangre;
}
