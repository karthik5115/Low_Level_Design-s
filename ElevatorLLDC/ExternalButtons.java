import java.util.List;

public class ExternalButtons {
    ExternalButtonDispatcher exbDispatcher;

    ExternalButtons(ExternalButtonDispatcher exbDispatcher) {
        this.exbDispatcher = exbDispatcher;
    }

    void pressUP(int floor) {
        exbDispatcher.assignRequestToElevator(floor, Direction.UP);
    }

    void pressDown(int floor) {
        exbDispatcher.assignRequestToElevator(floor, Direction.DOWN);
    }
}

class ExternalButtonDispatcher {
    List<ElevatorCarController> ecControllersList;

    protected ExternalButtonDispatcher(List<ElevatorCarController> ecControllerList) {
        this.ecControllersList = ecControllerList;
    }

    public void assignRequestToElevator(int floor, Direction direction) {
        ecControllersList.get(0).acceptNewRequest(floor, direction);
    }
}

class ExternalButtonDispatcherEven extends ExternalButtonDispatcher {
    ExternalButtonDispatcherEven(List<ElevatorCarController> ecControllerList) {
        super(ecControllerList);
    }

    @Override
    public void assignRequestToElevator(int floor, Direction direction) {
        ElevatorCarController assignedController;
        for (ElevatorCarController ecController : ecControllersList) {
            if (ecController.elevatorCar.getCarId() % 2 == 0 && floor % 2 == 0) {
                ecController.acceptNewRequest(floor, direction);
                break;
            } else if (ecController.elevatorCar.getCarId() % 2 != 0 && floor % 2 != 0) {
                ecController.acceptNewRequest(floor, direction);
                break;
            }
        }
    }
}

class ExternalButtonDispatcherTopbottom extends ExternalButtonDispatcher {
    int mid = 5;
    int[] liftIdTop = { 1, 3, 5 };
    int[] liftIdBot = { 2, 4, 6 };

    ExternalButtonDispatcherTopbottom(List<ElevatorCarController> ecControllerList) {
        super(ecControllerList);
    }

    @Override
    public void assignRequestToElevator(int floor, Direction direction) {
        int[] targetLiftIds = (floor > mid) ? liftIdTop : liftIdBot;

        for (ElevatorCarController ecController : ecControllersList) {
            int carId = ecController.elevatorCar.getCarId();
            if (isEligibleLift(targetLiftIds, carId)) {
                ecController.acceptNewRequest(floor, direction);
                break;
            }
        }
    }

    private boolean isEligibleLift(int[] liftIds, int carId) {
        for (int id : liftIds) {
            if (id == carId) {
                return true;
            }
        }
        return false;
    }
}
