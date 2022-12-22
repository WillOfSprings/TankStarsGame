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
        private Texture tank;
        private TextureRegion tank1;
        private int tankX = 400;
        private int tankY = 400;
        private Weapons missile;
        private int health;
//        private bullets getMissile
    public int getHealth() {return health;}
    public void setHealth(int health) {this.health = health;}

    public int getTankX() {return tankX;}
    public void setTankX(int tankX) {this.tankX = tankX;}
    public int getTankY() {return tankY;}
    public void setTankY(int tankY) {this.tankY = tankY;}

    public Tank_A(World world){

        this.world = world;
        tank = new Texture("images/gameAbramas.png");
        tank1 = new TextureRegion(tank, 0,0,130,100);
        setBounds(0,0,130/ TankStars.scaling,100/ TankStars.scaling);
        setRegion(tank1);
        missile = new Weapons(this);
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
//        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//            fireBullet();
//        }
    }
//    public void fireBullet(){
//        missile = new bullets(this);
//    }
    public void defineTank(){
        BodyDef bdef= new BodyDef();
        bdef.position.set(this.getTankX()/ TankStars.scaling,this.getTankY()/ TankStars.scaling );
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10/ TankStars.scaling);

        fdef.shape = shape;
        b2body.createFixture(fdef);


    }
}