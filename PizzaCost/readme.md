DP used: Decorator — dynamically add responsibilities to objects.

Flow:
Base: PizzaBase (interface) implemented by Margerita, FarmHouse, ThinCrust.
Decorator: DecoratorToppings implements PizzaBase and holds a PizzaBase reference.
Compose: instantiate a base, then wrap with toppings: pizza = new Cheese(pizza);
Delegate: each decorator calls wrapped getDescription() / getCost() and adds its own text/value.
Finish: Helper composes the decorated pizza and prints the final description and total cost.


OOPs / design principles used:
Encapsulation: cost/description kept inside each class.
Polymorphism: bases and toppings used via the PizzaBase interface.
Interface / Inheritance: concrete classes implement PizzaBase.
Composition over Inheritance: decorators hold a PizzaBase instance to extend behavior.
Open/Closed Principle: add new toppings without changing existing code.
Single Responsibility: each class handles one concern (base, topping, or order composition).


Example:
new Cheese(new Margerita()) → description "Margerita, Cheese"; cost = base + cheese.
