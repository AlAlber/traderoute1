package com.traderoute;

import com.traderoute.data.ProductClassReport;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loadui.testfx.exceptions.NoNodesFoundException;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;

import java.util.function.BiConsumer;

@ExtendWith(ApplicationExtension.class)
public class TestBaseClass {

    /**
     * IMPORTED FROM TESTFX LOADUI, BUT NEEDED TO COPY METHODS IN ORDER
     * to access them.
     */

    static TableView<?> getTableView(String tableSelector, FxRobot robot) {
        Node node = robot.lookup(tableSelector).queryTableView();
        if (!(node instanceof TableView)) {
            throw new NoNodesFoundException(tableSelector + " selected " + node + " which is not a TableView!");
        } else {
            return (TableView)node;
        }
    }

    protected static TableRow<?> row(String tableSelector, int row, FxRobot robot) {
        TableView<?> tableView = getTableView(tableSelector, robot);

        ObservableList current;
        for(current = tableView.getChildrenUnmodifiable(); current.size() == 1; current = ((Parent)current.get(0)).getChildrenUnmodifiable()) {
        }

        for(current = ((Parent)current.get(1)).getChildrenUnmodifiable(); !(current.get(0) instanceof TableRow); current = ((Parent)current.get(0)).getChildrenUnmodifiable()) {
        }

        Node node = (Node)current.get(row);
        if (node instanceof TableRow) {
            return (TableRow)node;
        } else {
            throw new RuntimeException("Expected Group with only TableRows as children");
        }
    }
    public void typeInCell(TableCell cell, String value, FxRobot robot){
        robot.doubleClickOn(cell).clickOn(cell);
        robot.press(KeyCode.CONTROL, KeyCode.A).release(KeyCode.CONTROL, KeyCode.A).write(value);
        robot.press(KeyCode.ENTER).release(KeyCode.ENTER);
    }
    public void checkCell(int row, int col, String input, String expected, String tableString,  BiConsumer consumer, FxRobot robot){
        TableView table = robot.lookup(tableString).queryTableView();
        TableCell tableCell = cell(tableString, row, col, robot);
        ProductClassReport report = (ProductClassReport)table.getItems().get(row);
//        BiConsumer<ProductClassReport, String> consumer = (repor, inpu) -> repor.setNet1Pod(new BigDecimal(inpu));
        robot.interact(()-> report.setColumn(consumer, report, input));
        Assertions.assertEquals(expected,tableCell.getText());
    }

    protected static TableCell<?, ?> cell(String tableSelector, int row, int column, FxRobot robot) {
        ObservableList current;
        for(current = row(tableSelector, row, robot).getChildrenUnmodifiable(); current.size() == 1 && !(current.get(0) instanceof TableCell); current = ((Parent)current.get(0)).getChildrenUnmodifiable()) {
        }

        Node node = (Node)current.get(column);
        if (node instanceof TableCell) {
            return (TableCell)node;
        } else {
            throw new RuntimeException("Expected TableRowSkin with only TableCells as children");
        }
    }
}
