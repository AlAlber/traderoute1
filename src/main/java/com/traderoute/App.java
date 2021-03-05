package com.traderoute;

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

    private static Scene scene;


    public static FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) throws IOException {
//        System.out.println(getFXMLLoader("secondTable").getController());
        fxmlLoader = createFXMLLoader("secondTable");
        scene = new Scene(fxmlLoader.load());
        System.out.println(fxmlLoader.getController().toString());
        stage.setScene(scene);
        stage.show();

    }


    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    static void setSceneRoot (Parent loadedFXML) throws IOException {
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