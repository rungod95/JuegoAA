package com.svalero.mijuego.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Vector2 position;
    private Vector2 velocity;
    private Rectangle bounds;
    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> jumpAnimation;
    private Animation<TextureRegion> lashAnimation;
    private float stateTime;
    private boolean facingRight;
    private int energy;
    private int lives;
    private boolean isJumping;
    private boolean isLashing;
    private float lashTime;
    private int stormlight;

    public Player(float x, float y) {
        position = new Vector2(x, y);
        velocity = new Vector2();
        bounds = new Rectangle(x, y, 32, 64); // Kaladin es más alto
        energy = 100;
        lives = 3;
        facingRight = true;
        stateTime = 0;
        isJumping = false;
        isLashing = false;
        lashTime = 0;
        stormlight = 100;
    }

    public void update(float delta) {
        // Aplicar gravedad si no está usando Lash
        if (!isLashing) {
            velocity.y -= 500 * delta;
        }

        position.add(velocity.x * delta, velocity.y * delta);
        bounds.setPosition(position.x, position.y);
        stateTime += delta;

        // Actualizar tiempo de Lash
        if (isLashing) {
            lashTime += delta;
            if (lashTime >= 5) { // El Lash dura 5 segundos
                isLashing = false;
                stormlight -= 20;
            }
        }

        // Recuperar Stormlight con el tiempo
        if (stormlight < 100) {
            stormlight += 5 * delta;
        }
    }

    public void render(SpriteBatch batch) {
        TextureRegion currentFrame;
        if (isLashing) {
            currentFrame = lashAnimation.getKeyFrame(stateTime, true);
        } else if (isJumping) {
            currentFrame = jumpAnimation.getKeyFrame(stateTime, true);
        } else {
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        }

        if (!facingRight) {
            batch.draw(currentFrame, position.x + currentFrame.getRegionWidth(), position.y, 
                    -currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        } else {
            batch.draw(currentFrame, position.x, position.y);
        }
    }

    public void move(float x, float y) {
        velocity.set(x, y);
        if (x > 0) facingRight = true;
        if (x < 0) facingRight = false;
    }

    public void jump() {
        if (!isJumping && !isLashing) {
            velocity.y = 400;
            isJumping = true;
            R.getSound("sounds/jump.wav").play();
        }
    }

    public void useLash() {
        if (!isLashing && stormlight >= 20) {
            isLashing = true;
            lashTime = 0;
            velocity.y = 200; // Movimiento hacia arriba con Lash
            R.getSound("sounds/lash.wav").play();
        }
    }

    public void takeDamage(int damage) {
        energy -= damage;
        if (energy <= 0) {
            lives--;
            energy = 100;
        }
    }

    public boolean isDead() {
        return lives <= 0;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public int getLives() {
        return lives;
    }

    public int getStormlight() {
        return stormlight;
    }

    public boolean isLashing() {
        return isLashing;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }
} 