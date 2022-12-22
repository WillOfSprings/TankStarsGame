package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Tank extends Sprite {
        public World world;
        private final Body b2body;

        int type;

    public Tank(World world, int type){

        this.world = world;
        this.type = type;
        BodyDef bdef= new BodyDef();
        if (type == 1) {
            bdef.position.set(400 / TankStars.scaling, 400/ TankStars.scaling );
        } else {
            bdef.position.set(1200 / TankStars.scaling, 450/ TankStars.scaling );
        }
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10/ TankStars.scaling);
        fdef.shape = shape;
        b2body.createFixture(fdef);
        if (type == 1) {
            Texture tank = new Texture("images/gameAbramas.png");
            TextureRegion tank2 = new TextureRegion(tank, 0, 0, 130, 100);
            setBounds(0,0,130/ TankStars.scaling,100/ TankStars.scaling);
            setRegion(tank2);
        } else {
            Texture tank = new Texture("images/rightHelios.png");
            TextureRegion tank2 = new TextureRegion(tank, 0, 0, 142, 80);
            setBounds(0,0,142/ TankStars.scaling,80/ TankStars.scaling);
            setRegion(tank2);
        }
    }
    public void update(){
        setPosition(b2body.getPosition().x-getWidth()/2, b2body.getPosition().y-getHeight()/2);
    }

    public void inputListener(){

        int up, right, left;
        if (type == 1) {
            up = Input.Keys.UP;
            right = Input.Keys.RIGHT;
            left = Input.Keys.LEFT;
        } else {
            up = Input.Keys.W;
            right = Input.Keys.D;
            left = Input.Keys.A;
        }
        if (Gdx.input.isKeyPressed(up)&& this.b2body.getLinearVelocity().y<=0.5){
            this.b2body.applyLinearImpulse(new Vector2(0f,0.5f),this.b2body.getWorldCenter(),true);
        }
        if (Gdx.input.isKeyPressed(right)&& this.getX()<Gdx.graphics.getWidth() && this.b2body.getLinearVelocity().x<=2){
            this.b2body.applyLinearImpulse(new Vector2(0.1f, 0f),this.b2body.getWorldCenter(),true);
        }
        if (Gdx.input.isKeyPressed(left)&&this.b2body.getLinearVelocity().x>=-2){
            this.b2body.applyLinearImpulse(new Vector2(-0.1f, 0f),this.b2body.getWorldCenter(),true);
        }
    }
}