class ElevatorCar {
    private int carId;
    private Display display;
    private int currentFloor;
    private Direction direction;
    private InternalButtons internalbuttons;
    private Doors doors;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public InternalButtons getInternalbuttons() {
        return internalbuttons;
    }

    public void setInternalbuttons(InternalButtons internalbuttons) {
        this.internalbuttons = internalbuttons;
    }

    public Doors getDoors() {
        return doors;
    }

    public void setDoors(Doors doors) {
        this.doors = doors;
    }
}
