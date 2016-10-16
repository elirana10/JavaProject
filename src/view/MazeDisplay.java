package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import algorithms.mazeGenerators.Position;

public class MazeDisplay extends Canvas {
//	private int mazeData[][][];
	private Character character;
	private String name;
	private int currFloor;

	private int mazeData[][] = {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,1,1,0,1,0,0,1},
			{0,0,1,1,1,1,1,0,0,1,0,1,0,1,1},
			{1,1,1,0,0,0,1,0,1,1,0,1,0,0,1},
			{1,0,1,0,1,1,1,0,0,0,0,1,1,0,1},
			{1,1,0,0,0,1,0,0,1,1,1,1,0,0,1},
			{1,0,0,1,0,0,1,0,0,0,0,1,0,1,1},
			{1,0,1,1,0,1,1,0,1,1,0,0,0,1,1},
			{1,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1}		
	};
	
	public MazeDisplay(Composite parent, int style) {
		super(parent, style);
		currFloor = 0;
		character = new Character();
		character.setPos(new Position(1, 2, 3));
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				switch (arg0.keyCode) {
				case SWT.ARROW_RIGHT:
					character.moveRight();
					redraw();
					break;
				case SWT.ARROW_LEFT:
					character.moveLeft();
					redraw();
					break;
				case SWT.ARROW_UP:
					character.moveUp();
					redraw();
					break;
				case SWT.ARROW_DOWN:
					character.moveDown();
					redraw();
					break;
				
			}
		}
		});
		
		
		this.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				if (mazeData == null)
					return;
				
				e.gc.setForeground(new Color(null,0,0,0));
				e.gc.setBackground(new Color(null,0,0,0));

				int width=getSize().x;
				int height=getSize().y;

				int w=width/mazeData[0].length;
				int h=height/mazeData.length;

				for(int i=0;i<mazeData.length;i++) {
					for(int j=0;j<mazeData[i].length;j++) {
						int x=j*w;
						int y=i*h;
						
						if(mazeData[i][j]!=0)
							e.gc.fillRectangle(x,y,w,h);
					}
				}
				
				   character.draw(w, h, e.gc);
			}
		});
	}

	public void setMazeData(int[][] mazeData) {
		this.mazeData = mazeData;
		this.redraw();
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getCurrFloor() {
		return this.currFloor;
	}
	
	public void setCurrFloor(int currFloor) {
		this.currFloor = currFloor;
	}

}
