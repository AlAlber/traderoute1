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
    private SimpleObjectProperty<Button> editButton;
    private SimpleObjectProperty<Button> commitButton;
    private SimpleObjectProperty<TableView> toplineTable;
    private SimpleObjectProperty<TableView> retailerTable;
    private SimpleObjectProperty<TextField> weeklyPromoUfswField;
    private SimpleObjectProperty<Label> everydayLabel;
    private SimpleObjectProperty<Label> costLabel;
    private SimpleObjectProperty<Label> gpmLabel;
    private SimpleObjectProperty<Label> plannedNet1RateLabel;
    private SimpleObjectProperty<Label> goalLabel;

    public PromoPlan(ObservableList<Parameter<?>> parameters, ObservableList<Summary> toplineSummaries,
                     ObservableList<Summary> retailerSummaries, BigDecimal weeklyPromoUfsw, RTMOption selectedRtm,
                     boolean commited, Integer year, ComboBox<RTMOption> rtmBox, TextField weeklyPromoUfswField,
                     Button editButton, Button commitButton, TableView toplineTable,TableView retailerTable,
                     Label everydayLabel, Label costLabel, Label gpmLabel, Label plannedNet1RateLabel, Label goalLabel) {
        this.parameters = new SimpleObjectProperty<>(parameters);
        this.toplineSummaries = new SimpleObjectProperty<>(toplineSummaries);
        this.retailerSummaries = new SimpleObjectProperty<>(retailerSummaries);
        this.weeklyPromoUfsw = new SimpleObjectProperty<>(weeklyPromoUfsw);
        this.selectedRtm = new SimpleObjectProperty<>(selectedRtm);
        this.commited = new SimpleBooleanProperty(commited);
        this.year = new SimpleIntegerProperty(year);
        this.rtmBox = new SimpleObjectProperty<>(rtmBox);
        this.weeklyPromoUfswField = new SimpleObjectProperty<>(weeklyPromoUfswField);
        this.editButton = new SimpleObjectProperty<>(editButton);
        this.commitButton = new SimpleObjectProperty<>(commitButton);
        this.retailerTable = new SimpleObjectProperty<>(retailerTable);
        this.toplineTable = new SimpleObjectProperty<>(toplineTable);
        this.everydayLabel = new SimpleObjectProperty<>(everydayLabel);
        this.costLabel = new SimpleObjectProperty<>(costLabel);
        this.gpmLabel = new SimpleObjectProperty<>(gpmLabel);
        this.plannedNet1RateLabel = new SimpleObjectProperty<>(plannedNet1RateLabel);
        this.goalLabel = new SimpleObjectProperty<>(goalLabel);


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

    public Label getPlannedNet1RateLabel() {
        return plannedNet1RateLabel.get();
    }

    public SimpleObjectProperty<Label> plannedNet1RateLabelProperty() {
        return plannedNet1RateLabel;
    }

    public void setPlannedNet1RateLabel(Label plannedNet1RateLabel) {
        this.plannedNet1RateLabel.set(plannedNet1RateLabel);
    }

    public Label getGoalLabel() {
        return goalLabel.get();
    }

    public SimpleObjectProperty<Label> goalLabelProperty() {
        return goalLabel;
    }

    public void setGoalLabel(Label goalLabel) {
        this.goalLabel.set(goalLabel);
    }

    public Label getEverydayLabel() {
        return everydayLabel.get();
    }

    public SimpleObjectProperty<Label> everydayLabelProperty() {
        return everydayLabel;
    }

    public void setEverydayLabel(Label everydayLabel) {
        this.everydayLabel.set(everydayLabel);
    }

    public Label getCostLabel() {
        return costLabel.get();
    }

    public SimpleObjectProperty<Label> costLabelProperty() {
        return costLabel;
    }

    public void setCostLabel(Label costLabel) {
        this.costLabel.set(costLabel);
    }

    public Label getGpmLabel() {
        return gpmLabel.get();
    }

    public SimpleObjectProperty<Label> gpmLabelProperty() {
        return gpmLabel;
    }

    public void setGpmLabel(Label gpmLabel) {
        this.gpmLabel.set(gpmLabel);
    }

    public Button getEditButton() {
        return editButton.get();
    }

    public SimpleObjectProperty<Button> editButtonProperty() {
        return editButton;
    }

    public void setEditButton(Button editButton) {
        this.editButton.set(editButton);
    }

    public Button getCommitButton() {
        return commitButton.get();
    }

    public SimpleObjectProperty<Button> commitButtonProperty() {
        return commitButton;
    }

    public void setCommitButton(Button commitButton) {
        this.commitButton.set(commitButton);
    }

    public TableView getToplineTable() {
        return toplineTable.get();
    }

    public SimpleObjectProperty<TableView> toplineTableProperty() {
        return toplineTable;
    }

    public void setToplineTable(TableView toplineTable) {
        this.toplineTable.set(toplineTable);
    }

    public TableView getRetailerTable() {
        return retailerTable.get();
    }

    public SimpleObjectProperty<TableView> retailerTableProperty() {
        return retailerTable;
    }

    public void setRetailerTable(TableView retailerTable) {
        this.retailerTable.set(retailerTable);
    }

    public TextField getWeeklyPromoUfswField() {
        return weeklyPromoUfswField.get();
    }

    public SimpleObjectProperty<TextField> weeklyPromoUfswFieldProperty() {
        return weeklyPromoUfswField;
    }

    public void setWeeklyPromoUfswField(TextField weeklyPromoUfswField) {
        this.weeklyPromoUfswField.set(weeklyPromoUfswField);
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
