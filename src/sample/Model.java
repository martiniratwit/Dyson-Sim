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
    
    //Gets a given clicked satellite object
    public Satellite getSatellite(Box box)
    {
    	for(int count = 0; count < satellites.size(); count++)
    	{
    		if(satellites.get(count).getBox().equals(box))
    		{
    			return satellites.get(count);
    		}
    	}
    	return null;
    }
    
    //Returns the total cost of given satellite based on clicked box object
    public double getTotalCost(Box box) 
    {   
    	Satellite sat = getSatellite(box);
    	if(sat == null)
    	{
    		System.out.println("Error getting sat values, using default values");
    		return DEFAULT.getTotalCost();
    	}
    	return sat.getTotalCost();
    }
    
    //Overloaded, for where values already found using box
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
    
    public double getArrayCost()
    {
    	double total = 0;
    	for(Satellite satellite : this.satellites)
    	{
    		total += satellite.getTotalCost();
    	}
    	return total;
    }
    
    public void reset()
    {
    	this.satellites.clear();
    	this.numSatellites = 0;
    }
}