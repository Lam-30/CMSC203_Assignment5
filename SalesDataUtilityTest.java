package Project5;

/*
 * Class: CMSC203-22537
 * Instructor: Ahmed Tarek
 * Description: JUnit 5 test class for SalesDataUtility.
 * Tests totals, averages, row and column calculations,
 * and highest and lowest sales values.
 * Due: 11/19/2026
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: ____Luis Aguero______
*/

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SalesDataUtilityTest 
{
	private final double[][] salesData = {
			{100.50, 200.75, 150.00},
			{80.00, 90.00},
			{220.10, 130.50, 300.00, 50.25}
	};
	
	/**
	 * Tests the total of all sales values.
	 */
	@Test
	public void testGetTotal()
	{
		double expected = 1322.10;
		
		double actual = SalesDataUtility.getTotal(salesData);
		
		assertEquals(expected, actual, 0.001);
	}
	
	/**
	 * Tests the average of all sales values.
	 */
	@Test
	public void testGetAverage()
	{
		double expected = 146.90;
		
		double actual = SalesDataUtility.getAverage(salesData);
		
		assertEquals(expected, actual, 0.001);
	}
	
	/**
	 * Tests the total of a specific row.
	 */
	@Test
	public void testGetRowTotal()
	{
		double expected = 700.85;
		
		double actual = SalesDataUtility.getRowTotal(salesData, 2);
		
		assertEquals(expected, actual, 0.001);
	}
	
	/**
	 * Tests the total of a column in a ragged array.
	 */
	@Test
	public void testGetColumnTotal()
	{
		double expected = 421.25;
		
		double actual = SalesDataUtility.getColumnTotal(salesData, 1);
		
		assertEquals(expected, actual, 0.001);
	}
	
	/**
	 * Tests the highest value in a specific row.
	 */
	@Test
	public void testGetHighestInRow()
	{
		double expected = 300.00;
		
		double actual = SalesDataUtility.getHighestInRow(salesData, 2);
		
		assertEquals(expected, actual, 0.001);
	}
	
	/**
	 * Tests the lowest value in a specific row.
	 */
	@Test
	public void testGetLowestInRow()
	{
		double expected = 50.25;
		
		double actual = SalesDataUtility.getLowestInRow(salesData, 2);
		
		assertEquals(expected, actual, 0.001);
	}
	
	/**
	 * Tests the highest value in the entire array.
	 */
	@Test
	public void testGetHighestInArray()
	{
		double expected = 300.00;
		
		double actual = SalesDataUtility.getHighestInArray(salesData);
		
		assertEquals(expected, actual, 0.001);
	}
	
	/**
	 * Tests the lowest value in the entire array.
	 */
	@Test
	public void testGetLowestInArray()
	{
		double expected = 50.25;
		
		double actual = SalesDataUtility.getLowestInArray(salesData);
		
		assertEquals(expected, actual, 0.001);
	}
	
	/**
	 * Tests a column that is missing from some rows.
	 */
	@Test
	public void testRaggedColumn()
	{
		double expected = 50.25;
		
		double actual = SalesDataUtility.getColumnTotal(salesData, 3);
		
		assertEquals(expected, actual, 0.001);
	}
	
	/**
	 * Tests invalid row handling.
	 */
	@Test
	public void testInvalidRow()
	{
		assertEquals(
				0.0,
				SalesDataUtility.getRowTotal(salesData, 10),
				0.001);
	}
	
	/**
	 * Tests empty array handling.
	 */
	@Test
	public void testEmptyArray()
	{
		double[][] emptyData = {};
		
		assertEquals(0.0, SalesDataUtility.getTotal(emptyData), 0.001);
		
		assertEquals(0.0, SalesDataUtility.getAverage(emptyData), 0.001);
		
		assertTrue(Double.isNaN(SalesDataUtility.getHighestInArray(emptyData)));

		assertTrue(Double.isNaN(SalesDataUtility.getLowestInArray(emptyData)));

	}
}
