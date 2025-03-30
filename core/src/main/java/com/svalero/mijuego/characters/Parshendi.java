package com.svalero.mijuego.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.svalero.mijuego.manager.R;

public class Parshendi extends NPC {
    private float attackCooldown;
    private float currentCooldown;
    private int damage;
    private boolean isInWarform;

    public Parshendi(float x, float y) {
        super(x, y, NPCType.HOSTILE);
        attackCooldown = 2.0f;
        currentCooldown = 0;
        damage = 20;
        isInWarform = false;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        
        if (currentCooldown > 0) {
            currentCooldown -= delta;
        }
    }

    @Override
    public void interact(Player player) {
        if (currentCooldown <= 0) {
            player.takeDamage(damage);
            currentCooldown = attackCooldown;
            R.getSound("sounds/parshendi_attack.wav").play();
        }
    }

    public void transformToWarform() {
        if (!isInWarform) {
            isInWarform = true;
            damage *= 2;
            health *= 2;
            R.getSound("sounds/parshendi_transform.wav").play();
        }
    }

    public boolean isInWarform() {
        return isInWarform;
    }
} 