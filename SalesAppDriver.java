package Project5;
/*
 * Class: CMSC203-22537
 * Instructor: Ahmed Tarek
 * Description: Console driver for the Store Sales Data Processor.
 * Prompts the user for filenames and displays sales statistics.
 * Due: 11/19/2026
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: ____Luis Aguero______
*/

import java.io.FileNotFoundException;
import java.util.Scanner;

public class SalesAppDriver 
{
	/**
	 * Main method that runs the console sales application.
	 * 
	 * @param args command-line arguments
	 */
	public static void main(String[] args) 
	{
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("====================================");
		System.out.println("  STORE SALES DATA PROCESSOR");
		System.out.println("====================================");
		
		System.out.print("Enter input file name: ");
		String inputFilename = keyboard.nextLine().trim();
		
		System.out.print("Enter output file name: ");
		String outputFilename = keyboard.nextLine().trim();
		
		try {
			
			double[][] salesData = SalesFileIO.readSalesData(inputFilename);
			
			if (SalesDataUtility.getMaxColumns(salesData) == 0)
			{
				System.out.println("The input file contains no sales data.");
				
				return;
			}
			
			System.out.println("\nSALES SUMMARY");
			System.out.println("---------------------------------");
			
			System.out.printf("Total sales: %.2f%n", SalesDataUtility.getTotal(salesData));
			
			System.out.printf("Average sales: %.2f%n", SalesDataUtility.getAverage(salesData));
			
			System.out.printf("Highest sales: %.2f%n", SalesDataUtility.getHighestInArray(salesData));

			System.out.printf("Lowest sales: %.2f%n", SalesDataUtility.getLowestInArray(salesData));

			System.out.println("\nDepartment Totals:");
			
			for (int row = 0; row < salesData.length; row++)
			{
				System.out.printf("Row %d total: %.2f%n", row, SalesDataUtility.getRowTotal(salesData, row));	
			}
			
			System.out.println("\nColumn Totals:");
			
			int maxColumns = SalesDataUtility.getMaxColumns(salesData);
			
			for (int col = 0; col < maxColumns; col++)
			{
				System.out.printf("Column %d total: %.2f%n", col, SalesDataUtility.getColumnTotal(salesData, col));
			}
			
			SalesFileIO.writeSummary(outputFilename, salesData);
			
			System.out.println("\nSummary successfully save to: " + outputFilename);
			
		} catch (FileNotFoundException e)
		{
			System.out.println("File error: " + e.getMessage());
			
		}catch (NumberFormatException e)
		{
			System.out.println("Input error: The file contains an invalid sales number.");
			
		}catch (Exception e)
		{
			System.out.println("An unexpected error occurred: " + e.getMessage());
		}
		
		keyboard.close();
	}

}
