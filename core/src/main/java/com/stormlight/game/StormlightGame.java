package com.stormlight.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.stormlight.game.screens.MainMenuScreen;
import com.stormlight.game.utils.TextureManager;
import com.stormlight.game.utils.MapManager;
import com.stormlight.game.utils.AnimationManager;

public class StormlightGame extends Game {
    private SpriteBatch batch;
    private TextureManager textureManager;
    private MapManager mapManager;
    private AnimationManager animationManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        textureManager = TextureManager.getInstance();
        mapManager = MapManager.getInstance();
        animationManager = AnimationManager.getInstance();
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        textureManager.dispose();
        mapManager.dispose();
        animationManager.dispose();
        super.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public TextureManager getTextureManager() {
        return textureManager;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public AnimationManager getAnimationManager() {
        return animationManager;
    }
} 