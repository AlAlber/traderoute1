///*
// * Copyright 2017 Jorge Campins y David Uzcategui
// *
// * Este archivo forma parte de Adalid.
// *
// * Adalid es software libre; usted puede redistribuirlo y/o modificarlo bajo los terminos de la
// * licencia "GNU General Public License" publicada por la Fundacion "Free Software Foundation".
// * Adalid se distribuye con la esperanza de que pueda ser util, pero SIN NINGUNA GARANTIA; sin
// * siquiera las garantias implicitas de COMERCIALIZACION e IDONEIDAD PARA UN PROPOSITO PARTICULAR.
// *
// * Para mas detalles vea la licencia "GNU General Public License" en http://www.gnu.org/licenses
// */
//package com.traderoute;
//
//import adalid.core.annotations.BaseField;
//import adalid.core.annotations.BigDecimalField;
//import adalid.core.annotations.CastingField;
//import adalid.core.annotations.ColumnField;
//import adalid.core.annotations.NumericDataGen;
//import adalid.core.annotations.NumericField;
//import adalid.core.annotations.PropertyAggregation;
//import adalid.core.annotations.PropertyField;
//import adalid.core.annotations.UniqueKey;
//import adalid.core.data.types.BigDecimalData;
//import adalid.core.interfaces.Property;
//import java.lang.annotation.Annotation;
//import java.util.List;
//
///**
// * @author Jorge Campins
// */
//public class BigDecimalProperty extends BigDecimalData implements Property {
//
//    @Override
//    protected List getValidFieldAnnotations() {
//        List valid = super.getValidFieldAnnotations();
//        valid.add(BaseField.class);
//        valid.add(BigDecimalField.class);
//        valid.add(CastingField.class);
//        valid.add(ColumnField.class);
//        valid.add(NumericDataGen.class);
//        valid.add(NumericField.class);
//        valid.add(PropertyField.class);
//        valid.add(PropertyAggregation.class);
//        valid.add(UniqueKey.class);
//        return valid;
//    }
//
//}