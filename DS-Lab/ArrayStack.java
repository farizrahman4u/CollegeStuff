import java.io.*;

public class ArrayStack
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

class Stack
{
	int max = 100;
	double[] data= new double[100];
	int top = -1;
	
	public boolean isEmpty()
	{
		return top == -1;
	}

	public int getCount()
	{
		return top + 1;
	}

	public boolean isFull()
	{
		return top == max - 1;
	}

	public boolean push(Double x)
	{
		if (isFull())
		{
			try
			{
				int nmax = max * 2;
				double[] data2 = new double[nmax];
				for (int i = 0; i <= top; i++)
				{
					data2[i] = data[i];
					data = data2;
					max = nmax;
				}
			}catch(Exception e){return false;}
		}
			data[top + 1] = x;
			top += 1;
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
			double x = data[top];
			top -= 1;
			return x;
		}
	}

}
