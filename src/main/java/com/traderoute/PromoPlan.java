package com.traderoute;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;

public class PromoPlan {
    private SimpleObjectProperty<ObservableList<Parameter<?>>> parameters;
    private SimpleObjectProperty<ObservableList<Summary>> toplineSummaries;
    private SimpleObjectProperty<ObservableList<Summary>> retailerSummaries;
    private SimpleObjectProperty<BigDecimal> weeklyPromoUfsw;
    private SimpleObjectProperty<ComboBox <RTMOption>> rtmBox;
    private SimpleObjectProperty<RTMOption> selectedRtm;
    private SimpleBooleanProperty commited;
    private SimpleIntegerProperty year;
    private Button editButton;
    private Button commitButton;
    private TableView toplineTable;
    private TableView retailerTable;
    private TextField weeklyPromoUfswField;
    private Label everydayLabel;
    private Label costLabel;
    private Label gpmLabel;
    private Label plannedNet1RateLabel;
    private Label plannedGoalLabel;

    public PromoPlan(ObservableList<Parameter<?>> parameters, ObservableList<Summary> toplineSummaries, ObservableList<Summary> retailerSummaries, BigDecimal weeklyPromoUfsw, RTMOption selectedRtm, boolean commited, Integer year, ComboBox<RTMOption> rtmBox) {
        this.parameters = new SimpleObjectProperty<>(parameters);
        this.toplineSummaries = new SimpleObjectProperty<>(toplineSummaries);
        this.retailerSummaries = new SimpleObjectProperty<>(retailerSummaries);
        this.weeklyPromoUfsw = new SimpleObjectProperty<>(weeklyPromoUfsw);
        this.selectedRtm = new SimpleObjectProperty<>(selectedRtm);
        this.commited = new SimpleBooleanProperty(commited);
        this.year = new SimpleIntegerProperty(year);
        this.rtmBox = new SimpleObjectProperty<>(rtmBox);


//        weeklyPromoUfswField.textProperty().bindBidirectional(weeklyPromoUfswProperty(), new BigDecimalStringConverter());
        rtmBox.valueProperty().bindBidirectional(selectedRtmProperty());

    }

    public PromoPlan( Integer year) {
        this.parameters = new SimpleObjectProperty<>();
        this.toplineSummaries = new SimpleObjectProperty<>();
        this.retailerSummaries = new SimpleObjectProperty<>();
        this.weeklyPromoUfsw = new SimpleObjectProperty<>();
        this.selectedRtm = new SimpleObjectProperty<>();
        this.commited = new SimpleBooleanProperty();
        this.year = new SimpleIntegerProperty(year);
    }

    public ComboBox<RTMOption> getRtmBox() {
        return rtmBox.get();
    }

    public SimpleObjectProperty<ComboBox<RTMOption>> rtmBoxProperty() {
        return rtmBox;
    }

    public void setRtmBox(ComboBox<RTMOption> rtmBox) {
        this.rtmBox.set(rtmBox);
    }

    public Button getEditButton() {
        return editButton;
    }

    public void setEditButton(Button editButton) {
        this.editButton = editButton;
    }

    public Button getCommitButton() {
        return commitButton;
    }

    public void setCommitButton(Button commitButton) {
        this.commitButton = commitButton;
    }

    public int getYear() {
        return year.get();
    }

    public SimpleIntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public ObservableList<Parameter<?>> getParameters() {
        return parameters.get();
    }

    public SimpleObjectProperty<ObservableList<Parameter<?>>> parametersProperty() {
        return parameters;
    }

    public void setParameters(ObservableList<Parameter<?>> parameters) {
        this.parameters.set(parameters);
    }

    public ObservableList<Summary> getToplineSummaries() {
        return toplineSummaries.get();
    }

    public SimpleObjectProperty<ObservableList<Summary>> toplineSummariesProperty() {
        return toplineSummaries;
    }

    public void setToplineSummaries(ObservableList<Summary> toplineSummaries) {
        this.toplineSummaries.set(toplineSummaries);
    }

    public ObservableList<Summary> getRetailerSummaries() {
        return retailerSummaries.get();
    }

    public SimpleObjectProperty<ObservableList<Summary>> retailerSummariesProperty() {
        return retailerSummaries;
    }

    public void setRetailerSummaries(ObservableList<Summary> retailerSummaries) {
        this.retailerSummaries.set(retailerSummaries);
    }

    public BigDecimal getWeeklyPromoUfsw() {
        return weeklyPromoUfsw.get();
    }

    public SimpleObjectProperty<BigDecimal> weeklyPromoUfswProperty() {
        return weeklyPromoUfsw;
    }

    public void setWeeklyPromoUfsw(BigDecimal weeklyPromoUfsw) {
        this.weeklyPromoUfsw.set(weeklyPromoUfsw);
    }

    public RTMOption getSelectedRtm() {
        return selectedRtm.get();
    }

    public SimpleObjectProperty<RTMOption> selectedRtmProperty() {
        return selectedRtm;
    }

    public void setSelectedRtm(RTMOption selectedRtm) {
        this.selectedRtm.set(selectedRtm);
    }

    public boolean isCommited() {
        return commited.get();
    }

    public SimpleBooleanProperty commitedProperty() {
        return commited;
    }

    public void setCommited(boolean commited) {
        this.commited.set(commited);
    }
}
