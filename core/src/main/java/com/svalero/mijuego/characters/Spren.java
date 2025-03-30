package com.svalero.mijuego.characters;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.svalero.mijuego.manager.R;

public class Spren extends NPC {
    private float healAmount;
    private float healCooldown;
    private float currentCooldown;
    private SprenType type;

    public enum SprenType {
        HONOR,
        LIFE,
        STORM
    }

    public Spren(float x, float y, SprenType type) {
        super(x, y, NPCType.FRIENDLY);
        this.type = type;
        healCooldown = 5.0f;
        currentCooldown = 0;
        
        switch (type) {
            case HONOR:
                healAmount = 30;
                break;
            case LIFE:
                healAmount = 50;
                break;
            case STORM:
                healAmount = 20;
                break;
        }
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
            switch (type) {
                case HONOR:
                    player.takeDamage(-(int)healAmount);
                    R.getSound("sounds/honor_spren.wav").play();
                    break;
                case LIFE:
                    player.takeDamage(-(int)healAmount);
                    R.getSound("sounds/life_spren.wav").play();
                    break;
                case STORM:
                    // Aumentar Stormlight
                    // TODO: Implementar aumento de Stormlight
                    R.getSound("sounds/storm_spren.wav").play();
                    break;
            }
            currentCooldown = healCooldown;
        }
    }

    public SprenType getType() {
        return type;
    }
} 