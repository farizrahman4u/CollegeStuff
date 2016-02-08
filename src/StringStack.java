import java.io.*;

public class StringStack
{
	public static void main(String[] args)  throws Exception
	{
		Stack stack = new Stack();
		DataInputStream in = new DataInputStream(System.in);
		System.out.println("\n Enter String to reverse : \n");
		String x = in.readLine();
		stack.push(x);
		String rev = "";
		while (rev.length() < x.length())
		{
			rev = rev + stack.pop();
		}
		System.out.println("\n Reversed string : " + rev);
		
	}
}

class Stack
{
	String data;

	
	public boolean isEmpty()
	{
		return data.length() == 0;
	}

	public int getCount()
	{
		return data.length();
	}

	public void push(String x)
	{
		data = data + x;

	}
	public void push (char x)
	{
		data = data + x;
	}

	public char pop()
	{
		if(isEmpty())
		{
			return ' ';
		}
		int n = data.length() - 1;
		char x = data.charAt(n);
		data = data.substring(0, n);
		return x;
		
	}

}
