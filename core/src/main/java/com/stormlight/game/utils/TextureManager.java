package com.stormlight.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;

public class TextureManager implements Disposable {
    private static TextureManager instance;
    private ObjectMap<String, Texture> textures;
    private ObjectMap<String, TextureRegion> regions;

    private TextureManager() {
        textures = new ObjectMap<>();
        regions = new ObjectMap<>();
        generateTextures();
    }

    public static TextureManager getInstance() {
        if (instance == null) {
            instance = new TextureManager();
        }
        return instance;
    }

    private void generateTextures() {
        // Generar texturas de plataformas
        TextureGenerator.generatePlatformTextures();
        
        // Generar texturas de fondo
        TextureGenerator.generateBackgroundTextures();
        
        // Cargar texturas generadas
        loadTexture("platforms", "textures/tilesets/platforms.png");
        loadTexture("background", "textures/tilesets/background.png");
    }

    private void loadTexture(String name, String path) {
        Texture texture = new Texture(Gdx.files.internal(path));
        textures.put(name, texture);
    }

    public void createRegion(String name, String textureName, int x, int y, int width, int height) {
        Texture texture = textures.get(textureName);
        if (texture != null) {
            TextureRegion region = new TextureRegion(texture, x, y, width, height);
            regions.put(name, region);
        }
    }

    public Texture getTexture(String name) {
        return textures.get(name);
    }

    public TextureRegion getRegion(String name) {
        return regions.get(name);
    }

    @Override
    public void dispose() {
        for (Texture texture : textures.values()) {
            texture.dispose();
        }
        textures.clear();
        regions.clear();
        instance = null;
    }
} 