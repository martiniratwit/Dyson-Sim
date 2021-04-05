package sample;
/*
        A = Solar Panel Cost (10$per sq ft)
        B = Square Footage ~500
        C = Production cost (constant 1mil per)
        D = Fuel Cost(1.65$ per gallon 3ton 770g/t)
        E = Launch Cost (Constant 20mil)
*/

import java.util.ArrayList;
import javafx.scene.shape.Box;


public class Model 
{

	private ArrayList<Satellite> satellites;
	
	private final Satellite DEFAULT = new Satellite(10, 500, 1270.5, 3, 1000000, 20000000, null);;
	
    //Only deals with creating the array to store new satellites
	public Model() 
    {
    	this.satellites = new ArrayList<>();
    }

    //Adds new satellite to satellites arraylist
	//Occurs when add satellite button is clicked in view, and controller sends request
    public void addSatellite(double costPerSqFootage, double sqFootage, double fuelPrice, 
    		double fuelTons, double productionCost, double launchCost, Box box)
    {
    	satellites.add(new Satellite(costPerSqFootage, sqFootage, fuelPrice, fuelTons, productionCost, launchCost, box));
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
    							 DEFAULT.getFuelPrice(), DEFAULT.getFuelTons(), 
    							 DEFAULT.getProductionCost(), DEFAULT.getLaunchCost()};
    	}
    	
    	//Check through every sat for corresponding box object
    	for(Satellite satellite : satellites)
    	{
    		if(satellite.getBox().equals(box))
    		{
    			return new double[] {satellite.getCostPerSqFootage(), satellite.getSqFootage(),
    								 satellite.getFuelPrice(), satellite.getFuelTons(),
    								 satellite.getProductionCost(), satellite.getLaunchCost()};
    		}
    	}
    	//Error value, should never happen
    	return null;
    }
    
    public void reset()
    {
    	satellites.clear();
    }
}