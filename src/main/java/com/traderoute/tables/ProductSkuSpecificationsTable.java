package com.traderoute.tables;

import com.traderoute.CustomColumn;
import com.traderoute.cells.*;
import com.traderoute.data.Sku;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;

import java.math.BigDecimal;

public class ProductSkuSpecificationsTable extends CustomTable{
    private TableColumn<Sku, String> flavorDescriptionCol = new CustomColumn(140.0, "Flavor Description", "Flavor Description");
    private TableColumn<Sku, Integer> casePackCol = new CustomColumn(100.0, "Case Pack", "Case Pack");
    private TableColumn<Sku, BigDecimal> unitWeightCol = new CustomColumn(100.0, "Unit Weight", "Unit Weight");
    private TableColumn<Sku, BigDecimal> grossCaseWeightCol = new CustomColumn(100.0, "Gross Case Weight", "Gross Case Weight");
    private TableColumn<Sku, BigDecimal> netCaseWeightCol = new CustomColumn(100.0, "Net Case Weight", "Net Case Weight");
    private TableColumn<Sku, BigDecimal> caseTareWeightCol = new CustomColumn(100.0, "Case Tare Weight", "Case Tare Weight");
    private TableColumn<Sku, BigDecimal> caseCubeCol = new CustomColumn(100.0, "Case Cube", "Case Cube");
    private TableColumn<Sku, BigDecimal> caseLengthCol = new CustomColumn(100.0, "Case Length", "Case Length");
    private TableColumn<Sku, BigDecimal> caseWidthCol = new CustomColumn(100.0, "Case Width", "Case Width");
    private TableColumn<Sku, BigDecimal> caseHeightCol = new CustomColumn(100.0, "Case Height", "Case Height");
    private TableColumn<Sku, BigDecimal> unitLengthCol = new CustomColumn(100.0, "Unit Length", "Unit Length");
    private TableColumn<Sku, BigDecimal> unitWidthCol = new CustomColumn(100.0, "Unit Width", "Unit Width");
    private TableColumn<Sku, BigDecimal> unitHeightCol = new CustomColumn(100.0, "Unit Height", "Unit Height");
    private TableColumn<Sku, Integer> casesPerLayerCol = new CustomColumn(100.0, "Cases Per Layer (TI)", "Cases Per Layer (TI)");
    private TableColumn<Sku, Integer> layersPerPalletCol = new CustomColumn(100.0, "Layers Per Pallet (HI)", "Layers Per Pallet (HI)");
    private TableColumn<Sku, Integer> casesPerPalletCol = new CustomColumn(100.0, "Cases Per Pallet", "Cases Per Pallet");
    private TableColumn<Sku, BigDecimal> palletHeightCol = new CustomColumn(100.0, "Pallet Height", "Pallet Height");
    private TableColumn<Sku, Integer> palletWeightCol = new CustomColumn(100.0, "Pallet Weight", "Pallet Weight");
    private TableColumn<Sku, Integer> palletsPerTruckCol = new CustomColumn(100.0, "Pallets Per Truck", "Pallets Per Truck");
    private TableColumn<Sku, Integer> deliveredShelfLifeCol = new CustomColumn(100.0, "Delivered Shelf-Life", "Delivered Shelf-Life");
    private TableColumn<Sku, String> packageUpcCol = new CustomColumn(100.0, "Package UPC", "Package UPC");
    private TableColumn<Sku, String> caseUpcCol = new CustomColumn(100.0, "Case UPC", "Case UPC");
    private TableColumn<Sku, BigDecimal> unitListCol = new CustomColumn(100.0, "Unit List", "Unit List");

    public ProductSkuSpecificationsTable (){
        setEditable(true);
        getColumns().addAll(flavorDescriptionCol, casePackCol, unitWeightCol,
                grossCaseWeightCol, netCaseWeightCol, caseTareWeightCol,
                caseCubeCol, caseLengthCol, caseWidthCol, caseHeightCol, unitLengthCol,
                unitWidthCol, unitHeightCol, casesPerLayerCol, layersPerPalletCol,
                casesPerPalletCol, palletHeightCol, palletWeightCol, palletsPerTruckCol,
                deliveredShelfLifeCol, packageUpcCol, caseUpcCol, unitListCol);
        getColumns().stream().forEach(tcol -> setSortPolicy(e -> false));
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
