package com.traderoute;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.math.BigDecimal;

public class RTMUpdateListener<Object> implements ChangeListener<Object> {
        private boolean changing;
        private RTMOption rtmOption;
        private boolean updateAllFromSecond;
        public RTMUpdateListener(RTMOption rtmOption, boolean updateAllFromSecond){
            super();
            this.rtmOption = rtmOption;
            this.updateAllFromSecond= updateAllFromSecond;
        }

    @Override
    public void changed(ObservableValue<? extends Object> observableValue, Object object, Object t1) {
        if (!changing) {
            try {
                changing = true;
                if (updateAllFromSecond){
                    rtmOption.updateElasticizedEstimatedUnitVelocity();
                    rtmOption.updateEstimatedAnnualVolumePerSku();
                }
                System.out.println("Spoils fees changing works");
                rtmOption.updateSlottingPaybackPeriod();
                rtmOption.updatePostFreightPostSpoilsWeCollect();
                rtmOption.updateUnspentTrade();
                rtmOption.updateFourYearEqGpPerSku();
                rtmOption.updateFourYearEqGpPerUnit();
            } finally {
                changing = false;
            }
        }
    }
}
