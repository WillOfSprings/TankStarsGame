package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Top implements Screen {

    private final TankStars game;
    private final Texture bg;
    private final TextureRegion bgr;
    private final OrthographicCamera camera;
    private final Viewport vp;
    private final Texture play_1, play_2, exit_1, exit_2, load_1, load_2;

    public Top(final TankStars game){
        this.game = game;
        bg = new Texture("images/title.png");
        bgr = new TextureRegion(bg, 0,0,1920,1080);
        camera = new OrthographicCamera();
        vp = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), camera);
        camera.position.set(((vp.getWorldWidth())/2),(vp.getWorldHeight()/2),0);

        play_1 = new Texture(Gdx.files.internal("images/newgame.png"));
        play_2 = new Texture(Gdx.files.internal("images/newgame2.png"));
        exit_1 = new Texture(Gdx.files.internal("images/exit.png"));
        exit_2 = new Texture(Gdx.files.internal("images/exit2.png"));
        load_1 = new Texture(Gdx.files.internal("images/load.png"));
        load_2 = new Texture(Gdx.files.internal("images/load2.png"));

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
        game.batch.draw(bgr, 0,0, 1920, 1080);

        int playb = Gdx.graphics.getWidth()/2 - 200;
        int exitb = Gdx.graphics.getWidth() - 600;
        bdraw(playb, play_1, play_2);
        bdraw(200, load_1, load_2);

        game.batch.draw(exit_1, exitb, 80, 400,100);
        if (Gdx.input.getX() < 400+exitb && Gdx.input.getX()>exitb && Gdx.graphics.getHeight()-Gdx.input.getY() <  180 && Gdx.graphics.getHeight()-Gdx.input.getY()>80){
            game.batch.draw(exit_2, exitb, 80, 400,100);
            if (Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }

        game.batch.end();
    }

    private void bdraw(int playb, Texture play1, Texture play2) {
        game.batch.draw(play1, playb, 80, 400, 100);
        if (Gdx.input.getX() < 400 + playb && Gdx.input.getX()> playb && Gdx.graphics.getHeight()-Gdx.input.getY() <  180 && Gdx.graphics.getHeight()-Gdx.input.getY()> 80){
            game.batch.draw(play2, playb, 80, 400, 100);
            if (Gdx.input.isTouched()){
                game.setScreen(new ChooseScreen(game));
            }
        }
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

        bg.dispose();
        bgr.getTexture().dispose();
        play_1.dispose();
        play_2.dispose();
        exit_1.dispose();
        exit_2.dispose();

    }
}
