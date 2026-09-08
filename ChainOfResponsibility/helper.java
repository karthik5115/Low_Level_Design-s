public class helper {
    public static void main(String[] args) {
        LogSystem logSystem = new InfoLogSystem(new DebugLogSystem(new ErrorLogSystem(null)));
        logSystem.log(3, "debug message");
        logSystem.log(2, "Error message");
        logSystem.log(1, "info message");
        logSystem.log(4, "404");
    }
}
