import java.io.*;

public class DoublyLinkedList
{
	public static void main(String[] args)  throws Exception
	{
		List list = new List();
		boolean flag = true;
		boolean success = true;
		int index;
		double x;
		while (flag)
		{
			System.out.println("\n 1 - Add\n 2 - Add at index\n 3 - Remove at index\n 4 - Search\n 5 - Search and delete\n 6 - Print list\n 7 - Exit \n");
			DataInputStream in = new DataInputStream(System.in);
			int op = Integer.parseInt(in.readLine());
			switch(op)
			{
				case 1:
					System.out.println("\n Enter value to add to list : ");
					x = Double.valueOf(in.readLine());
					success = list.add(x);
					if (success)
					{
						System.out.println(x + " added to list.\n");
					}

					break;
				case 2:
					System.out.println("\n Enter value and index to add to list : ");
					x = Double.valueOf(in.readLine());
					index = Integer.parseInt(in.readLine());
					success = list.addAt(x, index);
					if (success)
					{
						System.out.println(x + " added to list at index " + index + "\n");
					}
					else
					{
						System.out.println("Index out of range!");						
					}

					break;
				case 3:
					System.out.println("\n Enter index to remove element : ");
					index = Integer.parseInt(in.readLine());
					if(index >= list.size())
					{
						System.out.println("Index out of range!");
					}
					else
					{
						x = list.removeAt(index);	
						System.out.println(x + " removed from index " + index + "\n");
					}
					break;
				case 4:
					System.out.println("\n Enter value to search for : ");
					x = Double.valueOf(in.readLine());
					index = list.search(x);
					if(index == -1)
					{
						System.out.println("Element not found in list!");			
					}
					else
					{
						System.out.println("Element found at index " + index +"\n");
					}
					break;
				case 5:
					System.out.println("\n Enter value to remove element : ");
					x = Double.valueOf(in.readLine());
					success = list.remove(x);	
					if(success)
					{
						System.out.println(x + " removed from list\n");
					}
					else
					{
						System.out.println("Element not found in list!");
					}
					break;
				case 6:
					if(list.size() == 0)
					{
						System.out.println("List is empty.");
					}
					else
					{					
					list.disp();
					}
					break;
				case 7:
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
	Node previous = null;
	public Node(double x)
	{
		data = x;
	}
}
class List
{

	Node front = null;
	Node rear = null;
	int count = 0;
	
	public boolean isEmpty()
	{
		return front == null;
	}

	public int size()
	{
		return count;
	}

	public boolean add(Double x)
	{
		Node node = new Node(x);
		if(count == 0)
		{
			front = node;
			rear = node;
		}
		else
		{
			rear.next = node;
			node.previous = rear;
			rear = node;
			if(count == 1)
			{
				front.next = rear;
				rear.previous = front;
			}
		}
		count ++;
		return true;
	}
	public boolean addAt(Double x, int index)
	{		
		if(index > count) return false;
		Node new_node = new Node(x);
		if(index == count)
		{
			rear.next = new_node;
			new_node.previous = rear;
			rear = new_node;
		}
		else if(index == 0)
		{
			new_node.next = front.next;
			front = new_node;
		}
		else
		{
			Node node = front;
			for(int i=0; i<index; i++)
			{
				node = node.next;
			}
			node.previous.next = new_node;
			new_node.next = node;
			node.previous = new_node;
		}
		count++;
		return true;
	}
	public double removeAt(int index)
	{
		if (index >= count) return -1;
		if(count == 1)
		{
			double tmp = front.data;
			front = null;
			front = null;
			count = 0;
			return tmp;
		}
		if(index == 0)
		{
			double tmp = front.data;
			front = front.next;
			count--;
			return tmp;
		}
		Node node = front;
		for(int i = 1; i < index; i++)
		{			
			node = node.next;
		}
		double temp = node.data;
		node.previous.next = node.next;
		if(node.next == null)
		{
			rear = node.previous;
		}
		else
		{
			node.previous.next.previous = node.previous;
		}

		count--;
		return temp;
	}

	public boolean remove(double x)
	{
		Node node = front;
		for(int i = 0; i< count; i++)

		{
			if(node.data == x)
			{
				if(i == 0)
				{
					front = front.next;
					count--;
					if(count == 1)
					{
						rear = front;
					}
					return true;
				}
				node.previous.next = node.next;
				if(node.next == null)
				{
					rear = node.previous;
				}
				else
				{
					node.previous.next.previous = node.previous;
				}
				count--;
				return true;
			}
			else
			{
				node = node.next;
			}
		}
		return false;
	}

	public int search(double x)
	{
		Node node = front;
		int i = -1;
		while(node != null)
		{
			i++;
			if(node.data == x)
			{
				return i;
			}
			else
			{
				node = node.next;
			}
		}
		return -1;
	}

	public void disp()
	{
		Node node = front;
		System.out.println("--------------");
		while(node != null)
		{
			System.out.println(node.data);
			node = node.next;
		}
		System.out.println("--------------");
	}

}
