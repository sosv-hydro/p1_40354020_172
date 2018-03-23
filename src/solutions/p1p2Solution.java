package solutions;

import java.util.Iterator;

import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;
import setIntersectionFinder.AbstractIntersectionFinder;

public class p1p2Solution<E> extends AbstractIntersectionFinder<E> {

	public p1p2Solution(String name) {
		super(name);
	}

	@Override
	public MySet<E> intersectSets(MySet<E>[] t) {
		MySet<E> setIntersection = null;

		if (t[0] instanceof Set1)
			setIntersection = (Set1<E>) new Set1<>();

		if (t[0] instanceof Set2)
			setIntersection = (Set2<E>) new Set2<>();

		Iterator<E> iter = setIntersection.iterator();
		for (E e : t[0])
			setIntersection.add(e);

		for (int j = 1; j < t.length; j++) {
			for (E e : t[0]) {
				if (!t[j].contains((E) e))
					setIntersection.remove((E) e);
			}
		}

		return setIntersection;
	}

}
