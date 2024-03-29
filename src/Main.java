package src;

import java.io.IOException;
import javax.swing.UIManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * PDI-Main
 */
public class Main extends Application {
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater( new Runnable() {
            public void run() {
                try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } 
                catch (Exception e) {}
            }
        });
        
        launch(args);
    }

    private static Stage stg;

    public static void setSTg(Stage stg){ Main.stg = stg; }

    @Override
    public void start(Stage stgTMP) throws Exception {
        stg = stgTMP;
        stg.show();
        iniciar();
    }

    public void iniciar() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/assets/Main.fxml"));
        Scene cena = new Scene(root);
        stg.getIcons().add(new Image("/assets/icon.png"));
        stg.setTitle("Processador de Imagem");
        stg.setScene(cena);
    }

    public static void fechar() {
        stg.close();
    }
}