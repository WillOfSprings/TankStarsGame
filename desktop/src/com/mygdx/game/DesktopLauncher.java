package com.mygdx.game;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		Graphics.DisplayMode display = Lwjgl3ApplicationConfiguration.getDisplayMode();
		config.setForegroundFPS(60);
		config.setWindowedMode(1920, 1080);
		config.setResizable(true);
		config.useVsync(true);
		config.setTitle("Tank Stars");
		new Lwjgl3Application(new TankStars(), config);
	}
}
