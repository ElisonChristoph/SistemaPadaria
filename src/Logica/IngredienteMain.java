/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
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
public class IngredienteMain extends Application {
    
    private static Stage stage;

    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/CadastroIngrediente.fxml"));
        stage.initStyle(StageStyle.DECORATED);
        Scene scene = new Scene(root);
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void fecha() {
        IngredienteMain.getStage().close();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        IngredienteMain.stage = stage;
    }
    
    
    
}