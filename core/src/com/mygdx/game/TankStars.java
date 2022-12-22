package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TankStars extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public static final float scaling = 100;

	@Override
	 public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		this.setScreen(new Top(this));
	}

	@Override
	public void render () {
		super.render();
	}
	public void dispose(){
		batch.dispose();
		font.dispose();
	}

}
