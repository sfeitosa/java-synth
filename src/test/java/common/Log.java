package common;
public class Log {

  public enum Severity {
    MSG_ERROR,
    MSG_WARN,
    MSG_INFO,
    MSG_DEBUG,
    MSG_XDEBUG,
  }

  public static Severity logLevel = Severity.MSG_ERROR;

  public static void showMessage(Severity s, String msg) {
    if (logLevel.ordinal() >= s.ordinal()) {
      System.out.println(msg);
    }
  }
}
