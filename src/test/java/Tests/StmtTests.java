package Tests;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.WhileStmt;
import common.Base;
import common.Log;
import common.Log.Severity;
import generator.JRGBase;
import generator.JRGCore;
import generator.JRGStmt;

import java.io.*;
import java.util.*;
import net.jqwik.api.*;
import net.jqwik.api.lifecycle.BeforeTry;

public class StmtTests extends Base {
  private JRGBase mBase;
  private JRGCore mCore;
  private JRGStmt mStmt;

  public StmtTests() throws FileNotFoundException, IOException {
    super();
  }

  @BeforeTry
  public void createObjects() {
    mBase = new JRGBase(mCT);
    mCore = new JRGCore(mCT, mBase);
    mStmt = new JRGStmt(mCT, mBase, mCore);
  }

  /**********************************
   *                                *
   *       Tests start here         *
   *                                *
   **********************************/
  
  /*
   *
   * Generate a BlockStmt containing a random program
   * from `JRGStmt.java` using the imports from `MainClass.java`
   * the code is generated from a list variables up to conditional statements
   *
   * # A further improvement would be to write this BlockStmt to a file instead
   * of writing to console everytime the test is ran
   *
   */
  // @Example
  boolean checkGenBlockStmt() throws ClassNotFoundException, IOException {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenBlockStmt::inicio");

    Arbitrary<BlockStmt> e = mStmt.genBlockStmt(mCtx);

    System.out.println("BlockStmt: " + e.sample());

    ClassOrInterfaceDeclaration classe = mSkeleton.getClassByName("MainClass").get();

    List<MethodDeclaration> ms = classe.getMethods();

    MethodDeclaration m = ms.get(0);

    m.setBody(e.sample());

    // compila("MainClass.java");

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenBlockStmt::fim");

    return true;
  }

  /*
   *
   * Generate a variety of variable declarations and assignments
   * using arbitrary data types and a valid string for the variable
   * label from `JRGStmt.java`
   *
   */
  // @Property(tries = 100)
  boolean checkGenVarDeclAssign() throws ClassNotFoundException {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenVarDeclaration::inicio");

    Arbitrary<VariableDeclarationExpr> e = mStmt.genVarDeclAssign(mCtx);

    System.out.println("checkGengenVarDeclaration: " + e.sample());

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenVarDeclaration::fim");

    return true;
  }

  /*
   *
   * Generate a variety of ONLY variable declarations using arbitrary
   * data types and a valid string for the variable label from `JRGStmt.java`
   *
   */
  // @Property(tries = 100)
  boolean checkGenVarDecl() throws ClassNotFoundException {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenVarDeclaration::inicio");

    Arbitrary<VariableDeclarationExpr> e = mStmt.genVarDecl(mCtx);

    System.out.println("checkGengenVarDeclaration: " + e.sample());

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenVarDeclaration::fim");

    return true;
  }

  /*
   *
   * Generate If and Else statements from `JRGStmt.java`
   *
   */
  // @Example
  // boolean checkGenIfStmt() throws ClassNotFoundException {
  //   Log.showMessage(Severity.MSG_XDEBUG, "checkGenIfStmt::inicio");

  //   Arbitrary<IfStmt> e = mStmt.genIfStmt(mCtx);

  //   System.out.println("checkGenIfStmt: " + e.sample());

  //   Log.showMessage(Severity.MSG_XDEBUG, "checkGenIfStmt::fim");

  //   return true;
  // }

  /*
   *
   * !ERROR - It's using a binaryExpr and looping conditional for some reason
   * Idk if it is supposed to be like this
   *
   */
  // @Example
  boolean checkWhileStmt() {
    Log.showMessage(Severity.MSG_XDEBUG, "checkWhileStmt::inicio");

    Arbitrary<WhileStmt> e = mStmt.genWhileStmt(mCtx);

    System.out.println("checkWhileStmt: " + e.sample());

    Log.showMessage(Severity.MSG_XDEBUG, "checkWhileStmt::fim");

    return true;
  }

  /*
   *
   * Generate conditional statements and a MainClass as well as functions
   * inside it with statements within itself from `JRGStmt.java`
   *
   */
  // @Example
  boolean checkGenStatement() throws ClassNotFoundException, IOException {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenStatement::inicio");

    Arbitrary<Statement> e = mStmt.genStatement(mCtx);

    System.out.println("checkGenStatement: " + e.sample());

    System.out.println(mSkeleton.getClassByName("MainClass"));

    ClassOrInterfaceDeclaration classe = mSkeleton
      .getClassByName("MainClass")
      .get();

    classe.addMethod(
      "main",
      Modifier.publicModifier().getKeyword(),
      Modifier.Keyword.STATIC
    );
    //mSkeleton.addInterface(e.sample().toString());

    classe.addInitializer().addAndGetStatement(e.sample());

    //imprimiDados(mSkeleton.addClass(classe.toString()));

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenStatement::fim");

    return true;
  }

  /*
   *
   * Generate a Logical statement from `JRGStmt.java`
   *
   */
  // @Example
  boolean checkGenExpressionStmt() {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenExpressionStmt::inicio");

    Arbitrary<ExpressionStmt> e = mStmt.genExpressionStmt(mCtx);

    System.out.println("checkGenExpressionStmt: " + e.sample());

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenExpressionStmt::fim");

    return true;
  }

  /*
   * 
   * Generate statements in a array format from `JRGStmt.java`
   * 
   */
  // @Example
  boolean checkGenStatementList() {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenStatementList::inicio");

    Arbitrary<NodeList<Statement>> e = mStmt.genStatementList(mCtx);

    System.out.println("checkGenStatementList: " + e.sample());

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenStatementList::fim");

    return true;
  }

  /*
   * 
   * Generate statements for variable declaration
   * From `JRGStmt.java`
   * 
   */
  // @Property(tries = 10)
  boolean checkGenVarDeclarationStmt() throws ClassNotFoundException {
    Log.showMessage(
      Severity.MSG_XDEBUG,
      "checkGenVarDeclarationStmt::inicio"
    );

    Arbitrary<ExpressionStmt> e = mStmt.genVarDeclarationStmt(mCtx);

    System.out.println("checkGenVarDeclarationStmt: " + e.sample());

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenVarDeclarationStmt::fim");

    return true;
  }

  /*
   * 
   * !ERROR - empty set of values
   * 
   */
  // @Example
  boolean checkGenVarAssingStmt() throws ClassNotFoundException {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenVarAssingStmt::inicio");

    Arbitrary<VariableDeclarationExpr> e = mStmt.genVarAssingStmt(mCtx);

    System.out.println("checkGenVarAssingStmt: " + e.sample());

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenVarAssingStmt::fim");

    return true;
  }

  /*
   * 
   * !ERROR - empty set of values
   * 
   */
  // @Example
  boolean checkGenTypeAssingStmt() throws ClassNotFoundException {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenTypeAssingStmt::inicio");

    Arbitrary<AssignExpr> e = mStmt.genTypeAssingStmt(mCtx);

    System.out.println("checkGenTypeAssingStmt: " + e.sample());

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenTypeAssingStmt::fim");

    return true;
  }

  /*
   * 
   * Generate For Loopings expressions with statements within
   * the loop using `JRGStmt.java`
   * 
   */
  // @Example
  // @Property(tries=4)
  // boolean checkGenFor() throws ClassNotFoundException {
  //   Log.showMessage(Severity.MSG_XDEBUG, "checkGenTypeAssingStmt::inicio");

  //   Arbitrary<ForStmt> e = mStmt.genForStmt(mCtx);
  //   //mStmt.genForStmt(mCtx);
  //   System.out.println("checkGenTypeAssingStmt: " + e.sample());

  //   Log.showMessage(Severity.MSG_XDEBUG, "checkGenTypeAssingStmt::fim");

  //   return true;
  // }

  /*
   * 
   * !IDK = Generate a selection of variable declarations and assignments
   * 
   */
  // @Example
  boolean checkGenList() throws ClassNotFoundException {
    Log.showMessage(Severity.MSG_XDEBUG, "checkGenTypeAssingStmt::inicio");

    List<Statement> e = mStmt.genList(mCtx);
    //mStmt.genForStmt(mCtx);
    System.out.println("checkGenTypeAssingStmt: " + e.get(0));

    Log.showMessage(Severity.MSG_XDEBUG, "checkGenTypeAssingStmt::fim");

    return true;
  }
}
