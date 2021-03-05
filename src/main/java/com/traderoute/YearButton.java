package com.traderoute;

import javafx.scene.control.Button;

public class YearButton extends Button {
    int year;
    public YearButton (Integer year){
        super();
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
