package application;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;

public class AirlineController implements Initializable{
	//Establishing variables and creating a file path
	FileSystem fp = FileSystems.getDefault();
	Path p = fp.getPath("C:\\Users\\ryang\\OneDrive\\Documents\\ComputerSc\\flights.txt");
	BufferedReader buf;
	String s;
	InputStream input;
	String[] arr = new String[4];
	//Used a double array to store the data from the text file(gets the columns and the rows)
	//Also added more columns and rows just in case if wanting to add more data to the text file. Hence the [10][10]
	String[][] data = new String[10][10];
	String choice;
	int data_count = 0;
	
	//Created Constructor that inputs and reads through the text file
	public AirlineController() 
	{
		try 
	       {
	           input = new BufferedInputStream(Files.newInputStream(p));
	            buf = new BufferedReader(new InputStreamReader(input));
	            s=buf.readLine();
	            
	            
	           while(s != null) 
	           {
	        	   //Array splits on the delimiter "," and reads line again which increments that data_count
	                arr = s.split(",");
	                data[data_count] = arr;
	                s = buf.readLine();
	                data_count++;
	           } 
	      }
			catch(IOException e) 
		{
				e.printStackTrace();

		}
	}
	
	

	@FXML
	private RadioButton airlinebtn;

    @FXML
    private RadioButton airnumbtn;

    @FXML
    private RadioButton departbtn;

    @FXML
    private RadioButton arrivalbtn;

    @FXML
    private Button submitbtn;

    @FXML
    private Button clearbtn;

    @FXML
    private ComboBox<String> airnumcombo;

    @FXML
    private ComboBox<String> departcombo;

    @FXML
    private ComboBox<String> arrivalcombo;
    @FXML
    private ListView<String> listview;
    
    @FXML
    private ComboBox<String> airlinecombo;
    
    
    @FXML
    void OnArrival(ActionEvent event) {
    //the forloop loads the data into String Item and it is loaded and added into the choicebox
    	for (int i=0; i<data_count; i++) 
    	{
    		String item = data[i][3];
    		if(!arrivalcombo.getItems().contains(item));
    			arrivalcombo.getItems().add(item);
    	}
    //Setting the .setDisable method to its appropriate boolean value for the given choicebox
    	airnumcombo.setDisable(true);
    	departcombo.setDisable(true);
    	arrivalcombo.setDisable(false);
    	airlinecombo.setDisable(true);
    //Setting the .setSelected method to its appropriate boolean value for the given radio button
    //Which deselects the other radio buttons while the one selected is true
    	airnumbtn.setSelected(false);
    	departbtn.setSelected(false);
    	arrivalbtn.setSelected(true);
    	airlinebtn.setSelected(false);
    }

    @FXML
    void OnClear(ActionEvent event) {
    //Closing the program
    	Platform.exit();
    }

    @FXML
    void OnSearch(ActionEvent event) {
    //First clearing listview to clear existing data and inputing new data into the listview
    	listview.getItems().clear();
    	String listheader = "Airline \t " + "AirlineNo \t" + "Departure \t" + "Arrival";
		listview.getItems().add(listheader);
		String choice;
	//This if statement selects the radio button
		if(airlinebtn.isSelected()) 
		{
	//Choice gets the selected item in the selected choicebox and stores it in a string
			choice = airlinecombo.getSelectionModel().getSelectedItem();
			for (int i=0; i<data_count; i++) {
	    		String airlinename = data[i][0];
	//If choice equals the String where the data is stored it will print the data to the listview in the below if statement    		
				if(choice.equals(airlinename)) 
				{
					String alterrow = String.format("%-11s\t%-11s\t%-11s\t%-11s\t", data[i][0], data[i][1], data[i][2], data[i][3]);	
					listview.getItems().add(alterrow);
					
				}
	
	    	}
			
		}
		
		if(airnumbtn.isSelected()) 
		{
			choice = airnumcombo.getSelectionModel().getSelectedItem();
			for (int i=0; i<data_count; i++) 
			{
	    		String airnumname = data[i][1];
	    		
				if(choice.equals(airnumname)) 
				{
					String alterrow = String.format("%-11s\t%-11s\t%-11s\t%-11s\t", data[i][0], data[i][1], data[i][2], data[i][3]);
					listview.getItems().add(alterrow);
					
				}

			}
		}
		
		if(departbtn.isSelected()) 
		{
			choice = departcombo.getSelectionModel().getSelectedItem();
			for (int i=0; i<data_count; i++) 
			{
	    		String departname = data[i][2];
	    		
				if(choice.equals(departname)) 
				{
					String alterrow = String.format("%-11s\t%-11s\t%-11s\t%-11s\t", data[i][0], data[i][1], data[i][2], data[i][3]);
					listview.getItems().add(alterrow);
					
				}

			}
		}
		
		if(arrivalbtn.isSelected()) 
		{
			choice = arrivalcombo.getSelectionModel().getSelectedItem();
			for (int i=0; i<data_count; i++) 
			{
	    		String arrivalname = data[i][3];
	    		
				if(choice.equals(arrivalname)) 
				{
					String alterrow = String.format("%-11s\t%-11s\t%-11s\t%-11s\t", data[i][0], data[i][1], data[i][2], data[i][3]);
					listview.getItems().add(alterrow);
					
				}

			}
		}
    }
    
    

    @FXML
    void OnairNum(ActionEvent event) {
    	for (int i=0; i<data_count; i++) 
    	{
    		String item = data[i][1];
    		if(!airnumcombo.getItems().contains(item))
    			airnumcombo.getItems().add(item);
    	}
    	airnumcombo.setDisable(false);
    	departcombo.setDisable(true);
    	arrivalcombo.setDisable(true);
    	airlinecombo.setDisable(true);
    	
    	airnumbtn.setSelected(true);
    	departbtn.setSelected(false);
    	arrivalbtn.setSelected(false);
    	airlinebtn.setSelected(false);

    }

    @FXML
    void onAirline(ActionEvent event) 
    {
    	for (int i=0; i<data_count; i++) 
    	{
    		String item = data[i][0];
    		if(!airlinecombo.getItems().contains(item))
    			airlinecombo.getItems().add(item);
    	}
    	airnumcombo.setDisable(true);
    	departcombo.setDisable(true);
    	arrivalcombo.setDisable(true);
    	airlinecombo.setDisable(false);

    	airnumbtn.setSelected(false);
    	departbtn.setSelected(false);
    	arrivalbtn.setSelected(false);
    	airlinebtn.setSelected(true);
   
    }

    @FXML
    void onDepartAir(ActionEvent event) {
 
    	for (int i=0; i<data_count; i++) 
    	{
    		String item = data[i][2];
    		if(!departcombo.getItems().contains(item))
    			departcombo.getItems().add(item);
    	}
    	airnumcombo.setDisable(true);
    	departcombo.setDisable(false);
    	arrivalcombo.setDisable(true);
    	airlinecombo.setDisable(true);
    	
    	airnumbtn.setSelected(false);
    	departbtn.setSelected(true);
    	arrivalbtn.setSelected(false);
    	airlinebtn.setSelected(false);

    }
    
  
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		airlinecombo.setPromptText("Select Airline");
		airnumcombo.setPromptText("Select Airline Number");
		departcombo.setPromptText("Select Departure Airport");
		arrivalcombo.setPromptText("Select Arrival Airport");
		String listheader = "Airline \t " + "AirlineNo \t" + "Departure \t" + "Arrival";
		listview.getItems().add(listheader);
	
	}

}






