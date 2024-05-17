package state;

public class TransitState implements State{
    @Override
    public void printStatus() {
        System.out.print("package is in transit state");
    }
}
