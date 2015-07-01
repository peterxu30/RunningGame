package com.runninggame.screens;

import oldCode.Play;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.runninggame.tween.SpriteAccessor;

/**
 * Splash screen for game. This is the first screen you see. Fades splash art
 * in and out. Leads to GameMain screen at the moment, plan to lead to MainMenu
 * after MainMenu is implemented.
 * @author Peter
 *
 */
public class Splash implements Screen {
	private SpriteBatch batch;
	private Sprite splash;
	private TweenManager tweenManager;
	private String path;
	
	public Splash(String imgPath) {
		path = imgPath;
	}
	
	@Override
	public void show() {
		
		batch = new SpriteBatch();
		tweenManager = new TweenManager(); 
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		
		Texture splashTexture = new Texture(path);
		splash = new Sprite(splashTexture);
		splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
		Tween.to(splash, SpriteAccessor.ALPHA, 1).target(1).repeatYoyo(1, 1).setCallback(new TweenCallback() {

			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				// TODO Auto-generated method stub
				((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
			}
			
		}).start(tweenManager);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		tweenManager.update(delta);
		
		batch.begin();
		splash.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		dispose();
		
	}

	@Override
	public void dispose() {
		Gdx.app.log("Splash", "Dispose");
		splash.getTexture().dispose();
	}

}
