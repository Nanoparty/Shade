package handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Content {
	
	public static Texture BA = load("arrowB.png");
	public static Texture GA = load("arrowG.png");
	public static Texture OA = load("arrowO.png");
	public static Texture GRA = load("arrowGrey.png");
	public static Texture GL = load("loopG.png");
	public static Texture OL = load("loopO.png");
	public static Texture BL = load("loopB.png");
	public static Texture CK = load("check.png");
	public static Texture XX = load("x.png");
	public static Texture CKB = load("checkB.png");
	public static Texture CKO = load("checkO.png");
	public static Texture B = load("back.png");
	
	public static Music music = Gdx.audio.newMusic(Gdx.files.internal("clockwork.mp3"));
	public static Sound sound = Gdx.audio.newSound(Gdx.files.internal("pc.wav"));
	public static Sound finish = Gdx.audio.newSound(Gdx.files.internal("upgrade.wav"));
	public static Sound bad = Gdx.audio.newSound(Gdx.files.internal("break.wav"));

	public static Texture load(String s){
		Texture image;
		try{
			
			Texture sprite = new Texture(Gdx.files.internal(s));
			return sprite;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error Loading Graphics");
		}
		return null;
		
	}

}
