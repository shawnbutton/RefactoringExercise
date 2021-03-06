package com.valuablecode.transform.valueCleaner;


public class RangeValueCleaner extends NonTextValueCleaner {
    @Override
    public String cleanValue(String value) {

        if (IGNORE_VALUES.contains(value)) {
            return null;
        }

        value = value.trim();

        return performExtraCharacterCleaning(value);

    }

}
