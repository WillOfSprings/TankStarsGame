package com.tankstars.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.tankstars.game.TankStars;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.useVsync(true);
		// set the resolution to 1920 x 1080
		config.setWindowedMode(1920,1080);
		config.setResizable(false);
		config.setTitle("Tank Stars");
		// set the icon for the game in the window settings
		// the asset folder is outside the core folder
		config.setWindowIcon("icon.jpg");
		new Lwjgl3Application(new TankStars(), config);
	}
}
