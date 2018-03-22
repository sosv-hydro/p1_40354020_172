package strategies;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

import interfaces.StrategiesTimeCollection;

public abstract class AbstractStrategiesTimeCollection<E> implements StrategiesTimeCollection<Integer>{
	
	private float sum; //sum of all the times in each trial 
	private float average; 
	private List<SimpleEntry<Integer, Float>> list; 
	public String stratName; 
//	private float total; 
	
	public AbstractStrategiesTimeCollection(String name) {
		list = new ArrayList<>();
		this.stratName = name; 
	}
	
	public float getSum() {
		return sum;
	}
	
	public void setSum(float sum) {
		this.sum = sum;
	}
	
	public float getAverage() {
		return average;
	}
	
	public void setAverage(float average) {
		this.average = average;
	}

	public void add(SimpleEntry<Integer, Float> simpleEntry) {
		list.add(simpleEntry);
	}
	
	public void resetSum() {
		sum = 0; 
	}
	
	public List<SimpleEntry<Integer, Float>> getList() {
		return list; 
	}
	
	
	
}
