# ATM Machine System - Low-Level Design (LLD)

A robust, object-oriented Low-Level Design of an ATM Machine system implemented in Java.

---

## 📁 Clean Modular Architecture

```text
ATMMachineC/
├── states/                      # State Pattern Implementation
│   ├── ATMState.java
│   ├── IdleState.java
│   ├── HasCardState.java
│   ├── OptionsSelectionState.java
│   ├── WithdrawState.java
│   ├── DepositState.java
│   ├── CheckBalanceState.java
│   └── CashDispenseState.java
├── dispensers/                  # Chain of Responsibility Note Dispensers
│   ├── CashDispenser.java
│   ├── FiveHundredDispenser.java
│   ├── TwoHundredDispenser.java
│   └── HundredDispenser.java
├── inventory/                   # Note Hierarchy & Inventory Management
│   ├── Note.java                # Note Interface
│   ├── FiveHundredNote.java     # 500 Denomination Note
│   ├── TwoHundredNote.java      # 200 Denomination Note
│   ├── HundredNote.java         # 100 Denomination Note
│   └── Inventory.java           # Manages Map<Note, Integer>
├── model/                       # Domain Models
│   ├── BankAccount.java
│   ├── Card.java
│   └── User.java
├── atm/                         # ATM Context
│   └── ATMMachine.java
├── Main.java                    # Entry Point & Test Simulation
└── README.md
```

---

## 🎯 Design Patterns & Key Components

### 1. State Design Pattern
The ATM Machine transitions through multiple distinct states during a user session. The **State Pattern** encapsulates state-specific behaviors within individual state classes located in the `states/` directory that implement the `ATMState` interface:
- **`IdleState`**: ATM is idle, waiting for a card to be inserted.
- **`HasCardState`**: Card is inserted; user can set/change PIN, authenticate PIN, or cancel.
- **`OptionsSelectionState`**: Switch-case menu routing user to Withdraw, Deposit, Check Balance, or Cancel.
- **`WithdrawState`**: Validates withdrawal amount, user account balance, and ATM cash inventory before dispensing.
- **`DepositState`**: Handles cash deposit into user's bank account.
- **`CheckBalanceState`**: Displays the user's current account balance.
- **`CashDispenseState`**: Dispenses notes via Chain of Responsibility, debits the account, updates inventory, ejects card, and resets ATM to `IdleState`.

### 2. Chain of Responsibility Pattern
Cash withdrawal is fulfilled using a chain of note dispensers in the `dispensers/` directory configured in descending order of denomination:
$$\text{Amount} \longrightarrow \mathbf{FiveHundredDispenser} \ (500) \longrightarrow \mathbf{TwoHundredDispenser} \ (200) \longrightarrow \mathbf{HundredDispenser} \ (100)$$

- Each dispenser calculates the number of required notes, checks available inventory in `Inventory` via `Map<Note, Integer>`, dispenses available notes, deducts inventory, and forwards the remainder down the chain.
- If the amount is not a multiple of 500, 200, or 100, the ATM rejects the transaction before dispensing.

### 3. Note & Inventory Management
- **`Note` (Interface)**: Defines note contracts with `getValue()`.
  - `FiveHundredNote`: Value = 500
  - `TwoHundredNote`: Value = 200
  - `HundredNote`: Value = 100
- **`Inventory`**: Contains `Map<Note, Integer> noteQuantityMap` to track real-time note quantities and calculate total ATM balance.

---

## 📊 UML Class Diagram

```mermaid
classDiagram
    namespace states {
        class ATMState {
            <<interface>>
            +InsertCard(Card card) Card
            +AuthenticatePin(Card card) boolean
            +Withdraw(Card card) void
            +SetATMPin(Card card) void
            +Deposit(Card card) void
            +Cancel(Card card) void
            +OptionstoSelect() void
            +ReturnCard(Card card) void
            +checkBalance(Card card) void
            +returntoOptions() void
            +cashDispense(double amt) void
        }
        class IdleState
        class HasCardState
        class OptionsSelectionState
        class WithdrawState
        class DepositState
        class CheckBalanceState
        class CashDispenseState
    }

    namespace atm {
        class ATMMachine {
            -ATMState currentATMState
            -Card currentCard
            -CashDispenser dispenserChain
            -Inventory inventory
            +insertCard(Card card) Card
            +authenticatePin(Card card) boolean
            +setATMPin(Card card) void
            +optionstoSelect() void
            +withdraw(Card card) void
            +deposit(Card card) void
            +checkBalance(Card card) void
            +cancel(Card card) void
            +returntoOptions() void
            +cashDispense(double amt) void
            +getInventory() Inventory
        }
    }

    namespace inventory {
        class Note {
            <<interface>>
            +getValue() int
        }
        class FiveHundredNote
        class TwoHundredNote
        class HundredNote
        class Inventory {
            -Map~Note, Integer~ noteQuantityMap
            +addNotes(Note note, int quantity) void
            +getNoteQuantity(Note note) int
            +deductNotes(Note note, int quantity) void
            +getTotalBalance() double
            +displayInventory() void
        }
    }

    namespace dispensers {
        class CashDispenser {
            <<abstract>>
            #CashDispenser nextDispenser
            +setNextDispenser(CashDispenser next) void
            +dispense(ATMMachine atmMachine, int amount)* void
        }
        class FiveHundredDispenser
        class TwoHundredDispenser
        class HundredDispenser
    }

    namespace model {
        class User {
            -Card card
            +getCard() Card
            +setCard(Card card) void
        }
        class Card {
            -String cardNO
            -int pin
            -String CVV
            -Date expiryDate
            -BankAccount account
            +validatePin(int pin) boolean
            +setPin(int pin) void
            +deposit(double amt) void
            +withdraw(double amt) void
            +checkBalance() void
            +getBalance() double
        }
        class BankAccount {
            -String AccountNumber
            -String ownerName
            -double balance
            +deposit(double amount) void
            +withdraw(double amount) boolean
            +getBalance() double
        }
    }

    ATMState <|.. IdleState
    ATMState <|.. HasCardState
    ATMState <|.. OptionsSelectionState
    ATMState <|.. WithdrawState
    ATMState <|.. DepositState
    ATMState <|.. CheckBalanceState
    ATMState <|.. CashDispenseState

    Note <|.. FiveHundredNote
    Note <|.. TwoHundredNote
    Note <|.. HundredNote

    Inventory o-- Note
    ATMMachine o-- Inventory
    ATMMachine o-- ATMState
    ATMMachine o-- Card
    ATMMachine o-- CashDispenser

    CashDispenser <|-- FiveHundredDispenser
    CashDispenser <|-- TwoHundredDispenser
    CashDispenser <|-- HundredDispenser

    User o-- Card
    Card o-- BankAccount
```

---

## 🔄 State Transition Flow

```mermaid
stateDiagram-v2
    [*] --> IdleState

    IdleState --> HasCardState : InsertCard(card)
    
    HasCardState --> HasCardState : SetATMPin(card)
    HasCardState --> OptionsSelectionState : AuthenticatePin(card) [Valid PIN]
    HasCardState --> IdleState : Cancel(card)

    state OptionsSelectionState {
        [*] --> SelectMenu
        SelectMenu --> WithdrawChoice : Option 1
        SelectMenu --> DepositChoice : Option 2
        SelectMenu --> CheckBalanceChoice : Option 3
        SelectMenu --> CancelChoice : Option 4
    }

    OptionsSelectionState --> WithdrawState : Option 1
    OptionsSelectionState --> DepositState : Option 2
    OptionsSelectionState --> CheckBalanceState : Option 3
    OptionsSelectionState --> IdleState : Option 4 (Cancel / Eject Card)

    WithdrawState --> CashDispenseState : Amount valid, account balance OK & ATM inventory OK
    WithdrawState --> OptionsSelectionState : returntoOptions()
    WithdrawState --> IdleState : Cancel(card)

    DepositState --> OptionsSelectionState : returntoOptions()
    DepositState --> IdleState : Cancel(card)

    CheckBalanceState --> OptionsSelectionState : returntoOptions()
    CheckBalanceState --> IdleState : Cancel(card)

    CashDispenseState --> IdleState : Dispense notes via Chain of Responsibility & eject card
```

---

## 🔁 End-to-End Execution Flow

```mermaid
sequenceDiagram
    autonumber
    actor User
    participant ATM as ATMMachine
    participant HasCard as HasCardState
    participant Options as OptionsSelectionState
    participant Withdraw as WithdrawState
    participant Dispense as CashDispenseState
    participant Chain as CashDispenser (500->200->100)
    participant Inventory as Inventory (Map~Note, Integer~)
    participant Card as Card / BankAccount

    User->>ATM: insertCard(card)
    ATM-->>User: Card inserted (State: HasCardState)

    User->>ATM: authenticatePin(card)
    ATM->>HasCard: AuthenticatePin(card)
    HasCard->>Card: validatePin(pin)
    Card-->>HasCard: Valid PIN
    HasCard->>ATM: setATMState(OptionsSelectionState)
    HasCard->>Options: OptionstoSelect()

    Options-->>User: Display menu (1.Withdraw, 2.Deposit, 3.CheckBalance, 4.Cancel)
    User->>Options: Select Option 1 (Withdraw)
    Options->>ATM: setATMState(WithdrawState)
    Options->>Withdraw: Withdraw(card)

    User->>Withdraw: Enter withdrawal amount (e.g. Rs. 3700)
    Withdraw->>Card: Check account balance >= 3700
    Withdraw->>Inventory: Check ATM total balance >= 3700
    Withdraw->>ATM: setATMState(CashDispenseState)
    Withdraw->>Dispense: cashDispense(3700)

    Dispense->>Card: withdraw(3700)
    Dispense->>Chain: dispense(ATM, 3700)
    Chain->>Inventory: deductNotes(FiveHundredNote, 7)
    Chain->>Inventory: deductNotes(TwoHundredNote, 1)
    Chain-->>Dispense: 7x500 notes + 1x200 notes
    Dispense-->>User: Cash dispensed & updated ATM inventory printed
    Dispense->>ATM: setATMState(IdleState)
    Dispense-->>User: Card ejected (State: IdleState)
```

---

## 🚀 How to Run

1. **Compile all Java packages:**
   ```bash
   javac model/*.java inventory/*.java dispensers/*.java states/*.java atm/*.java Main.java
   ```

2. **Run the Application:**
   ```bash
   java Main
   ```

3. **Select Execution Mode:**
   - Mode `1`: Run the automated end-to-end test suite with note inventory verification.
   - Mode `2`: Start the interactive ATM console.
