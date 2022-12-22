package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class Weapons {

    private final Tank_A tank_a;
    private Body mbody;
    private final BodyDef bd;
    private final float[] vertices={0, 0, 0, 12, 30, 12, 30, 0};
    TextureRegion mtr;
    Texture mtx = new Texture(Gdx.files.internal("images/missile.png"));
    public Weapons(Tank_A tank){

        this.bd = new BodyDef();
        bd.type= BodyDef.BodyType.DynamicBody;
        this.tank_a = tank;
        this.mtr = new TextureRegion(mtx);
    }
    public  void update(SpriteBatch batch) {
        batch.draw(mtr, mbody.getPosition().x, mbody.getPosition().y, 0, 0, 30, 15, 1, 1, (float) Math.toDegrees(mbody.getAngle()));
    }


    public void launch(World world){
        bd.position.set(tank_a.getX(), tank_a.getY()+50);
        mbody = world.createBody(bd);
        PolygonShape pg = new PolygonShape();
        pg.set(vertices);
        mbody.createFixture(pg,100);
        Vector2 path = new Vector2(0, 70);
        path.rotateDeg(300);
        mbody.setLinearVelocity(path);
        mbody.setTransform(mbody.getPosition(), (float)Math.atan(mbody.getLinearVelocity().y / mbody.getLinearVelocity().x));
    }


}

