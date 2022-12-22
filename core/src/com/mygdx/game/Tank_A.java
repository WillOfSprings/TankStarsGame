package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Tank_A extends Sprite {
        public World world;
        private Body b2body;

    public Tank_A(World world){

        this.world = world;
        Texture tank = new Texture("images/gameAbramas.png");
        TextureRegion tank1 = new TextureRegion(tank, 0, 0, 130, 100);
        setBounds(0,0,130/ TankStars.scaling,100/ TankStars.scaling);
        setRegion(tank1);
        defineTank();
    }
    public void update(float dt){
        setPosition(b2body.getPosition().x-getWidth()/2, b2body.getPosition().y-getHeight()/2);
    }

    public void handleInput(){
        if (Gdx.input.isKeyPressed(Input.Keys.UP)&& this.b2body.getLinearVelocity().y<=0.5){
            this.b2body.applyLinearImpulse(new Vector2(0f,0.5f),this.b2body.getWorldCenter(),true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)&& this.getX()<Gdx.graphics.getWidth() && this.b2body.getLinearVelocity().x<=2){
            this.b2body.applyLinearImpulse(new Vector2(0.1f, 0f),this.b2body.getWorldCenter(),true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)&&this.b2body.getLinearVelocity().x>=-2){
            this.b2body.applyLinearImpulse(new Vector2(-0.1f, 0f),this.b2body.getWorldCenter(),true);
        }
    }
    public void defineTank(){
        BodyDef bdef= new BodyDef();
        bdef.position.set(400 / TankStars.scaling, 400 / TankStars.scaling );
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10/ TankStars.scaling);

        fdef.shape = shape;
        b2body.createFixture(fdef);


    }
}