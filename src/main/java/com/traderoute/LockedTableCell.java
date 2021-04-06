package com.traderoute;

import javafx.application.Platform;
import javafx.scene.AccessibleAttribute;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.skin.TableColumnHeader;
import javafx.scene.control.skin.TableHeaderRow;
import javafx.scene.layout.Region;
import javafx.scene.control.skin.TableViewSkinBase;
import javafx.scene.control.skin.TableHeaderRow;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
//
//public abstract class LockedTableCell<T, S> extends TableCell<T, S> {
//
//    {
//
//        Platform.runLater(() -> {
//
//            ScrollBar sc = (ScrollBar) getTableView().queryAccessibleAttribute(AccessibleAttribute.HORIZONTAL_SCROLLBAR);
//
//            TableHeaderRow thr = (TableHeaderRow) getTableView().queryAccessibleAttribute(AccessibleAttribute.HEADER);
//
//            Region headerNode = thr.getColumnHeaderFor(this.getTableColumn());
//
//            sc.valueProperty().addListener((ob, o, n) -> {
//
//                double doubleValue = n.doubleValue();
//
//                headerNode.setTranslateX(doubleValue);
//
//                headerNode.toFront();
//
//                this.setTranslateX(doubleValue);
//
//                this.toFront();
//
//            });
//
//        });
//        }


//}
