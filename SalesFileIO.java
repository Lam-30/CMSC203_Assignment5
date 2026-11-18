package Project5;

/*
 * Class: CMSC203-22537
 * Instructor: Ahmed Tarek
 * Description: Handles reading sales info from an input
 * file and writing calculated sales summaries to an output file.
 * Due: 11/19/2026
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: ____Luis Aguero______
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;

public class SalesFileIO 
{
	/**
	 * Reads sales information from a text file.
	 * Each line becomes a separate row in the ragged array.
	 * 
	 * @param filename name of the input file
	 * @return ragged two-dimensional sales array
	 * @throws FileNotFoundException if the file cannot be found
	 */
	public static double[][] readSalesData(String filename)
		throws FileNotFoundException
		{
			List<double[]> rows = new ArrayList<>();
			
			try (Scanner input = new Scanner(new File(filename)))
			{
				while (input.hasNextLine())
				{
					String line = input.nextLine().trim();
					
					//Skip empty lines.
					if (line.isEmpty())
					{
						continue;
					}
					
					String[] values = line.split("\\s+");
					
					double[] currentRow = new double[values.length];
					
					for (int col = 0; col < values.length; col++)
					{
						currentRow[col] = Double.parseDouble(values[col]);
					}
					
					rows.add(currentRow);
				}
			}
			
			double[][] salesData = new double[rows.size()][];
			
			for (int row = 0; row < rows.size(); row++)
			{
				salesData[row] = rows.get(row);
			}
			
			return salesData;
		}
	
	/**
	 * Writes sales totals and statistics to an output file.
	 * 
	 * @param filename name of the output file
	 * @param data sales information
	 * @throws FileNotFoundException if the output file cannot be created
	 */
	public static void writeSummary(String filename, double[][] data)
		throws FileNotFoundException
		{
		try (PrintWriter output = new PrintWriter(filename))
		{
			output.printf(Locale.US, "Total sales: %.2f%n", SalesDataUtility.getTotal(data));
			
			output.printf(Locale.US, "Average sale: %.2f%n", SalesDataUtility.getAverage(data));
			
			output.println();
			
			//Each department's total.
			for (int row = 0; row < data.length; row++)
			{
				output.printf(Locale.US, "Row %d total: %.2f%n", row, SalesDataUtility.getRowTotal(data, row));
			}
			
			output.println();
			
			// Each possible column's total.
			int maxColumns = SalesDataUtility.getMaxColumns(data);
			
			for (int col = 0; col < maxColumns; col++)
			{
				output.printf(Locale.US, "Columns %d total: %.2f%n", col, SalesDataUtility.getColumnTotal(data, col));
			}
			
			output.println();
			
			output.printf(Locale.US, "Highest sale: %.2f%n", SalesDataUtility.getHighestInArray(data));
			
			output.printf(Locale.US,  "Lowest sale: %.2f%n", SalesDataUtility.getHighestInArray(data));
		}
	}
}
