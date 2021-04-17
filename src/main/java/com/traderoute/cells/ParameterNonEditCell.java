package com.traderoute.cells;

import com.traderoute.data.Parameter;
import com.traderoute.data.PromoPlan;
import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ParameterNonEditCell extends TableCell<Parameter<?>, Object> {
    @Override
    public void updateItem(Object item, boolean empty) {

        super.updateItem(item, empty);
        Parameter<?> param = getTableRow().getItem();
        if (empty || item==null) {
            setText("");
            setGraphic(null);
        } else {
            String itemString = "";
            if (item instanceof BigDecimal){
                 if (item != null){
                     itemString= (((BigDecimal) item).setScale(0 , RoundingMode.HALF_UP).toString());
                 }
            } else if (item instanceof Integer){
                if (item != null) {
                    itemString =item.toString();
                }
            } else {
                itemString = (String) item;
            }

            if (param.getPre().equals("%")){
                setText(itemString+ param.getPre());
                System.out.println(param.getPre() + itemString);
                setTooltip(new Tooltip(itemString+ param.getPre()));
            }else {
                setText(param.getPre() + itemString);
                System.out.println(itemString + itemString);
                setTooltip(new Tooltip(param.getPre() + itemString));
            }
//            if (param.getJanuary() instanceof String) {
//                if (item.equals("")) {
//                        setStyle("-fx-background-color:  transparent");
//                        setPrefHeight(40); // CAUSING BUG: SEASONALITY INDICE GETS 40 WIDTH OUTTA NOWHERE
//                    } else {
//                        setStyle("-fx-background-color:  rgb(255,255,255, 0.3);\n" +
//                                "    -fx-background-insets: 0, 0 0 1 0;");
//                    }
//                }
//                if (param.getJanuary() instanceof Number) {
//                    int comparator = new BigDecimal(item.toString()).compareTo(new BigDecimal("0.0"));
//                    if (param.getName().startsWith("Total Volume")){
//                        setStyle("-fx-text-fill: #A79543;");
//                    } else if (param.getName().startsWith("Gross Profit")){
//                        if (comparator >=0){
//                            setStyle("-fx-text-fill: green;");
//                        } else {
//                            setStyle("-fx-text-fill: red;");
//                        }
//                    } else {
//                        int i = comparator;
//                        if (i > 0) {
//                            setStyle("-fx-background-color:  rgb(255,255,255, 0.3);\n" +
//                                    "    -fx-background-insets: 0, 0 0 1 0;");
//                        }
//                        if (i == 0) {
//                            setStyle("-fx-text-fill:  rgb(255,255,255, 0.3);");
//                        }
//                    }
//                }
//            }
        }
    }

//    private String getString() {
//
//    }
}
