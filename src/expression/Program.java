package expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.Instruction;
import exception.WrongComplexException;
import parser.ExpressionParser;

public class Program extends ComplexExpression 
{
	public Program(String input) throws WrongComplexException {
		super(input, Instruction.PROGRAM, 1, null);
		SimpeExpresion.openPorts.clear();
		ExpressionParser parser = new ExpressionParser(input);
		statements = parser.parse();
	}

	@Override
	public Expression get(int index) throws IndexOutOfBoundsException {
		return statements.get(index);
	}

	@Override
	public String toString() {
		return "Program";
	}
	public boolean isValid() {
		return true;
	}
	public void splitBlock(Instruction instruction, int currentLine, String in) throws WrongComplexException {
		line = currentLine;
		this.content = in;
	}
	public Map<Integer, List<Expression>> mapExpression()
	{
		Map<Integer, List<Expression>> instructions = new HashMap<Integer, List<Expression>>();
		for(Expression exp : statements)
			exp.addtoInstructions(instructions);
		return instructions;
	}

}
