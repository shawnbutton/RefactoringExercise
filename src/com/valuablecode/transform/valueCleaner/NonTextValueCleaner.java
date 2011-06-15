package com.valuablecode.transform.valueCleaner;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class NonTextValueCleaner extends ValueCleaner {

    protected static final List<String> IGNORE_VALUES = Arrays.asList("UNABLE TO CALCULATE", "NOT CALCULATED", "unable to calculate",
                "unable to perform", "uanble to calculate", "UANBLE TO CALCULATE", "a", "A");


    protected String startAllDecimalsWithZero(String theValue) {
        if (theValue.startsWith(".")) {
            theValue = "0" + theValue;
        }
        return theValue;
    }

    protected String removeCharactersThatAreToBeIgnored(String theValue) {

        Pattern pattern = Pattern.compile("%|<|extended|venous| ");
        Matcher matcher = pattern.matcher(theValue);

        return matcher.replaceAll("");

    }

    protected String removeAllCharactersAfterSpace(String theValue) {
        int spacePosition = theValue.indexOf(" ");
        if (spacePosition > 0) {
            theValue = theValue.substring(0, spacePosition);
        }
        return theValue;
    }


    protected String performExtraCharacterCleaning(String value) {
        String trimmedValue = removeAllCharactersAfterSpace(value);

        String cleanedValue = removeCharactersThatAreToBeIgnored(trimmedValue);

        String addedZeroValue = startAllDecimalsWithZero(cleanedValue);

        return addedZeroValue;
    }
}
