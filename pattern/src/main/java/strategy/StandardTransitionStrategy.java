package strategy;

public class StandardTransitionStrategy implements TransitionStrategy{
    @Override
    public double getPrice(float weight) {
        return 2.5 * weight;
    }
}
