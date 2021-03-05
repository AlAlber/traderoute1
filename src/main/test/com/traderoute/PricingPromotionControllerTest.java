package com.traderoute;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.api.FxRobot;
import org.junit.jupiter.api.Test;


import java.io.IOException;

@ExtendWith(ApplicationExtension.class)
public class PricingPromotionControllerTest{

    @Start
    public void start(Stage stage) throws IOException {
//        System.out.println(getFXMLLoader("secondTable").getController());
        FXMLLoader fxmlLoader = App.createFXMLLoader("pricingPromotion");
        Scene scene = new Scene(fxmlLoader.load());
        System.out.println(fxmlLoader.getController().toString());
        stage.setScene(scene);
        stage.show();

    }
    @org.junit.jupiter.api.BeforeEach
    void setUp() {


    }
    @Test
    public void testGetFirstSelectedRtmOption(FxRobot robot){

        ComboBox firstRtmBox = robot.lookup("firstRtmBox").queryAs(ComboBox.class);
        robot.clickOn(firstRtmBox);
        RTMOption rtmOption = (RTMOption) firstRtmBox.getItems().get(0);
        robot.clickOn(firstRtmBox);
        Assertions.assertNotNull(firstRtmBox);
    }

    @Test
    public void testGetSlottingGameTheoryd() {
//        Assert.assertEquals(PricingPromotionController.getSlottingGameTheoryd(), new BigDecimal("3500.0")); //true


    }

    @Test
    public void getEverydayCost() {
    }

    @Test
    public void getPromoCost() {
    }

    @Test
    public void getEverydayRetailWeeks() {
    }

    @Test
    public void getEveryDayVolume() {
    }

    @Test
    public void getWeeksInPeriod() {
    }
}