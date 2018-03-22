package solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import interfaces.MySet;
import mySetImplementations.Set2;
import setIntersectionFinder.AbstractIntersectionFinder;

public class p4Solution<E> extends AbstractIntersectionFinder<E> {

	public p4Solution(String name) {
		super(name);
	}

	public MySet<E> intersectSets(MySet<E>[] t) {
		ArrayList<Integer> allElements = new ArrayList<>();
		 
		for (int k = 0; k < t.length; k++) {
			for(E e : t[k]) {
				allElements.add((Integer) e);
			}
		}
		
		HashMap<E, Integer> map = new HashMap<>(); 
		for (Integer e : allElements) { 
		     Integer c = map.getOrDefault(e, 0); 
		     map.put((E) e, c+1); 
		}
		
		MySet<E> mySet2 = new Set2<>(); 
		for (Map.Entry<E, Integer> entry : map.entrySet())
		     if (entry.getValue() == t.length) 
		        mySet2.add(entry.getKey()); 

		return mySet2;
	}

}
