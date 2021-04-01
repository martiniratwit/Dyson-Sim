package sample;
/*
        A = Solar Panel Cost (10$per sq ft)
        B = Square Footage ~500
        C = Production cost (constant 1mil per)
        D = Fuel Cost(1.65$ per gallon 3ton 770g/t)
        E = Launch Cost (Constant 20mil)
*/

import javafx.scene.shape.Box;

public class Controller
{
	
	private View view;
	private Model model;
	
    public Controller(View view) 
    {
    	this.view = view;
    	this.model = new Model();
    }
    
    private double getTotalCost(Box box)
    {
    	double total = this.model.getTotalCost(box);
    	return total;
    }
    
    public void addSatellite(double costPerSqFootage, double sqFootage, double fuelPrice, 
    		double fuelTons, double productionCost, double launchCost, Box box)
    {
    	this.model.addSatellite(costPerSqFootage, sqFootage, fuelPrice, fuelTons, productionCost, launchCost, box);
    }
    
    public void satelliteWindow(Box box) 
    {
    	double[] values = this.model.getSatelliteValues(box);
    	double total = this.model.getTotalCost(values);
    	this.view.setSatelliteWindow(values, total);
    	//Implement pause function on click when added
    }
    
    public void reset()
    {
    	this.view.reset();
    	this.model.reset();
    }
}
