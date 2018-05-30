package handlers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;


public class InputProcessor extends InputAdapter {
	
	public boolean keyDown(int k) {
		if(k == Keys.Z) {
			MyInput.setKey(MyInput.BUTTON1, true);
		}
		if(k == Keys.X) {
			MyInput.setKey(MyInput.BUTTON2, true);
		}
		if(k == Keys.LEFT) {
			MyInput.setKey(MyInput.LEFT, true);
		}
		if(k == Keys.RIGHT) {
			MyInput.setKey(MyInput.RIGHT, true);
		}
		if(k == Keys.UP) {
			MyInput.setKey(MyInput.UP, true);
		}
		if(k == Keys.DOWN) {
			MyInput.setKey(MyInput.DOWN, true);
		}
		if(k == Keys.L) {
			MyInput.setKey(MyInput.LEFTM, true);
		}
		return true;
	}
	
	public boolean keyUp(int k) {
		if(k == Keys.Z) {
			MyInput.setKey(MyInput.BUTTON1, false);
		}
		if(k == Keys.X) {
			MyInput.setKey(MyInput.BUTTON2, false);
		}
		if(k == Keys.LEFT) {
			MyInput.setKey(MyInput.LEFT, false);
		}
		if(k == Keys.RIGHT) {
			MyInput.setKey(MyInput.RIGHT, false);
		}
		if(k == Keys.UP) {
			MyInput.setKey(MyInput.UP, false);
		}
		if(k == Keys.DOWN) {
			MyInput.setKey(MyInput.DOWN, false);
		}
		return true;
	}
	
public boolean touchDown(int x,int y,int p,int b){
		
		if(b == Input.Buttons.LEFT){
			MyInput.setKey(MyInput.LEFTM, true);
			MyInput.setTouchPosition(x,y);
		}
		return true;
		
	}
	
	public boolean touchUp(int x,int y,int p,int b){
		
		if(b == Input.Buttons.LEFT){
			MyInput.setKey(MyInput.LEFTM, false);
		}
		return true;
		
	}
	public boolean touchDragged(int x, int y, int p){
		MyInput.setTouchPosition(x, y);
		return true;
	}
	
	
	
}
