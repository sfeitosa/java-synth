package Tests;
import com.github.javaparser.ast.expr.*;
import common.Base;
import common.Log;
import common.ReflectParserTranslator;
import common.Log.Severity;
import generator.JRGBase;
import generator.JRGCore;
import generator.JRGOperator;
import java.io.*;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeTry;

public class OperatorTests extends Base {
  private JRGOperator mOperator;
  private JRGBase mBase;
  private JRGCore mCore;

  public OperatorTests() throws FileNotFoundException, IOException {
    super();
  }

  @BeforeTry
  public void createObjects() {
    mBase = new JRGBase(mCT);
    mCore = new JRGCore(mCT, mBase);
    mOperator = new JRGOperator(mCT, mBase, mCore);
  }

  /**********************************
   *                                *
   *       Tests start here         *
   *                                *
   **********************************/

  /*
   *
   * Generate a Logical Expressions from `JRGOperator.java`
   *
   */
  // @Example
  // @Property(tries = 10)
  boolean checkGenLogiExpression() {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenLogiExpression::inicio");

    Arbitrary<BinaryExpr> e = mOperator.genLogiExpression(mCtx);

    System.out.println("checkGenLogiExpression: " + e.sample());

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenLogiExpression::fim");

    return true;
  }

  /*
   *
   * Generate a Relational Expressions from `JRGOperator.java`
   * Using comparision signs as <, ==, >= for example
   *
   */
  // @Example
  // @Property(tries = 10)
  boolean checkGenRelaExpression() {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenRelaExpression::inicio");

    Arbitrary<BinaryExpr> e = mOperator.genRelaExpression(mCtx);

    System.out.println("checkGenRelaExpression: " + e.sample());

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenRelaExpression::fim");

    return true;
  }

    /*
   *
   * Generate a Arithmetic Expressions from `JRGOperator.java`
   * Using %, ==, +, -, * between two or more statements for example
   *
   */
  // @Example
  // @Property(tries = 10)
  boolean checkGenArithExpression() {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenArithExpression::inicio");

    Arbitrary<BinaryExpr> e = mOperator.genArithExpression(
      mCtx,
      ReflectParserTranslator.reflectToParserType("int")
    );

    System.out.println("checkGenArithExpression: " + e.sample());

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenArithExpression::fim");

    return true;
  }
}
