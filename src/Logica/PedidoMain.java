/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import View.AddEstoqueController;
import View.PedidoController;
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
public class PedidoMain extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/Pedido.fxml"));
        PedidoController controller1 = new PedidoController();

        Parent root = loader.load();
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        controller1.showStage();
        //stage.show();
        setStage(stage);
        //Fecha janela ao pressionar ESC
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent t) -> {
            if (t.getCode() == KeyCode.ESCAPE) {
                fecha();
            }
        });
        */
        PedidoController controller1 = new PedidoController(null);

        // Show the new stage
        controller1.showStage();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void fecha() {
        PedidoMain.getStage().close();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        PedidoMain.stage = stage;
    }

}
