package com.valuablecode.transform;

import com.valuablecode.transform.valueCleaner.ValueCleaner;
import com.valuablecode.transform.valueCleaner.ValueCleanerFactory;

import java.util.Arrays;
import java.util.List;

public class DataWarehouseValueCleaner {

    public String cleanIncomingValues(String theValue, ResultValueType resultValueType) {

        if (theValue == null) {
            return null;
        }

        final ValueCleaner cleaner = ValueCleanerFactory.createCleanerFromValueType(resultValueType);
        return cleaner.cleanValue(theValue);

    }



}