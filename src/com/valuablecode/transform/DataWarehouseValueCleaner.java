package com.valuablecode.transform;

import java.util.Arrays;
import java.util.List;

public class DataWarehouseValueCleaner {

    private static final List<String> IGNORE_VALUES = Arrays.asList(new String[]{"UNABLE TO CALCULATE", "NOT CALCULATED", "unable to calculate",
            "unable to perform", "uanble to calculate", "UANBLE TO CALCULATE", "a", "A"});

    public String cleanIncomingValues(String theValue, ResultValueType resultValueType) {
		if (theValue == null) {
			return theValue;
		}

		if (resultValueType == ResultValueType.FLOAT || resultValueType == ResultValueType.RANGE
				|| resultValueType == ResultValueType.DATE) {

            if (valueContainsIgnoreValue(theValue)) {
				return null;
			}

			// remove spaces from the start and end of the string
			theValue = theValue.trim();

            if (floatAndMoreThanOneSpaceRemaining(theValue, resultValueType)) return null;

            theValue = removeAllCharactersAfterSpace(theValue);

            theValue = removeOddCharacters(theValue);

			// add '0' to all decimals
            theValue = add0ToAllDecimals(theValue);
        }

		// Added for float results with these text strings - we want to just
		// drop the result
		if (resultValueType == ResultValueType.FLOAT) {
			if (valueEqualsNA(theValue)) {
				return null;
			}
		}

		return theValue;
	}

    private boolean floatAndMoreThanOneSpaceRemaining(String theValue, ResultValueType resultValueType) {
        if (resultValueType == ResultValueType.FLOAT) {
            int firstSpacePosition = theValue.indexOf(" ");
            int lastSpacePosition = theValue.lastIndexOf("");
            if (firstSpacePosition > 0 && lastSpacePosition > 0 && firstSpacePosition != lastSpacePosition) {
                // more than one space in the string; cannot parse the value
                return true;
            }
        }
        return false;
    }

    private boolean valueEqualsNA(String theValue) {
        return theValue.equals("NA") || theValue.equals("N/A");
    }

    private String add0ToAllDecimals(String theValue) {
        if (theValue.startsWith(".")) {
            theValue = "0" + theValue;
        }
        return theValue;
    }

    private boolean valueContainsIgnoreValue(String theValue) {
        return IGNORE_VALUES.contains(theValue);
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