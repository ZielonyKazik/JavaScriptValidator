package enums;

public enum Instruction {
	
	TRY("try"),
	ELSE("else"),
	IF("if\\s*\\("),
	FOR("([\\w$_]+:\\s*)?for\\s*\\("),
	WHILE("([\\w$_]+:\\s*)?while\\s*\\("),
	CATCH("catch\\s*\\("),
	FUNCITON("function\\s+[_\\$a-zA-Z]+[_\\$\\w]*\\s*\\("),
	PROGRAM("");
	
	public final String content;
	Instruction(String str)
	{
		content = str;
	}
	public String toString()
	{
		return content;
	}
}
