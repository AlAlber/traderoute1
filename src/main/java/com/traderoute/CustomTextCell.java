package com.traderoute;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CustomTextCell<Object, String> extends TextFieldTableCell<Object , String> {

    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText("Hello");
        }
        if (item == null) {
            setText("");
        }
        else {
            if (item.toString().equals("Promotional Commentary")){
                setPrefHeight(40);
            }
            setText(item.toString());
        }
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
