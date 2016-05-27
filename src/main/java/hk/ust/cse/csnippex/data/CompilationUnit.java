package hk.ust.cse.csnippex.data;

import java.util.ArrayList;

/**
 * @author vterragni
 *
 */
public class CompilationUnit {

	public String packageDeclaration;
	public ArrayList<String> imports;
	public String body;
	public String className;
	public String main;

	public CompilationUnit(String packageDeclaration,
			ArrayList<String> imports, String body, String className,
			String main) {
		super();
		this.packageDeclaration = packageDeclaration;
		this.imports = imports;
		this.body = body;
		this.className = className;
		this.main = main;
	}

	@Override
	public String toString() {
		return "CompilationUnit [packageDeclaration=" + packageDeclaration
				+ ", imports=" + imports + ", body=" + body + ", className="
				+ className + ", main=" + main + "]";
	}
	
	public String getStringCode() {

		String s = packageDeclaration;
		if (imports != null)
			for (final String line : imports) {
				s = s + "\n" + line;
			}
		if(!main.equals("")){
			if (body.contains("}"))
				s = s+"\n"+ body.substring(0, body.lastIndexOf("}") - 1)
				+ "\n"
				+ "public static void main(String[] args) throws Exception {\n "
				+ main + "\n} \n}";
			else
				s = s+"\n"+body
				+ "public static void main(String[] args) throws Exception {\n "
				+ main + "\n} \n}";
		}else{
			s = s+"\n"+body;
		}
		return s;
	}

}
