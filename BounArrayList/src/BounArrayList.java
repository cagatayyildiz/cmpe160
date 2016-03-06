import java.util.ArrayList;

/**
 * An alternative implementation of the ArrayList data structure. 
 * Appropriate to be used for pedagogical purposes as the most basic goal in this implementation
 * is to visualize details of how operations on an ArrayList are performed in memory.
 * For default implementation, see http://hg.openjdk.java.net/jdk7/jdk7/jdk/file/tip/src/share/classes/java/util/ArrayList.java
 *  
 * @author cagatay
 *
 * @param <T> type of the data stored
 */
public class BounArrayList<T> {

	/**
	 * the amount of time spent by default between each action in the simulation (in ms)
	 */
	private static final double DEFAULT_SIMULATION_SPEED = 1000;

	/**
	 * the default size of the ArrayList stored backgroud
	 */
	private static final int DEFAULT_ARRAYLIST_SIZE = 10;
	
	/**
	 * the explicit storage of the data
	 */
	private ArrayList<T> data;
	
	/**
	 * the amount of time spent between each action in the simulation (in ms)
	 */
	private double sim_speed;
	
	/**
	 * the size of the ArrayList stored in the background
	 */
	private int size;
	
	public BounArrayList() {
		this(DEFAULT_SIMULATION_SPEED);
	}
	
	public BounArrayList(double speed) {
		data = new ArrayList<T>();
		sim_speed = speed;
		size = DEFAULT_ARRAYLIST_SIZE;
	}
	
	
}
