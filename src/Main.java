package src;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * PDI-Main
 * 
 * Vm arguments
 * 
 * --module-path C:\Users\pedro\OneDrive\Documents\JAVA\javafx-sdk-17.0.0.1\lib --add-modules javafx.controls,javafx.fxml,javafx.web
 * -Xms4m
 */
public class Main extends Application {
    public static void main(String[] args) {
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