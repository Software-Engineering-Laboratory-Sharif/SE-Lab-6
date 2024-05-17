package strategy;

public class ExpressTransitionStrategy implements TransitionStrategy{
    @Override
    public double getPrice(float weight) {
        return 3.5 * weight;
    }
}
