package states;

import handlers.Content;
import handlers.GameStateManager;
import handlers.MyInput;

import java.util.ArrayList;

import Core.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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



public class Options extends GameState{
	
	private SpriteBatch batch;
	private ShapeRenderer sr;
	private BitmapFont font1;
	private BitmapFont font2;
	private BitmapFont font3;
	private BitmapFont font4;
	private BitmapFont font5;
	
	private boolean music;
	private boolean sfx;
	
	private Texture back;
	
	private int hover;
	
	private Texture arrow;
	
	private float alpha;
	private boolean tin;
	private boolean tout;
	

	public Options(GameStateManager gsm) {
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
		
		Preferences prefs = Gdx.app.getPreferences("HighScores");
		music = prefs.getBoolean("music");
		sfx = prefs.getBoolean("sfx");
		
		alpha = 1;
		tin = true;
		tout = false;
	
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
				//music = !music;
				//save();
				//gsm.setMusic(music);
				sfx = !sfx;
				save();
			}
			else if(y < 320 && y > 320 - height){
				//sfx = !sfx;
				//save();
			}
			
			else if(y < 160 && y > 160 - height){
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
		
		font1.draw(batch, "Options", 50, 600);
		//font2.draw(batch, "Music:", 50, 400);
		font2.draw(batch, "Sound:", 50, 400);
	
		font5.draw(batch,"Back",50,160);
		
		/*
		if(music){
			font2.draw(batch,"On",190,400);
		}else{
			font2.draw(batch,"Off",190,400);
		}
		if(sfx){
			font3.draw(batch,"On",160,320);
		}else{
			font3.draw(batch,"Off",160,320);
		}
		*/
		
		if(sfx){
			font2.draw(batch,"On",210,400);
		}else{
			font2.draw(batch,"Off",210,400);
		}
		
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
			//sr.rect(35,270,5,50);
			break;
		case 3:
			sr.setColor(Color.ORANGE);
			//sr.rect(35,190,5,50);
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
			gsm.setState(GameStateManager.MENU);
		}
		sr.end();
		
		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	public void save(){
		Preferences prefs = Gdx.app.getPreferences("HighScores");
		prefs.putBoolean("music", music);
		prefs.putBoolean("sfx", sfx);
		prefs.flush();
	}

}
