package sample;
import java.util.ArrayList;
import javafx.scene.shape.Box;


public class Model 
{

	private ArrayList<Satellite> satellites;
	
	private final Satellite DEFAULT = new Satellite(10, 500, 1270.5, 3, 1000000, 20000000, null);
	
	private int numSatellites;
	
    //Only deals with creating the array to store new satellites
	public Model() 
    {
    	this.satellites = new ArrayList<>();
    	numSatellites = 0;
    }

    //Adds new satellite to satellites arraylist
	//Occurs when add satellite button is clicked in view, and controller sends request
    public void addSatellite(double costPerSqFootage, double sqFootage, double fuelPrice, 
    		double fuelTons, double productionCost, double launchCost, Box box)
    {
    	this.satellites.add(new Satellite(costPerSqFootage, sqFootage, fuelPrice, fuelTons, productionCost, launchCost, box));
    	numSatellites++;
    }

    //Returns the total cost of given satellite based on clicked box object
    public double getTotalCost(Box box) 
    {   
    	//Gets values from satellite object
    	double[] values = getSatelliteValues(box);
    	
    	//Error checking, provides values with default sat values
    	if(values == null)
    	{
    		values = getSatelliteValues(null);
    		System.out.println("Error getting sat values, using default values");
    	}
    	return (values[0] * values[1]) + (values[2] * values[3]) + values[4] + values[5];
    }
    
    //Overloaded method for cases where values already found
    public double getTotalCost(double[] values)
    {
    	return (values[0] * values[1]) + (values[2] * values[3]) + values[4] + values[5];
    }
    
    //Used to easily transfer data in single request
    public double[] getSatelliteValues(Box box)
    {
    	//Checks to see if the default satellite values are needed
    	if(box == null)
    	{
    		return new double[] {DEFAULT.getCostPerSqFootage(), DEFAULT.getSqFootage(),
    							 DEFAULT.getFuelCost(), DEFAULT.getFuelTons(), 
    							 DEFAULT.getProductionCost(), DEFAULT.getLaunchCost()};
    	}
    	
    	//Check through every sat for corresponding box object
    	for(Satellite satellite : this.satellites)
    	{
    		if(satellite.getBox().equals(box))
    		{
    			return new double[] {satellite.getCostPerSqFootage(), satellite.getSqFootage(),
    								 satellite.getFuelCost(), satellite.getFuelTons(),
    								 satellite.getProductionCost(), satellite.getLaunchCost()};
    		}
    	}
    	//Error value, should never happen
    	return null;
    }
    
    public int getNumSatellites()
    {
    	return this.numSatellites;
    }
    
    public void reset()
    {
    	this.satellites.clear();
    	this.numSatellites = 0;
    }
}