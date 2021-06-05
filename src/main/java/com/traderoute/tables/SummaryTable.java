package com.traderoute.tables;

import com.traderoute.cells.*;
import com.traderoute.data.Summary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.math.BigDecimal;

public abstract class SummaryTable extends CustomTable {
    private TableColumn<Summary, String> descriptionCol;
    private TableColumn<Summary, BigDecimal> valueCol;
    private CellSpecs decSpecs = StdSpecs.DECPOS6$.getSpecs();

    public SummaryTable (String tableType) {
        getColumns().addAll(descriptionCol,valueCol);
        if (tableType.equals("topline")){
            setItems(getDefaultToplineSummaries());
        }
        if (tableType.equals("retailer")){
            setItems(getDefaultRetailerSummaries());
        }
        setEditable(false);
    }

    @Override
    public void setCellFactories(){
        descriptionCol.setCellFactory(tc -> new CustomTextCell<>());
        valueCol.setCellFactory(tc -> new PromoRowEditCell1());
    };


    @Override
    public void setCellValueFactories() {
        descriptionCol.setCellValueFactory(cellData -> cellData.getValue().summaryTypeProperty());
        valueCol.setCellValueFactory(cellData -> cellData.getValue().summaryValueProperty());
    }

    private ObservableList getDefaultToplineSummaries() {
        ObservableList<Summary> summaries = FXCollections.observableArrayList();
        summaries.add(new Summary(decSpecs, "Gross Sales", new BigDecimal("0.0")));
        summaries.add(new Summary(decSpecs, "Net Sales", new BigDecimal("0.0")));
        summaries.add(new Summary(StdSpecs.INTPOS6$.getSpecs(), "Total Units", new BigDecimal("0.0")));
        return summaries;
    }

    private ObservableList getDefaultRetailerSummaries() {
        ObservableList<Summary> summaries = FXCollections.observableArrayList();
        summaries.add(new Summary(decSpecs,"Gross Sales", new BigDecimal("0.0")));
        summaries.add(new Summary(StdSpecs.PERCENT.getSpecs(), "GPM", new BigDecimal("0.0")));
        summaries.add(new Summary(decSpecs, "Avg. Retail", new BigDecimal("0.0")));
        return summaries;
    }
}
