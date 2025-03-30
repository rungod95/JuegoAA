package com.stormlight.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

public class AnimationManager implements Disposable {
    private static AnimationManager instance;
    private final Map<String, Map<String, Animation<TextureRegion>>> animations;
    private final JsonReader jsonReader;
    private final JsonValue animationsConfig;
    private final TextureManager textureManager;

    private AnimationManager() {
        animations = new HashMap<>();
        jsonReader = new JsonReader();
        textureManager = TextureManager.getInstance();
        
        // Cargar configuración de animaciones
        animationsConfig = jsonReader.parse(Gdx.files.internal("animations/animations.json"));
        
        // Cargar animaciones
        loadAnimations();
    }

    public static AnimationManager getInstance() {
        if (instance == null) {
            instance = new AnimationManager();
        }
        return instance;
    }

    private void loadAnimations() {
        // Cargar animaciones del jugador
        JsonValue playerAnimations = animationsConfig.get("player");
        loadCharacterAnimations("player", playerAnimations);

        // Cargar animaciones de enemigos
        JsonValue enemyAnimations = animationsConfig.get("enemies");
        loadCharacterAnimations("enemies", enemyAnimations);

        // Cargar animaciones de spren
        JsonValue sprenAnimations = animationsConfig.get("spren");
        loadCharacterAnimations("spren", sprenAnimations);
    }

    private void loadCharacterAnimations(String character, JsonValue animationsConfig) {
        Map<String, Animation<TextureRegion>> characterAnimations = new HashMap<>();
        
        for (JsonValue animConfig : animationsConfig) {
            String name = animConfig.name();
            float frameDuration = animConfig.getFloat("duration");
            String[] frames = animConfig.get("frames").asStringArray();
            
            TextureRegion[] regions = new TextureRegion[frames.length];
            for (int i = 0; i < frames.length; i++) {
                regions[i] = textureManager.getRegion(frames[i]);
            }
            
            Animation<TextureRegion> animation = new Animation<>(frameDuration, regions);
            characterAnimations.put(name, animation);
        }
        
        animations.put(character, characterAnimations);
    }

    public Animation<TextureRegion> getAnimation(String character, String name) {
        Map<String, Animation<TextureRegion>> characterAnimations = animations.get(character);
        if (characterAnimations != null) {
            return characterAnimations.get(name);
        }
        return null;
    }

    public float getAnimationDuration(String character, String name) {
        Animation<TextureRegion> animation = getAnimation(character, name);
        if (animation != null) {
            return animation.getFrameDuration() * animation.getKeyFrames().length;
        }
        return 0f;
    }

    @Override
    public void dispose() {
        animations.clear();
        instance = null;
    }
} 