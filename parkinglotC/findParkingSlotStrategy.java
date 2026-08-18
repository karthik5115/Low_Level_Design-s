import java.util.List;

interface findParkingSlotStrategy {
    ParkingSlot getSlot(List<ParkingSlot> slots);
}

class getNearestSlot implements findParkingSlotStrategy {
    public ParkingSlot getSlot(List<ParkingSlot> slots) {
        for (ParkingSlot slot : slots) {
            if (slot.isAvailable()) {
                return slot;
            }
        }
        return null;
    }
}

class getNearestSlotToExit implements findParkingSlotStrategy {
    public ParkingSlot getSlot(List<ParkingSlot> slots) {
        for (int i = slots.size() - 1; i >= 0; i--) {
            if (slots.get(i).isAvailable()) {
                return slots.get(i);
            }
        }
        return null;
    }
}

class getNearestSlotToElevator implements findParkingSlotStrategy {
    public ParkingSlot getSlot(List<ParkingSlot> slots) {
        int left = slots.size() / 2, right = slots.size() / 2 + 1;
        while (left >= 0 && right <= slots.size() - 1) {
            if (slots.get(left).isAvailable()) {
                return slots.get(left);
            }
            if (slots.get(right).isAvailable()) {
                return slots.get(right);
            }
            left--;
            right++;
        }
        return null;
    }
}