package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class Pause implements Screen {

    private final TankStars game;

    private final OrthographicCamera gameCam;
    private final Viewport gamePort;

    private final Texture pauseBackground;
    private final Texture RESUME_BUTTON_ACTIVE;
    private final Texture RESUME_BUTTON_INACTIVE;
    private final Texture EXIT_BUTTON;
    private final Texture RESUME_BASE;

    private final Texture SAVE_BUTTON_ACTIVE;
    private final Texture SAVE_BUTTON_INACTIVE;


    public Pause(TankStars game){
        this.game = game;
        pauseBackground = new Texture("images/pauseScreenBackground.jpg");

        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),gameCam);
        gameCam.position.set(((gamePort.getWorldWidth())/2),(gamePort.getWorldHeight()/2),0);

        RESUME_BASE = new Texture("images/mainMenuBase.png");
        RESUME_BUTTON_ACTIVE = new Texture("images/button_resume.png");
        RESUME_BUTTON_INACTIVE = new Texture("images/button_resume_DOWN.png");
        EXIT_BUTTON = new Texture("images/button_exit.png");
        SAVE_BUTTON_ACTIVE = new Texture("images/button_save-game.png");
        SAVE_BUTTON_INACTIVE = new Texture("images/button_save-game_DOWN.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);   //Default Base color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameCam.update();
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();

        game.batch.draw(pauseBackground, 0,0);

        int baseWidth, exitWidth,baseHeight, exitHeight,resumeWidth, resumeHeight, saveHeight,saveWidth;
        baseWidth =1000;
        baseHeight = 900;
        resumeHeight=exitHeight =saveHeight= 100;
        exitWidth= resumeWidth= saveWidth= 400;
        int baseX = Gdx.graphics.getWidth()/2- baseWidth/2;
        int baseY = Gdx.graphics.getHeight()/2- baseHeight/2;

        game.batch.draw(RESUME_BASE, baseX,baseY, baseWidth,baseHeight);

        game.batch.draw(RESUME_BUTTON_ACTIVE,770 , 540,resumeWidth,resumeHeight );
        if (Gdx.input.getX()>770 && Gdx.input.getX()< 770+resumeWidth && Gdx.input.getY()>540 && Gdx.input.getY()<540+resumeHeight ){
            game.batch.draw(RESUME_BUTTON_INACTIVE, 770,540,resumeWidth,resumeHeight);
            if (Gdx.input.isTouched()){
                game.setScreen(new Pause(game));
            }
        }
        game.batch.draw(SAVE_BUTTON_ACTIVE,770 , 397,resumeWidth,resumeHeight );
        if (Gdx.input.getX()>770 && Gdx.input.getX()< 770+resumeWidth && Gdx.input.getY()>397 && Gdx.input.getY()<397+resumeHeight ){
            game.batch.draw(SAVE_BUTTON_INACTIVE, 770,397,resumeWidth,resumeHeight);

        }

        game.batch.draw(EXIT_BUTTON, 770,250,exitWidth,exitHeight);
        if (Gdx.input.getX()>770 && Gdx.input.getX()< 770+exitWidth && Gdx.input.getY()>250 && Gdx.input.getY()<250+exitHeight ){
            Gdx.app.exit();
        }
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        gameCam.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
