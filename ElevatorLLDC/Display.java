class Display {
    public void showFloor(int floor) {
        System.out.println(floor);
    }

    public void showDirection(Direction direction) {
        System.out.println(direction);
    }
}

enum Direction {
    UP,
    DOWN,
    IDLE
}