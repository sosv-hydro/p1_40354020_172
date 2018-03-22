package solutions;

import java.util.ArrayList;

import interfaces.MySet;
import mySetImplementations.Set2;
import setIntersectionFinder.AbstractIntersectionFinder;

public class p3Solution<E> extends AbstractIntersectionFinder<E> {

	public p3Solution(String name) {
		super(name);
	}

	public MySet<E> intersectSets(MySet<E>[] t) {
		ArrayList<Integer> allElements = new ArrayList<>();
		 
		for (int k = 0; k < t.length; k++) {
			for(E e : t[k]) {
				allElements.add((Integer) e);
			}
		}

		allElements.sort(null);
		MySet<E> set2 = new Set2(); // sets in P3's solution are of type Set2
		E e = (E) allElements.get(0);
		Integer c = 1;
		for (int i = 1; i < allElements.size(); i++) {
			if (allElements.get(i).equals(e))
				c++;
			else {
				if (c == t.length)
					set2.add(e); // m is as in the previous discussion
				e = (E) allElements.get(i);
				c = 1;
			}
		}
		if (c == t.length)
			set2.add((E) allElements.get(allElements.size() - 1));

		return set2;
	}

}
