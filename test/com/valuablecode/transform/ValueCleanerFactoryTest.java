package com.valuablecode.transform;

import com.valuablecode.transform.valueCleaner.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class ValueCleanerFactoryTest {


    private Class<ValueCleaner> expectedClassType;
    private ResultValueType valueType;

    public ValueCleanerFactoryTest(ResultValueType valueType, Class<ValueCleaner> expectedClassType) {
        this.valueType = valueType;
        this.expectedClassType = expectedClassType;

    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return asList(new Object[][]{
                {ResultValueType.COMPOUND, CompoundCleaner.class},
                {ResultValueType.FLOAT, FloatValueCleaner.class},
                {ResultValueType.RANGE, RangeValueCleaner.class},
                {ResultValueType.DATE, DateValueCleaner.class},
                {ResultValueType.TEXT, TextValueCleaner.class},

    }

    );
}


    @Test
    public void should_return_correct_cleaner_type() {


        assertCorrectCleanerTypeCreated(this.valueType, this.expectedClassType);


    }

    private void assertCorrectCleanerTypeCreated(ResultValueType valueType, Class<ValueCleaner> expectedClassType) {
        ValueCleaner cleaner = ValueCleanerFactory.createCleanerFromValueType(valueType);

        assertThat(cleaner, instanceOf(expectedClassType));
    }


}
