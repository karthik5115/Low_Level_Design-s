import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;

class ElevatorCarController {
    ElevatorCar elevatorCar;
    Queue<Integer> upQueue;
    Queue<Integer> downQueue;
    Queue<Integer> pendingQueueUP;
    Queue<Integer> pendingQueueDown;

    ElevatorCarController(ElevatorCar elevatorCar) {
        this.elevatorCar = elevatorCar;
        this.upQueue = new PriorityQueue<>();
        this.downQueue = new PriorityQueue<>((a, b) -> b - a);
        this.pendingQueueUP = new LinkedList<>();
        this.pendingQueueDown = new LinkedList<>();
    }

    public void acceptNewRequest(int floor, Direction direction) {
        Direction elevatorDirection = elevatorCar.getDirection();
        if (elevatorDirection == Direction.IDLE) {
            if (floor > elevatorCar.getCurrentFloor()) {
                elevatorCar.setDirection(Direction.UP);
                upQueue.offer(floor);
            } else if (floor < elevatorCar.getCurrentFloor()) {
                elevatorCar.setDirection(Direction.DOWN);
                downQueue.offer(floor);
            } else {
                elevatorCar.getDoors().openDoors();
                elevatorCar.getDoors().closeDoors();
            }
        } else if (elevatorDirection == Direction.UP) {
            if (floor >= elevatorCar.getCurrentFloor() && direction == Direction.UP) {
                upQueue.offer(floor);
            } else if (direction == Direction.DOWN) {
                pendingQueueDown.offer(floor);
            } else {
                pendingQueueUP.offer(floor);
            }
        } else if (elevatorDirection == Direction.DOWN) {
            if (floor <= elevatorCar.getCurrentFloor() && direction == Direction.DOWN) {
                downQueue.offer(floor);
            } else if (direction == Direction.UP) {
                pendingQueueUP.offer(floor);
            } else {
                pendingQueueDown.offer(floor);
            }
        }
    }

    public void processInternalRequest(int floor) {
        if (elevatorCar.getCurrentFloor() > floor) {
            acceptNewRequest(floor, Direction.DOWN);
        } else if (elevatorCar.getCurrentFloor() < floor) {
            acceptNewRequest(floor, Direction.UP);
        } else {
            System.out.println("You are at " + floor + " floor already. click another floor or get down");
        }
    }

    public void move() {
        while (!upQueue.isEmpty() || !downQueue.isEmpty() || !pendingQueueUP.isEmpty() || !pendingQueueDown.isEmpty()) {
            if (elevatorCar.getDirection() == Direction.UP) {
                while (!upQueue.isEmpty()) {
                    int floor = upQueue.poll();
                    elevatorCar.getDisplay().showFloor(floor);
                    elevatorCar.getDisplay().showDirection(Direction.UP);
                    elevatorCar.setCurrentFloor(floor);
                    elevatorCar.getDoors().openDoors();
                    elevatorCar.getDoors().closeDoors();
                }

                while (!pendingQueueDown.isEmpty()) {
                    downQueue.offer(pendingQueueDown.poll());
                }

                if (!downQueue.isEmpty()) {
                    elevatorCar.setDirection(Direction.DOWN);
                } else if (!pendingQueueUP.isEmpty()) {
                    while (!pendingQueueUP.isEmpty()) {
                        upQueue.offer(pendingQueueUP.poll());
                    }
                    elevatorCar.setDirection(Direction.UP);
                } else {
                    elevatorCar.setDirection(Direction.IDLE);
                }

            } else if (elevatorCar.getDirection() == Direction.DOWN) {
                while (!downQueue.isEmpty()) {
                    int floor = downQueue.poll();
                    elevatorCar.getDisplay().showFloor(floor);
                    elevatorCar.getDisplay().showDirection(Direction.DOWN);
                    elevatorCar.setCurrentFloor(floor);
                    elevatorCar.getDoors().openDoors();
                    elevatorCar.getDoors().closeDoors();
                }

                while (!pendingQueueUP.isEmpty()) {
                    upQueue.offer(pendingQueueUP.poll());
                }

                if (!upQueue.isEmpty()) {
                    elevatorCar.setDirection(Direction.UP);
                } else if (!pendingQueueDown.isEmpty()) {
                    while (!pendingQueueDown.isEmpty()) {
                        downQueue.offer(pendingQueueDown.poll());
                    }
                    elevatorCar.setDirection(Direction.DOWN);
                } else {
                    elevatorCar.setDirection(Direction.IDLE);
                }

            } else {
                if (!upQueue.isEmpty()) {
                    elevatorCar.setDirection(Direction.UP);
                } else if (!downQueue.isEmpty()) {
                    elevatorCar.setDirection(Direction.DOWN);
                } else if (!pendingQueueUP.isEmpty()) {
                    while (!pendingQueueUP.isEmpty()) {
                        upQueue.offer(pendingQueueUP.poll());
                    }
                    elevatorCar.setDirection(Direction.UP);
                } else if (!pendingQueueDown.isEmpty()) {
                    while (!pendingQueueDown.isEmpty()) {
                        downQueue.offer(pendingQueueDown.poll());
                    }
                    elevatorCar.setDirection(Direction.DOWN);
                } else {
                    break;
                }
            }
        }

        elevatorCar.setDirection(Direction.IDLE);
    }
}