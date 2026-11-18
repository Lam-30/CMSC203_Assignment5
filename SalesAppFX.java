package Project5;

/*
 * Class: CMSC203-22537
 * Instructor: Ahmed Tarek
 * Description: JavaFX graphical interface for the Store Sales
 * Data Processor. Allows users to load sales data, display 
 * statistics, and save a summary to an output file.
 * Due: 11/19/2026
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print your Name here: ____Luis Aguero______
*/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class SalesAppFX extends Application
{
	private double[][] salesData;
	
	private TextField inputFileField;
	private TextField outputFileField;
	private TextArea resultsArea;
	
	/**
	 * Creates and displays the JavaFX application window.
	 * 
	 * @param primaryStage main application window
	 */
	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Store Sales Data Processor");
		
		Label inputLabel = new Label("Input File Name:");
		
		inputFileField = new TextField();
		inputFileField.setPromptText("Enter salesdata.txt");
		
		Label outputLabel = new Label("Output File Name:");
		
		outputFileField = new TextField();
		outputFileField.setPromptText("Enter sales_summary.txt");
		
		resultsArea = new TextArea();
		resultsArea.setEditable(false);
		resultsArea.setPrefRowCount(15);
		resultsArea.setPromptText("Sales results will appear here.");
		
		Button loadButton = new Button("Load & Process");
		Button saveButton = new Button("Save Summary");
		
		loadButton.setOnAction(e -> loadAndProcess());
		
		saveButton.setOnAction(e -> saveSummary());
		
		HBox buttonBox = new HBox(10, loadButton, saveButton);
		
		VBox layout = new VBox(10);
		
		layout.setPadding(new Insets(15));
		
		layout.getChildren().addAll(
				new Label("STORE SALES DATA PROCESSOR"),
				inputLabel,
				inputFileField,
				outputLabel,
				outputFileField,
				buttonBox,
				new Label("Sales Results:"),
				resultsArea);
		
		Scene scene = new Scene(layout, 500, 550);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * Loads sales information from the input file and displays
	 * all calculated sales statistics in the results area.
	 */
	private void loadAndProcess()
	{
		String inputFilename = inputFileField.getText().trim();
		
		if (inputFilename.isEmpty())
		{
			resultsArea.setText("Please enter an input filename.");
			return;
		}
		
		try {
			
			salesData = SalesFileIO.readSalesData(inputFilename);
			
			if (SalesDataUtility.getMaxColumns(salesData) == 0)
			{
				salesData = null;
				resultsArea.setText("The input file contains no sales data.");
				return;
			}
			
			StringBuilder results = new StringBuilder();
			
			results.append("STORE SALES SUMMARY\n");
			results.append("==============================\n\n");
			
			results.append(String.format(
					"Total sales: %.2f%n",
					SalesDataUtility.getTotal(salesData)));
			
			results.append(String.format(
					"Average sale: %.2f%n",
					SalesDataUtility.getAverage(salesData)));
			
			results.append(String.format(
					"Highest sale: %.2f%n",
					SalesDataUtility.getHighestInArray(salesData)));
					
			results.append(String.format(
					"Lowest sale: %.2f%n",
					SalesDataUtility.getLowestInArray(salesData)));
			
			results.append("\nDEPARTMENT TOTALS\n");
			
			for (int row = 0; row < salesData.length; row++)
			{
				results.append(String.format(
						"Row %d total: %.2f%n",
						row,
						SalesDataUtility.getRowTotal(salesData, row)));
			}
			
			results.append("\nCOLUMN TOTALS\n");
			
			int maxColumns = SalesDataUtility.getMaxColumns(salesData);
			
			for (int col = 0; col < maxColumns; col++)
			{
				results.append(String.format(
						"Column %d total: %.2f%n",
						col,
						SalesDataUtility.getColumnTotal(salesData, col)));
			}
			
			results.append("\nProcessing completed successfully!");
			
			resultsArea.setText(results.toString());
			
		}catch (FileNotFoundException e)
		{
			salesData = null;
			
			resultsArea.setText(
					"Error: Input file was not found.\n" + e.getMessage());
			
		}catch (NumberFormatException e)
		{
			salesData = null;
			
			resultsArea.setText(
					"Error: The input file contains invalid numbers.");
			
		}catch (Exception e)
		{
			salesData = null;
			
			resultsArea.setText(
					"Error processing file: " + e.getMessage());
		}
	}
	
	/**
	 * Saves the previously loaded sales data to the output file.
	 * Displays a message if the data or output filename is missing.
	 */
	private void saveSummary()
	{
		String outputFilename = outputFileField.getText().trim();
		
		if (salesData == null)
		{
			resultsArea.setText("Please load and process sales data first.");
			
			return;
		}
		
		if (outputFilename.isEmpty())
		{
			resultsArea.setText("Please enter an output filename.");
			
			return;
		}
		
		try {
			
			SalesFileIO.writeSummary(outputFilename, salesData);
			
			resultsArea.appendText(
					"\n\nSummary successfully saved to: "
					+ outputFilename);
			
		} catch (FileNotFoundException e)
		
		{
			resultsArea.appendText("\n\nError saving file: " + e.getMessage());
		}
	}
	
	/**
	 * Launches the JavaFX application.
	 * 
	 * @param args command-line arguments
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
}
