package p1MainClasses;

import java.io.FileNotFoundException;

import dataGenerator.SetCreator;
import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;
import solutions.p1p2Solution;
import solutions.p3Solution;
import solutions.p4Solution;

public class Part1Main {

	public static <E> void main(String args[]) {
		try {
			SetCreator reader = new SetCreator();
			Integer[][][] datasets = (Integer[][][]) reader.readDataFiles();

			int m = reader.getM();
			int n = reader.getN();

			Set1 set1 = new Set1<>();
			Set2 set2 = new Set2<>();

			MySet<E>[] p1 = (MySet<E>[]) setsFij(datasets, m, n, "Set1");
			MySet<E>[] p2 = (MySet<E>[]) setsFij(datasets, m, n, "Set2");

			if (args.length ==1 && !args[0].isEmpty()) {
				switch (args[0]) {
				case "1":
					p1Solution(p1);
					break;
				case "2":
					p2Solution(p2);
					break;
				case "3":
					p3Solution(p2);
					break;
				case "4":
					p4Solution(p2);
					break;
				}
			}
			else 
			AllSolutions(p1, p2);
/*
			p1Solution(p1);
			p2Solution(p2);
			p3Solution(p2);
			p4Solution(p2);
*/
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}

	public static void p1Solution(MySet[] mySets) {
		p1p2Solution p1Intersect = new p1p2Solution("P1");
		System.out.println("Final Set by P1: " + p1Intersect.intersectSets(mySets));
	}

	public static void p2Solution(MySet[] mySets) {
		p1p2Solution p2Intersect = new p1p2Solution("P2");
		System.out.println("Final Set by P2: " + p2Intersect.intersectSets(mySets));
	}

	public static void p3Solution(MySet[] mySets) {
		p3Solution p3Intersect = new p3Solution("P3");
		System.out.println("Final Set by P3: " + p3Intersect.intersectSets(mySets));
	}

	public static void p4Solution(MySet[] mySets) {
		p4Solution p4Intersect = new p4Solution("P4");
		System.out.println("Final Set by P4: " + p4Intersect.intersectSets(mySets));
	}

	public static void AllSolutions(MySet[] mySets1, MySet[] mySets2) {
		p1p2Solution p1Intersect = new p1p2Solution("P1");
		System.out.println("Final Set by P1: " + p1Intersect.intersectSets(mySets1));
		
		p1p2Solution p2Intersect = new p1p2Solution("P2");
		System.out.println("Final Set by P2: " + p2Intersect.intersectSets(mySets2));

		p3Solution p3Intersect = new p3Solution("P3");
		System.out.println("Final Set by P3: " + p3Intersect.intersectSets(mySets2));

		p4Solution p4Intersect = new p4Solution("P4");
		System.out.println("Final Set by P4: " + p4Intersect.intersectSets(mySets2));

	}

	public static <E> MySet<E>[] setsFij(Integer[][][] datasets, int m, int n, String set) {
		MySet<E>[] set1Obj = (MySet<E>[]) new MySet[m];

		try {
			MySet<E> eSet;
			for (int j = 0; j < m; j++) {
				if (set.equals("Set1")) {
					eSet = (Set1<E>) new Set1<>();
				} else
					eSet = (Set2<E>) new Set2<>();

				for (int i = 0; i < n; i++) {
					for (int k = 0; k < datasets[i][j].length; k++) {
						eSet.add((E) datasets[i][j][k]);
					}
				}
				set1Obj[j] = eSet;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return set1Obj;
	}

	public static <E> void printSets(int n, int m, MySet<E>[] t) {
		System.out.println("Sets Fij are: ");
		for (int i = 0; i < m; i++)

			printArray(t[i].toArray());
	}

	private static void printArray(Object[] objects) {
		for (int i = 0; i < objects.length; i++)
			System.out.print(" " + objects[i] + "  ");
		System.out.println();
	}
}
