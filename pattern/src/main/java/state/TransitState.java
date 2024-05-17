package state;

public class TransitState implements State{
    @Override
    public String getStatus() {
        return "in transit";
    }
}
