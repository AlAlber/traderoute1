package com.traderoute;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.Date;

public class Meeting {
    private SimpleStringProperty description, location,time,  notes;
    private SimpleObjectProperty<Date> date;

    public Meeting(String description, String location, Date date,String time,  String notes) {
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.date = new SimpleObjectProperty<>(date);
        this.time = new SimpleStringProperty(time);
        this.notes = new SimpleStringProperty(notes);
    }
    public Meeting() {
        this.description = new SimpleStringProperty();
        this.location = new SimpleStringProperty();
        this.date = new SimpleObjectProperty<>();
        this.time = new SimpleStringProperty();
        this.notes = new SimpleStringProperty();
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String toString(){
        return "Description: "+getDescription()+" Location: "+getLocation()+" Date: "+ getDate() + " Notes: "+ getNotes();
    }


    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getLocation() {
        return location.get();
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getNotes() {
        return notes.get();
    }

    public SimpleStringProperty notesProperty() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }
}
