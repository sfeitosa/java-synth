package Tests;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.type.*;
import common.Base;
import common.Log;
import common.ReflectParserTranslator;
import common.Log.Severity;
import generator.JRGBase;
import generator.JRGCore;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeTry;

public class CoreTests extends Base {
  private JRGBase mBase;
  private JRGCore mCore;

  public CoreTests() throws FileNotFoundException, IOException {
    super();
  }

  @BeforeTry
  public void createObjects() {
    mBase = new JRGBase(mCT);
    mCore = new JRGCore(mCT, mBase);
  }

  /**********************************
   *                                *
   *       Tests start here         *
   *                                *
   **********************************/

  /*
   *
   * Generate a new Class or Interface type from `JRGCore.java`
   *
   */
  // @Example
  boolean checkGenObjectCreation() throws ClassNotFoundException {
    Log.showMessage(
      Severity.MSG_XDEBUG,
      "checkGenObjectCreation" + "::inicio"
    );

    ClassOrInterfaceType c = new ClassOrInterfaceType();

    c.setName("br.edu.ifsc.javargexamples.B");

    //Arbitrary<ObjectCreationExpr> e = mCore.genObjectCreation(c);
    Arbitrary<Expression> e = mCore.genObjectCreation(mCtx, c);

    if (e != null) {
      System.out.println("ObjectCreation gerado: " + e.sample().toString());
    } else {
      Log.showMessage(
        Severity.MSG_ERROR,
        "Não foi possível gerar " + "criação de objeto"
      );
    }

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenObjectCreation::fim");

    return true;
  }

  /*
   *
   * Generate a random Method from `JRGCore.java` ClassTable
   *
   */
  // @Property(tries = 10)
  boolean checkGenMethodInvokation() throws ClassNotFoundException {
    Log.showMessage(
      Severity.MSG_XDEBUG,
      "checkGenMethodInvokation" + "::inicio"
    );

    ClassOrInterfaceType c = new ClassOrInterfaceType();

    c.setName("br.edu.ifsc.javargexamples.B");
    //Arbitrary<MethodCallExpr> e = mCore.genMethodInvokation(c);
    Arbitrary<MethodCallExpr> e = mCore.genMethodInvokation(
      mCtx,
      ReflectParserTranslator.reflectToParserType("int")
    );

    if (e != null) {
      System.out.println("Method gerado: " + e.sample().toString());
    } else {
      Log.showMessage(
        Severity.MSG_ERROR,
        "Não foi possível gerar " + "criação do método"
      );
    }

    Log.showMessage(
      Severity.MSG_XDEBUG,
      "checkGenMethodInvokation" + "::fim"
    );

    return true;
  }

  /*
   *
   * Picks a random Method from a list of avaiable methods
   * from `JRGCore.java` using the given type "int" as a parameter
   *
   */
  // @Example
  boolean checkGenCandidatesMethods() throws ClassNotFoundException {
    Log.showMessage(
      Severity.MSG_XDEBUG,
      "checkGenCandidatesMethods" + "::inicio"
    );

    Arbitrary<Method> b = mCore.genCandidatesMethods("int");

    System.out.println("Candidatos Methods: " + b.sample());

    Log.showMessage(
      Severity.MSG_XDEBUG,
      "checkGenCandidatesMethods" + "::fim"
    );

    return true;
  }

  /*
   *
   * Picks a random Field/Attribute from a list of avaiable fields/attributes
   * from `JRGCore.java` using the given type "int" as a parameter
   *
   */
  // @Example
  boolean checkGenCandidatesFields() throws ClassNotFoundException {
    Log.showMessage(
      Severity.MSG_XDEBUG,
      "checkGenCandidatesFields" + "::inicio"
    );

    Arbitrary<Field> b = mCore.genCandidatesField("int");

    System.out.println("Candidatos Fields: " + b.sample());

    Log.showMessage(
      Severity.MSG_XDEBUG,
      "checkGenCandidatesFields:" + ":fim"
    );

    return true;
  }

  /*
   *
   * Picks a random Constructor from a list of avaiable constructors
   * from `JRGCore.java` using the given type class as a parameter
   *
   */
  // @Example
  boolean checkGenCandidatesConstructors() throws ClassNotFoundException {
    Log.showMessage(
      Severity.MSG_XDEBUG,
      "checkGenCandidatesConstructors" + "::inicio"
    );

    Arbitrary<Constructor> b = mCore.genCandidatesConstructors(
      "br.edu." + "ifsc.javargexamples.B"
    );

    System.out.println("Candidatos Constructors: " + b.sample());

    Log.showMessage(
      Severity.MSG_XDEBUG,
      "checkGenCandidatesConstructors" + "::fim"
    );

    return true;
  }

  /*
   *
   * Generate a selection of random expressions using attributes and literal integers
   *
   */
  //@Property(tries = 10)
  @Example
  boolean checkGenExpression() {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenExpression::inicio");

    try {
      Arbitrary<Expression> e = mCore.genExpression(
        mCtx,
        ReflectParserTranslator.reflectToParserType("int")
      );
      System.out.println("Expressão gerada: " + e.sample());
    } catch (Exception ex) {
      System.out.println("Erro: " + ex.getMessage());
      return false;
    }

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenExpression::fim");

    return true;
  }

  /*
   *
   * Generate a statement for accessing an attribute of type 'int'
   * from `JRGCore.java` using 'tname: int' as parameter
   *
   */
  // @Example
  boolean checkGenAttributeAccess() throws ClassNotFoundException {
    Log.showMessage(
      Severity.MSG_XDEBUG,
      "checkGenAtributteAcess" + "::inicio"
    );

    Arbitrary<FieldAccessExpr> e = mCore.genAttributeAccess(
      mCtx,
      ReflectParserTranslator.reflectToParserType("int")
    );

    System.out.println("Acesso gerado: " + e.sample());

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenExpression::fim");

    return true;
  }

  /*
   *
   * Generate a Cast expression for convertion
   * from `JRGCore.java` using a Class as parameter
   *
   */
  // @Example
  boolean checkGenUpCast() throws ClassNotFoundException {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenUpCast" + "::inicio");

    Arbitrary<CastExpr> e = mCore.genUpCast(
      mCtx,
      ReflectParserTranslator.reflectToParserType(
        "br.edu.ifsc." + "javargexamples.Aextend"
      )
    );

    System.out.println("CheckGenUpCast: " + e.sample());

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenUpCast" + "::final");

    return true;
  }

  /*
   *
   * !ERROR "Jwqik empty set of values"
   *
   */
  // @Example
  boolean checkGenVar() throws ClassNotFoundException {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenVar" + "::inicio");

    Arbitrary<NameExpr> e = mCore.genVar(
      mCtx,
      ReflectParserTranslator.reflectToParserType("int")
    );

    System.out.println("checkGenVar: " + e.sample());

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenVar" + "::final");
    return true;
  }

  /*
   * 
   * Generate Lambda expressions from `JRGCore.java`
   * 
   */
  //@Example
  // @Property(tries = 10)
  boolean checkGenLambdaExpr() throws ClassNotFoundException {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenLambdaExpr::inicio");

    Arbitrary<LambdaExpr> e = mCore.genLambdaExpr(mCtx);

    System.out.println("checkGenLambdaExpr: " + e.sample());

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenLambdaExpr::fim");

    return true;
  }

}