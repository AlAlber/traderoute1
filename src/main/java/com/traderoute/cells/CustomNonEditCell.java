package com.traderoute.cells;

import com.traderoute.data.ProductClassReport;
import com.traderoute.data.Summary;
import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;

public class CustomNonEditCell<Object, BigDecimal> extends TableCell<Object, BigDecimal> {
    private String per;
    private final String dollar;

    public CustomNonEditCell(String dollar, String per) {

        super();
        this.per = per;
        this.dollar = dollar;
    }

    /**
     * This method is intended to add a new address for the customer. However do note that it only allows a single
     * address per zip code. Hence, this will override any previous address with the same postal code.
     *
     * @param address
     *            an address to be added for an existing customer
     */
    /*
     * This method makes use of the custom implementation of equals method to avoid duplication of an address with same
     * zip code.
     */
    @Override
    protected void updateItem(BigDecimal item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText("Hello");
        }
        if (item == null) {
            setText("");
        } else {
            if (getTableRow().getIndex() == 0 && getTableRow().getItem() instanceof ProductClassReport) {
                setStyle("-fx-background-color: rgb(105,105,105,0.5)");
            }
            if (getTableView().getItems().get(getIndex()) instanceof Summary) {
                Summary summary = (Summary) getTableView().getItems().get(getIndex());
                if (summary.getSummaryType().equals("Total Units")) {
                    setText(String.format("%,.0f", item));
                    setTooltip(new Tooltip(String.format("%,.0f", item)));
                } else if (summary.getSummaryType().equals("GPM")) {
                    setText(String.format("%,.2f", item) + "%");
                    setTooltip(new Tooltip(String.format("%,.2f", item) + "%"));
                } else {
                    setText(dollar + String.format("%,.2f", item) + per);
                    setTooltip(new Tooltip(dollar + String.format("%,.2f", item) + per));
                }
            } else {
                setText(dollar + String.format("%,.2f", item) + per);
            }
        }
    }
}
