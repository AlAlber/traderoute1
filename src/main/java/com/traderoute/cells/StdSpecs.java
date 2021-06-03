package com.traderoute.cells;

import java.math.BigDecimal;

public enum StdSpecs {
    INTPOS5X(new CellSpecsBuilder().pre("").post("").defaultValue(0).minValue(0).maxValue(10000).build()),
    INTPOS5$(new CellSpecsBuilder().pre("$").post("").defaultValue(0).minValue(0).maxValue(10000).build()),
    INTPOS6$(new CellSpecsBuilder().pre("$").post("").defaultValue(0).minValue(0).maxValue(100000).build()),
    DECPOS5$(new CellSpecsBuilder().pre("$").post("").defaultValue(new BigDecimal("0.0"))
            .minValue(new BigDecimal("0.0")).maxValue(new BigDecimal("10000")).build()),
    DECPOS6$(new CellSpecsBuilder().pre("$").post("").defaultValue(new BigDecimal("0.0"))
            .minValue(new BigDecimal("0.0")).maxValue(new BigDecimal("100000")).build()),
    PERCENT(new CellSpecsBuilder().pre("$").post("").defaultValue(new BigDecimal("0.0"))
            .minValue(new BigDecimal("0.0")).maxValue(new BigDecimal("99")).build());


    private CellSpecs specs;

//    private CrunchifyEnumCompany(String s) {
//        companyLetter = s;
//    }

    private StdSpecs(CellSpecs specifications) {
        specs = specifications;
    }

    public CellSpecs getSpecs() {
        return specs;
    }
}
