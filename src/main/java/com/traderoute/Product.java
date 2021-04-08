package com.traderoute;

import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

import java.math.BigDecimal;

public class Product {
    private SimpleStringProperty brandName;
    private SimpleStringProperty productClass;
    private SimpleObjectProperty<BigDecimal> unitListCost;
    private SimpleObjectProperty<BigDecimal> unitBaseFreight;
    private SimpleObjectProperty<BigDecimal> unitFobCost;
    private SimpleObjectProperty<BigDecimal> unitNet1Goal;
    private SimpleObjectProperty<BigDecimal> unitBlendedCogs;
    private SimpleObjectProperty<BigDecimal> elasticityMultiple;
    private SimpleObjectProperty<ObservableList<SimpleObjectProperty<BigDecimal>>> seasonalityIndices;
    private SimpleObjectProperty<BigDecimal> janIndice;
    private SimpleObjectProperty<BigDecimal> febIndice;
    private SimpleObjectProperty<BigDecimal> marIndice;
    private SimpleObjectProperty<BigDecimal> aprIndice;
    private SimpleObjectProperty<BigDecimal> mayIndice;
    private SimpleObjectProperty<BigDecimal> junIndice;
    private SimpleObjectProperty<BigDecimal> julIndice;
    private SimpleObjectProperty<BigDecimal> augIndice;
    private SimpleObjectProperty<BigDecimal> sepIndice;
    private SimpleObjectProperty<BigDecimal> octIndice;
    private SimpleObjectProperty<BigDecimal> novIndice;
    private SimpleObjectProperty<BigDecimal> decIndice;


    public Product(){
        this.brandName = new SimpleStringProperty();
        this.productClass = new SimpleStringProperty();
        this.unitListCost = new SimpleObjectProperty<>();
        this.unitBaseFreight = new SimpleObjectProperty<>();
        this.unitFobCost = new SimpleObjectProperty<>();
        this.unitNet1Goal = new SimpleObjectProperty<>();
        this.unitBlendedCogs = new SimpleObjectProperty<>();
        this.elasticityMultiple = new SimpleObjectProperty<>();
        this.seasonalityIndices = new SimpleObjectProperty<>();

        this.janIndice = new SimpleObjectProperty<>();
        this.febIndice = new SimpleObjectProperty<>();
        this.marIndice = new SimpleObjectProperty<>();
        this.aprIndice = new SimpleObjectProperty<>();
        this.mayIndice = new SimpleObjectProperty<>();
        this.junIndice = new SimpleObjectProperty<>();
        this.julIndice = new SimpleObjectProperty<>();
        this.augIndice = new SimpleObjectProperty<>();
        this.sepIndice = new SimpleObjectProperty<>();
        this.octIndice = new SimpleObjectProperty<>();
        this.novIndice = new SimpleObjectProperty<>();
        this.decIndice = new SimpleObjectProperty<>();
    }
    public Product(String brandName, String productClass, BigDecimal unitListCost,
                   BigDecimal unitBaseFreight, BigDecimal unitFobCost, BigDecimal unitNet1Goal
                    , BigDecimal unitBlendedCogs, BigDecimal elasticityMultiple,BigDecimal janIndice,
                   BigDecimal febIndice, BigDecimal marIndice, BigDecimal aprIndice, BigDecimal mayIndice,
                   BigDecimal junIndice, BigDecimal julIndice, BigDecimal augIndice, BigDecimal sepIndice,
                   BigDecimal octIndice, BigDecimal novIndice, BigDecimal decIndice){
        this.brandName = new SimpleStringProperty(brandName);
        this.productClass = new SimpleStringProperty(productClass);
        this.unitListCost = new SimpleObjectProperty<>(unitListCost);
        this.unitBaseFreight = new SimpleObjectProperty<>(unitBaseFreight);
        this.unitFobCost = new SimpleObjectProperty<>(unitFobCost);
        this.unitNet1Goal = new SimpleObjectProperty<>(unitNet1Goal);
        this.unitBlendedCogs = new SimpleObjectProperty<>(unitBlendedCogs);
        this.elasticityMultiple = new SimpleObjectProperty<>(elasticityMultiple);
//        this.seasonalityIndices = new SimpleObjectProperty<>(seasonalityIndices);
        this.janIndice = new SimpleObjectProperty<>(janIndice);
        this.febIndice = new SimpleObjectProperty<>(febIndice);
        this.marIndice = new SimpleObjectProperty<>(marIndice);
        this.aprIndice = new SimpleObjectProperty<>(aprIndice);
        this.mayIndice = new SimpleObjectProperty<>(mayIndice);
        this.junIndice = new SimpleObjectProperty<>(junIndice);
        this.julIndice = new SimpleObjectProperty<>(julIndice);
        this.augIndice = new SimpleObjectProperty<>(augIndice);
        this.sepIndice = new SimpleObjectProperty<>(sepIndice);
        this.octIndice = new SimpleObjectProperty<>(octIndice);
        this.novIndice = new SimpleObjectProperty<>(novIndice);
        this.decIndice = new SimpleObjectProperty<>(decIndice);

    }

    public BigDecimal getJanIndice() {
        return janIndice.get();
    }

    public SimpleObjectProperty<BigDecimal> janIndiceProperty() {
        return janIndice;
    }

    public void setJanIndice(BigDecimal janIndice) {
        this.janIndice.set(janIndice);
    }

    public BigDecimal getFebIndice() {
        return febIndice.get();
    }

    public SimpleObjectProperty<BigDecimal> febIndiceProperty() {
        return febIndice;
    }

    public void setFebIndice(BigDecimal febIndice) {
        this.febIndice.set(febIndice);
    }

    public BigDecimal getMarIndice() {
        return marIndice.get();
    }

    public SimpleObjectProperty<BigDecimal> marIndiceProperty() {
        return marIndice;
    }

    public void setMarIndice(BigDecimal marIndice) {
        this.marIndice.set(marIndice);
    }

    public BigDecimal getAprIndice() {
        return aprIndice.get();
    }

    public SimpleObjectProperty<BigDecimal> aprIndiceProperty() {
        return aprIndice;
    }

    public void setAprIndice(BigDecimal aprIndice) {
        this.aprIndice.set(aprIndice);
    }

    public BigDecimal getMayIndice() {
        return mayIndice.get();
    }

    public SimpleObjectProperty<BigDecimal> mayIndiceProperty() {
        return mayIndice;
    }

    public void setMayIndice(BigDecimal mayIndice) {
        this.mayIndice.set(mayIndice);
    }

    public BigDecimal getJunIndice() {
        return junIndice.get();
    }

    public SimpleObjectProperty<BigDecimal> junIndiceProperty() {
        return junIndice;
    }

    public void setJunIndice(BigDecimal junIndice) {
        this.junIndice.set(junIndice);
    }

    public BigDecimal getJulIndice() {
        return julIndice.get();
    }

    public SimpleObjectProperty<BigDecimal> julIndiceProperty() {
        return julIndice;
    }

    public void setJulIndice(BigDecimal julIndice) {
        this.julIndice.set(julIndice);
    }

    public BigDecimal getAugIndice() {
        return augIndice.get();
    }

    public SimpleObjectProperty<BigDecimal> augIndiceProperty() {
        return augIndice;
    }

    public void setAugIndice(BigDecimal augIndice) {
        this.augIndice.set(augIndice);
    }

    public BigDecimal getSepIndice() {
        return sepIndice.get();
    }

    public SimpleObjectProperty<BigDecimal> sepIndiceProperty() {
        return sepIndice;
    }

    public void setSepIndice(BigDecimal sepIndice) {
        this.sepIndice.set(sepIndice);
    }

    public BigDecimal getOctIndice() {
        return octIndice.get();
    }

    public SimpleObjectProperty<BigDecimal> octIndiceProperty() {
        return octIndice;
    }

    public void setOctIndice(BigDecimal octIndice) {
        this.octIndice.set(octIndice);
    }

    public BigDecimal getNovIndice() {
        return novIndice.get();
    }

    public SimpleObjectProperty<BigDecimal> novIndiceProperty() {
        return novIndice;
    }

    public void setNovIndice(BigDecimal novIndice) {
        this.novIndice.set(novIndice);
    }

    public BigDecimal getDecIndice() {
        return decIndice.get();
    }

    public SimpleObjectProperty<BigDecimal> decIndiceProperty() {
        return decIndice;
    }

    public void setDecIndice(BigDecimal decIndice) {
        this.decIndice.set(decIndice);
    }

    public String toString(){
        String product = "brandName: " + this.getBrandName() + " productClass: " + this.getProductClass() + " Unit List Cost: " + this.getUnitListCost();
        return product;
    }

    public String getBrandName() {
        return brandName.get();
    }

    public SimpleStringProperty brandNameProperty() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName.set(brandName);
    }

    public String getProductClass() {
        if(productClass.get()==null){
            return "Hello";
        }
        return productClass.get();
    }


    public SimpleStringProperty productClassProperty() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass.set(productClass);
    }

    public BigDecimal getUnitListCost() {
        return unitListCost.get();
    }

    public SimpleObjectProperty<BigDecimal> unitListCostProperty() {
        return unitListCost;
    }

    public void setUnitListCost(BigDecimal unitListCost) {
        this.unitListCost.set(unitListCost);
    }

    public BigDecimal getUnitBaseFreight() {
        return unitBaseFreight.get();
    }

    public SimpleObjectProperty<BigDecimal> unitBaseFreightProperty() {
        return unitBaseFreight;
    }

    public void setUnitBaseFreight(BigDecimal unitBaseFreight) {
        this.unitBaseFreight.set(unitBaseFreight);
    }

    public BigDecimal getUnitFobCost() {
        return unitFobCost.get();
    }

    public SimpleObjectProperty<BigDecimal> unitFobCostProperty() {
        return unitFobCost;
    }

    public void setUnitFobCost(BigDecimal unitFobCost) {
        this.unitFobCost.set(unitFobCost);
    }

    public BigDecimal getUnitNet1Goal() {
        return unitNet1Goal.get();
    }

    public SimpleObjectProperty<BigDecimal> unitNet1GoalProperty() {
        return unitNet1Goal;
    }

    public void setUnitNet1Goal(BigDecimal unitNet1Goal) {
        this.unitNet1Goal.set(unitNet1Goal);
    }

    public BigDecimal getUnitBlendedCogs() {
        return unitBlendedCogs.get();
    }

    public SimpleObjectProperty<BigDecimal> unitBlendedCogsProperty() {
        return unitBlendedCogs;
    }

    public void setUnitBlendedCogs(BigDecimal unitBlendedCogs) {
        this.unitBlendedCogs.set(unitBlendedCogs);
    }

    public BigDecimal getElasticityMultiple() {
        return elasticityMultiple.get();
    }

    public SimpleObjectProperty<BigDecimal> elasticityMultipleProperty() {
        return elasticityMultiple;
    }

    public void setElasticityMultiple(BigDecimal elasticityMultiple) {
        this.elasticityMultiple.set(elasticityMultiple);
    }
}
