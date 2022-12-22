package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Tank_B extends Sprite {
    public World world;
    public Body b2body;

    public Tank_B(World world){

        this.world = world;
        defineTank();
        Texture tank = new Texture("images/rightHelios.png");
        TextureRegion tank2 = new TextureRegion(tank, 0, 0, 142, 80);
        setBounds(0,0,142/ TankStars.scaling,80/ TankStars.scaling);
        setRegion(tank2);
    }
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W) && this.b2body.getLinearVelocity().y <= 0.5) {
            this.b2body.applyLinearImpulse(new Vector2(0f, 0.5f), this.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && this.getX() < Gdx.graphics.getWidth() && this.b2body.getLinearVelocity().x <= 2) {
            this.b2body.applyLinearImpulse(new Vector2(0.1f, 0f), this.b2body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && this.b2body.getLinearVelocity().x >= -2) {
            this.b2body.applyLinearImpulse(new Vector2(-0.1f, 0f), this.b2body.getWorldCenter(), true);
        }
    }
    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/2, b2body.getPosition().y-getHeight()/2);
    }
    public void defineTank(){
        BodyDef bdef= new BodyDef();
        bdef.position.set(1200 / TankStars.scaling, 450/ TankStars.scaling );
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10/ TankStars.scaling);

        fdef.shape = shape;
        b2body.createFixture(fdef);

    }
}
