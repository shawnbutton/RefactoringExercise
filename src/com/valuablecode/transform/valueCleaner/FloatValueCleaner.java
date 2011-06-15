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

        value = value.trim();

        if (moreThanOneSpaceRemaining(value)) return null;

        value = removeAllCharactersAfterSpace(value);

        value = removeOddCharacters(value);

        value = startAllDecimalsWithZero(value);

        return value;

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
