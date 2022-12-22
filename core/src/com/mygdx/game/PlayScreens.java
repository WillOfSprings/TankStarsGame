package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.text.View;

public class PlayScreens extends InputAdapter implements Screen {

    private tankStars game;                               //To pass on the whole game to other classes
    private Texture texture, tank1, tank2;                //To create Images
    SpriteBatch batch;

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;

    private OrthographicCamera gameCam;

    private Viewport gamePort;
    private TiledMap map;
    private TmxMapLoader mapLoader;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    //Make 2 tanks (currently making one)
    private gameTank1 player1;
    private gameTank2 player2;

    private Texture pauseIcon;

    private Texture chooseIcon;
    private Texture topHUD;
    private bullets missile ;
    //FOr tanks
    Body b2body;
    int tank1X = 400;
    int tank1Y = 320;
    int tank2X = 1090;
    int tank2Y = 325;
    int tankSpeed = 16;
    public PlayScreens(tankStars game){

        this.game = game;
        gameCam = new OrthographicCamera();

        gamePort = new FitViewport(Gdx.graphics.getWidth()/tankStars.scaling,Gdx.graphics.getHeight()/tankStars.scaling,gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("sprites/G2ground1080.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/tankStars.scaling);
        gameCam.position.set(((gamePort.getWorldWidth()/2)),(gamePort.getWorldHeight()/2),0);

        world = new World(new Vector2(0,-10),true); //for gravity in world
        b2dr = new Box2DDebugRenderer();
        player1 = new gameTank1(world);
        player2 = new gameTank2(world);
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //For missile
        missile = new bullets(player1);

        //creating map Fixture

         for (MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth()/2)/tankStars.scaling,(rect.getY()+rect.getHeight()/2)/tankStars.scaling );
            body = world.createBody(bdef);
            shape.setAsBox((rect.getWidth() / 2)/tankStars.scaling, (rect.getHeight() / 2)/tankStars.scaling);
            fdef.shape = shape;
            body.createFixture(fdef);

        }

        tank1 = new Texture("images/Abramas.png");
        tank2 = new Texture("images/rightHelios.png");
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
//        missile.launch();
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
                game.setScreen(new PauseScreen(game));
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
        batch.dispose();
        texture.dispose();
    }
}

