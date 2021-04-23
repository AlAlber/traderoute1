package com.traderoute.data;

import java.lang.reflect.GenericArrayType;
import java.util.GregorianCalendar;

public class Meeting1 {

    GregorianCalendar startTime;
    GregorianCalendar endTime;

    public Meeting1(GregorianCalendar startTime, GregorianCalendar endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public GregorianCalendar getStartTime() {
        return startTime;
    }

    public void setStartTime(GregorianCalendar startTime) {
        this.startTime = startTime;
    }

    public GregorianCalendar getEndTime() {
        return endTime;
    }

    public void setEndTime(GregorianCalendar endTime) {
        this.endTime = endTime;
    }
}
