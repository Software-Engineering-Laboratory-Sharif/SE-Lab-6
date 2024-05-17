import org.junit.jupiter.api.Test;
import state.DeliveredState;
import state.State;
import state.TransitState;
import strategy.ExpressTransitionStrategy;
import strategy.StandardTransitionStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShipmentTest {
    @Test
    void testTransitState() {
        State state = new TransitState();
        assertEquals("in transit", state.getStatus());
    }

    @Test
    void testDeliveredState() {
        State state = new DeliveredState();
        assertEquals("delivered", state.getStatus());
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
        Shipment shipment = new Shipment(100);
        shipment.setState(new DeliveredState());
        assertEquals("delivered", shipment.getState());
    }

    @Test
    void standardTransitionPriceTest() {
        StandardTransitionStrategy strategy = new StandardTransitionStrategy();
        assertEquals(100, strategy.getPrice(40));
    }
}
