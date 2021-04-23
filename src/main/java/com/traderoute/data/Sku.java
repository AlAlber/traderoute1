package com.traderoute.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import java.math.BigDecimal;

public class Sku {
    private SimpleStringProperty flavorDescription;
    private SimpleStringProperty status;
    private SimpleStringProperty skuNotes;
    private SimpleStringProperty packageUpc, caseUpc;

    private SimpleIntegerProperty casePack, casesPerLayer, layersPerPallet, casesPerPallet, palletWeight,
            palletsPerTruck, deliveredShelfLife;

    private SimpleObjectProperty<BigDecimal> unitWeight, grossCaseWeight, netCaseWeight, caseTareWeight, caseCube,
            caseLength, caseWidth, caseHeight, unitLength, unitWidth, unitHeight, palletHeight, unitList;

    public Sku(String flavorDescription, String status, String skuNotes) {
        this.flavorDescription = new SimpleStringProperty(flavorDescription);
        this.status = new SimpleStringProperty(status);
        this.skuNotes = new SimpleStringProperty(skuNotes);
    }

    public Sku(String flavorDescription, Integer casePack, BigDecimal unitWeight, BigDecimal grossCaseWeight,
            BigDecimal netCaseWeight, BigDecimal caseTareWeight, BigDecimal caseCube, BigDecimal caseLength,
            BigDecimal caseWidth, BigDecimal caseHeight, BigDecimal unitLength, BigDecimal unitWidth,
            BigDecimal unitHeight, Integer casesPerLayer, Integer layersPerPallet, Integer casesPerPallet,
            BigDecimal palletHeight, Integer palletWeight, Integer palletsPerTruck, Integer deliveredShelfLife,
            String packageUpc, String caseUpc, BigDecimal unitList) {
        this.flavorDescription = new SimpleStringProperty(flavorDescription);
        this.casePack = new SimpleIntegerProperty(casePack);
        this.unitWeight = new SimpleObjectProperty<>(unitWeight);
        this.grossCaseWeight = new SimpleObjectProperty<>(grossCaseWeight);
        this.netCaseWeight = new SimpleObjectProperty<>(netCaseWeight);
        this.caseTareWeight = new SimpleObjectProperty<>(caseTareWeight);
        this.caseCube = new SimpleObjectProperty<>(caseCube);
        this.caseLength = new SimpleObjectProperty<>(caseLength);
        this.caseWidth = new SimpleObjectProperty<>(caseWidth);
        this.caseHeight = new SimpleObjectProperty<>(caseHeight);
        this.unitLength = new SimpleObjectProperty<>(unitLength);
        this.unitWidth = new SimpleObjectProperty<>(unitWidth);
        this.unitHeight = new SimpleObjectProperty<>(unitHeight);
        this.casesPerLayer = new SimpleIntegerProperty(casesPerLayer);
        this.layersPerPallet = new SimpleIntegerProperty(layersPerPallet);
        this.casesPerPallet = new SimpleIntegerProperty(casesPerPallet);
        this.palletHeight = new SimpleObjectProperty<>(palletHeight);
        this.palletWeight = new SimpleIntegerProperty(palletWeight);
        this.palletsPerTruck = new SimpleIntegerProperty(palletsPerTruck);
        this.deliveredShelfLife = new SimpleIntegerProperty(deliveredShelfLife);
        this.packageUpc = new SimpleStringProperty(packageUpc);
        this.caseUpc = new SimpleStringProperty(caseUpc);
        this.unitList = new SimpleObjectProperty<>(unitList);
    }

    public Sku() {
        this.flavorDescription = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
        this.skuNotes = new SimpleStringProperty();
    }

    public String getFlavorDescription() {
        return flavorDescription.get();
    }

    public SimpleStringProperty flavorDescriptionProperty() {
        return flavorDescription;
    }

    public void setFlavorDescription(String flavorDescription) {
        this.flavorDescription.set(flavorDescription);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getSkuNotes() {
        return skuNotes.get();
    }

    public SimpleStringProperty skuNotesProperty() {
        return skuNotes;
    }

    public void setSkuNotes(String skuNotes) {
        this.skuNotes.set(skuNotes);
    }

    public String getPackageUpc() {
        return packageUpc.get();
    }

    public SimpleStringProperty packageUpcProperty() {
        return packageUpc;
    }

    public void setPackageUpc(String packageUpc) {
        this.packageUpc.set(packageUpc);
    }

    public String getCaseUpc() {
        return caseUpc.get();
    }

    public SimpleStringProperty caseUpcProperty() {
        return caseUpc;
    }

    public void setCaseUpc(String caseUpc) {
        this.caseUpc.set(caseUpc);
    }

    public int getCasePack() {
        return casePack.get();
    }

    public SimpleIntegerProperty casePackProperty() {
        return casePack;
    }

    public void setCasePack(int casePack) {
        this.casePack.set(casePack);
    }

    public int getCasesPerLayer() {
        return casesPerLayer.get();
    }

    public SimpleIntegerProperty casesPerLayerProperty() {
        return casesPerLayer;
    }

    public void setCasesPerLayer(int casesPerLayer) {
        this.casesPerLayer.set(casesPerLayer);
    }

    public int getLayersPerPallet() {
        return layersPerPallet.get();
    }

    public SimpleIntegerProperty layersPerPalletProperty() {
        return layersPerPallet;
    }

    public void setLayersPerPallet(int layersPerPallet) {
        this.layersPerPallet.set(layersPerPallet);
    }

    public int getCasesPerPallet() {
        return casesPerPallet.get();
    }

    public SimpleIntegerProperty casesPerPalletProperty() {
        return casesPerPallet;
    }

    public void setCasesPerPallet(int casesPerPallet) {
        this.casesPerPallet.set(casesPerPallet);
    }

    public int getPalletWeight() {
        return palletWeight.get();
    }

    public SimpleIntegerProperty palletWeightProperty() {
        return palletWeight;
    }

    public void setPalletWeight(int palletWeight) {
        this.palletWeight.set(palletWeight);
    }

    public int getPalletsPerTruck() {
        return palletsPerTruck.get();
    }

    public SimpleIntegerProperty palletsPerTruckProperty() {
        return palletsPerTruck;
    }

    public void setPalletsPerTruck(int palletsPerTruck) {
        this.palletsPerTruck.set(palletsPerTruck);
    }

    public int getDeliveredShelfLife() {
        return deliveredShelfLife.get();
    }

    public SimpleIntegerProperty deliveredShelfLifeProperty() {
        return deliveredShelfLife;
    }

    public void setDeliveredShelfLife(int deliveredShelfLife) {
        this.deliveredShelfLife.set(deliveredShelfLife);
    }

    public BigDecimal getUnitWeight() {
        return unitWeight.get();
    }

    public SimpleObjectProperty<BigDecimal> unitWeightProperty() {
        return unitWeight;
    }

    public void setUnitWeight(BigDecimal unitWeight) {
        this.unitWeight.set(unitWeight);
    }

    public BigDecimal getGrossCaseWeight() {
        return grossCaseWeight.get();
    }

    public SimpleObjectProperty<BigDecimal> grossCaseWeightProperty() {
        return grossCaseWeight;
    }

    public void setGrossCaseWeight(BigDecimal grossCaseWeight) {
        this.grossCaseWeight.set(grossCaseWeight);
    }

    public BigDecimal getNetCaseWeight() {
        return netCaseWeight.get();
    }

    public SimpleObjectProperty<BigDecimal> netCaseWeightProperty() {
        return netCaseWeight;
    }

    public void setNetCaseWeight(BigDecimal netCaseWeight) {
        this.netCaseWeight.set(netCaseWeight);
    }

    public BigDecimal getCaseTareWeight() {
        return caseTareWeight.get();
    }

    public SimpleObjectProperty<BigDecimal> caseTareWeightProperty() {
        return caseTareWeight;
    }

    public void setCaseTareWeight(BigDecimal caseTareWeight) {
        this.caseTareWeight.set(caseTareWeight);
    }

    public BigDecimal getCaseCube() {
        return caseCube.get();
    }

    public SimpleObjectProperty<BigDecimal> caseCubeProperty() {
        return caseCube;
    }

    public void setCaseCube(BigDecimal caseCube) {
        this.caseCube.set(caseCube);
    }

    public BigDecimal getCaseLength() {
        return caseLength.get();
    }

    public SimpleObjectProperty<BigDecimal> caseLengthProperty() {
        return caseLength;
    }

    public void setCaseLength(BigDecimal caseLength) {
        this.caseLength.set(caseLength);
    }

    public BigDecimal getCaseWidth() {
        return caseWidth.get();
    }

    public SimpleObjectProperty<BigDecimal> caseWidthProperty() {
        return caseWidth;
    }

    public void setCaseWidth(BigDecimal caseWidth) {
        this.caseWidth.set(caseWidth);
    }

    public BigDecimal getCaseHeight() {
        return caseHeight.get();
    }

    public SimpleObjectProperty<BigDecimal> caseHeightProperty() {
        return caseHeight;
    }

    public void setCaseHeight(BigDecimal caseHeight) {
        this.caseHeight.set(caseHeight);
    }

    public BigDecimal getUnitLength() {
        return unitLength.get();
    }

    public SimpleObjectProperty<BigDecimal> unitLengthProperty() {
        return unitLength;
    }

    public void setUnitLength(BigDecimal unitLength) {
        this.unitLength.set(unitLength);
    }

    public BigDecimal getUnitWidth() {
        return unitWidth.get();
    }

    public SimpleObjectProperty<BigDecimal> unitWidthProperty() {
        return unitWidth;
    }

    public void setUnitWidth(BigDecimal unitWidth) {
        this.unitWidth.set(unitWidth);
    }

    public BigDecimal getUnitHeight() {
        return unitHeight.get();
    }

    public SimpleObjectProperty<BigDecimal> unitHeightProperty() {
        return unitHeight;
    }

    public void setUnitHeight(BigDecimal unitHeight) {
        this.unitHeight.set(unitHeight);
    }

    public BigDecimal getPalletHeight() {
        return palletHeight.get();
    }

    public SimpleObjectProperty<BigDecimal> palletHeightProperty() {
        return palletHeight;
    }

    public void setPalletHeight(BigDecimal palletHeight) {
        this.palletHeight.set(palletHeight);
    }

    public BigDecimal getUnitList() {
        return unitList.get();
    }

    public SimpleObjectProperty<BigDecimal> unitListProperty() {
        return unitList;
    }

    public void setUnitList(BigDecimal unitList) {
        this.unitList.set(unitList);
    }
}
