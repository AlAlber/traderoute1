package com.traderoute;

import javafx.beans.property.SimpleStringProperty;

public class Sku {
    private SimpleStringProperty flavorDescription;
    private SimpleStringProperty status;
    private SimpleStringProperty skuNotes;

    public Sku(String flavorDescription, String status, String skuNotes) {
        this.flavorDescription = new SimpleStringProperty(flavorDescription);
        this.status = new SimpleStringProperty(status);
        this.skuNotes = new SimpleStringProperty(skuNotes);
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
}
