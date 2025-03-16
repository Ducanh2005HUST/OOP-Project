//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage primaryStage) throws IOException {
        Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("hello-view.fxml"));
        primaryStage.setTitle("Đồng hành cùng FC36HUST");
        primaryStage.setScene(new Scene(root, (double)600.0F, (double)400.0F));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
