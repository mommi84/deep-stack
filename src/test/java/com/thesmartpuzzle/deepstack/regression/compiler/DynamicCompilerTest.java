package com.thesmartpuzzle.deepstack.regression.compiler;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;


public class DynamicCompilerTest extends TestCase{

	  @Before
	  public void setUp() { // Note: It is not required to call this setUp()
	    
	  }
	@Test
	public void testCompile_String(){
		
		
       Result res = DynamicCompiler.compile("    System.out.println(200+300); ");

		assertTrue(res.isCompilable==true);
		assertTrue(res.isExecutable==true);
	}
	
	@Test
	public void testCompile_Nonsense(){
		
		
       Result res = DynamicCompiler.compile(" dhfjdhjfhdjfhjdhfjdhjf  ");

		assertTrue(res.isCompilable==false);
		assertTrue(res.isExecutable==false);
	}
	
	
	
	
}
