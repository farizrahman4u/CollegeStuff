import java.io.*;
import java.util.*;

public class ExpConv
{
	public static void main(String args[]) throws IOException
	{
		
		DataInputStream in = new DataInputStream(System.in);
		print ("Enter Infix Expression : ");
		String input = in.readLine();
		input = input.replace(" ", "");
		input = "(" + input + ")";
		Expression infix = new Expression(input);
		Expression postfix = Expression.toPostfix(infix);
		Expression rev = infix;
		rev.reverse();
		Expression prefix = Expression.toPostfix(rev);
		prefix.reverse();
		infix.reverse();
		print ("Infix expression : " + infix.toString());
		infix.reverse();
		print ("Post expression : " + postfix.toString());
		print ("Prefix expression : " + prefix.toString());
		print ("Evaluating postfix expression....");
		Expression postfix_copy = new Expression();
		//Expression prefix_copy = new Expression();
		for (int i =0; i< postfix.size();i++)
		{
			postfix_copy.tokens.add(postfix.tokens.get(i));
			//prefix_copy.tokens.add(prefix.tokens.get(i));
			
		}
		postfix_copy.invert = postfix.invert;
		//prefix_copy.invert = prefix.invert;
		Integer value = Evaluater.postfixEvaluate(postfix_copy);
		print (value.toString());
		print("Retrieving infix from postfix : ");
		Expression inf = Expression.fromPostfix(postfix);
		print(inf.toString());
		print("Retrieving infix from prefix : ");
		inf = Expression.fromPrefix(prefix);
		print(inf.toString());

	}
	private static void print(String x)
	{
		System.out.println(x);
	}

}

class Token
{
	String data = "";
	public Token(String x)
	{
		data = x;
	}
	public Token(){}
	public String toString()
	{
		return data;
	}
	public void push(char x)
	{
		data += x;
	}
}

class Expression
{
	public List<Token> tokens = new ArrayList<Token>();
	public boolean invert = false;
	public void reverse()
	{
		if (invert)
		{
			invert = false;
		}
		else
		{
			invert = true;
		}
	}
	public Expression(String x)
	{
		int n = x.length();
		Token token = new Token();



		char d[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8' ,'9' };
		List<Character> digits = new ArrayList<Character>();
		for (int j=0; j<10; j++)
			digits.add(d[j]);
		boolean reading_num = false;
		for(int i=0; i<n; i++)
		{
			char c = x.charAt(i);
			boolean is_digit = digits.contains(c);
			if (! is_digit)
				{
					if (reading_num)
					{
						reading_num = false;
						tokens.add(token);
						token = new Token();
					}
					tokens.add(new Token(Character.toString(c)));
				}
			else
			{
				reading_num = true;
				token.push(c);
			}

		}
		if (token.toString().length() > 0)
		{
			tokens.add(token);
		}
	}
	public Expression(){}
	public String toString()
	{
		String str = "";
		int i;
		if (! invert)
		{
			for (i=0; i<size(); ++i)
			{
				str += tokens.get(i).toString() + " ";
			}
		}
		else
		{
			for (i=size()-1; i>=0; --i)
			{
				str += tokens.get(i).toString() + " ";
			}
		}
		return str;
	}
	public void push(Token t)
	{
		if (! invert)
		{
			tokens.add(t);
		}
		else
		{
			List<Token> tmp = new ArrayList<Token>();
			tmp.add(t);
			while (size() > 0)
			{
				tmp.add(tokens.get(0));
				tokens.remove(0);
			}
			tokens = tmp;		
		}
	}	
	public Token pop()
	{
		int n = 0;
		if (! invert)
		{	
			n = tokens.size() - 1;
		}
		Token x = tokens.get(n);
		tokens.remove(n);
		return x;
	}
	public Token peek()
	{
		int n = 0;
		if (! invert)
		{	
			n = tokens.size() - 1;
		}
		Token x = tokens.get(n);
		return x;
	}
	public int size()
	{
		return tokens.size();
	}
	public static Expression toPostfix(Expression infix)
	{
		Expression postfix = new Expression();
		Expression stack = new Expression();

		int n = infix.size();
		for (int i=0; i<n; i++) // for every token
		{

			Token t = infix.tokens.get(i);
			String tstr = t.toString();

			boolean isop = isOp(tstr);

			if (tstr.charAt(0) == '(') // if open braces
			{
				stack.push(t);

			}
			else if (tstr.charAt(0) == ')') // if closed braces
			{
				boolean flag = true;

				while(flag)
				{

					Token spop = stack.pop();
					if (spop.toString().charAt(0) == '(')
					{
						flag = false;

					}

					else
					{
						postfix.push(spop);


					}
				}
			}
			else if (isop) // is operator
			{
				if (stack.size() == 0) // just push
				{
					stack.push(t);

				}
				else if(stack.peek().toString() == "(") // just push
				{
					stack.push(t);

				}
				else
				{
					boolean f = true;
					while(f)
					{
						if(stack.size() == 0) // empty, exit loop
						{
							f = false;
						}
						else
						{
							Token op = stack.peek(); // peek 
							if (opComp(op.toString(), tstr) == 1) //precedence greater
							{
								stack.pop(); // pop from stack
								postfix.push(op); // push to output
								stack.push(t); // push to stack

								
							}
							else
							{
								f = false;
								stack.push(t); // push to stack
							}
						}
					}

				}
			}
			else //operand
			{

			postfix.push(t);

			}
			
			
			
		}
		return postfix;
	}
	private static boolean isOp(String t)
	{
		String ops[] = {"/", "*", "+", "-"};
		for(int i=0; i<4; i++)
		{
			if(t.charAt(0) == ops[i].charAt(0))
			{
				return true;
			}
		}
		return false;		
	}
	private static int opComp(String op1, String op2)
	{
		String ops[] = {"/", "*", "+", "-"};
		int n1 = 0;
		int n2 = 0;
		for(int i=0; i<4; i++)
		{
			if (op1 == ops[i])
			{
				n1 = i;
			}
			if (op2 == ops[i])
			{
				n2 = i;
			}
		}
		if (n1 == n2)
			return 0;
		if (n1 < n2)
		{
			return 1;
		}
		else return -1;
	}
	private static void print(String x)
	{
		System.out.println(x);
	}
	public  static Expression fromPostfix(Expression expr)
	{

		expr.reverse();
		Expression stack = new Expression();
		while(expr.size() > 0)
		{
			Token exp_pop = expr.pop();
			String str_pop = exp_pop.toString();
			boolean isop = isOp(str_pop);
			if (isop)
			{
				String b = stack.pop().toString();
				String a = stack.pop().toString();
				String exp = "(" + a + str_pop + b + ")";
				stack.push(new Token(exp));
			}
			else
			{
				stack.push(exp_pop);
			}
		}
		return new Expression(stack.pop().toString());
	}

	public  static Expression fromPrefix(Expression expr)
	{

		expr.reverse();
		Expression stack = new Expression();
		while(expr.size() > 0)
		{
			Token exp_pop = expr.pop();
			String str_pop = exp_pop.toString();
			boolean isop = isOp(str_pop);
			if (isop)
			{
				stack.push(exp_pop);
			}
			else
			{
				String b = stack.peek().toString();
				boolean isop2 = isOp(b);
				if(isop2)
				{
					stack.push(exp_pop);
				}
				else
				{
					stack.pop();
					String opr = stack.pop().toString();					
					String exp = "(" + str_pop + opr + b + ")";
					stack.push(new Token(exp));
				}
			}
		}
		return new Expression(stack.pop().toString());
	}


}

class Evaluater
{
	private static int eval(int a, int b, char op)
	{
		if(op == '+')
		{
			return a + b;
		}
		else if(op == '*')
		{
			return a * b;
		}
		else if(op == '-')
		{
			return a - b;
		}
		else if (op == '/')
		{
			return a / b;
		}
		else if (op == '%')
		{
			return a % b;
		}
		else 
		{
			return 0;
		}
	}
	public static int postfixEvaluate(Expression expression)
	{
		Expression expr = new Expression();
		expr.tokens = expression.tokens;
		expr.invert = expression.invert;
		expr.reverse();
		Expression stack = new Expression();
		while(expr.size() > 0)
		{
			Token exp_pop = expr.pop();
			String str_pop = exp_pop.toString();
			boolean isop = isOp(str_pop);
			if (isop)
			{
				int b = Integer.parseInt(stack.pop().toString());
				int a = Integer.parseInt(stack.pop().toString());
				Integer c = eval(a, b, str_pop.charAt(0));
				stack.push(new Token(c.toString()));
			}
			else
			{
				stack.push(exp_pop);
			}
		}
		return Integer.parseInt(stack.pop().toString());
	}



	private static void print(String x)
	{
		System.out.println(x);
	}
	private static boolean isOp(String t)
	{
		String ops[] = {"/", "*", "+", "-"};
		for(int i=0; i<4; i++)
		{
			if(t.charAt(0) == ops[i].charAt(0))
			{
				return true;
			}
		}
		return false;		
	}
}
