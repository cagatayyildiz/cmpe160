package tr.edu.boun.cmpe.objectvisualizer.widget;

import java.awt.Font;
import java.awt.Graphics;

import tr.edu.boun.cmpe.objectvisualizer.BounCanvas;
import tr.edu.boun.cmpe.objectvisualizer.BounObject;
import tr.edu.boun.cmpe.objectvisualizer.BounStaticTracker;
import tr.edu.boun.cmpe.objectvisualizer.ObjectVisualizerContext;
import tr.edu.boun.cmpe.objectvisualizer.exception.BounVisualizerException;

/**
 * Simple component to display text on canvas.
 * @author triforce
 *
 */
public class Label extends BounObject {

	private String text;
	
	private Font font;
	
	public Label() throws BounVisualizerException {
		super();
	}
	
	public Label(String text) throws BounVisualizerException {
		super();
		this.text = text;
		this.invalidate();
	}
	
	public Label(ObjectVisualizerContext context) {
		super(context);
	}
	
	public Label(ObjectVisualizerContext context, String text) {
		super(context);
		this.text = text;
		this.invalidate();
	}
	
	@Override
	public void update(long dt) {}

	@Override
	public void draw(Graphics canvas) {
		canvas.setColor(getColor());
		canvas.setFont(font);
		int textY = this.getY() + canvas.getFontMetrics().getAscent();
		canvas.drawString(text, getX(), textY);
	}

	@Override
	public void measure() {
		BounCanvas canvas = getContext().getFrame().getCanvas();
		int width = canvas.getFontMetrics(canvas.getFont()).stringWidth(getText());
		int height = canvas.getFontMetrics(canvas.getFont()).getHeight();
		
		this.setWidth(width);
		this.setHeight(height);
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
		invalidate();
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
		invalidate();
	}
	
	
}
