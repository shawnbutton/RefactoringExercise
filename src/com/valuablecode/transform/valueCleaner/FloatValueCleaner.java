package com.valuablecode.transform.valueCleaner;

public class FloatValueCleaner extends NonTextValueCleaner {


    @Override
    public String cleanValue(String value) {

        if (valueEqualsNA(value)) {
            return null;
        }

        if (IGNORE_VALUES.contains(value)) {
            return null;
        }

        final String trimmedValue = value.trim();

        if (moreThanOneSpaceRemaining(trimmedValue)) return null;

        return performExtraCharacterCleaning(trimmedValue);


    }

    private boolean valueEqualsNA(String theValue) {
        return theValue.equals("NA") || theValue.equals("N/A");
    }

    private boolean moreThanOneSpaceRemaining(String theValue) {
        int firstSpacePosition = theValue.indexOf(" ");
        int lastSpacePosition = theValue.lastIndexOf("");
        return (firstSpacePosition > 0) && (lastSpacePosition > 0) && (firstSpacePosition != lastSpacePosition);
    }


}
