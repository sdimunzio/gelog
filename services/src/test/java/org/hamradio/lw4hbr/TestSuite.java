package org.hamradio.lw4hbr;

import junit.framework.Test;
import junit.framework.TestCase;
import org.hamradio.lw4hbr.cty.CountryFinder;

/**
 * Unit test for simple App.
 */
public class TestSuite
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestSuite(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new junit.framework.TestSuite(TestSuite.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    public void testCtyCountryFinder() {
        assertTrue(CountryFinder.getCountryFromCall("lw4hr").getName().equalsIgnoreCase("Argentina") );
        assertFalse(CountryFinder.getCountryFromCall("ly4hr").getName().equalsIgnoreCase("Argentina"));
    }
}
