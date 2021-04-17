package com.traderoute;

import com.traderoute.controllers.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;


    public static FXMLLoader fxmlLoader;



    @Override
    public void start(Stage stage) throws IOException {
        fxmlLoader = createFXMLLoader("menu");
        scene = new Scene(fxmlLoader.load());

        MenuController controller = fxmlLoader.getController();
        controller.setHostServices(getHostServices());
        stage.setScene(scene);
        stage.show();

    }

    public static Stage getStage() {
        return (Stage) scene.getWindow();
    }


    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    public static void setSceneRoot (Parent loadedFXML) throws IOException {
        scene.setRoot(loadedFXML);
    }
    public static FXMLLoader createFXMLLoader(String fxml) throws IOException{
        return new FXMLLoader(App.class.getResource(fxml+".fxml"));
    }
    public static FXMLLoader getFXMLLoader(){
        return fxmlLoader;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}