/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import View.ListarPedidosController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author Elison Christoph
 */
public class ListarPedidosMain extends Application {

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
        ListarPedidosController controller1 = new ListarPedidosController();

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
        ListarPedidosMain.getStage().close();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ListarPedidosMain.stage = stage;
    }

}
