package common;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.*;
import com.github.javaparser.printer.DotPrinter;

import common.Log.Severity;
import java.io.*;
import java.util.*;

public class Base {
  private static final String SKELETON_PATH =
    "src/main/java/base/Main.java";

  protected CompilationUnit mSkeleton;

  protected ClassTable mCT;

  protected Map<String, String> mCtx;

  public Base() throws FileNotFoundException, IOException {
    mSkeleton = StaticJavaParser.parse(new File(SKELETON_PATH));

    //dumpAST();

    Log.logLevel = Severity.MSG_INFO;

    mCT = new ClassTable(loadImports());

    mCtx = new HashMap<String, String>();
  }

  // Auxiliary methods
  private List<String> loadImports() {
    NodeList<ImportDeclaration> imports = mSkeleton.getImports();

    List<String> list = new ArrayList<>();

    Iterator<ImportDeclaration> it = imports.iterator();
    while (it.hasNext()) {
      ImportDeclaration i = it.next();
      list.add(i.getName().asString());
    }

    return list;
  }

  /*
   * 
   * Write AST - Arbitrary Sintax Tree to file
   * using FileWriter output filename is `ast.dot`
   * 
   */
  public void dumpAST() throws IOException {
    DotPrinter printer = new DotPrinter(true);

    try (
      FileWriter fileWriter = new FileWriter("ast.dot");
      PrintWriter printWriter = new PrintWriter(fileWriter)
    ) {
      printWriter.print(printer.output(mSkeleton));
    }
  }

  // private void imprimiDados(CompilationUnit Classe) throws IOException {
  //   //DotPrinter printer = new DotPrinter(true);

  //   PrettyPrinter printer = new PrettyPrinter();

  //   try (
  //     FileWriter arq = new FileWriter("MainClass.java");
  //     PrintWriter gravarArq = new PrintWriter(arq)
  //   ) {
  //     //gravarArq.print(mSkeleton.toString());
  //     //gravarArq.print(mSkeleton.toString());
  //     gravarArq.print(printer.print(Classe));
  //   }
  // }

  // public void compila(String arquivo2) throws IOException {
  //   PrintWriter saida = new PrintWriter(new FileWriter("logCompilacao.txt"));

  //   int resultadoCompilacao = com.sun.tools.javac.Main.compile(
  //     new String[] { arquivo2 },
  //     saida
  //   );
  // }
}
