public class floors {
    ExternalButtons buttons;
    int floor;

    floors(int floor, ExternalButtons buttons) {
        this.floor = floor;
        this.buttons = buttons;
    }

    void pressUp() {
        buttons.pressUP(floor);
    }

    void pressDown() {
        buttons.pressDown(floor);
    }
}
