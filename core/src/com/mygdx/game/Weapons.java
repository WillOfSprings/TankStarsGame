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

    private final Tank_A tank1;
    private Body body3;
    private float x;
    private float y;

    private final BodyDef bodyDef;

    private float width,height;
    private final float[] vertices={0,0,0,12,30,12,30,0};


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }


    TextureRegion hehe;

    Texture missileimg=new Texture(Gdx.files.internal("images/missile1.png"));
    public Weapons(Tank_A tank){

        this.bodyDef=new BodyDef();

        bodyDef.type= BodyDef.BodyType.DynamicBody;
        this.tank1=tank;



        this.hehe=new TextureRegion(missileimg);
        this.width=45;
        this.height=30;



    }
    public  void update(SpriteBatch batch) {
        batch.draw(hehe, body3.getPosition().x, body3.getPosition().y, 0, 0, 30, 15, 1, 1, (float) Math.toDegrees(body3.getAngle()));
    }


    public void launch(World world){
        bodyDef.position.set(tank1.getX(),tank1.getY()+70);
        body3=world.createBody(bodyDef);
        PolygonShape missy=new PolygonShape();
        missy.set(vertices);


        body3.createFixture(missy,100);
        Vector2 traj = new Vector2(0, 70);
        traj.rotateDeg(300);
        body3.setLinearVelocity(traj);
        body3.setTransform(body3.getPosition(), (float)Math.atan(body3.getLinearVelocity().y / body3.getLinearVelocity().x));




    }


}

