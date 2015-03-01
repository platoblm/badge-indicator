package net.badgeindicator;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;

public class UnitTestRunner extends RobolectricTestRunner {
    public UnitTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }
}
