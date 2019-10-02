/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import static Logica.TelaLoginMain.setStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Elison Christoph
 */
public class ControleMain extends Application {

    private static Stage stage;

     public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Controle.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        //stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
        //Fecha janela ao pressionar ESC
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent t) -> {
            if (t.getCode() == KeyCode.ESCAPE) {
                fecha();
            }
        });
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ControleMain.stage = stage;
    }
    
    public void fecha() {
        ControleMain.getStage().close();
    }
    
}
