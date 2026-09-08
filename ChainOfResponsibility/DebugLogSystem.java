public class DebugLogSystem extends LogSystem {
    public DebugLogSystem(LogSystem logSystem) {
        super(logSystem);
    }

    public void log(int level, String message) {
        if (level == LogSystem.DEBUG) {
            System.out.println("Debug LOG " + message);
            return;
        }
        super.log(level, message);
    }

}
