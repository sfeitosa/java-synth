package Tests;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.type.*;
import common.Base;
import generator.JRGBase;
import java.io.*;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeTry;

public class BaseTests extends Base {
  private JRGBase mBase;

  public BaseTests() throws FileNotFoundException, IOException {
    super();
  }

  @BeforeTry
  public void createObjects() {
    mBase = new JRGBase(mCT);
  }

  /**********************************
   *                                *
   *       Tests start here         *
   *                                *
   **********************************/

  /*
   *
   * Generate a random primitive type all available primitive
   * types can be found at JRGBase.java `primitiveTypes()` which
   * then use 'net.jqwik.api.Arbitraries' to fetch all possible types
   *
   */
  // @Example
  boolean checkGenPrimitiveType() {
    Arbitrary<PrimitiveType.Primitive> t = mBase.primitiveTypes();

    Arbitrary<LiteralExpr> e = t.flatMap(
      tp -> mBase.genPrimitiveType(new PrimitiveType(tp))
    );

    System.out.println(
      "Expressão gerada (tipo primitivo): " + e.sample().toString()
    );

    return true;
  }

  /*
   *
   * Generate a random String literal with min_length = 1
   * and max_length = 5, ranging chars from 'a' to 'z'
   *
   */
  // @Example
  boolean checkGenPrimitiveString() {
    Arbitrary<LiteralExpr> s = mBase.genPrimitiveString();

    System.out.println("Frase gerada: " + s.sample());

    return true;
  }
}
