import java.io.*;

public class LinkedStack
{
	public static void main(String[] args)  throws Exception
	{
		Stack stack = new Stack();
		boolean flag = true;
		while (flag)
		{
			System.out.println("\n 1 - Push\n 2 - Pop\n 3 - Exit");
			DataInputStream in = new DataInputStream(System.in);
			int op = Integer.parseInt(in.readLine());
			switch(op)
			{
				case 1:
					System.out.println("\n Enter value to push to stack : ");
					double x = Double.valueOf(in.readLine());
					boolean success = stack.push(x);
					if (success)
					{
						System.out.println(x + " successfully pushed to stack.\n");
					}
					else
					{
						System.out.println("Error! Stack overflow.\n");
					}
					break;
				case 2:
					if (stack.isEmpty())
					{
						System.out.println("Error! Stack underflow.\n");
					}
					else
					{
						double p = stack.pop();
						System.out.println("\n" + p + "\n");
					}
					break;
				case 3:
					flag = false;
					break;
			}
		}
	}
}

class Node
{
	double data;
	Node next = null;
	public Node(double x)
	{
		data = x;
	}
}
class Stack
{

	Node top = null;
	int count = 0;
	
	public boolean isEmpty()
	{
		return top == null;
	}

	public int getCount()
	{
		return count;
	}

	public boolean push(Double x)
	{
		Node node = new Node(x);
		node.next = top;
		top = node;
		count += 1;
		return true;
	}

	public double pop()
	{
		if(isEmpty())
		{
			return 0;
		}
		else
		{
			double x = top.data;
			top = top.next;
			count -= 1;
			return x;
		}
	}

}
