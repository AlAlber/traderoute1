package com.traderoute;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

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

    public Product(){
        this.brandName = new SimpleStringProperty();
        this.productClass = new SimpleStringProperty();
        this.unitListCost = new SimpleObjectProperty<>();
        this.unitBaseFreight = new SimpleObjectProperty<>();
        this.unitFobCost = new SimpleObjectProperty<>();
        this.unitNet1Goal = new SimpleObjectProperty<>();
        this.unitBlendedCogs = new SimpleObjectProperty<>();
        this.elasticityMultiple = new SimpleObjectProperty<>();
    }
    public Product(String brandName, String productClass, BigDecimal unitListCost,
                   BigDecimal unitBaseFreight, BigDecimal unitFobCost, BigDecimal unitNet1Goal
                    , BigDecimal unitBlendedCogs, BigDecimal elasticityMultiple){
        this.brandName = new SimpleStringProperty(brandName);
        this.productClass = new SimpleStringProperty(productClass);
        this.unitListCost = new SimpleObjectProperty<>(unitListCost);
        this.unitBaseFreight = new SimpleObjectProperty<>(unitBaseFreight);
        this.unitFobCost = new SimpleObjectProperty<>(unitFobCost);
        this.unitNet1Goal = new SimpleObjectProperty<>(unitNet1Goal);
        this.unitBlendedCogs = new SimpleObjectProperty<>(unitBlendedCogs);
        this.elasticityMultiple = new SimpleObjectProperty<>(elasticityMultiple);
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
