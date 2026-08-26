import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestElevator {
    public static void main(String[] args) {
        ElevatorCar car1 = new ElevatorCar();
        car1.setCarId(1);
        car1.setCurrentFloor(0);
        car1.setDirection(Direction.IDLE);
        car1.setDisplay(new Display());
        car1.setDoors(new Doors());

        ElevatorCarController controller1 = new ElevatorCarController(car1);
        InternalButtonDispatcher ibDispatcher1 = new InternalButtonDispatcher(controller1);
        InternalButtons internalButtons1 = new InternalButtons(0, ibDispatcher1);
        car1.setInternalbuttons(internalButtons1);

        ElevatorCar car2 = new ElevatorCar();
        car2.setCarId(2);
        car2.setCurrentFloor(0);
        car2.setDirection(Direction.IDLE);
        car2.setDisplay(new Display());
        car2.setDoors(new Doors());

        ElevatorCarController controller2 = new ElevatorCarController(car2);
        InternalButtonDispatcher ibDispatcher2 = new InternalButtonDispatcher(controller2);
        InternalButtons internalButtons2 = new InternalButtons(0, ibDispatcher2);
        car2.setInternalbuttons(internalButtons2);

        List<ElevatorCarController> controllers = Arrays.asList(controller1, controller2);
        ExternalButtonDispatcher dispatcher = new ExternalButtonDispatcherEven(controllers);
        ExternalButtons externalButtons = new ExternalButtons(dispatcher);

        List<floors> buildingFloors = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            buildingFloors.add(new floors(i, externalButtons));
        }

        System.out.println("=== Test Case 1: External Button Requests (Even/Odd Dispatching) ===");
        buildingFloors.get(0).pressUp();
        buildingFloors.get(2).pressUp();
        buildingFloors.get(1).pressUp();
        buildingFloors.get(3).pressDown();

        System.out.println("\n--- Moving Elevator 1 (Odd Floors) ---");
        controller1.move();
        System.out.println("Elevator 1 Final Floor: " + car1.getCurrentFloor() + ", Direction: " + car1.getDirection());

        System.out.println("\n--- Moving Elevator 2 (Even Floors) ---");
        controller2.move();
        System.out.println("Elevator 2 Final Floor: " + car2.getCurrentFloor() + ", Direction: " + car2.getDirection());

        System.out.println("\n=== Test Case 2: Internal Button Requests ===");
        car1.getInternalbuttons().pressButton(5);
        car1.getInternalbuttons().pressButton(1);
        System.out.println("\n--- Moving Elevator 1 after Internal Requests ---");
        controller1.move();
        System.out.println("Elevator 1 Final Floor: " + car1.getCurrentFloor() + ", Direction: " + car1.getDirection());

        System.out.println("\n=== Test Case 3: Mixed Bi-directional LOOK Algorithm ===");
        buildingFloors.get(1).pressUp();
        buildingFloors.get(3).pressDown();
        car2.getInternalbuttons().pressButton(0);
        System.out.println("\n--- Moving Elevator 2 with Bi-directional Requests ---");
        controller2.move();
        System.out.println("Elevator 2 Final Floor: " + car2.getCurrentFloor() + ", Direction: " + car2.getDirection());
    }
}
