package com.svalero.mijuego.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.svalero.mijuego.util.Constants;

public class R {
    private static AssetManager assetManager;
    private static Skin skin;

    public static void init() {
        assetManager = new AssetManager();
        loadResources();
    }

    private static void loadResources() {
        // Cargar texturas
        assetManager.load(Constants.TEXTURES_PATH + Constants.KALADIN_ATLAS, TextureAtlas.class);
        assetManager.load(Constants.TEXTURES_PATH + Constants.PASHENDI_ATLAS, TextureAtlas.class);
        assetManager.load(Constants.TEXTURES_PATH + Constants.SPREN_ATLAS, TextureAtlas.class);

        // Cargar sonidos
        assetManager.load(Constants.SOUNDS_PATH + "jump.wav", Sound.class);
        assetManager.load(Constants.SOUNDS_PATH + "lash.wav", Sound.class);
        assetManager.load(Constants.SOUNDS_PATH + "parshendi_attack.wav", Sound.class);
        assetManager.load(Constants.SOUNDS_PATH + "parshendi_transform.wav", Sound.class);
        assetManager.load(Constants.SOUNDS_PATH + "honor_spren.wav", Sound.class);
        assetManager.load(Constants.SOUNDS_PATH + "life_spren.wav", Sound.class);
        assetManager.load(Constants.SOUNDS_PATH + "storm_spren.wav", Sound.class);

        // Cargar música
        assetManager.load(Constants.MUSIC_PATH + "main_theme.mp3", Music.class);
        assetManager.load(Constants.MUSIC_PATH + "battle_theme.mp3", Music.class);

        // Cargar skin
        assetManager.load(Constants.SKIN_PATH + Constants.UI_SKIN, Skin.class);

        // Esperar a que se carguen todos los recursos
        assetManager.finishLoading();

        // Inicializar skin
        skin = assetManager.get(Constants.SKIN_PATH + Constants.UI_SKIN, Skin.class);
    }

    public static TextureAtlas getAtlas(String name) {
        return assetManager.get(Constants.TEXTURES_PATH + name, TextureAtlas.class);
    }

    public static Sound getSound(String name) {
        return assetManager.get(Constants.SOUNDS_PATH + name, Sound.class);
    }

    public static Music getMusic(String name) {
        return assetManager.get(Constants.MUSIC_PATH + name, Music.class);
    }

    public static Skin getSkin() {
        return skin;
    }

    public static void dispose() {
        assetManager.dispose();
    }
} 