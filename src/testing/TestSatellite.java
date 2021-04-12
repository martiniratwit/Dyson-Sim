package testing;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Test;
import org.junit.Assert;


import org.junit.Assert;

import sample.Satellite;

public class TestSatellite
{
	public static void main(String[] args)
	{
		
	}
	
	//Negatives are not able to be entered into the text box due to View testText method
	public void testgetCPSF()
	{
		Satellite zero = new Satellite(0,0,0,0,0,0,null);
		Satellite one = new Satellite(1,0,0,0,0,0,null);
		Satellite two = new Satellite(1234,0,0,0,0,0,null);
		
		Assert.assertEquals(0,zero.getCostPerSqFootage(),0);
		Assert.assertEquals(1,one.getCostPerSqFootage(),0);
		Assert.assertEquals(1234,two.getCostPerSqFootage(),0);
	}
	
	public void testgetSF()
	{
		Satellite zero = new Satellite(0,0,0,0,0,0,null);
		Satellite one = new Satellite(0,1,0,0,0,0,null);
		Satellite two = new Satellite(0,1234,0,0,0,0,null);
		
		Assert.assertEquals(0,zero.getCostPerSqFootage(),0);
		Assert.assertEquals(1,one.getCostPerSqFootage(),0);
		Assert.assertEquals(1234,two.getCostPerSqFootage(),0);
	}

	
	
	
	
	
	
	
	
	
}