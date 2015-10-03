package com.thesmartpuzzle.deepstack.regression.compiler;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;



/**
 * 
 * This class compile a piece of code, in case of errors if fixes compilation
 * errors and execute the code snippet
 * 
 * @author vterragni
 * 
 */
@SuppressWarnings("all")
public class DynamicCompiler {
	/** where shall the compiled class be saved to (should exist already) */
	private static String classOutputFolder = "./bin";
	// results
	private static Result result;

	// number of trials to compile and execute
	private static final int numTries = 5;

	//
	private static ArrayList<String> codelist = null;

	/**
	 * reset everything
	 */
	private static void reset() {

		codelist = new ArrayList<String>();
		result = new Result();
	}

	public static class MyDiagnosticListener implements
			DiagnosticListener<JavaFileObject> {
		public void report(Diagnostic<? extends JavaFileObject> diagnostic) {

			result.compfixes++;

			System.out.println("Line Number->" + diagnostic.getLineNumber());
			System.out.println("code->" + diagnostic.getCode());
			System.out.println("Message->"
					+ diagnostic.getMessage(Locale.ENGLISH));
			System.out.println("Source->" + diagnostic.getSource());
			  System.out.println( "Position/column: " + diagnostic.getPosition()
                      + "    " + diagnostic.getColumnNumber() );
			  
			  
			System.out.println(" ");
		}
	}

	/**
	 * java File Object represents an in-memory java source file <br>
	 * so there is no need to put the source file on hard disk
	 **/
	public static class InMemoryJavaFileObject extends SimpleJavaFileObject {
		private String contents = null;

		public InMemoryJavaFileObject(String className, String contents)
				throws Exception {
			super(URI.create("string:///" + className.replace('.', '/')
					+ Kind.SOURCE.extension), Kind.SOURCE);
			this.contents = contents;
		}

		public CharSequence getCharContent(boolean ignoreEncodingErrors)
				throws IOException {
			return contents;
		}
	}

	/**
	 * Get a simple Java File Object ,<br>
	 * It is just for demo, content of the source code is dynamic in real use
	 * case
	 */
	private static JavaFileObject getJavaFileObject(String code) {
		
		
		codelist.add("package test;");
		codelist.add(" \n public class TestTrial{ ");
		codelist.add(" \n  public void testCode(){ ");
		codelist.add(" \n " +code);
		codelist.add(" \n }");
		codelist.add(" \n }");
		
		
		String s = "";
		
		for(int i = 0; i< codelist.size(); i++){
			if(i!=0)
			s = s  + codelist.get(i);
			else
				s = s + codelist.get(i);
	
		}

		JavaFileObject so = null;
		try {
			so = new InMemoryJavaFileObject("test.TestTrial",
					s);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return so;
	}

	/** compile your files by JavaCompiler */
	public static void compile(Iterable<? extends JavaFileObject> files) {

		// get system compiler:
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		// for compilation diagnostic message processing on compilation
		// WARNING/ERROR
		MyDiagnosticListener c = new MyDiagnosticListener();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				c, Locale.ENGLISH, Charset.defaultCharset());
		// specify classes output folder
		Iterable options = Arrays.asList("-d", classOutputFolder);
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
				c, options, null, files);
		result.isCompilable = task.call();

	}

	/** run class from the compiled byte code file by URLClassloader */
	public static void runIt() {
		// Create a File object on the root of the directory
		// containing the class file
		File file = new File(classOutputFolder);

		try {
			// Convert File to a URL
			URL url = file.toURL(); // file:/classes/demo
			URL[] urls = new URL[] { url };

			// Create a new class loader with the directory
			ClassLoader loader = new URLClassLoader(urls);

			// Load in the class; Class.childclass should be located in
			// the directory file:/class/demo/
			Class thisClass = loader.loadClass("test.TestTrial");

			Class params[] = {};
			Object paramsObj[] = {};
			Object instance = thisClass.newInstance();
			Method thisMethod = thisClass.getDeclaredMethod("testCode", params);

			// run the testAdd() method on the instance:
			thisMethod.invoke(instance, paramsObj);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception ex) {

			ex.printStackTrace();
		}

		result.isExecutable = true;
	}

	/**
	 * Given a piece of code returns noon-empty String if it is compilable and
	 * can be executed without exception, it returns an empty String otherwise.
	 * 
	 * @param code
	 * @return
	 */
	public static Result compile(String code) {

		reset();

		int count = 0;
		while (result.isCompilable == false && count < numTries) {
			codelist.clear();
			// 1.Construct an in-memory java source file from your dynamic code
			JavaFileObject file = getJavaFileObject(code);
			Iterable<? extends JavaFileObject> files = Arrays.asList(file);

			// 2.Compile your files by JavaCompiler
			compile(files);
			count++;
		}

		// 3.Load your class by URLClassLoader, then instantiate the instance,
		// and call method by reflection

		if (result.isCompilable)
			runIt();

		return result;
	}

}
