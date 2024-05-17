import org.junit.jupiter.api.Test;
import state.DeliveredState;
import state.State;
import state.TransitState;
import strategy.ExpressTransitionStrategy;
import strategy.StandardTransitionStrategy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipmentTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Test
    void testTransitState() {
        System.setOut(new PrintStream(outContent));
        State state = new TransitState();
        state.printStatus();
        assertEquals("package is in transit state", outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void testDeliveredState() {
        System.setOut(new PrintStream(outContent));
        State state = new DeliveredState();
        state.printStatus();
        assertEquals("package is delivered", outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void expressTransitionPriceTest() {
        ExpressTransitionStrategy strategy = new ExpressTransitionStrategy();
        assertEquals(3500, strategy.getPrice(1000));
    }

    @Test
    void setTransitionStrategy() {
        Shipment shipment = new Shipment(4);
        shipment.setTransitionStrategy(new StandardTransitionStrategy());
        assertEquals(10, shipment.executeTransition());
    }

    @Test
    void setState() {
        System.setOut(new PrintStream(outContent));
        Shipment shipment = new Shipment(100);
        shipment.setState(new DeliveredState());
        shipment.getState();
        assertEquals("package is delivered", outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    void standardTransitionPriceTest() {
        StandardTransitionStrategy strategy = new StandardTransitionStrategy();
        assertEquals(100, strategy.getPrice(40));
    }
}
