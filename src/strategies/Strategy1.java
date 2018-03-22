package strategies;

import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;
import setIntersectionFinder.AbstractIntersectionFinder;
import solutions.p1p2Solution;
import solutions.p3Solution;
import solutions.p4Solution;

public class Strategy1<E> extends AbstractStrategiesTimeCollection<E> {

	AbstractIntersectionFinder<E> strategyToInspect;

	public Strategy1(String name, AbstractIntersectionFinder<E> strategy) {
		super(name);

		if (strategy instanceof p1p2Solution && strategy.getName().equals("P1"))
			strategyToInspect = new p1p2Solution("P1");
		else if (strategy instanceof p1p2Solution && strategy.getName().equals("P2"))
			strategyToInspect = new p1p2Solution("P2");
		else if (strategy instanceof p3Solution)
			strategyToInspect = new p3Solution("P3");
		else if (strategy instanceof p4Solution)
			strategyToInspect = new p4Solution("P4");
	}

	@Override
	public void runTrial(Integer[][][] data) {
		MySet<E>[] set1Obj = (MySet<E>[]) new MySet[data.length];

		try {
			MySet<E> eSet;
			for (int j = 0; j < data.length; j++) {

				if (this.strategyToInspect instanceof p1p2Solution && this.stratName.equals("P1"))
					eSet = (Set1<E>) new Set1<>();
				else 
					eSet = (Set2<E>) new Set2<>(); 

				for (int i = 0; i < data[j].length; i++) {
					for (int k = 0; k < data[j][i].length; k++) {
						eSet.add((E) data[j][i][k]);
					}
				}
				set1Obj[j] = eSet;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		strategyToInspect.intersectSets(set1Obj);
	}

	@Override
	public void incSum(int estimatedTime) {
		setSum(getSum() + estimatedTime);

	}

}
