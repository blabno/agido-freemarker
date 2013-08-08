import org.junit.Test;
import pl.itcrowd.agido.server.domain.Numbering;

import static junit.framework.Assert.assertEquals;

public class NumberingTest {

    @Test
    public void test() {
        final Numbering numbering = new Numbering();
        assertEquals("1",numbering.toString());
        numbering.increment();
        assertEquals("2",numbering.toString());
        numbering.increment();
        assertEquals("3",numbering.toString());
        numbering.levelDown();
        assertEquals("3.1",numbering.toString());
        numbering.increment();
        assertEquals("3.2",numbering.toString());
    }
}
