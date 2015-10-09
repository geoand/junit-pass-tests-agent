package geoand.junit.sample;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by gandrianakis on 8/10/2015.
 */
public class SampleTest {

    @Test
    public void assertionFailure() {
        assertEquals("Long values do not match", 1L, 2L);
    }

    @Test
    public void throwsException() {
        throw new RuntimeException("dummy");
    }

    @Test(expected = RuntimeException.class)
    public void expectsException() {

    }
}
