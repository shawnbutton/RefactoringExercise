package com.valuablecode.transform.valueCleaner;

import com.valuablecode.transform.ResultValueType;

public class ValueCleanerFactory {
    public static ValueCleaner createCleanerFromValueType(ResultValueType valueType) {


        switch (valueType) {
           case COMPOUND:
               return new CompoundCleaner();

            case FLOAT:
                return new FloatValueCleaner();

           case RANGE:
               return new RangeValueCleaner();

           case TEXT:
               return new TextValueCleaner();

            case DATE:
                return new DateValueCleaner();

           }


        return new CompoundCleaner();


    }
}
