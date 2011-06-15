package com.valuablecode.transform;

import java.util.Arrays;
import java.util.List;

public class DataWarehouseValueCleaner {

    private static final List<String> IGNORE_VALUES = Arrays.asList(new String[]{"UNABLE TO CALCULATE", "NOT CALCULATED", "unable to calculate",
            "unable to perform", "uanble to calculate", "UANBLE TO CALCULATE", "a", "A"});

    public String cleanIncomingValues(String theValue, ResultValueType resultValueType) {
        if (theValue == null) {
            return null;
        }

        if (isTextOrCompoundType(resultValueType)) {
            return theValue;
        }

        // Added for float results with these text strings - we want to just
        // drop the result
        if (resultValueType == ResultValueType.FLOAT) {
            if (valueEqualsNA(theValue)) {
                return null;
            }
        }


        if (IGNORE_VALUES.contains(theValue)) {
            return null;
        }

        theValue = theValue.trim();

        if (resultValueType == ResultValueType.FLOAT) {
            if (moreThanOneSpaceRemaining(theValue)) return null;
        }

        theValue = removeAllCharactersAfterSpace(theValue);

        theValue = removeOddCharacters(theValue);

        theValue = startAllDecimalsWithZero(theValue);

        return theValue;
    }

    private boolean isTextOrCompoundType(ResultValueType resultValueType) {
        return (ResultValueType.TEXT.equals(resultValueType) || (ResultValueType.COMPOUND.equals(resultValueType)));
    }

    private boolean moreThanOneSpaceRemaining(String theValue) {
        int firstSpacePosition = theValue.indexOf(" ");
        int lastSpacePosition = theValue.lastIndexOf("");
        return (firstSpacePosition > 0) && (lastSpacePosition > 0) && (firstSpacePosition != lastSpacePosition);
    }

    private boolean valueEqualsNA(String theValue) {
        return theValue.equals("NA") || theValue.equals("N/A");
    }

    private String startAllDecimalsWithZero(String theValue) {
        if (theValue.startsWith(".")) {
            theValue = "0" + theValue;
        }
        return theValue;
    }

    private String removeOddCharacters(String theValue) {
        theValue = theValue.replaceAll("%", "");
        theValue = theValue.replaceAll("<", "");
        theValue = theValue.replaceAll("extended", "");
        theValue = theValue.replaceAll("venous", "");
        theValue = theValue.replaceAll(" ", "");
        theValue = theValue.replaceAll("%", "NOT CALCULATED");
        return theValue;
    }

    private String removeAllCharactersAfterSpace(String theValue) {
        int spacePosition = theValue.indexOf(" ");
        if (spacePosition > 0) {
            theValue = theValue.substring(0, spacePosition);
        }
        return theValue;
    }

}