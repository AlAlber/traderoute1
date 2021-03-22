package com.traderoute;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class PromoPlan {
    private SimpleObjectProperty<ObservableList<Parameter<?>>> parameters;
    private SimpleObjectProperty<ObservableList<Summary>> toplineSummaries;
    private SimpleObjectProperty<ObservableList<Summary>> retailerSummaries;
    private SimpleObjectProperty<BigDecimal> weeklyPromoUfsw;
//    private SimpleObjectProperty<ComboBox <RTMOption>> rtmBox;
    private SimpleObjectProperty<RTMOption> selectedRtm;
    private SimpleBooleanProperty committed;
    private SimpleIntegerProperty year;
//    private SimpleObjectProperty<Button> editButton;
//    private SimpleObjectProperty<Button> commitButton;
//    private SimpleObjectProperty<TableView> toplineTable;
//    private SimpleObjectProperty<TableView> retailerTable;
//    private SimpleObjectProperty<TextField> weeklyPromoUfswField;
//    private SimpleObjectProperty<Label> everydayLabel;
//    private SimpleObjectProperty<Label> costLabel;
//    private SimpleObjectProperty<Label> gpmLabel;
//    private SimpleObjectProperty<Label> plannedNet1RateLabel;
//    private SimpleObjectProperty<Label> goalLabel;

    public PromoPlan(ObservableList<Parameter<?>> parameters, ObservableList<Summary> toplineSummaries,
                     ObservableList<Summary> retailerSummaries, BigDecimal weeklyPromoUfsw,
                     boolean commited) {
        this.parameters = new SimpleObjectProperty<>(parameters);
        this.toplineSummaries = new SimpleObjectProperty<>(toplineSummaries);
        this.retailerSummaries = new SimpleObjectProperty<>(retailerSummaries);
        this.weeklyPromoUfsw = new SimpleObjectProperty<>(weeklyPromoUfsw);
        this.selectedRtm = new SimpleObjectProperty<>();
        this.committed = new SimpleBooleanProperty(commited);
//        this.rtmBox = new SimpleObjectProperty<>(rtmBox);
//        this.weeklyPromoUfswField = new SimpleObjectProperty<>(weeklyPromoUfswField);
//        this.editButton = new SimpleObjectProperty<>(editButton);
//        this.commitButton = new SimpleObjectProperty<>(commitButton);
//        this.retailerTable = new SimpleObjectProperty<>(retailerTable);
//        this.toplineTable = new SimpleObjectProperty<>(toplineTable);
//        this.everydayLabel = new SimpleObjectProperty<>(everydayLabel);
//        this.costLabel = new SimpleObjectProperty<>(costLabel);
//        this.gpmLabel = new SimpleObjectProperty<>(gpmLabel);
//        this.plannedNet1RateLabel = new SimpleObjectProperty<>(plannedNet1RateLabel);
//        this.goalLabel = new SimpleObjectProperty<>(goalLabel);

//        this.commitButton.get().setWrapText(true);
//        this.editButton.get().setWrapText(true);
//        this.rtmBox.get().setConverter(new RtmBoxConverter());
//        this.weeklyPromoUfswField.get().setTextFormatter(new TextFormatter<Double>(firstTableController.getDoubleInputConverter(), 0.0, firstTableController.getDoubleInputFilter()));
//        this.weeklyPromoUfswField.get().setTooltip(new Tooltip("Please add the weekly velocity Unit/Flavor/Sku/Week"));
//        this.rtmBox.get().setTooltip(new Tooltip("Please select a Route-to-Market from the ones you configured on the Route to Market Page"));
        //        weeklyPromoUfswField.textProperty().bindBidirectional(weeklyPromoUfswProperty(), new BigDecimalStringConverter());
//        rtmBox.valueProperty().bindBidirectional(selectedRtmProperty());
    }


    public PromoPlan( Integer year) {
        this.parameters = parametersProperty();
        this.toplineSummaries = toplineSummariesProperty();
        this.retailerSummaries = retailerSummariesProperty();
        this.weeklyPromoUfsw = weeklyPromoUfswProperty();
        this.selectedRtm = new SimpleObjectProperty<>(); // add controller logic of what to do when RTM is null
        this.committed = committedProperty();
        this.year = yearProperty();
    }

//    public Label getPlannedNet1RateLabel() {
//        return plannedNet1RateLabel.get();
//    }
//
//    public SimpleObjectProperty<Label> plannedNet1RateLabelProperty() {
//        return plannedNet1RateLabel;
//    }
//
//    public void setPlannedNet1RateLabel(Label plannedNet1RateLabel) {
//        this.plannedNet1RateLabel.set(plannedNet1RateLabel);
//    }
//
//    public Label getGoalLabel() {
//        return goalLabel.get();
//    }
//
//    public SimpleObjectProperty<Label> goalLabelProperty() {
//        return goalLabel;
//    }
//
//    public void setGoalLabel(Label goalLabel) {
//        this.goalLabel.set(goalLabel);
//    }
//
//    public Label getEverydayLabel() {
//        return everydayLabel.get();
//    }
//
//    public SimpleObjectProperty<Label> everydayLabelProperty() {
//        return everydayLabel;
//    }
//
//    public void setEverydayLabel(Label everydayLabel) {
//        this.everydayLabel.set(everydayLabel);
//    }
//
//    public Label getCostLabel() {
//        return costLabel.get();
//    }
//
//    public SimpleObjectProperty<Label> costLabelProperty() {
//        return costLabel;
//    }
//
//    public void setCostLabel(Label costLabel) {
//        this.costLabel.set(costLabel);
//    }
//
//    public Label getGpmLabel() {
//        return gpmLabel.get();
//    }
//
//    public SimpleObjectProperty<Label> gpmLabelProperty() {
//        return gpmLabel;
//    }
//
//    public void setGpmLabel(Label gpmLabel) {
//        this.gpmLabel.set(gpmLabel);
//    }
//
//    public Button getEditButton() {
//        return editButton.get();
//    }
//
//    public SimpleObjectProperty<Button> editButtonProperty() {
//        return editButton;
//    }
//
//    public void setEditButton(Button editButton) {
//        this.editButton.set(editButton);
//    }
//
//    public Button getCommitButton() {
//        return commitButton.get();
//    }
//
//    public SimpleObjectProperty<Button> commitButtonProperty() {
//        return commitButton;
//    }
//
//    public void setCommitButton(Button commitButton) {
//        this.commitButton.set(commitButton);
//    }
//
//    public TableView getToplineTable() {
//        return toplineTable.get();
//    }
//
//    public SimpleObjectProperty<TableView> toplineTableProperty() {
//        return toplineTable;
//    }
//
//    public void setToplineTable(TableView toplineTable) {
//        this.toplineTable.set(toplineTable);
//    }
//
//    public TableView getRetailerTable() {
//        return retailerTable.get();
//    }
//
//    public SimpleObjectProperty<TableView> retailerTableProperty() {
//        return retailerTable;
//    }
//
//    public void setRetailerTable(TableView retailerTable) {
//        this.retailerTable.set(retailerTable);
//    }
//
//    public TextField getWeeklyPromoUfswField() {
//        return weeklyPromoUfswField.get();
//    }
//
//    public SimpleObjectProperty<TextField> weeklyPromoUfswFieldProperty() {
//        return weeklyPromoUfswField;
//    }
//
//    public void setWeeklyPromoUfswField(TextField weeklyPromoUfswField) {
//        this.weeklyPromoUfswField.set(weeklyPromoUfswField);
//    }
//
//    public ComboBox<RTMOption> getRtmBox() {
//        return rtmBox.get();
//    }
//
//    public SimpleObjectProperty<ComboBox<RTMOption>> rtmBoxProperty() {
//        return rtmBox;
//    }
//
//    public void setRtmBox(ComboBox<RTMOption> rtmBox) {
//        this.rtmBox.set(rtmBox);
//    }

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
        if (parameters==null){
            return new SimpleObjectProperty<>(getEmptyParameters());
        }
        return parameters;
    }

    public void setParameters(ObservableList<Parameter<?>> parameters) {
        this.parameters.set(parameters);
    }

    public ObservableList<Summary> getToplineSummaries() {
        return toplineSummaries.get();
    }

    public SimpleObjectProperty<ObservableList<Summary>> toplineSummariesProperty() {
        if (toplineSummaries== null){
            return new SimpleObjectProperty<>(getEmptyToplineSummaries());
        }
        return toplineSummaries;
    }

    public void setToplineSummaries(ObservableList<Summary> toplineSummaries) {
        this.toplineSummaries.set(toplineSummaries);
    }

    public ObservableList<Summary> getRetailerSummaries() {
        return retailerSummaries.get();
    }

    public SimpleObjectProperty<ObservableList<Summary>> retailerSummariesProperty() {
        if (retailerSummaries==null){
            return new SimpleObjectProperty<>(getEmptyRetailerSummaries());
        }
        return retailerSummaries;
    }

    public void setRetailerSummaries(ObservableList<Summary> retailerSummaries) {
        this.retailerSummaries.set(retailerSummaries);
    }

    public BigDecimal getWeeklyPromoUfsw() {
        return weeklyPromoUfsw.get();
    }

    public SimpleObjectProperty<BigDecimal> weeklyPromoUfswProperty() {
        if (weeklyPromoUfsw==null){
            return new SimpleObjectProperty<>(new BigDecimal("0.0"));
        }
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

    public boolean getCommitted() {
        return committed.get();
    }

    public SimpleBooleanProperty committedProperty() {
        if (committed==null){
            return new SimpleBooleanProperty(false);
        }
        return committed;
    }

    public void setCommitted(boolean committed) {
        this.committed.set(committed);
    }

    public ObservableList<Parameter<?>> getEmptyParameters(){
        ObservableList<Parameter<?>> parameters = FXCollections.observableArrayList();
        parameters.add(new BigDecimalParameter("Skus In Distribution", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), false));
        parameters.add(new IntegerParameter("Sku-Count Change", "", 0,0,0,0,0,0,0,0,0,0,0,0));
        Parameter confidencePer = new BigDecimalParameter("Confidence %", "%");
        parameters.add(confidencePer);
        parameters.add(new BigDecimalParameter("Slotting Investment", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new IntegerParameter("Store Count", "", 0, 0, 0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Everyday Retail", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new BigDecimalParameter("Everyday Unit Cost", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new BigDecimalParameter());;
        parameters.add(new BigDecimalParameter("Seasonality Indices", "",new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new BigDecimalParameter("Promoted Retail 1", "$",new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),true));
        parameters.add(new BigDecimalParameter("Required GPM % 1", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 1", "", 0, 0, 0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 1", "",new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 1", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 1", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Discount % 1", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new StringParameter("Promotional Commentary", "", "","" ,"","","","","","","","","",""));
        parameters.add(new BigDecimalParameter("Promoted Retail 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Required GPM % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new IntegerParameter("Duration (weeks) 2", "", 0,0,0,0,0,0,0,0,0,0,0,0));
        parameters.add(new BigDecimalParameter("Volume Lift Multiple 2", "", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Fixed Costs 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Unit Cost 2", "$", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        parameters.add(new BigDecimalParameter("Promo Discount % 2", "%", new BigDecimal("0.0"),new BigDecimal("0.0") ,new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"),new BigDecimal("0.0"), true));
        return parameters;
    }
    private ObservableList getEmptyToplineSummaries() {
        ObservableList<Summary> summaries = FXCollections.observableArrayList();
        summaries.add(new Summary("Gross Sales", new BigDecimal("0.0")));
        summaries.add(new Summary("Net Sales", new BigDecimal("0.0")));
        summaries.add(new Summary("Total Units", new BigDecimal("0")));
        return summaries;
    }
    private ObservableList getEmptyRetailerSummaries() {
        ObservableList<Summary> summaries = FXCollections.observableArrayList();
        summaries.add(new Summary("Gross Sales", new BigDecimal("0.0")));
        summaries.add(new Summary("GPM", new BigDecimal("0.0")));
        summaries.add(new Summary("Avg. Retail", new BigDecimal("0.0")));
        return summaries;
    }
}
