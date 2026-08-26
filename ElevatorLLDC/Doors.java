public class Doors {
    private boolean isOpen;

    public void openDoors() {
        System.out.println("Doors  opened..");
        this.isOpen = false;
    }

    public void closeDoors() {
        System.out.println("Doors closed..");
        this.isOpen = false;
    }
}
