package com.traderoute.tables;

import com.traderoute.cells.*;
import com.traderoute.data.Sku;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;

import java.math.BigDecimal;

public class ProductSkuSpecificationsTable extends CustomTable{
    private TableColumn<Sku, String> flavorDescriptionCol;
    private TableColumn<Sku, Integer> casePackCol;
    private TableColumn<Sku, BigDecimal> unitWeightCol;
    private TableColumn<Sku, BigDecimal> grossCaseWeightCol;
    private TableColumn<Sku, BigDecimal> netCaseWeightCol;
    private TableColumn<Sku, BigDecimal> caseTareWeightCol;
    private TableColumn<Sku, BigDecimal> caseCubeCol;
    private TableColumn<Sku, BigDecimal> caseLengthCol;
    private TableColumn<Sku, BigDecimal> caseWidthCol;
    private TableColumn<Sku, BigDecimal> caseHeightCol;
    private TableColumn<Sku, BigDecimal> unitLengthCol;
    private TableColumn<Sku, BigDecimal> unitWidthCol;
    private TableColumn<Sku, BigDecimal> unitHeightCol;
    private TableColumn<Sku, Integer> casesPerLayerCol;
    private TableColumn<Sku, Integer> layersPerPalletCol;
    private TableColumn<Sku, Integer> casesPerPalletCol;
    private TableColumn<Sku, BigDecimal> palletHeightCol;
    private TableColumn<Sku, Integer> palletWeightCol;
    private TableColumn<Sku, Integer> palletsPerTruckCol;
    private TableColumn<Sku, Integer> deliveredShelfLifeCol;
    private TableColumn<Sku, String> packageUpcCol;
    private TableColumn<Sku, String> caseUpcCol;
    private TableColumn<Sku, BigDecimal> unitListCol;

    public ProductSkuSpecificationsTable (){
        setEditable(true);
        getColumns().addAll(flavorDescriptionCol, casePackCol, unitWeightCol,
                grossCaseWeightCol, netCaseWeightCol, caseTareWeightCol,
                caseCubeCol, caseLengthCol, caseWidthCol, caseHeightCol, unitLengthCol,
                unitWidthCol, unitHeightCol, casesPerLayerCol, layersPerPalletCol,
                casesPerPalletCol, palletHeightCol, palletWeightCol, palletsPerTruckCol,
                deliveredShelfLifeCol, packageUpcCol, caseUpcCol, unitListCol);
    }
    @Override
    public void setCellFactories() {
        flavorDescriptionCol.setCellFactory(tc -> new CustomTextCell<>());
        CellSpecs decNoPreSpecs = StdSpecs.DECPOS5X.getSpecs();
        CellSpecs decInchSpecs = new CellSpecsBuilder().pre("").post("\"").build();
        CellSpecs decLbsSpecs = new CellSpecsBuilder().pre("").post(" lbs.").build();
        casePackCol.setCellFactory(tc -> new BigDecimalEditCell1(decNoPreSpecs));
        unitWeightCol.setCellFactory(tc -> new BigDecimalEditCell1(decLbsSpecs));
        grossCaseWeightCol.setCellFactory(tc -> new BigDecimalEditCell1(decLbsSpecs));
        netCaseWeightCol.setCellFactory((tc -> new BigDecimalEditCell1(decLbsSpecs)));
        caseTareWeightCol.setCellFactory(tc -> new BigDecimalEditCell1(decLbsSpecs));
        caseCubeCol.setCellFactory(tc -> new BigDecimalEditCell1(decNoPreSpecs));
        caseLengthCol.setCellFactory(tc -> new BigDecimalEditCell1(decInchSpecs));
        caseWidthCol.setCellFactory(tc -> new BigDecimalEditCell1(decInchSpecs));
        caseHeightCol.setCellFactory(tc -> new BigDecimalEditCell1(decInchSpecs));
        unitLengthCol.setCellFactory(tc -> new BigDecimalEditCell1(decInchSpecs));
        unitWidthCol.setCellFactory(tc -> new BigDecimalEditCell1(decInchSpecs));
        unitHeightCol.setCellFactory(tc -> new BigDecimalEditCell1(decInchSpecs));
        casesPerLayerCol.setCellFactory(tc -> new BigDecimalEditCell1(decNoPreSpecs));
        layersPerPalletCol.setCellFactory(tc -> new BigDecimalEditCell1(decNoPreSpecs));
        casesPerPalletCol.setCellFactory(tc -> new BigDecimalEditCell1(decNoPreSpecs));
        palletHeightCol.setCellFactory(tc -> new BigDecimalEditCell1(decInchSpecs));
        palletWeightCol.setCellFactory(tc -> new BigDecimalEditCell1(decLbsSpecs));
        palletsPerTruckCol.setCellFactory(tc -> new BigDecimalEditCell1(decNoPreSpecs));
        deliveredShelfLifeCol.setCellFactory(tc -> new BigDecimalEditCell1(new CellSpecsBuilder().pre("").post(" Months").build()));
        packageUpcCol.setCellFactory(tc -> new CustomTextCell<>());
        caseUpcCol.setCellFactory(tc -> new CustomTextCell<>());
        unitListCol.setCellFactory(tc -> new BigDecimalEditCell1(StdSpecs.DECPOS6$.getSpecs()));
    }

    @Override
    public void setCellValueFactories() {
        flavorDescriptionCol
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().flavorDescriptionProperty());
        casePackCol.setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().casePackProperty());
        unitWeightCol.setCellValueFactory(cellData -> cellData.getValue().unitWeightProperty());
        grossCaseWeightCol.setCellValueFactory(cellData -> cellData.getValue().grossCaseWeightProperty());
        netCaseWeightCol.setCellValueFactory(cellData -> cellData.getValue().netCaseWeightProperty());
        caseTareWeightCol.setCellValueFactory(cellData -> cellData.getValue().caseTareWeightProperty());
        caseCubeCol.setCellValueFactory(cellData -> cellData.getValue().caseCubeProperty());
        caseLengthCol.setCellValueFactory(cellData -> cellData.getValue().caseLengthProperty());
        caseWidthCol.setCellValueFactory(cellData -> cellData.getValue().caseWidthProperty());
        caseHeightCol.setCellValueFactory(cellData -> cellData.getValue().caseHeightProperty());
        unitLengthCol.setCellValueFactory(cellData -> cellData.getValue().unitLengthProperty());
        unitWidthCol.setCellValueFactory(cellData -> cellData.getValue().unitWidthProperty());
        unitHeightCol.setCellValueFactory(cellData -> cellData.getValue().unitHeightProperty());
        casesPerLayerCol
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().casesPerLayerProperty());
        layersPerPalletCol
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().layersPerPalletProperty());
        casesPerPalletCol
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().casesPerPalletProperty());
        palletHeightCol.setCellValueFactory(cellData -> cellData.getValue().palletHeightProperty());
        palletWeightCol
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().palletWeightProperty());
        palletsPerTruckCol
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().palletsPerTruckProperty());
        deliveredShelfLifeCol
                .setCellValueFactory(cellData -> (ObservableValue) cellData.getValue().deliveredShelfLifeProperty());
        packageUpcCol.setCellValueFactory(cellData -> cellData.getValue().packageUpcProperty());
        caseUpcCol.setCellValueFactory(cellData -> cellData.getValue().caseUpcProperty());
        unitListCol.setCellValueFactory(cellData -> cellData.getValue().unitListProperty());
    }
}
