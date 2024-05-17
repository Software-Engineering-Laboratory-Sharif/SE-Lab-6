import state.State;
import strategy.TransitionStrategy;

public class Shipment {
    private State state;
    private TransitionStrategy transitionStrategy;
    private final float weight;

    Shipment (float weight){
        this.weight = weight;
    }

    public void setTransitionStrategy(TransitionStrategy transitionStrategy){
        this.transitionStrategy = transitionStrategy;
    }

    public double executeTransition(){
        return this.transitionStrategy.getPrice(this.weight);
    }

    public void setState(State state){
        this.state = state;
    }

    public String getState(){
        return this.state.getStatus();
    }

}
