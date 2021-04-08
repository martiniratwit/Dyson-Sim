package sample;
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
    
    public double[] getDefaults()
    {
    	return this.model.getSatelliteValues(null);
    }
    
    public void addSatellite(double costPerSqFootage, double sqFootage, double fuelPrice, 
    		double fuelTons, double productionCost, double launchCost, Box box)
    {
    	this.model.addSatellite(costPerSqFootage, sqFootage, fuelPrice, fuelTons, productionCost, launchCost, box);
    	this.getNumSatellites();
    }
    
    public void satelliteWindow(Box box) 
    {
    	double[] values = this.model.getSatelliteValues(box);
    	double total = this.model.getTotalCost(values);
    	this.view.setSatelliteWindow(values, total);
    	//Implement pause function on click when added
    }
    
    public void getNumSatellites()
    {
    	this.view.setNumSatellites(this.model.getNumSatellites());
    }
    
    public void reset()
    {
    	this.view.reset(this.getDefaults());
    	this.model.reset();
    	this.getNumSatellites();
    }
}
