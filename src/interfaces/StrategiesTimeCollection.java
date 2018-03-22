package interfaces;

import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public interface StrategiesTimeCollection<E> {

	public void runTrial(E[][][] data);
	
	public void resetSum(); 
	
	public void incSum(int estimatedTime);

	public void add(SimpleEntry<E, Float> simpleEntry);

	public float getSum();
	
	public List<SimpleEntry<Integer, Float>> getList();
}
