import java.util.List;

public class InternalButtons {
    InternalButtonDispatcher ibDispatcher;
    int floor;

    InternalButtons(int floor, InternalButtonDispatcher ibDispatcher) {
        this.floor = floor;
        this.ibDispatcher = ibDispatcher;
    }

    public void pressButton(int floor) {
        ibDispatcher.submitRequest(floor);
    }
}

class InternalButtonDispatcher {
    ElevatorCarController ecCarController;

    InternalButtonDispatcher(ElevatorCarController ecCarController) {
        this.ecCarController = ecCarController;
    }

    public void submitRequest(int floor) {
        ecCarController.processInternalRequest(floor);
    }
}