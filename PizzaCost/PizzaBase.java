interface PizzaBase {
    String getDescription();
    double getCost();
}
class ThinCrust implements PizzaBase {
    @Override
    public String getDescription() {
        return "Thin Crust";
    }

    @Override
    public double getCost() {
        return 100.00;
    }
}
class Margerita implements PizzaBase {
    @Override
    public String getDescription()  {
        return "Margerita pizza";
    }

    @Override
    public double getCost() {
        return 120.00;
    }
}
class FarmHouse implements PizzaBase {
    @Override
    public String getDescription() {
        return "FarmHouse pizza";
    }

    @Override
    public double getCost() {
        return 150.00;
    }
}