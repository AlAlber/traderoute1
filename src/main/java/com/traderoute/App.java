package com.traderoute;

import com.traderoute.controllers.MenuController;
import com.traderoute.controllers.MyController;
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
    public static FXMLLoader switchToNewScene(String fxml) throws IOException {
        FXMLLoader loader = App.createFXMLLoader(fxml);
        App.setSceneRoot(loader.load());
        return loader;
    }
    public static MyController getNewController(String fxml) throws IOException {
        FXMLLoader loader = App.createFXMLLoader(fxml);
        App.setSceneRoot(loader.load());
        return loader.getController();
    }

    public static Stage getStage() {
        return (Stage) scene.getWindow();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void setSceneRoot(Parent loadedFXML) throws IOException {
        scene.setRoot(loadedFXML);
    }

    public static FXMLLoader createFXMLLoader(String fxml) throws IOException {
        fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

//    public static FXMLLoader getFXMLLoader() {
//        return fxmlLoader;
//    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static Scene getScene(){
        return scene;
    }
    public static void setNewScene(Scene sceneToLoad){
        scene = sceneToLoad;
    }

    public static FXMLLoader getFxmlLoader(){
        return fxmlLoader;
    }


    public static void main(String[] args) {
        launch();
    }

}