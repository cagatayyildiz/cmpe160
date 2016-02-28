package tr.edu.boun.cmpe.experimental;

import java.util.Random;

import tr.edu.boun.cmpe.objectvisualizer.ObjectVisualizerContext;

public class CagatayMain {

	
	public static void main(String[] args) throws Exception {
		ObjectVisualizerContext context = new ObjectVisualizerContext();
		BounArrayList list = new BounArrayList(context);
		Random r = new Random();
		for (int i=0; i<20; i++) {
			list.insert(r.nextInt(100));
		}
	}
}
