package tr.edu.boun.cmpe.objectvisualizer.widget;

import java.awt.Font;
import java.awt.Graphics;

import tr.edu.boun.cmpe.objectvisualizer.BounObject;
import tr.edu.boun.cmpe.objectvisualizer.ObjectVisualizerContext;

/**
 * Simple component to display text on canvas.
 * @author triforce
 *
 */
public class Label extends BounObject {

	private String text;
	
	private Font font;
	
	public Label(ObjectVisualizerContext context) {
		super(context);
	}
	
	public Label(ObjectVisualizerContext context, String text) {
		super(context);
		this.text = text;
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

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
	
	
}
