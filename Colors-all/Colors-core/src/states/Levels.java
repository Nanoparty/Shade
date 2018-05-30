package states;

import handlers.Content;
import handlers.GameStateManager;
import handlers.MyInput;

import java.util.ArrayList;

import Core.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;



public class Levels extends GameState{
	
	private SpriteBatch batch;
	private ShapeRenderer sr;
	private BitmapFont font1;
	private BitmapFont font2;
	private BitmapFont font3;
	private BitmapFont font4;
	private BitmapFont font5;
	
	private Texture back;
	
	private int hover;
	
	private float alpha;
	private boolean tin;
	private boolean tout;
	private int level;
	
	private Texture arrow;
	

	public Levels(GameStateManager gsm) {
		super(gsm);
		
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("blank.otf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 100;
		
		//parameter.characters = "hello";
		font1 = generator.generateFont(parameter); // font size 12 pixels
		float c = 100/255f;
		font1.setColor(new Color(c,c,c,1));
		
		parameter.size = 65;
		font2 = generator.generateFont(parameter);
		font2.setColor(Color.GREEN);
		font3 = generator.generateFont(parameter);
		font4 = generator.generateFont(parameter);
		font3.setColor(new Color(48/255f,106/255f,1,1));
		font4.setColor(new Color(1,138/255f,0,1));
		
		font5 = generator.generateFont(parameter); // font size 12 pixels
		font5.setColor(new Color(c,c,c,1));
		
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
		arrow = Content.GRA;
		back = Content.B;
		
		alpha = 1;
		tin = true;
		tout = false;
		level = 0;
	
	}

	@Override
	public void handleInput() {
		
		if(MyInput.isDown(MyInput.LEFTM)){
			
			int x = MyInput.getTouchX()/2;
			int y =640 - MyInput.getTouchY()/2;
			int width = 360;
			int height = 70;
			
			
				if(y < 400 && y > 400 - height)hover = 1;//play
				else if(y < 320 && y > 320 - height)hover = 2;//scores
				else if(y < 240 && y > 240 - height)hover = 3;//options
				else if(y < 160 && y > 160 - height)hover = 4;
				else hover = 0;//none
				
			
			
		}else hover = 0;
		
		if(MyInput.isReleased(MyInput.LEFTM)){
			
			int x = MyInput.getTouchX()/2;
			int y =640 - MyInput.getTouchY()/2;
			int width = 360;
			int height = 70;
			
		
			if(y < 400 && y > 400 - height){
				level = 1;
				tout = true;
				alpha = 0;
			}
			else if(y < 320 && y > 320 - height){
				level = 2;
				alpha = 0;
				tout = true;
			}
			else if(y < 240 && y > 240 - height){
				level = 3;
				alpha = 0;
				tout = true;
			}
			else if(y < 160 && y > 160 - height){
				level = 4;
				alpha = 0;
				tout = true;
			}
			
				
				
			
			
		}
		
		
	}

	@Override
	public void update(float dt) {
		if(!tin && !tout){
			handleInput();
		}
		
	
		
	}

	@Override
	public void render() {
		Gdx.graphics.getGL20().glClearColor( 0,0,0, 1 );
		Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		batch.setProjectionMatrix(cam.combined);
		sr.setProjectionMatrix(cam.combined);
		
		sr.begin(ShapeType.Filled);
		float c = 37/255f;
		sr.setColor(new Color(c,c,c,1));
		sr.rect(0,0,Core.V_WIDTH,Core.V_HEIGHT);
		sr.end();
		
		batch.begin();
		batch.draw(back,0,0);
		batch.end();
		
		sr.begin(ShapeType.Filled);
		sr.setColor(new Color(100/255f,100/255f,100/255f,1));
		sr.rect(30,500,300,5);
		sr.end();
		
		batch.begin();
		
		font1.draw(batch, "Levels", 60, 600);
		font2.draw(batch, "Easy", 50, 400);
		font3.draw(batch, "Normal", 50, 320);
		font4.draw(batch, "Hard", 50, 240);
		font5.draw(batch,"Back",50,160);
		
		//batch.draw(arrow,(Core.V_WIDTH-arrow.getWidth())/2,20);
		
		batch.end();
		
		sr.begin(ShapeType.Filled);
		switch(hover){
		case 1: 
			sr.setColor(Color.GREEN);
			sr.rect(35,350,5,50);
			break;
		case 2:
			sr.setColor(new Color(48/255f,106/255f,1,1));
			sr.rect(35,270,5,50);
			break;
		case 3:
			sr.setColor(new Color(1,138/255f,0,1));
			sr.rect(35,190,5,50);
			break;
		case 4:
			float a = 100/255f;
			sr.setColor(new Color(a,a,a,1));
			sr.rect(35,110,5,50);
			break;
		}
		sr.end();
		
		sr.begin(ShapeType.Filled);
		sr.setColor(new Color(0,0,0,alpha));
		Gdx.gl.glEnable(GL20.GL_BLEND);
	    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		sr.rect(0,0,Core.V_WIDTH,Core.V_HEIGHT);
		if(tin)alpha-=.04f;
		if(alpha <= 0 && tin)tin = false;
		if(tout)alpha+=.04;
		if(alpha >= 1 && tout){
			switch(level){
			case 1:
				gsm.setState(GameStateManager.PLAY);
				break;
			case 2:
				gsm.setState(GameStateManager.NORMAL);
				break;
			case 3:
				gsm.setState(GameStateManager.HARD);
				break;
			case 4:
				gsm.setState(GameStateManager.MENU);
			}
		}
		sr.end();
		
		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
