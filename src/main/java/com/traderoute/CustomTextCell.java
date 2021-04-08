package com.traderoute;

import javafx.scene.control.TableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CustomTextCell<Object, String> extends TableCell<Object , String> {


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
            if (getTableRow().getIndex()==0 && getTableRow().getItem() instanceof ProductClassReport){
                setStyle("-fx-background-color: rgb(105,105,105,0.5)");
            }

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
