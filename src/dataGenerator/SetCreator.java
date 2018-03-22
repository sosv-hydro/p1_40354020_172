package dataGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;

public class SetCreator extends DataReader {

	private int n; // number of data generators (telephone companies in p1_4035_4020_172
	private int m; // number of data sets produced per data generator

	public SetCreator() throws FileNotFoundException {
		super();
		setParameters();

	}
	
	public static MySet<Object>[] setsFij(Integer[][][] datasets, int m, int n, String set) {
		MySet<Object>[] set1Obj = (MySet<Object>[]) new MySet[n];

		try {
			MySet<Object> eSet;
			for (int j = 0; j < n; j++) {
				if (set.equals("Set1")) {
					eSet = (Set1<Object>) new Set1<>();
				} else
					eSet = (Set2<Object>) new Set2<>();

				for (int i = 0; i < m; i++) {
					for (int k = 0; k < datasets[j][i].length; k++) {
						eSet.add(datasets[j][i][k]);
					}
				}
				set1Obj[j] = eSet;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return set1Obj;
	}
	
	public void setParameters() throws FileNotFoundException {
		String file = "testInputFiles";
		Scanner parameters = new Scanner(new File(file, "parameters2.txt"));
		// the values of n and m shall be read from file: "inputFiles/parameters.txt".
		this.n = parameters.nextInt();
		this.m = parameters.nextInt();
		parameters.close();
	}

	public int getN() {
		return n;
	}

	public int getM() {
		return m;
	}
	

}
