public abstract class LogSystem {
    public static int INFO = 1;
    public static int ERROR = 2;
    public static int DEBUG = 3;
    private LogSystem nexLogSystem;

    public LogSystem(LogSystem logSystem) {
        this.nexLogSystem = logSystem;
    }

    public void log(int level, String message) {
        if (nexLogSystem == null) {
            System.out.print("can't process this message!" + message);
            return;
        }
        nexLogSystem.log(level, message);
    }

}
