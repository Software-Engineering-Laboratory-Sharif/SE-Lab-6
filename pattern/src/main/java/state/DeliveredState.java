package state;

public class DeliveredState implements State{

    @Override
    public void printStatus() {
        System.out.print("package is delivered");
    }
}
