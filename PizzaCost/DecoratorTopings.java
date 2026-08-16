interface DecoratorToppings extends PizzaBase {
    String getDescription();
    double getCost();
}
class Cheese implements DecoratorToppings {
    private PizzaBase pizzaBase;

    public Cheese(PizzaBase pizzaBase) {
        this.pizzaBase = pizzaBase;
    }

    @Override
    public String getDescription() {
        return pizzaBase.getDescription() + ", Cheese";
    }

    @Override
    public double getCost() {
        return pizzaBase.getCost() + 20.00;
    }
}
class Olives implements DecoratorToppings {
    private PizzaBase pizzaBase;
    public Olives(PizzaBase pizzaBase) {
        this.pizzaBase = pizzaBase;
    }
    @Override
    public String getDescription() {
        return pizzaBase.getDescription() + ", Olives";
    }

    @Override
    public double getCost() {
        return pizzaBase.getCost() + 15.00;
    }
}