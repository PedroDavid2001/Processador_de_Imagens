package main.java;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * PDI-Main
 */
public class Main extends Application {
    
    private static Stage stg;

    public static void setSTg(Stage stg){ Main.stg = stg; }

    @Override
    public void start(Stage stgTMP) throws Exception {
        stg = stgTMP;
        stg.show();
        iniciar();
    }

    public void iniciar() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/Main.fxml"));
        Scene cena = new Scene(root);
        stg.getIcons().add(new Image("/src/main/resources/icon.png"));
        stg.setTitle("FotoXop");
        stg.setScene(cena);
    }

    public static void fechar() {
        stg.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}