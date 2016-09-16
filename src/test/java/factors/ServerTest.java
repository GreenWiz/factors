package factors;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ServerTest {

    @Test
    public void testFactors() throws Exception {
        Object[] actual = Server.factor(4).toArray();
        Long[] exp = new Long[]{2L, 2L};
        assertArrayEquals(exp, actual);

        actual = Server.factor(1024).toArray();
        exp = new Long[]{2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L};
        assertArrayEquals(exp, actual);

        actual = Server.factor(1111).toArray();
        exp = new Long[]{11L, 101L};
        assertArrayEquals(exp, actual);
    }
}