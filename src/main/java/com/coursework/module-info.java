module com.coursework.wordle {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.xml.bind;
    
    opens com.coursework.gui.impl.controllers to javafx.fxml;
    opens com.coursework.core.impl.languages to jakarta.xml.bind;

    exports com.coursework.gui;
}