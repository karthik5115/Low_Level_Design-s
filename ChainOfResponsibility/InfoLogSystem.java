public class InfoLogSystem extends LogSystem {
    public InfoLogSystem(LogSystem logSystem) {
        super(logSystem);
    }

    public void log(int level, String message) {
        if (level == LogSystem.INFO) {
            System.out.println("INFO LOG " + message);
            return;
        }
        super.log(level, message);
    }

}
