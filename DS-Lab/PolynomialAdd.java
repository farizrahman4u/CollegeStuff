import java.io.*;
import java.util.*;

public class PolynomialAdd
{
	public static void main(String args[]) throws IOException
	{
		System.out.println("\n Enter Polynomial 1 : ");		
		DataInputStream dip = new DataInputStream(System.in);
		String input = dip.readLine();
		Polynomial p1 = new Polynomial(input);
		System.out.println(p1.toString());
		System.out.println("\n Enter Polynomial 2 : ");
		input = dip.readLine();
		Polynomial p2 = new Polynomial(input);
		System.out.println(p2.toString());
		Polynomial sum = PolynomialAdder.add(p1, p2);
		System.out.println("\n Sum = \n" + sum.toString());
	}
}


class Term
{
	Integer coeff;
	Integer exp;
	Term next;
	Term previous;
	public Term(int c,int e)
	{
		coeff = c;
		exp = e;
	}
	public String toString()
	{
		return coeff.toString() + "x^" + exp.toString();
	}
}

class Polynomial
{
	int count = 0;
	Term start = null;
	public int size()
	{
		return count;
	}
	public void push(Term t)
	{
		if(start == null)
		{
			start = t;
		}
		else
		{
			start.previous = t;
			t.next = start;
			start = t;
			start.previous = null;
			
		}
		count++;	
	}
	public Term pop()
	{	
		if(count == 0)
		{
			return null;
		}
		Term temp = start;
		start = start.next;
		if (start != null)
		{
			start.previous = null;
		}
		count--;
		return temp;
	}
	public Term peek()
	{
		return start;
	}
	public void sortedPush(Term t)
	{
		if(start == null)
		{
			t.next = null;
			t.previous = null;
			start = t;
			start.previous = null;
			start.next = null;
			count++;
			return;
		}
		Term term = start;
		Term end = null;
		while(term != null)
		{
			if(term.exp == t.exp)
			{
				Term sum = new Term(term.coeff + t.coeff, t.exp);
				sum.previous = term.previous;
				sum.next = term.next;
				if(sum.previous != null)
				{
					sum.previous.next = sum;
				}
				if(sum.next != null)
				{
					sum.next.previous = sum;
				}
				if(term.previous == null)
				{
					start = sum;
				}
				return;
			}
			else if (term.exp > t.exp)
			{
				t.next = term;
				t.previous = term.previous;
				if(t.previous != null)
				{
					t.previous.next = t;
				}
				if(t.previous == null)
				{
					start = t;
				}
				term.previous = t;
				count++;
				return;
			}
			end = term;
			term = term.next;
		}
		end.next = t;
		t.previous = end;
		t.next = null;

	}

	public String toString()
	{
		String str = "";
		Term term = start;
		while(term != null)
		{
			str += term.toString();
			if(term.next != null)
			{
				str += " + ";
			}
			term = term.next;
		}
		return str;
	}
	public Polynomial()
	{
	}
	public Polynomial(String str)
	{
		str += "+";
		char d[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8' ,'9' };
		List<Character> digits = new ArrayList<Character>();
		for (int j=0; j<10; j++)
			digits.add(d[j]);
		str = str.replace(" ", "");
		String coeff = "";
		String exp = "";
		boolean reading_coeff = true;
		boolean var_detect = false;
		int n = str.length();
		for(int i=0; i<n; i++)
		{
			char c = str.charAt(i);
			boolean isdigit = digits.contains(c);
			if (isdigit)
			{
				if(reading_coeff)
				{
					coeff += c;
				}
				else
				{
					exp += c;
				}
			}
			else if(c == 'x' || c == 'X' || c == 'Y' || c == 'y')
			{
				var_detect = true;
				if(coeff.length() == 0)
				{
					coeff = "1";
				}
			}
			else if(c == '^')
			{
				reading_coeff = false;
			}
			else if(c == '+')
			{
				if(reading_coeff)
				{
					if(var_detect)
					{
						exp = "1";
					}
					else
					{
						exp = "0";
					}
				}
				reading_coeff = true;
				Term term = new Term(Integer.parseInt(coeff), Integer.parseInt(exp));
				sortedPush(term);
				coeff = "";
				exp = "";
				var_detect = false;
			}
			
		}
	}
}

class PolynomialAdder
{
	public static Polynomial add(Polynomial p1, Polynomial p2)
	{
		Polynomial sum = new Polynomial();
		boolean flag = true;
		while(flag)
		{
			Term t1 = p1.peek();
			Term t2 = p2.peek();

			if (t1.exp == t2.exp)
			{
				Term nterm = new Term(t1.coeff + t2.coeff, t1.exp);
				sum.push(nterm);
				p1.pop();
				p2.pop();
			}	
			else if(t1.exp > t2.exp)
			{
				sum.push(t1);
				p1.pop();
			}
			else
			{
				sum.push(t2);
				p2.pop();
			}
			flag = ((p1.size() > 0) && (p2.size() > 0));
		}
		while(p1.size() > 0)
		{
			sum.push(p1.pop());
		}
		while(p2.size() > 0)
		{
			sum.push(p2.pop());
		}
		return sum;
	}
	
}
