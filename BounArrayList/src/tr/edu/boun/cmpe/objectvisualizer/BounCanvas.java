package tr.edu.boun.cmpe.objectvisualizer;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BounCanvas extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private ObjectVisualizerContext context;
	
	public BounCanvas(ObjectVisualizerContext context) {
		super();
		this.context = context;
	}
	
	@Override
	public void paint(Graphics canvas) {
		canvas.setColor(Color.WHITE);
		canvas.fillRect(0, 0, VisualizerFrame.FRAME_WIDTH, VisualizerFrame.FRAME_HEIGHT);
		canvas.setColor(Color.BLACK);
		for (BounObject obj : context.getObjectList()) {
			obj.draw(canvas);
		}
	}

}
