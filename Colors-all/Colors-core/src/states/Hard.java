package states;

import handlers.Content;
import handlers.GameStateManager;
import handlers.MyInput;

import java.util.ArrayList;

import javax.swing.GroupLayout.Alignment;

import Core.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import entities.cubeB;
import entities.cubeG;
import entities.cubeO;



public class Hard extends GameState{
	
	private SpriteBatch batch;
	private ShapeRenderer sr;
	private BitmapFont font1;
	private BitmapFont font2;
	private BitmapFont font3;
	private BitmapFont font4;
	
	private Texture arrow;
	private Texture loop;
	private Texture check;
	private Texture xx;
	private Texture back;
	
	private boolean CK;
	private boolean XX;
	
	private float timer;
	private float time;
	private int flash;
	private boolean click;
	private boolean reset;
	private float endTime;
	private int score;
	private int correct;
	private int count;
	private boolean won;
	
	private Sound s1;
	private Sound s2;
	private Sound s3;
	private float pitch;
	
	private int on;
	private boolean played;
	private float vol;
	
	private ArrayList<cubeO> cubes;
	
	private float alpha;
	private boolean tin;
	private boolean tout;

	public Hard(GameStateManager gsm) {
		super(gsm);
	//	System.out.println("hard");
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
		
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
		
		cubes = new ArrayList<cubeO>();
		//System.out.println("hard2");
		generateCubes();
		
		time = 0;
		timer = 0;
		flash = 5;
		click = false;
		reset = false;
		endTime = 0;
		score = 0;
		correct = 0;
		won = false;
		
		arrow = Content.OA;
		loop = Content.OL;
		check = Content.CKO;
		xx = Content.XX;
		back = Content.B;
		
		XX = false;
		CK = false;
		
		pitch = 1;
		s1 = Content.sound;
		s2 = Content.finish;
		s3 = Content.bad;
		
		on = 0;
		played = false;
		Preferences prefs = Gdx.app.getPreferences("HighScores");
		if(prefs.getBoolean("sfx")){
			vol = 2f;
		}
		else{
			vol = 0f;
		}
		
		alpha = 1;
		tin = true;
		tout = false;
	}
	
	private void generateCubes(){
		
		boolean on = false;
		count = 0;;
		boolean loop = true;
		System.out.println("hard2");
		while(loop){
			for(int row = 0;row < 6;row++){
				for(int col = 0;col<6;col++){
					if(Math.random() > .5){
						on = true;
						if(count < 16){
						count++;
						}
					}
					cubes.add(new cubeO(on,row,col));
					on = false;
				}
			}
			if(count > 8) loop = false;
		}
		
		
		
	}

	@Override
	public void handleInput() {
		
		if(MyInput.isDown(MyInput.LEFTM)){}
		
		if(MyInput.isReleased(MyInput.LEFTM)){
			
			int mx = MyInput.getTouchX()/2;
			int my = 640 - MyInput.getTouchY()/2;
			
			if(mx > 20 && mx < 20 + arrow.getWidth()){
				if(my > 20 && my < 20 + arrow.getHeight()){
					save();
					alpha = 0;
					tout = true;
				}
			}
			
			if(mx > 270 && mx < 270 + loop.getWidth()){
				if(my > 20 && my < 20 + loop.getHeight()){
					save();
					CK = false;
					XX = false;
					cubes.clear();
					generateCubes();
					time = 0;
					reset = false;
					score = 0;
					pitch = 1f;
					on = 0;
					played = false;
				}
			}
			
			
			for(int i = 0;i < cubes.size();i++){
				cubeO g = cubes.get(i);
				
				if(mx > g.getX() && mx < g.getX() + g.getWidth() && click){
					if(my > g.getY() && my < g.getY() + g.getWidth()){
						g.clicked();
						if(g.getCorrect()){
							on++;
						}
						if(on < count && g.getCorrect()){
							s1.play(vol, pitch, 1f);
							}
							
							pitch+=.1f;
					}
				}
			}
			
			if(reset){
				pitch = 1f;
				if(won)score++;
				CK = false;
				XX = false;
				cubes.clear();
				generateCubes();
				time = 0;
				reset = false;
				played = false;
				on = 0;
			}
			
		}
		
		
	}

	@Override
	public void update(float dt) {
		if(!tin && !tout){
			handleInput();
		}
		//System.out.println("" + endTime);
		
		time += dt;
		int ti = 0;
		//System.out.println("" + time);
		if(time > 1.5){
			for(int i = 0;i < cubes.size();i++){
				cubes.get(i).flash(time);
			}
		}
		
		for(int i = 0;i < cubes.size();i++){
			cubes.get(i).update();
			
			if(cubes.get(i).getClickable()){
				click = true;
			}
			
		}
		int f = 0;
		for(int i = 0;i < cubes.size();i++){
			if(cubes.get(i).getCorrect())f++;
		}
		if(f == count){
			for(int r = 0;r < cubes.size();r++){
				save();
				if(!played){
					s2.play(vol);
					played = true;
				}
				won = true;
				CK = true;
				click = false;
				cubes.get(r).fin();
				reset = true;
				
				
			}
		}
		
		for(int i = 0;i < cubes.size();i++){
			if(cubes.get(i).getWrong()){
				for(int r = 0;r < cubes.size();r++){
					save();
					if(!played){
						s3.play(vol);
						played = true;
					}
					click = false;
					score = 0;
					cubes.get(r).fin();
					//cubes.get(r).setClickable(false);
					reset = true;
					won = false;
					XX = true;
				}
			}
			
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
		
		for(int i = 0;i < cubes.size();i++){
			cubes.get(i).render(sr);
		}
		batch.begin();
		
		String s = String.valueOf(score);
		TextBounds r = font2.getBounds("" + score);
		float le = r.width;
		
		font4.draw(batch,"" + score,(Core.V_WIDTH - le)/2,620);
		//System.out.println(length + "");
		
		if(CK)batch.draw(check,140,100);
		if(XX)batch.draw(xx,140,100);
		batch.draw(arrow,20,20);
		batch.draw(loop,270,10);
		batch.end();
		
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
		if(prefs.getLong("hard") < score){
			prefs.putLong("hard", score);
		}
		prefs.flush();
	}

}
