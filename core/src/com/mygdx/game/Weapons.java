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
    private float speed;
    private float xs;

    private BodyDef bodyDef;

    private float width,height;


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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getXs() {
        return xs;
    }

    public void setXs(float xs) {
        this.xs = xs;
    }

    public BodyDef getBodyDef() {
        return bodyDef;
    }

    public void setBodyDef(BodyDef bodyDef) {
        this.bodyDef = bodyDef;
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



    public float[] getVertices() {
        return vertices;
    }

    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }

    TextureRegion hehe;

    Texture missileimg=new Texture(Gdx.files.internal("images/missile1.png"));
    public Weapons(Tank_A tank){

        this.bodyDef=new BodyDef();

        bodyDef.type= BodyDef.BodyType.DynamicBody;
        this.tank1=tank;



        this.hehe=new TextureRegion(missileimg);

        this.speed=4;
        this.width=45;
        this.height=30;



    }
    public  void update(SpriteBatch batch) {
        batch.draw(hehe, body3.getPosition().x, body3.getPosition().y, 0, 0, 30, 15, 1, 1, (float) Math.toDegrees(body3.getAngle()));
    }

    private float[] vertices={0,0,0,12,30,12,30,0};


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

