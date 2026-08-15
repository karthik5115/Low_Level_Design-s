Notify Me
A Java-based Low-Level Design (LLD) project demonstrating the Observer Design Pattern for product stock notifications.

Problem Statement
In an online shopping system, customers often want to be notified as soon as a product becomes available again. A store may have multiple interested customers, and each customer should receive updates without the store needing to manually contact them one by one.

The challenge is to design a notification system that is:

Scalable for many customers
Easy to extend for multiple stores/products
Loosely coupled between the store and its subscribers
Efficient when stock availability changes
Requirements
Customers should be able to subscribe to stock alerts for a store.
A store should notify all subscribed customers when the product is available.
Customers should be able to unsubscribe from updates when needed.
The design should support multiple store types and multiple customer types.
The implementation should be flexible and maintainable.
The solution should follow the Observer Design Pattern.
Approach
I used the Observer pattern to model the notification flow.

The store acts as the Subject / Observable.
Customers act as Observers.
Whenever a product is marked as in stock, the store notifies all registered observers.
Each observer receives the update and prints or handles the alert message.
This keeps the store independent from the customer implementation, allowing easy addition or removal of subscribers without modifying the core logic.

Design
The project consists of the following classes and interfaces:

Observable

Defines the contract for subscription management.
Methods:
addObserver(Customer observer)
removeObserver(Customer observer)
notifyObservers(String message)
Customer

Abstract base class for all customers.
Stores customer details like name, email, and phone number.
Declares the update(String message) method to receive notifications.
IphoneNotificationAlert

Concrete customer implementation for iPhone stock alerts.
Handles updates with a notification message.
samsungNotify

Concrete customer implementation for Samsung stock alerts.
Receives and processes the product availability message.
IphoneStore

Implements the Observable interface.
Maintains a list of subscribed customers.
Calls notifyObservers() when the product becomes available.
SamsungStore

Similar to IphoneStore, but for Samsung products.
Tracks product stock and notifies customers when the product is available.
helper

Contains the demo/test execution.
Adds customers to stores and triggers stock availability changes.
Design Pattern
Observer Pattern

The Observer Pattern is used to establish a one-to-many dependency between a subject and multiple observers.

Flow:

A customer subscribes to a store.
The store keeps a list of all active subscribers.
When stock is available, the store triggers notifyObservers().
Every subscribed customer receives the same alert message.
Observers can be added or removed dynamically without disturbing the store logic.
This pattern is ideal for:

Stock availability alerts
News notifications
Event broadcasting
Subscription-based updates
Key Learnings
Loose coupling is achieved between the store and customers.
The Observer pattern helps scale notification systems efficiently.
Subject logic is independent of individual observer implementations.
Adding new stores or customer types becomes straightforward.
It is a strong example of real-world event-driven communication in Java.
Project Summary
This LLD demonstrates how to build a notification system where product availability updates are broadcast to interested users automatically. It is a simple yet effective example of event-driven architecture and clean design using the Observer Pattern.

Example Flow
Customer subscribes to IphoneStore
Product stock is set to available
IphoneStore calls notifyObservers()
All subscribed customers receive the notification message
This repository is a good beginner-friendly project for understanding:

Java classes and abstraction
Interface-based design
Observer pattern usage
Low-Level Design principles
