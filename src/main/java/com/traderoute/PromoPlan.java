package com.traderoute;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PromoPlan {

    private final int manufacturerGrossSalesMethod =0;
    private final int manufacturerNet1RevMethod =1;
    private final int totalVolumeMethod = 2;
    private final int retailerGrossSalesMethod =3;
    private final int retailerGrossProfitMethod =4;
    private final int everydayVolumeMethod = 5;
    private final int promotedVolumeMethod = 6;
    private final int totalTSMethod = 6;

    private static final int skusInDistributionIndex = 0;
    private static final int skuCountChangeIndex =1;
    private static final int confidencePerIndex=2;
    private static final int slottingInvestmentIndex =3;
    private static final int storeCountIndex =4;
    private static final int everydayRetailIndex =6;
    private static final int everydayUnitCostIndex =7;
    private static final int seasonalityIndicesIndex =9;
    private static final int promotedRetail1Index =10;
    private static final int requiredGpm1Index =11;
    private static final int durationWeeks1Index =12;
    private static final int volumeLiftMultiple1Index =13;
    private static final int fixedCosts1Index =14;
    private static final int promoUnitCost1Index=15;
    private static final int promoDiscount1Index=16;
    private static final int promotionalCommentaryIndex =17;
    private static final int promotedRetail2Index =18;
    private static final int requiredGpm2Index =19;
    private static final int durationWeeks2Index =20;
    private static final int volumeLiftMultiple2Index =21;
    private static final int fixedCosts2Index =22;
    private static final int promoUnitCost2Index=23;
    private static final int promoDiscount2Index=24;
    private static final int totalVolumeIndex =25;
    private static final int grossProfitPlanIndex =26;

    private SimpleObjectProperty<Retailer> retailer;
    private SimpleObjectProperty<ObservableList<Parameter<?>>> parameters;
    private SimpleObjectProperty<ObservableList<Summary>> toplineSummaries;
    private SimpleObjectProperty<ObservableList<Summary>> retailerSummaries;
    private SimpleObjectProperty<BigDecimal> weeklyPromoUfsw;
    private SimpleObjectProperty<RTMOption> selectedRtm;
    private SimpleBooleanProperty committed;
    private SimpleIntegerProperty selectedIndex;
//    private SimpleObjectProperty<BigDecimal> totalVolume,promotedVolume, grossRevenue, tradeRevenue;



    public PromoPlan( ObservableList<Parameter<?>> parameters, ObservableList<Summary> toplineSummaries,
                     ObservableList<Summary> retailerSummaries, BigDecimal weeklyPromoUfsw, RTMOption selectedRtm,
                     boolean committed) {
        this.retailer = new SimpleObjectProperty<>();
        this.parameters = new SimpleObjectProperty<>(parameters);
        this.toplineSummaries = new SimpleObjectProperty<>(toplineSummaries);
        this.retailerSummaries = new SimpleObjectProperty<>(retailerSummaries);
        this.weeklyPromoUfsw = new SimpleObjectProperty<>(weeklyPromoUfsw);
        this.selectedRtm = new SimpleObjectProperty<>(selectedRtm);
        this.committed = new SimpleBooleanProperty(committed);
//        this.totalVolume = new SimpleObjectProperty<>(totalVolume);
//        this.everydayVolume = new SimpleObjectProperty<>(everydayVolume);
//        this.promotedVolume = new SimpleObjectProperty<>(promotedVolume);
//        this.grossRevenue = new SimpleObjectProperty<>(grossRevenue);
//        this.tradeRevenue = new SimpleObjectProperty<>(tradeRevenue);
//        this.net1Rev = new SimpleObjectProperty<>(net1Rev);
//        this.net1Pod = new SimpleObjectProperty<>(net1Pod);
//        this.goal = new SimpleObjectProperty<>(goal);
//        this.averageSkus = new SimpleObjectProperty<>(averageSkus);
//        this.averageSellingPrice = new SimpleObjectProperty<>(averageSellingPrice);
//        this.pointsOfDistribution = new SimpleObjectProperty<>(pointsOfDistribution);
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
        this.selectedIndex = selectedIndexProperty();
    }
    public String toString(){
        return "Promoplan weeklyUfsw:"+ this.getWeeklyPromoUfsw();
    }

    public Retailer getRetailer() {
        return retailer.get();
    }

    public SimpleObjectProperty<Retailer> retailerProperty() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer.set(retailer);
    }
    public BigDecimal  getSumValue(int value, boolean topline ) {
        BigDecimal sumValue = new BigDecimal("0.0");
        switch (value) {
            case 0:
                if (topline){
                    sumValue = this.getTwelveMonthTotal(manufacturerGrossSalesMethod);
                } else{
                    sumValue = this.getTwelveMonthTotal(retailerGrossSalesMethod);
                } break;
            case 1:
                if (topline) {
                    sumValue = this.getTwelveMonthTotal(manufacturerNet1RevMethod);
                } else {
                    if (this.getTwelveMonthTotal(retailerGrossSalesMethod).compareTo(new BigDecimal("0.0"))==0) {
                        sumValue = new BigDecimal("0.0");
                    } else{
                        sumValue = this.getTwelveMonthTotal(retailerGrossProfitMethod).divide(this.getTwelveMonthTotal(retailerGrossSalesMethod), 4 , RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
                    }
                } break;
            case 2:
                if (topline) {
                    sumValue = this.getTwelveMonthTotal(totalVolumeMethod);
                } else {
                    if (this.getTwelveMonthTotal(totalVolumeMethod).compareTo(new BigDecimal("0.0"))==0) {
                        sumValue= new BigDecimal("0.0");
                    } else {
                        sumValue = this.getTwelveMonthTotal(retailerGrossSalesMethod).divide(this.getTwelveMonthTotal(totalVolumeMethod), 4, RoundingMode.HALF_UP);
                    }
                }break;
        } return sumValue.setScale(4,RoundingMode.HALF_UP);
    }

    public BigDecimal getTwelveMonthTotal(int methodToCall){
        BigDecimal computedValue = new BigDecimal("0.0");
        for (int i= 1; i<=12; i++) {
            switch (methodToCall) {
                case 0:
                    computedValue = computedValue.add(this.getManufacturerGrossSalesActual(i));
                    break;
                case 1:
                    computedValue = computedValue.add(this.getManufacturerNet1Rev(i));
                    break;
                case 2:
                    computedValue = computedValue.add(this.getTotalVolume(i));
                    break;
                case 3:
                    computedValue = computedValue.add(this.getRetailerGrossSales(i));
                    break;
                case 4:
                    computedValue= computedValue.add(this.getRetailerGrossProfit(i));
                    break;
                case 5:
                    computedValue = computedValue.add(this.getEverydayVolume(i));
                    break;
                case 6:
                    computedValue = computedValue.add(this.getPromoVolume(i,1));
                    computedValue = computedValue.add(this.getPromoVolume(i,2));
                    break;
                case 7:
                    computedValue = computedValue.add(this.getTotalTS(i));
                    break;
                case 8:
                    computedValue = computedValue.add(this.getFobDiscounts(i));
                    break;
                default:
                    System.out.println("No method corresponds to this int");
            }
        }
        return computedValue;
    }

    public BigDecimal getSlottingGameTheoryd (int month){
        BigDecimal confidencePer = ((BigDecimal) getParameters().get(confidencePerIndex).getMonth(month)).divide(new BigDecimal(100.0));
        BigDecimal slottingInvestment = ((BigDecimal) getParameters().get(slottingInvestmentIndex).getMonth(month));
        return confidencePer.multiply(slottingInvestment);
    }

    public BigDecimal getEverydayCost (int month){
        BigDecimal everydayGPM = getRetailer().getEverydayGPM();
        BigDecimal everydayRetail = ((BigDecimal) getParameters().get(everydayRetailIndex).getMonth(month));
        return everydayRetail.multiply(new BigDecimal("1.0").subtract(everydayGPM.divide(new BigDecimal(100.0))));
    }
    public BigDecimal getPromoCost (int month, int promo){
        BigDecimal requiredGPM = new BigDecimal("0.0");
        BigDecimal promotedRetail = new BigDecimal("0.0");
        if (promo==1) {
            requiredGPM = (BigDecimal)getParameters().get(requiredGpm1Index).getMonth(month);
            promotedRetail = (BigDecimal)getParameters().get(promotedRetail1Index).getMonth(month);
        } if (promo==2) {
            requiredGPM = (BigDecimal)getParameters().get(requiredGpm2Index).getMonth(month);
            promotedRetail = (BigDecimal)getParameters().get(promotedRetail2Index).getMonth(month);
        }
        return promotedRetail.multiply((new BigDecimal("1.0").subtract(requiredGPM.divide(new BigDecimal("100.0")))));
    }

    public int getEverydayRetailWeeks(int month){
        int durationWeeks1 = (int) getParameters().get(durationWeeks1Index).getMonth(month);
        int durationWeeks2 = (int) getParameters().get(durationWeeks2Index).getMonth(month);
        return getWeeksInPeriod(month)- (durationWeeks1)-(durationWeeks2);
    }

    public BigDecimal getEverydayVolume(int month) {
        BigDecimal skusInDistribution = (BigDecimal) getParameters().get(skusInDistributionIndex).getMonth(month);
        // make getweekly velocity set weekly promo ufsw
        BigDecimal weeklyVelocity = weeklyPromoUfsw.get();
        BigDecimal storeCount = new BigDecimal((int) getParameters().get(storeCountIndex).getMonth(month));
        BigDecimal everyDayRetailWeeks = new BigDecimal(getEverydayRetailWeeks(month));
        return skusInDistribution.multiply(storeCount).multiply(weeklyVelocity).multiply(everyDayRetailWeeks);
    }

    public BigDecimal getPromoVolume(int month, int promo){
        BigDecimal skusInDistribution = (BigDecimal) getParameters().get(skusInDistributionIndex).getMonth(month);
        BigDecimal weeklyVelocity = weeklyPromoUfsw.get();
        BigDecimal storeCount = new BigDecimal((int) getParameters().get(storeCountIndex).getMonth(month));
        BigDecimal volumeLiftMultiple = new BigDecimal("0.0");
        BigDecimal durationWeeks = new BigDecimal("0.0");
        if (promo==1) {
            volumeLiftMultiple = (BigDecimal)getParameters().get(volumeLiftMultiple1Index).getMonth(month);
            durationWeeks= new BigDecimal((int)getParameters().get(durationWeeks1Index).getMonth(month));;
        }if (promo==2) {
            volumeLiftMultiple = (BigDecimal)getParameters().get(volumeLiftMultiple2Index).getMonth(month);
            durationWeeks= new BigDecimal((int)getParameters().get(durationWeeks2Index).getMonth(month));;
        }
        return skusInDistribution.multiply(weeklyVelocity).multiply(storeCount).multiply(volumeLiftMultiple).multiply(durationWeeks);
    }

    public BigDecimal getTotalVolume(int month){
        return getEverydayVolume(month).add(getPromoVolume(month,1).add(getPromoVolume(month,2)));
    }
    public BigDecimal getPromoDiscount(int month, int promo){
        if (getEverydayCost(month).compareTo(new BigDecimal("0.0"))==0 || getPromoCost(month,promo).compareTo(new BigDecimal("0.0"))==0){
            return new BigDecimal("0.0");
        } return new BigDecimal("100").multiply((getEverydayCost(month).subtract(getPromoCost(month, promo))).divide(getEverydayCost(month),4, RoundingMode.HALF_UP));
    }
    public BigDecimal getRetailerGrossSales(int month){
        BigDecimal promotedRetail1 = (BigDecimal)getParameters().get(promotedRetail1Index).getMonth(month);
        BigDecimal promotedRetail2 = (BigDecimal)getParameters().get(promotedRetail2Index).getMonth(month);
        BigDecimal everydayRetail = (BigDecimal)getParameters().get(everydayRetailIndex).getMonth(month);
        return everydayRetail.multiply(getEverydayVolume(month))
                .add(promotedRetail1.multiply(getPromoVolume(month,1)))
                .add(promotedRetail2.multiply(getPromoVolume(month,2)));
    }
    public BigDecimal getRetailerNetCost(int month){
        return (getEverydayCost(month).multiply(getEverydayVolume(month))).add(getPromoCost(month,1).multiply(getPromoVolume(month,1))).add(getPromoCost(month,2).multiply(getPromoVolume(month,2)));
    }
    public BigDecimal getRetailerGrossProfit(int month){
        return getRetailerGrossSales(month).subtract(getRetailerNetCost(month));
    }
    public BigDecimal getManufacturerGrossSalesList(int month){
        return getTotalVolume(month).multiply(getRetailer().getRetailerProducts().get(getRetailer().getCurrentRetailerProductIndex()).getProduct().getUnitListCost()); //new BigDecimal("3.59") HARDCODED LIST FOR NOW
    }
    public BigDecimal getManufacturerGrossSalesActual(int month){
        return getManufacturerGrossSalesList(month).subtract(getFobDiscounts(month));
    }

    public BigDecimal getFobDiscounts(int month) {// HARDCODED TRUE FOR NOW
//        getPromoPlans().get(getCurrentPromoPlanIndex()).getSelectedRtm();
        RTMOption selectedRtm = this.getSelectedRtm();
        if (selectedRtm!=null) {
            if (selectedRtm.isFob()) {// F.O.B
                Product product = getRetailer().getRetailerProducts().get(getRetailer().getCurrentRetailerProductIndex()).getProduct();
                return getTotalVolume(month).multiply((product.getUnitListCost()).subtract(product.getUnitFobCost())); // LISt and FOB -- CHANGED
            }return new BigDecimal("0.0");
        }

        return null;
    }

    public BigDecimal getSpoilsFeesTS(int month){
        return (getRetailer().getSpoilsFees()).divide(new BigDecimal("100")).multiply(getManufacturerGrossSalesActual(month));
    }
    public BigDecimal getEverydayAllowanceTS(int month){
        //getPromoPlans().get(getCurrentPromoPlanIndex())
        RTMOption selectedRtm = this.getSelectedRtm();
        Product product= getRetailer().getRetailerProducts().get(getRetailer().getCurrentRetailerProductIndex()).getProduct();
        if (selectedRtm!=null) {
            if (selectedRtm.isFob()) {
                return getTotalVolume(month).multiply((product.getUnitFobCost()).subtract(selectedRtm.getFirstReceiver()));
            } else {
                return getTotalVolume(month).multiply((product.getUnitListCost()).subtract(selectedRtm.getFirstReceiver()));
            }
        } System.out.println("False values: Please select an RTM- everydayALLOWANcETS");
        return null;
    }
    public BigDecimal getPromoTS(int month, int promo){
        return getPromoVolume(month,promo).multiply(getEverydayCost(month).subtract(getPromoCost(month,promo)));
    }
    public BigDecimal getFixedCostsTS(int month){
        BigDecimal fixedCosts1 = (BigDecimal)getParameters().get(fixedCosts1Index).getMonth(month);;
        BigDecimal fixedCosts2 = (BigDecimal)getParameters().get(fixedCosts2Index).getMonth(month);
        return fixedCosts1.add(fixedCosts2);
    }
    public BigDecimal getTotalTS (int month){
        return getEverydayAllowanceTS(month).add(getPromoTS(month,1)).add(getPromoTS(month,2)).add(getFixedCostsTS(month)).add(getSpoilsFeesTS(month));
    }
    public BigDecimal getManufacturerNet1Rev(int month){ // CONTAINS BUG IN MAIN EXCEL APP
        return getManufacturerGrossSalesActual(month).subtract(getTotalTS(month)).add(getFobDiscounts(month));
    }
    public BigDecimal getManufacturerFreightCost(int month){
        RTMOption selectedRTM= this.getSelectedRtm();
        if (selectedRTM!=null) {
            return selectedRTM.getFreightOutPerUnit().add(getFobDiscounts(month)); //HARDCODED THE FREIHT OUT PER UNIT
        } System.out.println("PLEASE SELECT RTM - man freight cost");
        return null;
    }
    public BigDecimal getManufacturerNet2Rev(int month){
        return getManufacturerNet1Rev(month).subtract(getManufacturerFreightCost(month));
    }
    public BigDecimal getManufacturerNet3Rev(int month){
        return getManufacturerNet2Rev(month).subtract(getSlottingGameTheoryd(month));
    }
    public BigDecimal getManufacturerCogs (int month){
        BigDecimal cogs = getRetailer().getRetailerProducts().get(getRetailer().getCurrentRetailerProductIndex()).getProduct().getUnitBlendedCogs();
        return cogs.multiply(getTotalVolume(month));
    }
    public BigDecimal getManufacturerGrossProfit (int month){
        return getManufacturerNet3Rev(month).subtract(getManufacturerCogs(month)); //2.05 COGS
    }
    public BigDecimal getPromoLiftMultiple(int month, int promo){
        BigDecimal volumeLiftMultiple = new BigDecimal("0.0");
        if (promo==1){
            volumeLiftMultiple= (BigDecimal) getParameters().get(volumeLiftMultiple1Index).getMonth(month);
        } if (promo==2) {
            volumeLiftMultiple= (BigDecimal) getParameters().get(volumeLiftMultiple2Index).getMonth(month);
        }
        return volumeLiftMultiple;
    }

    public static int getWeeksInPeriod(int month){
        if (month == 2 ||month == 3 ||month == 5 || month == 6 || month == 8 || month == 9 || month == 11 || month ==12){
            return 4;
        }
        return 5;
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

    public int getSelectedIndex() {
        return selectedIndex.get();
    }

    public SimpleIntegerProperty selectedIndexProperty() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex.set(selectedIndex);
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
