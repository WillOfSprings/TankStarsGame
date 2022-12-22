package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.maps.objects.RectangleMapObject;



public class MainScreen extends InputAdapter implements Screen {

    private final TankStars game;
    SpriteBatch batch;

    private final OrthographicCamera gameCam;

    private final Viewport gamePort;
    private final OrthogonalTiledMapRenderer renderer;

    private final World world;
    private final Box2DDebugRenderer b2dr;

    private final Tank_A player1;
    private final Tank_B player2;

    private final Texture chooseIcon;
    private final Texture topHUD;
    private final Weapons missile;
    public MainScreen(TankStars game){

        this.game = game;
        gameCam = new OrthographicCamera();

        gamePort = new FitViewport(Gdx.graphics.getWidth()/ TankStars.scaling,Gdx.graphics.getHeight()/ TankStars.scaling,gameCam);

        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("sprites/G2ground1080.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/ TankStars.scaling);
        gameCam.position.set(((gamePort.getWorldWidth()/2)),(gamePort.getWorldHeight()/2),0);

        world = new World(new Vector2(0,-10),true); //for gravity in world
        b2dr = new Box2DDebugRenderer();
        player1 = new Tank_A(world);
        player2 = new Tank_B(world);
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //For missile
        missile = new Weapons(player1);

        //creating map Fixture

         for (RectangleMapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = object.getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/ TankStars.scaling,(rect.getY()+rect.getHeight()/2)/ TankStars.scaling );
            body = world.createBody(bdef);
            shape.setAsBox((rect.getWidth() / 2)/ TankStars.scaling, (rect.getHeight() / 2)/ TankStars.scaling);
            fdef.shape = shape;
            body.createFixture(fdef);

        }

        Texture tank1 = new Texture("images/Abramas.png");
        //To create Images
        Texture tank2 = new Texture("images/rightHelios.png");
        chooseIcon = new Texture("images/chooseIcon.png");
        topHUD = new Texture("images/TopHUD.png");

    }


    public void update(float dt){
//        handleInput(dt);
        world.step(1/60f, 6,2);
        player1.update(dt);
        player2.update(dt);
//        missile.update(batch);
        gameCam.update();
        renderer.setView(gameCam);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);   //Default Base color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        game.batch.setProjectionMatrix(gameCam.combined);
        b2dr.render(world, gameCam.combined);

        game.batch.begin();
        player1.draw(game.batch);
        player2.draw(game.batch);
        game.batch.draw(chooseIcon, 20,Gdx.graphics.getHeight()-120,100,100);
        missile.launch(world);
        missile.update(game.batch);

        if (Gdx.input.getX()<120 && Gdx.input.getX()>20 && Gdx.input.getY()<120 && Gdx.input.getY()>20){
            if (Gdx.input.isTouched()){
                game.setScreen(new Pause(game));
            }
        }
        //Creating HUD, Top of game :
        //Make health meter, and other menus of the top
        player1.handleInput();
        player2.handleInput();
        game.batch.draw(topHUD,300,Gdx.graphics.getHeight()-160);
        //render tanks and their respective motions



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

