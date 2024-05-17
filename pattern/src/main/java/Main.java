import state.DeliveredState;
import state.TransitState;
import strategy.ExpressTransitionStrategy;
import strategy.StandardTransitionStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter the weight of the shipment: ");
        float weight = scanner.nextFloat();
        Shipment shipment = new Shipment(weight);

        while (true) {
            System.out.print(
                    "\nChoose an option to modify: \n1. Change State\n2. Change Transition\nEnter your choice: ");
            int option = scanner.nextInt();

            if (option == 1) {
                System.out.print("Select the new state: \n1. In-transit\n2. Delivered\nEnter your choice: ");
                int stateOption = scanner.nextInt();
                if (stateOption == 1) {
                    shipment.setState(new TransitState());
                    System.out.println("The shipment is now in-transit.");
                } else if (stateOption == 2) {
                    shipment.setState(new DeliveredState());
                    System.out.println("The shipment has been delivered.");
                    break;
                } else {
                    System.out.println("Invalid state choice. Please try again.");
                    continue;
                }
                System.out.println("Current state: " + shipment.getState());
            } else if (option == 2) {
                System.out.print("Select the transition type: \n1. Standard\n2. Express\nEnter your choice: ");
                int transitionOption = scanner.nextInt();
                if (transitionOption == 1) {
                    shipment.setTransitionStrategy(new StandardTransitionStrategy());
                    System.out.println("The shipment is now using standard transition.");
                } else if (transitionOption == 2) {
                    shipment.setTransitionStrategy(new ExpressTransitionStrategy());
                    System.out.println("The shipment is now using express transition.");
                } else {
                    System.out.println("Invalid transition choice. Please try again.");
                    continue;
                }
                System.out.println("Transition type changed. The new cost is $" + shipment.executeTransition());
            } else {
                System.out.println("Invalid option. Please enter either 1 or 2.");
            }
        }
    }
}
