import java.sql.Date;
import java.util.List;

public class Helper {

    /**
     * Helper method to create a reservation
     */
    public static Reservation createReservation(Store store, User user, Vehicle vehicle, Date pickupDate, Date returnDate) {
        if (vehicle.getStatus() != Status.active) {
            System.out.println("❌ Vehicle " + vehicle.getRegistrationNo() + " is not available for reservation.");
            return null;
        }

        Date reservationDate = new Date(System.currentTimeMillis());
        Reservation reservation = new Reservation(reservationDate, pickupDate, returnDate, store, user, vehicle);

        // Add reservation to store and user
        store.addReservation(reservation);
        user.addReservation(reservation);

        System.out.println("\n----------------- RESERVATION CREATED -----------------");
        System.out.println("Reservation ID   : " + reservation.getReservationId());
        System.out.println("User             : " + user.getUsername() + " (ID: " + user.getUserId() + ")");
        System.out.println("Vehicle          : " + vehicle.getName() + " (" + vehicle.getModel() + ") [" + vehicle.getRegistrationNo() + "]");
        System.out.println("Store Location   : " + store.getLocation().getCity());
        System.out.println("Pickup Date      : " + pickupDate);
        System.out.println("Return Date      : " + returnDate);
        System.out.println("Daily Rate       : ₹" + vehicle.getDailyRate());
        System.out.println("Total Amount     : ₹" + reservation.getTotalAmount());
        System.out.println("Booking Status   : " + reservation.getRstatus());
        System.out.println("-------------------------------------------------------");

        return reservation;
    }

    /**
     * Helper method to process payment
     */
    public static Payment processPayment(Reservation reservation, PaymentMode paymentMode) {
        if (reservation == null) {
            System.out.println("❌ Cannot process payment: Reservation is null.");
            return null;
        }

        System.out.println("\n💳 Processing Payment of ₹" + reservation.getTotalAmount() + " via " + paymentMode.getClass().getSimpleName() + "...");
        PaymentStatus status = paymentMode.paybill(reservation.getTotalAmount());

        Date paymentDate = new Date(System.currentTimeMillis());
        Payment payment = new Payment(paymentMode, paymentDate, status, reservation.getTotalAmount());
        reservation.setPayment(payment);

        if (status == PaymentStatus.Paid) {
            reservation.setRstatus(ReservationStatus.Completed);
            // Mark vehicle as inactive in store inventory
            reservation.getStore().updateVehicleStatus(reservation.getVehicle().getVehicleId(), Status.inactive);

            System.out.println("✅ Payment Successful! Payment ID: " + payment.getPaymentId());
            System.out.println("🎉 Reservation #" + reservation.getReservationId() + " CONFIRMED for " + reservation.getUser().getUsername());
        } else {
            reservation.setRstatus(ReservationStatus.Cancelled);
            System.out.println("❌ Payment Failed! Reservation #" + reservation.getReservationId() + " CANCELLED.");
        }

        return payment;
    }

    /**
     * Helper method to print reservation receipt
     */
    public static void printReservationReceipt(Reservation reservation) {
        if (reservation == null) return;
        System.out.println("\n========================================================");
        System.out.println("                 RESERVATION RECEIPT                    ");
        System.out.println("========================================================");
        System.out.println(" Reservation ID   : " + reservation.getReservationId());
        System.out.println(" Customer Name    : " + reservation.getUser().getUsername());
        System.out.println(" Mobile / License : " + reservation.getUser().getMobilenumber() + " / " + reservation.getUser().getLicenseid());
        System.out.println(" Store            : Store #" + reservation.getStore().getStoreId() + " (" + reservation.getStore().getLocation().getCity() + ")");
        System.out.println(" Vehicle Booked   : " + reservation.getVehicle().getName() + " - " + reservation.getVehicle().getModel() + " [" + reservation.getVehicle().getRegistrationNo() + "]");
        System.out.println(" Vehicle Type     : " + reservation.getVehicle().getType());
        System.out.println(" Pickup Date      : " + reservation.getPickupDate());
        System.out.println(" Return Date      : " + reservation.getReturnDate());
        System.out.println(" Total Cost       : ₹" + reservation.getTotalAmount());
        System.out.println(" Reservation St.  : " + reservation.getRstatus());
        if (reservation.getPayment() != null) {
            System.out.println(" Payment ID       : " + reservation.getPayment().getPaymentId());
            System.out.println(" Payment Status   : " + reservation.getPayment().getPaymentStatus());
            System.out.println(" Payment Mode     : " + reservation.getPayment().getPmode().getClass().getSimpleName());
        }
        System.out.println("========================================================\n");
    }

    public static void main(String args[]) {
        System.out.println("========================================================");
        System.out.println("           VEHICLE RENTAL SYSTEM INITIALIZATION         ");
        System.out.println("========================================================");

        // 1. Create Bike Vehicles
        Vehicle v1 = new Bike(1, "KA12AB1234", "Dio", "Honda", "2023", "red", 100, Status.active);
        Vehicle v2 = new Bike(2, "KA12AB5678", "Activa", "Honda", "2023", "black", 150, Status.active);
        Vehicle v3 = new Bike(3, "KA12AB9012", "Jupiter", "TVS", "2023", "white", 200, Status.active);
        Vehicle v4 = new Bike(4, "KA12AB3456", "Fascino", "Yamaha", "2023", "blue", 250, Status.active);
        Vehicle v5 = new Bike(5, "KA12AB7890", "Dio", "Honda", "2023", "red", 100, Status.active);

        // 2. Create Car Vehicles
        Vehicle c1 = new Car(6, "KA12CD1111", "Creta", "Hyundai", "2023", "white", 2500, Status.active);
        Vehicle c2 = new Car(7, "KA12CD2222", "City", "Honda", "2023", "black", 2200, Status.active);
        Vehicle c3 = new Car(8, "KA12CD3333", "Thar", "Mahindra", "2023", "red", 3000, Status.active);
        Vehicle c4 = new Car(9, "KA12CD4444", "Swift", "Maruti", "2023", "blue", 1800, Status.active);
        Vehicle c5 = new Car(10, "KA12CD5555", "Nexon", "Tata", "2023", "grey", 2000, Status.active);

        // 3. Create Inventories and add vehicles
        VehicleInventory bikeInventory = new BikeInventory();
        bikeInventory.addVehicle(v1);
        bikeInventory.addVehicle(v2);
        bikeInventory.addVehicle(v3);
        bikeInventory.addVehicle(v4);
        bikeInventory.addVehicle(v5);

        VehicleInventory carInventory = new CarInventory();
        carInventory.addVehicle(c1);
        carInventory.addVehicle(c2);
        carInventory.addVehicle(c3);
        carInventory.addVehicle(c4);
        carInventory.addVehicle(c5);

        // 4. Create Stores
        Store bikeStore = new BikeStore(1, new Location("123 Indiranagar", "Hyderabad", "Telangana", "India", "500012"), bikeInventory);
        Store carStore = new CarStore(2, new Location("456 Hitec City", "Hyderabad", "Telangana", "India", "500081"), carInventory);

        // Associate inventories with stores
        bikeInventory.setStore(bikeStore);
        carInventory.setStore(carStore);

        // 5. Create Users
        User user1 = new User(1, "John Doe", "+91-9876543210", "DL-HYD-001");
        User user2 = new User(2, "Jane Smith", "+91-9876543211", "DL-HYD-002");

        // 6. Create Rental System and register Users and Stores
        RentalSystem rentalSystem = new RentalSystem();
        rentalSystem.addUser(user1);
        rentalSystem.addUser(user2);

        rentalSystem.addStore(bikeStore);
        rentalSystem.addStore(carStore);

        System.out.println("\nSystem setup complete. Stores and users registered.\n");


        // =========================================================================
        // RESERVATION 1: User 1 (John Doe) books a Car from CarStore in Hyderabad
        // =========================================================================
        System.out.println("********************************************************");
        System.out.println("  FLOW 1: User 1 ('John Doe') Books a Car");
        System.out.println("********************************************************");

        // 1. Search for available cars in Car Store
        System.out.println("\nChecking available Cars at CarStore (ID: " + carStore.getStoreId() + "):");
        carStore.printAvailableVehicles();

        // 2. Select Car (Creta - Vehicle ID: 6)
        Vehicle selectedCar = carStore.getVehicleById(6);

        // 3. Define rental dates: 3 days rental (2026-09-01 to 2026-09-04) -> 3 * 2500 = ₹7500
        Date pickupDate1 = Date.valueOf("2026-09-01");
        Date returnDate1 = Date.valueOf("2026-09-04");

        // 4. Create Reservation 1
        Reservation res1 = createReservation(carStore, user1, selectedCar, pickupDate1, returnDate1);

        // 5. User 1 makes payment via UPI
        PaymentMode upiPayment = new UPI();
        processPayment(res1, upiPayment);

        // 6. Print Receipt
        printReservationReceipt(res1);


        // =========================================================================
        // RESERVATION 2: User 2 (Jane Smith) books a Bike from BikeStore in Hyderabad
        // =========================================================================
        System.out.println("********************************************************");
        System.out.println("  FLOW 2: User 2 ('Jane Smith') Books a Bike");
        System.out.println("********************************************************");

        // 1. Search for available bikes in Bike Store
        System.out.println("\nChecking available Bikes at BikeStore (ID: " + bikeStore.getStoreId() + "):");
        bikeStore.printAvailableVehicles();

        // 2. Select Bike (Activa - Vehicle ID: 2)
        Vehicle selectedBike = bikeStore.getVehicleById(2);

        // 3. Define rental dates: 4 days rental (2026-09-02 to 2026-09-06) -> 4 * 150 = ₹600
        Date pickupDate2 = Date.valueOf("2026-09-02");
        Date returnDate2 = Date.valueOf("2026-09-06");

        // 4. Create Reservation 2
        Reservation res2 = createReservation(bikeStore, user2, selectedBike, pickupDate2, returnDate2);

        // 5. User 2 makes payment via Credit Card
        PaymentMode creditCardPayment = new CreditCard();
        processPayment(res2, creditCardPayment);

        // 6. Print Receipt
        printReservationReceipt(res2);


        // =========================================================================
        // POST-BOOKING VERIFICATIONS
        // =========================================================================
        System.out.println("********************************************************");
        System.out.println("  POST-BOOKING VERIFICATION & INVENTORY STATUS");
        System.out.println("********************************************************");

        System.out.println("\nRemaining Available Cars at CarStore (Creta should be removed/inactive):");
        carStore.printAvailableVehicles();

        System.out.println("\nRemaining Available Bikes at BikeStore (Activa should be removed/inactive):");
        bikeStore.printAvailableVehicles();

        // Check user booking records
        user1.printAllReservations();
        user2.printAllReservations();
    }
}
