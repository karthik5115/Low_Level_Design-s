public class ErrorLogSystem extends LogSystem {
    public ErrorLogSystem(LogSystem logSystem) {
        super(logSystem);
    }

    public void log(int level, String message) {
        if (level == LogSystem.ERROR) {
            System.out.println("ERROR LOG " + message);
            return;
        }
        super.log(level, message);
    }

}
