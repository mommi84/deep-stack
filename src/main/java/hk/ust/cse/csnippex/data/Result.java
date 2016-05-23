package hk.ust.cse.csnippex.data;

import java.util.HashSet;

/**
 * 
 * 
 * This is a serializable object
 * 
 * 
 * @author vterragni
 *
 */
public class Result {

	public Result(long answerId, HashSet<CompilationUnit> units,
			String classPath, boolean isCompilable, int sizeErrors, long time) {
		super();
		this.answerId = answerId;
		this.units = units;
		this.classPath = classPath;
		this.isCompilable = isCompilable;
		this.sizeErrors = sizeErrors;
		this.time = time;
	}

	public long answerId = 0;
	public HashSet<CompilationUnit> units = new HashSet<CompilationUnit>();
	public String classPath = "";
	public boolean isCompilable;
	public int sizeErrors = 0;
	public long time;

	@Override
	public String toString() {
		return "Result [answerId=" + answerId + ", units=" + units
				+ ", classPath=" + classPath + ", isCompilable=" + isCompilable
				+ ", sizeErrors=" + sizeErrors + ", time=" + time + "]";
	}

}
