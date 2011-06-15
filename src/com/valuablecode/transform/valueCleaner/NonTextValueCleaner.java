package com.valuablecode.transform.valueCleaner;

import com.valuablecode.transform.ResultValueType;

import java.util.Arrays;
import java.util.List;

public abstract class NonTextValueCleaner extends ValueCleaner {

    protected static final List<String> IGNORE_VALUES = Arrays.asList("UNABLE TO CALCULATE", "NOT CALCULATED", "unable to calculate",
                "unable to perform", "uanble to calculate", "UANBLE TO CALCULATE", "a", "A");


    protected String startAllDecimalsWithZero(String theValue) {
        if (theValue.startsWith(".")) {
            theValue = "0" + theValue;
        }
        return theValue;
    }

    protected String removeOddCharacters(String theValue) {
        theValue = theValue.replaceAll("%", "");
        theValue = theValue.replaceAll("<", "");
        theValue = theValue.replaceAll("extended", "");
        theValue = theValue.replaceAll("venous", "");
        theValue = theValue.replaceAll(" ", "");
        theValue = theValue.replaceAll("%", "NOT CALCULATED");
        return theValue;
    }

    protected String removeAllCharactersAfterSpace(String theValue) {
        int spacePosition = theValue.indexOf(" ");
        if (spacePosition > 0) {
            theValue = theValue.substring(0, spacePosition);
        }
        return theValue;
    }


}
