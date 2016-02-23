package tr.edu.boun.cmpe.objectvisualizer;

import javax.swing.JFrame;

/**
 * Frame to visualize every change on object movements.
 * @author triforce
 *
 */
public class VisualizerFrame {
	
	public static final int FRAME_WIDTH = 640;
	public static final int FRAME_HEIGHT = 480; 
	
	public static final int FPS = 60;
	
	private ObjectVisualizerContext context;
	
	private FrameUpdaterThread thread = new FrameUpdaterThread();
	
	private JFrame frame;
	private BounCanvas canvas;
	/**
	 * Standard constructor with a context object.
	 * @param context	Context object
	 */
	public VisualizerFrame(ObjectVisualizerContext context) {
		this.context = context;
		canvas = new BounCanvas(context);
		frame = new JFrame("Frame 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.getContentPane().add(canvas);
		frame.setVisible(true);
		thread.setRunning(true);
		
		thread.start();
	}
	
	/**
	 * Standard constructor with a context object and a frame
	 * title
	 * @param context	Context object
	 * @param title		Title of the frame
	 */
	public VisualizerFrame(ObjectVisualizerContext context, String title) {
		this.context = context;
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.getContentPane().add(canvas);
		frame.setVisible(true);
		thread.setRunning(true);
		
		thread.start();
	}
	
	/**
	 * Updates all the objects on the screen. Every update
	 * call starts the draw procedure immediately.
	 * @param dt		Time passed since the 
	 * 					last update call.
	 */
	private void update(long dt) {
		if(!context.getAddQueue().isEmpty()) {
			for (BounObject obj : context.getAddQueue()) {
				context.getObjectList().add(obj);
			}
			context.getAddQueue().clear();
		}
		
		for (BounObject obj : context.getObjectList()) {
			obj.updater(dt);
		}
		if(!context.getRemoveQueue().isEmpty()) {
			for (BounObject obj : context.getAddQueue()) {
				context.getObjectList().remove(obj);
			}
			context.getRemoveQueue().clear();
		}
		draw();
	}
	
	/**
	 * Draws all the objects on the frame.
	 * @param dt		Time passed since the 
	 * 					last update call.
	 */
	private void draw() {
		this.canvas.repaint();
	}
	
	/**
	 * Separate thread to control update calls from UI controller.
	 * @author triforce
	 *
	 */
	public class FrameUpdaterThread extends Thread {
		
		private boolean running = false;
		private long lastTime = 0;
		
		public FrameUpdaterThread() {
			super();
		}
		
		@Override
		public void run() {
			lastTime = System.currentTimeMillis();
			while(running) {
				long currentTime = System.currentTimeMillis();
				long dt = currentTime - lastTime;
				lastTime = currentTime;
				//dt = Math.min(dt, 20);
				update(dt);
				
				
				try {
					Thread.sleep(1000 / VisualizerFrame.FPS); // frame-rate delimiter
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public boolean isRunning() {
			return running;
		}

		public void setRunning(boolean running) {
			this.running = running;
		}
		
		
	}
	
}
