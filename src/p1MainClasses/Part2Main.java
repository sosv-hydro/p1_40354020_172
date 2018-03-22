package p1MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Random;

import interfaces.StrategiesTimeCollection;
import mySetImplementations.Set2;
import solutions.p1p2Solution;
import solutions.p3Solution;
import solutions.p4Solution;
import strategies.Strategy1;

public class Part2Main {

	public static <E> void main(String args[]) {

		int n = 20;
		int m = 50;
		int isize = 10;
		int fsize = 200;
		int istep = 2;
		int rep = 2;

		if (args.length <= 3) {

			if (args.length >= 1)
				n = Integer.parseInt(args[0]);
			if (args.length >= 2)
				m = Integer.parseInt(args[1]);
			if (args.length == 3)
				isize = Integer.parseInt(args[2]);
			if (args.length == 4)
				fsize = Integer.parseInt(args[2]);
			if (args.length == 5)
				istep = Integer.parseInt(args[2]);
			if (args.length == 6)
				rep = Integer.parseInt(args[2]);
		} else
			System.out.println("Invalid number of parameters. Must be <= 2.");

		StrategiesTimeCollection<E>[] resultsPerStrategy = (StrategiesTimeCollection<E>[]) new StrategiesTimeCollection[4];

		StrategiesTimeCollection strategy1 = (Strategy1) new Strategy1("P1", new p1p2Solution("P1"));
		StrategiesTimeCollection strategy2 = (Strategy1) new Strategy1("P2", new p1p2Solution("P2"));
		StrategiesTimeCollection strategy3 = (Strategy1) new Strategy1("P3", new p3Solution("P3"));
		StrategiesTimeCollection strategy4 = (Strategy1) new Strategy1("P4", new p4Solution("P4"));

		resultsPerStrategy[0] = (strategy1);
		resultsPerStrategy[1] = (strategy2);
		resultsPerStrategy[2] = (strategy3);
		resultsPerStrategy[3] = (strategy4);

		int count = 0; 
		
		for (int size = isize; size <= fsize; size += istep) {

			for (StrategiesTimeCollection<E> strategy : resultsPerStrategy)
				strategy.resetSum(); // set to 0 an internal instance variable used to accumulate sum of times

			// Run all trials for the current size.
			for (int r = 0; r < rep; r++) {

		//		DataGenerator generator = new DataGenerator(n, m, size);
				Integer[][][] dataset = (Integer[][][]) generateData(n,m,size);

				for (StrategiesTimeCollection<E> strategy : resultsPerStrategy) {
					long startTime = System.nanoTime(); // Measure system’s clock time before.
					
					System.out.println("run trial " + count++);
					
					strategy.runTrial((E[][][]) dataset); // Run the strategy using the data in dataset.
					long endTime = System.nanoTime(); // Measure system’s clock time after.
					
					int estimatedTime = (int) (endTime - startTime); // The estimated time.
					// Accumulate the estimated time (add it) to sum of times that the current
					// strategy has
					// so far accumulated on the previous trials for datasets of the current size.
					strategy.incSum(estimatedTime);
				}
			}

			// For each strategy, compute the average time for the current size.
			for (StrategiesTimeCollection<E> strategy : resultsPerStrategy)
				strategy.add((SimpleEntry<E, Float>) new AbstractMap.SimpleEntry<Integer, Float>(size,
						(Math.abs(strategy.getSum()) / ((float) rep))));
		}
		
		try {
			allStrategyResults((StrategiesTimeCollection<Object>[]) resultsPerStrategy);
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void allStrategyResults(StrategiesTimeCollection<Object>[] results)
			throws UnsupportedEncodingException, FileNotFoundException {
		PrintWriter writer = new PrintWriter(new File("").getAbsolutePath() + "/bin/ExperimentalResults/allResults.txt",
				"UTF-8");
		writer.write("size\tP1\tP2\tP3\tP4\r\n");
		String print = "";
		for (int x = 0; x < results[0].getList().size(); x++) { // for each size
			for (int i = 0; i < results.length; i++) { // for each strategy
				if (i == 0) {
					writer.write(Integer.toString((results[i].getList().get(x).getKey()))); // write the size
																							// appropriate to the row
					print += Integer.toString((results[i].getList().get(x).getKey()));
				}
				writer.write("\t" + Float.toString(results[i].getList().get(x).getValue())); // Add the time in the
																								// appropriate column
				print += "\t" + Float.toString(results[i].getList().get(x).getValue());
			}
			writer.write("\r\n");// Line break for the next set of times per size
			System.out.println(print);
			print = "";
		}
		writer.close();
	}
	
	public static Object[][][] generateData(int n, int m, int size) {
		Object[][][] dataSet = new Integer[n][m][];
		Random rnd = new Random(); 
	//	generateSizes();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// HashSet<Integer> set = new HashSet<>();
				Set2<Integer> set = new Set2<>();
				while (set.size() != size) {
					set.add(rnd.nextInt(size*2));
				}
				
				// add a common value to sets in row 0
				if (i == 0)
					set.add(size*2);
				dataSet[i][j] = (Integer[]) set.toArray(new Integer[0]);
			}
		}

	//	System.out.println("n = " + n + "  m = " + m);
		return dataSet;
	}
	

}
