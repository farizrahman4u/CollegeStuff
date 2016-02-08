//Circular queue qith dynamic memory
import java.io.*;

public class ArrayQueue
{
	public static void main(String[] args)  throws Exception
	{
		Queue q = new Queue(10);
		boolean flag = true;
		while (flag)
		{
			System.out.println("\n 1 - Insert\n 2 - Remove\n 3 - Exit");
			DataInputStream in = new DataInputStream(System.in);
			int op = Integer.parseInt(in.readLine());
			switch(op)
			{
				case 1:
					System.out.println("\n Enter value to insert to queue : ");
					double x = Double.valueOf(in.readLine());
					boolean success = q.insert(x);
					if (success)
					{	
						System.out.println(x + " successfully inserted to queue.\n");
					}
					else
					{
						System.out.println("Error! queue full.\n");
					}
					break;
				case 2:
					if (q.isEmpty())
					{
						System.out.println("Error! queue empty.\n");
					}
					else
					{
						double p = q.remove();
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
class Queue
{
	int max = 100;
	double[] data= new double[100];
	int front = -1;
	int rear= -1;
	int count = 0;

	public Queue()
	{
	}

	public Queue(int m)
	{
		max = m;
		data = new double[m];
	}
	public boolean isEmpty()
	{
		return count == 0;
	}

	public int getCount()
	{
		return count;
	}

	public boolean isFull()
	{
		return count == max;
	}

	public boolean insert(double x)
	{
		if (isFull())
		{
			try
			{
				System.out.println("Expanding queue size...");
				int nmax = max * 2;
				double data2[] = new double[nmax];
				for (int i = 0; i < count; i++)
				{
					data2[i] = data[front];
					if (front == max - 1)
					{
						front = 0;
					}
					else
					{
						front += 1;
					}
				}
				front = 0;
				rear = max - 1;
				data = data2;
				max = nmax;
					
			}catch(Exception e){return false;}
		}
			
		if (count == 0)
		{
			data[0] = x;
			front = 0;
			rear = 0;
			count = 1;
			return true;
		}
		else
		{
			if (rear == max - 1)
			{
				rear = 0;
			}
			else
			{
				rear += 1;
			}
			data[rear] = x;
			count += 1;
			return true;
		}
		
	}

	public double remove()
	{
		if(isEmpty())
		{
			return 0;
		}
		else
		{
			double x = data[front];
			if (front == max - 1)
			{
				front = 0;
			}
			else
			{
				front += 1;
			}
			count = count - 1;
			return x;
		}
	}

}
