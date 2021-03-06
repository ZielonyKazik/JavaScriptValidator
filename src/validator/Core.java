package validator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.WrongComplexException;
import expression.Expression;
import expression.Program;
import parser.ExpressionParser;

public class Core extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try{
		Context.clear();
		Context.variableWithUnderscoreValid=request.getParameter("underscore")!=null;
		Program program = new Program(request.getParameter("javaScript"));
		List<String> rows = Arrays.asList(ValidUtils.color(ValidUtils.htmlValidReplace(request.getParameter("javaScript"))).split("\n"));
		String language = request.getParameter("language");
		out.println(String.format(ValidUtils.html, makeResponse(rows, program, language)));
		}catch(WrongComplexException e){}
	}
	private String makeResponse(List<String> rows, Program program,String language)
	{
		String body = "";
		Map<Integer, List<Expression>> map = program.mapExpression();
		for(int i = 0; i < rows.size(); i++)
		{
			List<Expression> list = map.get(i+1);
			if(!rows.get(i).matches("\\s*") && list != null)
				body += String.format(ValidUtils.row, i+1, ValidUtils.countSpace(rows.get(i)), rows.get(i), ValidUtils.hasErrors(list, i +1) ? "error" : "noError", ValidUtils.hasErrors(list, i +1) ? ValidUtils.prepareErrors(list, i +1, language) : ValidUtils.prepareExpressions(list, language));
			else
				body += String.format(ValidUtils.row, i+1, ValidUtils.countSpace(rows.get(i)), rows.get(i), "plain", "plain");
		}
		return body;
	}
}