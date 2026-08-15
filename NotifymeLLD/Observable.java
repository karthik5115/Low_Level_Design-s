interface Observable {
    void addObserver(Customer observer);
    void removeObserver(Customer observer);
    void notifyObservers(String message);

}