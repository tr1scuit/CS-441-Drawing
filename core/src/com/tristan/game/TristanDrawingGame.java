package com.tristan.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TristanDrawingGame extends ApplicationAdapter implements InputProcessor {

	// TristanDrawingGame - Quick game

	// Simple Cooking Game inspired by Dungeon Meshi
	// Tap on monsters to slay them, tap on the cooking pot to make food!

	// Code for handling Touch Events inspired by
	// https://gamefromscratch.com/libgdx-tutorial-5-handling-input-touch-and-gestures/


	class TouchInfo {
		public float touchX = 0;
		public float touchY = 0;
		public boolean touched = false;
	}

	SpriteBatch batch;
	TouchInfo tap;
	long tick;
	BitmapFont font;
	Texture img; // base logo
	Texture dungeon; // background
	Sprite rSlime;
	int slimeX;
	int slimeY;
	Sprite potE;
	Sprite potF;
	int potX;
	int potY;
	int potX2;
	int potY2;
	boolean potFull = false;

	@Override
	// Init
	public void create () {
		GameState.getInstance();
		font = new BitmapFont();
		batch = new SpriteBatch();
		tap = new TouchInfo();
		Gdx.input.setInputProcessor(this);
		dungeon = new Texture("dungeon.jpg");
		rSlime = new Sprite(new Texture("slime_red.png"));
		slimeX = 200; slimeY = 700;
		potE = new Sprite(new Texture("pot_empty.png"));
		potF = new Sprite(new Texture("pot_full.png"));
		potX = 40; potY = 10;
		potX2 = 40; potY2 = 10;

	}

	@Override
	// Render loop
	public void render () {

		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


/*		// simple timing render
		float updateTime = 0;
		//if(updateTime >= 0.016666){
		//	updateTime += Gdx.graphics.getDeltaTime();
		//	System.out.println("updateTime "+  updateTime);
//			tick();
		//} else{
//			updateTime += Gdx.graphics.getDeltaTime();
		//}
*/
//
		if(tap.touched){
			//System.out.println(tap.touchX + ", " + tap.touchY);
			// hitbox for Slime
			if(tap.touchX > 272 && tap.touchX < 725
			&& tap.touchY > 1056 && tap.touchY < 1282 && GameState.getInstance().slimeState == true){
				System.out.println("SLIME TAPPED");
				GameState.getInstance().slimeCount++;
				GameState.getInstance().slimeState = false;
				slimeX = 9000;
			}
			// hitbox for Pot Empty
			if(tap.touchX > 115 && tap.touchX < 538
					&& tap.touchY > 1634 && tap.touchY < 1827 && GameState.getInstance().potState == false){
				System.out.println("POT TAPPED EMPTY");
				try {
					Thread.sleep(200);
					System.out.println("sleeping");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				GameState.getInstance().slimeCount--;
				GameState.getInstance().potState = true;
				potX2 = 40;
				potX = 9000;
			}
			// hitbox for Pot Full
			else if(tap.touchX > 115 && tap.touchX < 538
					&& tap.touchY > 1634 && tap.touchY < 1827 && GameState.getInstance().potState == true){
				try {
					Thread.sleep(200);
					System.out.println("sleeping");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("POT TAPPED True");
				GameState.getInstance().potState = false;
				GameState.getInstance().slimeState = true;
				potX = 40;
				potX2 = 9000;
				slimeX = 200;
			}
		}

		batch.begin();

		batch.draw(dungeon, -300, -190, 1800, 2400);
		batch.draw(rSlime, slimeX, slimeY);
		batch.draw(potF, potX2, potY2);
		batch.draw(potE, potX, potY);

		batch.end();
	}


	@Override
	// Clean up garbage before ending app
	public void dispose () {
		GameState.resetInstance();
		batch.dispose();
		img.dispose();
	}

	// inputs
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		tap.touchX = screenX;
		tap.touchY = screenY;
		tap.touched = true;
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		tap.touchX = 0;
		tap.touchY = 0;
		tap.touched = false;
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}


	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

}
