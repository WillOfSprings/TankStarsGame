package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.maps.objects.RectangleMapObject;



public class MainScreen extends InputAdapter implements Screen {

    private final TankStars game;

    private final OrthographicCamera camera;

    private final Viewport vp;
    private final OrthogonalTiledMapRenderer otmr;

    private final World world;
    private final Box2DDebugRenderer b2ddr;

    private final Tank tank_a;
    private final Tank tank_b;

    private final Texture chooseIcon;
    private final Texture topHUD;
    private final Weapons missile;

    public MainScreen(TankStars game){

        this.game = game;
        camera = new OrthographicCamera();

        vp = new FitViewport(Gdx.graphics.getWidth()/ TankStars.scaling,Gdx.graphics.getHeight()/ TankStars.scaling, camera);

        TmxMapLoader mapLoader = new TmxMapLoader();
        TiledMap map = mapLoader.load("sprites/G2ground1080.tmx");
        otmr = new OrthogonalTiledMapRenderer(map, 1/ TankStars.scaling);
        camera.position.set(((vp.getWorldWidth()/2)),(vp.getWorldHeight()/2),0);

        world = new World(new Vector2(0,-10),true); //for gravity in world
        b2ddr = new Box2DDebugRenderer();
        tank_a = new Tank(world, 1);
        tank_b = new Tank(world, 2);
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;
        missile = new Weapons(tank_a);


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
        chooseIcon = new Texture("images/chooseIcon.png");
        topHUD = new Texture("images/TopHUD.png");

    }




    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.step(1/60f, 6,2);
        tank_a.update();
        tank_b.update();
        camera.update();
        otmr.setView(camera);
        Gdx.gl.glClearColor(0,0,0,1);   //Default Base color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        otmr.render();

        game.batch.setProjectionMatrix(camera.combined);
        b2ddr.render(world, camera.combined);

        game.batch.begin();
        tank_a.draw(game.batch);
        tank_b.draw(game.batch);
        game.batch.draw(chooseIcon, 20,Gdx.graphics.getHeight()-120,100,100);
        missile.setMissile(world);
        missile.update(game.batch);
        tank_a.inputListener();
        tank_b.inputListener();
        game.batch.draw(topHUD,300,Gdx.graphics.getHeight()-160);



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

