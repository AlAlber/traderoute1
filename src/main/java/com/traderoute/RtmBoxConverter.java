package com.traderoute;

import com.traderoute.data.RTMOption;
import javafx.util.StringConverter;

public class RtmBoxConverter extends StringConverter<RTMOption> {
    @Override
    public String toString(RTMOption rtmOption) {
        if (rtmOption != null) {
            return rtmOption.getRtmName();
        }
        return null;
    }

    @Override
    public RTMOption fromString(String s) {
        return null;
    }
}
