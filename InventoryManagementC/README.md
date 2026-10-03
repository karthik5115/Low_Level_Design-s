# Inventory Management & Product Delivery System (LLD)

A modular, object-oriented Low-Level Design (LLD) implementation of an **Inventory Management and Product Delivery System** in Java.

---

## 📌 Table of Contents
1. [Overview](#overview)
2. [Design Patterns Used](#design-patterns-used)
3. [Architecture & UML Class Diagram](#architecture--uml-class-diagram)
4. [System Execution Flow](#system-execution-flow)
5. [Project Structure](#project-structure)
6. [Compilation & Execution](#compilation--execution)

---

## 📖 Overview

The system models an end-to-end e-commerce / product delivery workflow with the following capabilities:
* **User Management**: Manages user profiles, delivery addresses, and user shopping carts.
* **Warehouse & Inventory Management**: Manages warehouses across locations, inventories, product categories, and physical product stocks.
* **Warehouse Selection**: Automatically resolves the optimal warehouse to fulfill an order using pluggable selection strategies.
* **Order Processing**: Manages order creation, lifecycle state transitions (`PLACED`, `PACKED`, `SHIPPED`, `DELIVERED`, `CANCELLED`), and automated inventory deduction/rollback.
* **Payment Processing**: Pluggable payment strategies (e.g., UPI, Cash on Delivery).
* **Invoice Generation**: Computes total items price, tax, and final amount.

---

## 🎨 Design Patterns Used

| Design Pattern | Purpose & Implementation |
| :--- | :--- |
| **Facade Pattern** | [`ProductDelivarySystem`](file:///Users/karthiksmacbook/Desktop/SystemDesign/LLD'S/InventoryManagementC/ProductDelivarySystem.java) acts as a unified facade interface that hides the complexity of coordinating multiple subsystems (`UserController`, `WareHouseController`, `OrderController`, `Inventory`). |
| **Strategy Pattern (Warehouse Selection)** | [`WareHouseSelectionStrategy`](file:///Users/karthiksmacbook/Desktop/SystemDesign/LLD'S/InventoryManagementC/Utilities/WareHouseSelectionStrategy.java) interface allows dynamic selection algorithms (e.g., [`NearestWareHouse`](file:///Users/karthiksmacbook/Desktop/SystemDesign/LLD'S/InventoryManagementC/Utilities/NearestWareHouse.java), shortest delivery time, lowest cost) without altering warehouse controller logic. |
| **Strategy Pattern (Payment Mode)** | [`Paymentmode`](file:///Users/karthiksmacbook/Desktop/SystemDesign/LLD'S/InventoryManagementC/Payment/Paymentmode.java) interface enables interchangeable payment implementations like [`UPIpaymentMode`](file:///Users/karthiksmacbook/Desktop/SystemDesign/LLD'S/InventoryManagementC/Payment/UPIpaymentMode.java) and [`CODPaymentMode`](file:///Users/karthiksmacbook/Desktop/SystemDesign/LLD'S/InventoryManagementC/Payment/CODPaymentMode.java). |
| **Controller Pattern** | Domain-specific controllers ([`UserController`](file:///Users/karthiksmacbook/Desktop/SystemDesign/LLD'S/InventoryManagementC/User/UserController.java), [`WareHouseController`](file:///Users/karthiksmacbook/Desktop/SystemDesign/LLD'S/InventoryManagementC/Store/WareHouseController.java), [`OrderController`](file:///Users/karthiksmacbook/Desktop/SystemDesign/LLD'S/InventoryManagementC/User/OrderController.java)) encapsulate business logic and collection state. |

---

## 🏛 Architecture & UML Class Diagram

```mermaid
classDiagram
    direction TB

    class ProductDelivarySystem {
        -OrderController orderController
        -UserController userController
        -WareHouseController wareHouseController
        +getUser(int userId) User
        +getWareHouse(Address address) WareHouse
        +getInventory(WareHouse wareHouse) Inventory
        +addProductToCart(User user, int categoryId, int quantity) void
        +placeOrder(User user, WareHouse wareHouse) Order
        +checkout(Order order) void
    }

    class UserController {
        -List~User~ userList
        +addUser(User user) void
        +removeUser(User user) void
        +getUser(int userId) User
    }

    class User {
        -int userId
        -String userName
        -Address address
        -Cart cartDetails
        -List~Order~ orders
        +getCart() Cart
        +getAddress() Address
        +getUserId() int
    }

    class Cart {
        -Map~int, int~ productCategoryIdQuantityMap
        +addProduct(int categoryId, int quantity) void
        +removeProduct(int categoryId) void
        +getProductCategoryIdQuantityMap() Map
    }

    class WareHouseController {
        -List~WareHouse~ warehouses
        -WareHouseSelectionStrategy wareHouseSelectionStrategy
        +addWareHouse(WareHouse wareHouse) void
        +removeWareHouse(WareHouse wareHouse) void
        +getWareHouse(Address address) WareHouse
    }

    class WareHouseSelectionStrategy {
        <<interface>>
        +getWareHouse(Address address, List~WareHouse~ wareHouses) WareHouse
    }

    class NearestWareHouse {
        +getWareHouse(Address address, List~WareHouse~ wareHouses) WareHouse
    }

    class WareHouse {
        +Inventory wareHouseInventory
        -Address address
        +addCategory(ProductCategory category) void
        +removeCategory(int categoryId) void
        +getInventory() Inventory
        +getAddress() Address
    }

    class Inventory {
        -Map~int, ProductCategory~ productCategories
        +addProduct(Map~int, int~ productCategoryIdQuantityMap) void
        +removeProductFromCategory(Map~int, int~ productCategoryIdQuantityMap) void
        +addCategory(ProductCategory category) void
        +removeCategory(int categoryId) void
        +getCategory(int categoryId) ProductCategory
        +listCategories() List~ProductCategory~
        +isProductAvailable(int categoryId, int quantity) boolean
    }

    class ProductCategory {
        -int categoryId
        -String categoryName
        -Double price
        -List~Product~ products
        +addProducts(int quantity) void
        +removeProducts(int count) void
        +getQuantity() int
        +getPrice() Double
    }

    class Product {
        -int productId
        -String productDescription
    }

    class OrderController {
        -List~Order~ orderList
        -Map~int, List~Order~~ userIDVsOrders
        +createNewOrder(User user, WareHouse warehouse) Order
        +removeOrder(Order order) void
        +getOrderByCustomerId(int userId) List~Order~
    }

    class Order {
        -User user
        -Address deliveryAddress
        -Map~int, int~ productCategoryIdQuantityMap
        -WareHouse wareHouse
        -Invoice invoice
        -Payment payment
        -OrderStatus orderStatus
        +checkOut() void
        +makePayment(Paymentmode paymentmode) boolean
        +generateInvoice() Invoice
    }

    class OrderStatus {
        <<enumeration>>
        PLACED
        PACKED
        SHIPPED
        DELIVERED
        CANCELLED
    }

    class Invoice {
        -int totalItemPrice
        -int totalTax
        -int totalFinalPrice
        -Order order
        -Inventory inventory
        +generateInvoice() Invoice
        +printInvoice() void
    }

    class Payment {
        -Paymentmode paymentMode
        +makePayment() boolean
    }

    class Paymentmode {
        <<interface>>
        +makePayment() boolean
    }

    class UPIpaymentMode {
        +makePayment() boolean
    }

    class CODPaymentMode {
        +makePayment() boolean
    }

    class Address {
        -String streetAddress
        -String city
        -String state
        -String country
        -String pincode
    }

    %% Relationships
    ProductDelivarySystem --> UserController
    ProductDelivarySystem --> WareHouseController
    ProductDelivarySystem --> OrderController

    UserController o-- User
    User *-- Cart
    User --> Address
    User o-- Order

    WareHouseController o-- WareHouse
    WareHouseController --> WareHouseSelectionStrategy
    WareHouseSelectionStrategy <|.. NearestWareHouse

    WareHouse *-- Inventory
    WareHouse --> Address
    Inventory *-- ProductCategory
    ProductCategory *-- Product

    OrderController o-- Order
    Order --> User
    Order --> WareHouse
    Order --> OrderStatus
    Order *-- Invoice
    Order *-- Payment

    Payment --> Paymentmode
    Paymentmode <|.. UPIpaymentMode
    Paymentmode <|.. CODPaymentMode
    Invoice --> Inventory
```

---

## 🔄 System Execution Flow

```mermaid
sequenceDiagram
    autonumber
    actor Client as Main / Client
    participant Facade as ProductDelivarySystem
    participant UserCtrl as UserController
    participant WHCtrl as WareHouseController
    participant Strategy as WareHouseSelectionStrategy
    participant WH as WareHouse
    participant Inv as Inventory
    participant Cart as User Cart
    participant OrderCtrl as OrderController
    participant Order as Order
    participant Pay as Payment

    Client->>Facade: getUser(userId)
    Facade->>UserCtrl: getUser(userId)
    UserCtrl-->>Facade: User

    Client->>Facade: getWareHouse(userAddress)
    Facade->>WHCtrl: getWareHouse(userAddress)
    WHCtrl->>Strategy: getWareHouse(userAddress, warehouses)
    Strategy-->>WHCtrl: Nearest WareHouse
    WHCtrl-->>Facade: Nearest WareHouse

    Client->>Facade: getInventory(WareHouse)
    Facade->>WH: getInventory()
    WH-->>Facade: Inventory

    Client->>Facade: addProductToCart(User, categoryId, quantity)
    Facade->>Inv: isProductAvailable(categoryId, quantity)
    alt Product Available
        Facade->>Cart: addProduct(categoryId, quantity)
    else Product Not Available
        Facade-->>Client: Product not available
    end

    Client->>Facade: placeOrder(User, WareHouse)
    Facade->>OrderCtrl: createNewOrder(User, WareHouse)
    OrderCtrl->>Order: new Order(user, warehouse)
    Order-->>OrderCtrl: Order (Status: PLACED)
    OrderCtrl-->>Facade: Order

    Client->>Facade: checkout(Order)
    Facade->>Order: checkOut()
    Order->>Inv: removeProductFromCategory(items)
    Note over Order: Status -> PACKED
    Order->>Pay: makePayment(Paymentmode)
    Pay-->>Order: Payment Success
    Note over Order: Status -> DELIVERED
    
    Client->>Order: generateInvoice()
    Order-->>Client: Invoice
    Client->>Client: invoice.printInvoice()
```

---

## 📁 Project Structure

```
.
├── main.java                          # Main entry point & simulation runner
├── ProductDelivarySystem.java         # Facade orchestrating all sub-controllers
├── README.md                          # Architecture, UML & Flow documentation
│
├── Payment/                           # Payment Subsystem (Strategy Pattern)
│   ├── Payment.java                   # Context class for payment
│   ├── Paymentmode.java               # Payment strategy interface
│   ├── UPIpaymentMode.java            # UPI payment implementation
│   └── CODPaymentMode.java            # Cash On Delivery implementation
│
├── Store/                             # Store & Inventory Domain
│   ├── Product.java                   # Product entity
│   ├── ProductCategory.java           # Category managing inventory units & prices
│   ├── Inventory.java                 # Catalog & stock management per warehouse
│   ├── WareHouse.java                 # Physical warehouse representation
│   └── WareHouseController.java       # Controller managing warehouse fleet
│
├── User/                              # User & Order Subsystem
│   ├── User.java                      # User entity
│   ├── UserController.java            # Controller for user operations
│   ├── Cart.java                      # Shopping cart holding selected items
│   ├── Order.java                     # Order aggregate root & state machine
│   ├── OrderController.java           # Controller managing orders
│   ├── OrderStatus.java               # Enum representing order states
│   └── Invoice.java                   # Bill calculation & presentation
│
└── Utilities/                         # Common Utilities & Selection Strategies
    ├── Address.java                   # Address entity
    ├── WareHouseSelectionStrategy.java # Strategy interface for warehouse lookup
    └── NearestWareHouse.java          # Concrete nearest-warehouse strategy
```

---

## 🚀 Compilation & Execution

### 1. Compile All Java Sources
```bash
javac main.java ProductDelivarySystem.java User/*.java Store/*.java Payment/*.java Utilities/*.java
```

### 2. Run the System
```bash
java Main
```

### 3. Expected Output
```text
Category Name: soft drink Price: 100.0Quantity Available:10
Category Name: CAke Price: 20.0Quantity Available:15
Category Name: Chocolate Price: 10.0Quantity Available:20
Product added to cart successfully
UPI payment mode
Total Item Price: 200
Total Tax: 20
Total Final Price: 220
```
