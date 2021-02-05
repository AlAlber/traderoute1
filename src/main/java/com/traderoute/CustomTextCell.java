package com.traderoute;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CustomTextCell<Object, BigDecimal> extends TextFieldTableCell<Object , BigDecimal> {


    @Override
    public void updateItem(BigDecimal item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText("Hello");
        }
        if (item == null) {
            setText("");
        } else {
            setText("$" + String.format("%,.2f", item,2, RoundingMode.HALF_UP));
        }
    }
    @Override
    public void commitEdit(BigDecimal var1){
        super.commitEdit(var1);
        setText("$"+var1.toString());
    }

//        if(item == null || empty) {
//            setText(null);
//            return;
//        }
//
//        if(!isEmpty()){
//
//            if(item.equals("error")){
//
//                this.setTextFill(Color.RED);
//                setText("$"+ String.format("%,.2f",item));
//
//            }else{
//                this.setTextFill(Color.BLACK);
//                setText(item);
//
//            }
//
//        }



}
