package entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class cubeG {
	
	private int x;
	private int y;
	private int tx,ty;
	private Color c;
	private int width;
	private int height;
	private int twidth,theight;
	private boolean on;
	private int row;
	private int col;
	private float timer;
	private float maxTime = 0.5f;
	private int flashes;
	private boolean dark;
	private boolean wrong;
	private boolean clickable;
	private boolean yes;
	
	public cubeG(boolean on,int row, int col){
		this.on = on;
		width = height = 70;
		twidth = theight = 0;
		this.row = row;
		this.col = col;
		timer = 1.5f;
		if(on){
			//o = new Color(1,138/255f,0,1);
			c = new Color(6/255f,1,0,1);
		}else{
			//o = new Color(108/255f,59/255f,0,1);
			c = new Color(50/255f,123/255f,49/255f,1);
		}
		x = 5+(row*width) + 10*(row+2);
		y = 500 - (col*height) - 10*(col+2);
		
		tx = x+ width/2;
		ty = y + height/2;
		
		flashes = 0;
		dark = false;
		wrong = false;
		clickable = false;
		yes = false;
	}
	
	public void flash(float time){
		
		//System.out.println("" + timer);

		if(on){
		if(time - timer > .04 && flashes < 15){
			timer = time;
			if(dark){
				dark = false;
				c = new Color(6/255f,1,0,1);
				
			}
			else{
				dark = true;
				c = new Color(50/255f,123/255f,49/255f,1);
				flashes++;
			}
				
		}
		if(flashes == 15)clickable = true;
		}
	}
	
	public void update(){
		
		if(twidth < 70) twidth = theight +=2;
		if(tx > x)tx-=1;
		if(ty > y)ty-=1;
		
	}
	
	public void render(ShapeRenderer sr){
		sr.begin(ShapeType.Filled);
		sr.setColor(c);
		sr.rect(tx,ty,twidth,theight);
		sr.end();
	}
	
	
	
	public int getWidth(){return width;}
	public int getX(){return x;}
	public int getY(){return y;}
	public boolean getWrong(){return wrong;}
	public void setClickable(){clickable = true;}
	public boolean getClickable(){return clickable;}
	
	public boolean getCorrect(){
		if(on){return yes;}
		return false;
	}
	
	public void fin(){
		clickable = false;
		if(on)c = new Color(6/255f,1,0,1);
		
	}
	
	public void clicked(){
		if(on){
			c = new Color(6/255f,1,0,1);
			yes = true;
		}else{
			
			c = new Color(Color.RED);
			wrong = true;
		}
	}

}
