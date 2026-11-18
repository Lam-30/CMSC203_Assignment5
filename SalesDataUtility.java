package Project5;

/*
 * Class: CMSC203-22537
 * Instructor: Ahmed Tarek
 * Description: Provides static utility methods to calculate
 * sales totals, averages, and highest and lowest sales values.
 * Due: 11/19/2026
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: ____Luis Aguero______
*/

public class SalesDataUtility 
{
	/**
	 * Calculates the total of all sales in the array.
	 * 
	 * @param data ragged array containing sales information
	 * @return total sales
	 */
	public static double getTotal(double[][] data) 
	{
		double total = 0.0;
		
		if (data == null)
		{
			return total;
		}
		
		for (int row = 0; row < data.length; row++)
		{
			if (data[row] != null)
			{
				for (int col = 0; col < data[row].length; col++)
				{
					total += data[row][col];
				}
			}
		}
		
		return total;
	}
	
	/**
	 * Calculates the average of all sales values.
	 * 
	 * @param data ragged array containing sales information
	 * @return average sales value
	 */
	public static double getAverage(double[][] data)
	{
		double total = 0.0;
		int count = 0;
		
		if (data == null)
		{
			return 0.0;
		}
		
		for (int row = 0; row < data.length; row++)
		{
			if (data[row] != null)
			{
				for (int col = 0; col < data[row].length; col++)
				{
					total += data[row][col];
					count++;
				}
			}
		}
		
		if (count > 0)
		{
			return total / count;
		}
		
		return 0.0;
	}
	
	/**
	 * Calculates the total sales for a specific row.
	 * 
	 * @param data ragged sales array
	 * @param row requested row number
	 * @return row total
	 */
	public static double getRowTotal(double[][] data, int row)
	{
		double total = 0.0;
		
		if (data == null || row < 0 || row >= data.length || data[row] == null)
		{
			return total;
		}
		
		for (int col = 0; col < data[row].length; col++)
		{
			total += data[row][col];
		}
		
		return total;
	}
	
	/**
	 * Calculates the total for a specific column.
	 * Rows without the requested column are skipped.
	 * 
	 * @param data ragged sales array
	 * @param col requested column number
	 * @return column total
	 */
	public static double getColumnTotal(double[][] data, int col)
	{
		double total = 0.0;
		
		if (data == null || col < 0)
		{
			return total;
		}
		
		for (int row = 0; row < data.length; row++)
		{
			if (data[row] != null && col < data[row].length)
			{
				total += data[row][col];
			}
		}
		
		return total;
	}
	
	/**
	 * Finds the highest sales value in a particular row.
	 * 
	 * @param data ragged sales array
	 * @param row requested row
	 * @return highest value or NaN if the row is invalid or empty
	 */
	public static double getHighestInRow(double[][] data, int row)
	{
		if (data == null || row < 0 || row >= data.length
				|| data[row] == null || data[row].length == 0)
		{
			return Double.NaN;
		}
		
		double highest = data[row][0];
		
		for (int col = 1; col < data[row].length; col++)
		{
			if (data[row][col] > highest)
			{
				highest = data[row][col];
			}
		}
		
		return highest;
	}
	
	/**
	 * Finds the lowest sales value in a particular row.
	 * 
	 * @param data ragged sales array
	 * @param row requested row
	 * @param lowest value or NaN if the row is invalid or empty
	 */
	public static double getLowestInRow(double[][] data, int row)
	{
		if (data == null || row < 0 || row >= data.length
				|| data[row] == null || data[row].length == 0)
		{
			return Double.NaN;
		}
		
		double lowest = data[row][0];
		
		for (int col = 1; col < data[row].length; col++)
		{
			if (data[row][col] < lowest)
			{
				lowest = data[row][col];
			}
		}
		
		return lowest;
	}
	
	/**
	 * Finds the highest sales value in the entire array.
	 * 
	 * @param data ragged sales array
	 * @return highest value of NaN if no values exist
	 */
	public static double getHighestInArray(double[][] data)
	{
		if (data == null)
		{
			return Double.NaN;
		}
		
		double highest = Double.NaN;
		
		for (int row = 0; row < data.length; row++)
		{
			if (data[row] != null)
			{
				for (int col = 0; col < data[row].length; col++)
				{
					if (Double.isNaN(highest) || data[row][col] > highest)
					{
						highest = data[row][col];
					}
				}
			}
		}
		
		return highest;
	}
	
	/**
	 * Finds the lowest sales value in the entire array.
	 * 
	 * @param data ragged sales array
	 * @return lowest value or NaN if no values exist
	 */
	public static double getLowestInArray(double[][] data)
	{
		if (data == null)
		{
			return Double.NaN;
		}
		
		double lowest = Double.NaN;
		
		for (int row = 0; row < data.length; row++)
		{
			if (data[row] != null)
			{
				for (int col = 0; col < data[row].length; col++)
				{
					if (Double.isNaN(lowest) || data[row][col] < lowest)
					{
						lowest = data[row][col];
					}
				}
			}
		}
		
		return lowest;
	}
	
	/**
	 * Finds the maximum number of columns in any row
	 * 
	 * @param data ragged sales array
	 * @return maximum number of columns
	 */
	public static int getMaxColumns(double[][] data)
	{
		int maxColumns = 0;
		
		if (data == null)
		{
			return maxColumns;
		}
		
		for (int row = 0; row < data.length; row++)
		{
			if (data[row] != null && data[row].length > maxColumns)
			{
				maxColumns = data[row].length;
			}
		}
		
		return maxColumns;
	}
}
