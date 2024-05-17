package state;

public class DeliveredState implements State{

    @Override
    public String getStatus() {
         return "delivered";
    }
}
