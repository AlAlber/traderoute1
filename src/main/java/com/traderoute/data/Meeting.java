package com.traderoute.data;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Meeting {
    private SimpleStringProperty meetingDescription, location, time, notes;
    private SimpleObjectProperty<LocalDate> date;

    public Meeting(String description, String location, LocalDate date, String time, String notes) {
        this.meetingDescription = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.date = new SimpleObjectProperty<>(date);
        this.time = new SimpleStringProperty(time);
        this.notes = new SimpleStringProperty(notes);
    }

    public Meeting() {
        this.meetingDescription = new SimpleStringProperty();
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

    public String toString() {
        return "Description: " + getMeetingDescription() + " Location: " + getLocation() + " Date: " + getDate() + " Notes: "
                + getNotes();
    }

    public String getMeetingDescription() {
        return meetingDescription.get();
    }

    public SimpleStringProperty meetingDescriptionProperty() {
        return meetingDescription;
    }

    public void setMeetingDescription(String meetingDescription) {
        this.meetingDescription.set(meetingDescription);
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

    public LocalDate getDate() {
        return date.get();
    }

    public SimpleObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }
}
