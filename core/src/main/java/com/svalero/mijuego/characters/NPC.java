package com.svalero.mijuego.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class NPC {
    protected Vector2 position;
    protected Vector2 velocity;
    protected Rectangle bounds;
    protected Animation<TextureRegion> animation;
    protected float stateTime;
    protected boolean facingRight;
    protected int health;
    protected NPCType type;

    public enum NPCType {
        FRIENDLY,
        HOSTILE,
        NEUTRAL
    }

    public NPC(float x, float y, NPCType type) {
        position = new Vector2(x, y);
        velocity = new Vector2();
        bounds = new Rectangle(x, y, 32, 32);
        this.type = type;
        stateTime = 0;
        facingRight = true;
        health = 100;
    }

    public void update(float delta) {
        position.add(velocity.x * delta, velocity.y * delta);
        bounds.setPosition(position.x, position.y);
        stateTime += delta;
    }

    public void render(SpriteBatch batch) {
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        if (!facingRight) {
            batch.draw(currentFrame, position.x + currentFrame.getRegionWidth(), position.y, 
                    -currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        } else {
            batch.draw(currentFrame, position.x, position.y);
        }
    }

    public abstract void interact(Player player);

    public void takeDamage(int damage) {
        health -= damage;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }

    public NPCType getType() {
        return type;
    }
} 