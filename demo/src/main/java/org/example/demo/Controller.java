//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller {
    @FXML
    private Button submitButton;
    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;

    public Controller() {
    }

    public void getData(ActionEvent actionEvent) throws IOException {
        System.out.println(this.name.getText());
        System.out.println(this.email.getText());
        System.out.println(this.password.getText());
        JavaPostgreSql.writeToDatabase(this.name.getText(), this.email.getText(), this.password.getText());
    }

    public void checkData(ActionEvent actionEvent) throws IOException {
        System.out.println(this.name.getText());
        System.out.println(this.email.getText());
        System.out.println(this.password.getText());
        JavaPostgreSql.checkToDatabase(this.name.getText(), this.email.getText(), this.password.getText());
    }
}
