import java.io.*;

public class MatrixOps
{
	public static void main(String args[]) throws IOException
	{
		int m, n, p, q, op = 1;
		while (op > 0 && op < 3)
		{		
			print("Enter dimensions of matrix 1 : ");
			m = readInt();
			n = readInt();
			print("Enter dimensions of matrix 2 : ");
			p = readInt();
			q = readInt();
			if (n != p && n!=q)
			{
				print("Dimension mismatch!");
			}
			else
			{
				Matrix a = new Matrix(m, n);
				Matrix b = new Matrix(p, q);
				print("\nEnter matrix 1 elements :\n");
				a.read();
				print ("\n Matrix 1 :");
				a.display();
				print ("\nEnter matrix 2 elements :\n");
				b.read();
				print ("\n Matrix 2 :");
				b.display();
				print ("\n 1 - Matrix Addition \n 2 - Matrix Multiplication \n 3 - Cancel\n");
				op = readInt();
				if(op == 1)	
				{
					Matrix c = Matrix.add(a, b);
					if (c == null)
					{
						print("Dimension mismatch! Addition not possible.");
					}
					else
					{
						c.display();
					}
				}
				else if(op == 2)
				{
					Matrix c = Matrix.dot(a, b);
					if (c == null)
					{
						print("Dimension mismatch! Multiplication not possible.");
					}
					else
					{
						c.display();
					}				
				}
			
			}
		}
	}
	static void print(String x)
	{
		System.out.println(x);
	}
	static String read() throws IOException
	{
		DataInputStream in = new DataInputStream(System.in);
		return in.readLine();
	}
	static int readInt() throws IOException
	{
		return Integer.parseInt(read());
	}
}

class Matrix
{
	double matrix[][] = new double[10][10];
	int m = 10;
	int n = 10;
	public Matrix(int r, int c)
	{
		matrix = new double[r][c];
		m = r;
		n = c;
		
	}
	public Matrix()
	{}
	public double getItem(int i, int j)
	{
		return matrix[i][j];
	}
	public void setItem(int i, int j, double x)
	{
		matrix[i][j] = x;
	}
	public void display()
	{
		System.out.println("\n------------------------\n");
		for(int i=0; i<m; ++i)
		{
			String row = "";
			for(int j=0; j<n; j++)
			{
				row += " " + ((Integer)(((Double)(matrix[i][j])).intValue())).toString();
			}
			System.out.println(row);
			System.out.println("\n");
		}
		System.out.println("------------------------\n");
	}
	public int height()
	{
		return m;
	}
	public int width()
	{
		return n;
	}
	public void read() throws IOException 
	{
		DataInputStream in = new DataInputStream(System.in);
		for(int i=0; i<m; ++i)
			for(int j=0; j<n; ++j)
				matrix[i][j] = Double.valueOf(in.readLine());
	}
	public static Matrix add(Matrix a, Matrix b)
	{
		int h = a.height();
		int w = a.width();
		if (h != b.height() || w != b.height())
		{
			return null;
		}
		Matrix c = new Matrix(h, w);
		for (int i=0; i<h; ++i)
			for(int j=0; j<w; ++j)
				c.setItem(i, j, a.getItem(i, j) + b.getItem(i, j));
		return c;
	}
	public static Matrix dot(Matrix a, Matrix b)
	{
		int _m = a.height();
		int _n = a.width();
		int _p = b.height();
		int _q = b.width();
		if (_n != _p)
		{
			return null;
		}
		Matrix c = new Matrix(_m, _q);
		for (int i=0; i<_m; ++i)
		{
			for(int j=0; j<_q; ++j)
			{
				for(int k=0; k<_n; k++)
					c.setItem(i, j, c.getItem(i, j) + (a.getItem(i, k) + b.getItem(k, j)));
			}
		}
		return c;
	}
}
