package com.traderoute.controllers;

import com.traderoute.App;
import com.traderoute.data.Meeting1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import jfxtras.scene.control.CalendarPicker;
import jfxtras.scene.control.LocalTimeTextField;
import jfxtras.scene.control.agenda.Agenda;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CalendarController implements Initializable {

    @FXML
    private Agenda agenda;
    @FXML
    private CalendarPicker calendar;
    @FXML
    private LocalTimeTextField startTime;
    @FXML
    private LocalTimeTextField endTime;
    @FXML
    private TextArea description;

    private Agenda.Appointment selectedAppointment;

    @FXML
    private BorderPane centerBorderPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        VCalendar vCalendar = new VCalendar();
//        Agenda agenda = new Agenda();






        centerBorderPane.setCenter(agenda);
//
//        List<Meeting1> events= getMeetings();
//
//        for (Meeting1 e : events) {
//            agenda.appointments().add(
//                    new Agenda.AppointmentImpl()
//                            .withStartTime(e.getStartTime())
//                            .withEndTime(e.getEndTime())
//                            .withSummary("Hello")
//                            .withDescription("Retailer")
//            );
//
//        }
//            Button button = new Button ("month");
//            button.setOnMouseClicked((actionEvent) -> {
//                agenda.setSkin(new AgendaMonthSkin(agenda));
//            });
//
//        agenda.setStyle("    -fx-fill: yellow;\n" +
//                "    -fx-stroke: green;");

//        AgendaSkinSwitcher newSkinSwitcher = new AgendaSkinSwitcher(agenda);
//
//        System.out.println(agenda.getUserAgentStylesheet());
//        agenda.setStyle("-fx-fill: black");
//
//        LocalDateTime displayedLocalDateTime = agenda.getDisplayedLocalDateTime().plus(Duration.ofHours(5));
//        agenda.setDisplayedLocalDateTime(displayedLocalDateTime);
//
//        Button increaseWeek = new Button(">");
//        Button decreaseWeek = new Button("<");
//        HBox buttonHBox = new HBox(decreaseWeek, increaseWeek, newSkinSwitcher);
//        centerBorderPane.setTop(buttonHBox);
//
//        increaseWeek.setOnAction((e) ->
//        {
//            LocalDateTime newDisplayedLocalDateTime = agenda.getDisplayedLocalDateTime().plus(Period.ofWeeks(1));
//            agenda.setDisplayedLocalDateTime(newDisplayedLocalDateTime);
//        });
//        decreaseWeek.setOnAction((e)-> {
//            LocalDateTime newDisplayedLocalDateTime = agenda.getDisplayedLocalDateTime().minus(Period.ofWeeks(1));
//            agenda.setDisplayedLocalDateTime(newDisplayedLocalDateTime);
//        });
    }

    public List<Meeting1> getMeetings(){
        List<Meeting1> meetings= new ArrayList<>();
        meetings.add(new Meeting1(new GregorianCalendar(2021, 3, 11, 16, 16, 00), new GregorianCalendar(2021, 3, 11, 17, 16, 00)));
        meetings.add(new Meeting1(new GregorianCalendar(2021, 3, 11, 9, 16, 00), new GregorianCalendar(2021, 3, 11, 10, 16, 00)));
        return  meetings;
    }

    public void switchToMenu(ActionEvent event) throws IOException {
        FXMLLoader menuLoader = App.createFXMLLoader("menu");
        App.setSceneRoot(menuLoader.load());

    }
}
