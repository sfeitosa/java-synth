package Tests;
import common.Base;
import common.Log;
import common.Log.Severity;
import java.io.*;
import java.util.*;

public class CTTests extends Base {

  public CTTests() throws FileNotFoundException, IOException {
    super();
  }

  /**********************************
   *                                *
   *       Tests start here         *
   *                                *
   **********************************/

  /*
   *
   * Get all super() from subsequents class inheritance calls
   * from ClassTable given a Class path as a parameter
   *
   */
  // @Example
  boolean checkSuperTypes() throws ClassNotFoundException {
    Log.showMessage(Severity.MSG_XDEBUG, "checkSuperTypes" + "::inicio");

    List<Class> b = mCT.superTypes(
      "br.edu.ifsc." + "javargexamples.AextendExtend"
    );

    b.forEach(
      i -> {
        System.out.println("SuperTypes: " + i);
      }
    );

    Log.showMessage(Severity.MSG_XDEBUG, "checkSuperTypes" + "::final");

    return true;
  }

  /*
   *
   * Get the subTypes from a given class object
   * from ClassTable given a Class path as a parameter
   *
   */
  // @Example
  boolean checkSubTypes() throws ClassNotFoundException {
    Log.showMessage(Severity.MSG_XDEBUG, "checkSubTypes" + "::inicio");

    List<Class> b = mCT.subTypes("br.edu.ifsc." + "javargexamples.A");

    b.forEach(
      i -> {
        System.out.println("subTypes: " + i.toString());
      }
    );

    Log.showMessage(Severity.MSG_XDEBUG, "checkSubTypes" + "::final");

    return true;
  }

  /*
   *
   * Get all superTypes from subsequent class calls
   * from ClassTable given a Class path as a parameter
   *
   * In fact, should be called `checkSuperTypes()`
   *
   */
  // @Example
  boolean checkSubTypes2() throws ClassNotFoundException {
    Log.showMessage(Severity.MSG_XDEBUG, "checkSubTypes" + "::inicio");

    List<Class> b = mCT.subTypes2("br.edu.ifsc." + "javargexamples.A");

    b.forEach(
      i -> {
        System.out.println("subTypes: " + i.toString());
      }
    );

    Log.showMessage(Severity.MSG_XDEBUG, "checkSubTypes" + "::final");

    return true;
  }
}
