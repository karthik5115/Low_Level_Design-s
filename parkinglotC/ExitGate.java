import java.util.Date;

public class ExitGate {
    private int gateId;

    double processExit(Ticket ticket, PricingStrategy pricingStrategy) {
        double cost = pricingStrategy.calculateCost(ticket);
        ticket.getParkingSlot().unParkVehicle();
        return cost;
    }
}

interface PricingStrategy {
    double calculateCost(Ticket ticket);
}

class MinBasedStrategy implements PricingStrategy {
    double minRate;

    MinBasedStrategy(double minRate) {
        this.minRate = minRate;
    }

    public double calculateCost(Ticket ticket) {
        long timeInMs = new Date().getTime() - ticket.getInTime().getTime();
        double timeInMins = timeInMs / 60000.0;
        double minPrice = ticket.getParkingSlot().getPrice();
        double priceInMins = timeInMins * minRate;
        if (priceInMins < minPrice) {
            return minPrice;
        }
        return priceInMins;
    }
}

class HourlyRateStrategy implements PricingStrategy {
    double hourlyRate;

    HourlyRateStrategy(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double calculateCost(Ticket ticket) {
        long timeInMs = new Date().getTime() - ticket.getInTime().getTime();
        double timeInHours = timeInMs / 3600000.0;
        return timeInHours * hourlyRate;
    }
}
