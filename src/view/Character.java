package view;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

public class Character {
	private Position pos;
	private Image img;
	
	public Character() {
		img = new Image(null, "resources/character.jpg");
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	public void draw(int cellWidth, int cellHeight, GC gc) {
		gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, 
				cellWidth * pos.getX(), cellHeight * pos.getY(), cellWidth, cellHeight);
	}
	
	public void moveRight() {
		pos.setPosition(pos.getX()+1, pos.getY(), pos.getZ());
	}
	public void moveLeft() {
		pos.setPosition(pos.getX()-1, pos.getY(), pos.getZ());
	}
	public void moveUp() {
		pos.setPosition(pos.getX(), pos.getY()-1, pos.getZ());
	}
	public void moveDown() {
		pos.setPosition(pos.getX(), pos.getY()+1, pos.getZ());
	}
	

}