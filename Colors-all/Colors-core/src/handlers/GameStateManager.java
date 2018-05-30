package handlers;

import java.util.Stack;

import states.GameState;
import states.Hard;
import states.Levels;
import states.Menu;
import states.Normal;
import states.Options;
import states.Play;
import states.Scores;
import Core.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;






public class GameStateManager {
	
	private Core game;
	
	private Stack<GameState> gameStates;
	
	public static final int PLAY = 0;
	public static final int MENU = 1;
	public static final int SCORES = 2;
	public static final int OPTIONS = 3;
	public static final int LEVELS = 4;
	public static final int NORMAL = 5;
	public static final int HARD = 6;
	
	public Music music;
	
	
	public GameStateManager(Core game) {
		this.game = game;
		
		gameStates = new Stack<GameState>();
		pushState(MENU);
		
		Preferences prefs = Gdx.app.getPreferences("HighScores");
		
		music = Content.music;
		music.setLooping(true);
		music.setVolume(.4f);
		if(prefs.getBoolean("music")){
		music.play();
		}
	}
	
	public Core game() { return game; }
	
	public void update(float dt) {
		gameStates.peek().update(dt);
		
	}
	
	
	
	public void render() {
		gameStates.peek().render();
	}
	
	private GameState getState(int state) {
		if(state == PLAY)return new Play(this);
			
		if(state == MENU)return new Menu(this);
		if(state == LEVELS)return new Levels(this);
		if(state == SCORES)return new Scores(this);
		if(state == OPTIONS)return new Options(this);
		if(state == NORMAL)return new Normal(this);
		if(state == HARD)return new Hard(this);
		return null;
	}
	
	public void setState(int state) {
		popState();
		pushState(state);
	}
	
	public void pushState(int state) {
		gameStates.push(getState(state));
	}
	
	public void popState() {
		GameState g = gameStates.pop();
		g.dispose();
	}
	
	public void setMusic(boolean b){
		if(b){
			music.setLooping(true);
			music.play();
		}else{
			music.pause();
		}
	}
	
	
	
}















