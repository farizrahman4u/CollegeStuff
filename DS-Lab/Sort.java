import java.io.*;
import java.util.*;

public class Sort
{
	public static void main(String args[])
	{
		int a[] = {11, 21, 13, 40}, n = 4;
		Sorter.bubbleSort(a, n);
		for(int i=0; i<n; i++)
		{
			System.out.println(a[i]);
		}
	}

}

class Sorter
{
	public static void bubbleSort(int a[], int n)
	{
		boolean flag = false;
		for(int i=0; i<n; i++)
		{
			flag = false;
			for(int j=0; j<n-(i+1); j++)
			{
			
				if(a[j] > a[j+1])
				{
					flag = true;
					int t = a[j];
					a[j] = a[j+1];
					a[j+1] = t;
				}
			}
			if(!flag)
			{
				return;
			}
		}
	}

	public static void selectionSort(int a[], int n)
	{
		for(int i=n-1; i>=0; i--)
		{
			int imax = 0;
			int max = a[0];
			for(int j=1; j<=i; j++)
			{
				int x = a[j];
				if(x > max)
				{
					imax = j;
					max = x;
				}
			}
			int t = a[i];
			a[i] = max;
			a[imax] = t;
		}
	}

	public static void insertionSort(int a[], int n)
	{
		
	}
}
