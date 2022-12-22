package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ChooseScreen implements Screen {
    private final TankStars game;
    private final OrthographicCamera camera;
    private final Viewport vp;
    private final Texture pick, pick_2, back;
    private final Texture bg1, bg2, logo;
    private final Texture tankimg, tankname;


    public ChooseScreen(TankStars game){
        this.game = game;
        camera = new OrthographicCamera();
        vp = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), camera);
        camera.position.set(((vp.getWorldWidth())/2),(vp.getWorldHeight()/2),0);


        tankimg = new Texture("images/Abramas.png");
        tankname = new Texture("images/Abramsname.png");
        back = new Texture("images/back.png");
        bg1 = new Texture("images/ctb.jpg");
        bg2 = new Texture("images/ctb2.png");
        pick = new Texture("images/pick.png");
        pick_2 = new Texture("images/pick2.png");
        logo = new Texture("images/logo.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);   //Default Base color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(bg1, 0,0,1200,1080);
        game.batch.draw(bg2,1100,0,820,1080);
        game.batch.draw(logo, 200,Gdx.graphics.getHeight()-400 );
        game.batch.draw(tankimg, 270,100, 700,600);
        game.batch.draw(tankname, 1270,Gdx.graphics.getHeight()/2-100);
        game.batch.draw(back, 20,Gdx.graphics.getHeight()-120,100,100);
        if (Gdx.input.getX()<120 && Gdx.input.getX()>20 && Gdx.input.getY()<120 && Gdx.input.getY()>20){
            if (Gdx.input.isTouched()){
                game.setScreen(new Top(game));
            }
        }

        int pickX = Gdx.graphics.getWidth() - 610;

        game.batch.draw(pick, pickX, 180,400,100);
        if (Gdx.input.getX() < 400+pickX &&
                Gdx.input.getX()>pickX &&
                Gdx.graphics.getHeight()-Gdx.input.getY() <  280 &&
                Gdx.graphics.getHeight()-Gdx.input.getY()>180){
            game.batch.draw(pick_2, pickX, 180,400,100);
            if (Gdx.input.isTouched()){
                game.setScreen(new MainScreen(game));
            }
        }

        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        vp.update(width, height);
        camera.update();
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
