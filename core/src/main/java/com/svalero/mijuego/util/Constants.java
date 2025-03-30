package com.svalero.mijuego.util;

public class Constants {
    // Píxeles por metro (para el sistema de física)
    public static final float PPM = 100;

    // Dimensiones de la ventana
    public static final float WINDOW_WIDTH = 800;
    public static final float WINDOW_HEIGHT = 480;

    // Física
    public static final float GRAVITY = -9.81f;
    public static final float JUMP_FORCE = 5f;
    public static final float MOVEMENT_SPEED = 2f;

    // Kaladin
    public static final float KALADIN_WIDTH = 32f;
    public static final float KALADIN_HEIGHT = 48f;
    public static final float KALADIN_MAX_HEALTH = 100f;
    public static final float KALADIN_MAX_STORMLIGHT = 100f;
    public static final float KALADIN_STORMLIGHT_RECOVERY = 1f;
    public static final float KALADIN_LASH_DURATION = 5f;
    public static final float KALADIN_LASH_COOLDOWN = 10f;

    // Parshendi
    public static final float PASHENDI_WIDTH = 32f;
    public static final float PASHENDI_HEIGHT = 48f;
    public static final float PASHENDI_MAX_HEALTH = 50f;
    public static final float PASHENDI_DAMAGE = 10f;
    public static final float PASHENDI_ATTACK_COOLDOWN = 2f;
    public static final float PASHENDI_WARFORM_MULTIPLIER = 2f;

    // Spren
    public static final float SPREN_WIDTH = 16f;
    public static final float SPREN_HEIGHT = 16f;
    public static final float SPREN_HEAL_COOLDOWN = 5f;

    // Capas del mapa
    public static final String MAP_LAYER_PLATFORMS = "platforms";
    public static final String MAP_LAYER_PLAYER = "player";
    public static final String MAP_LAYER_PASHENDI = "parshendi";
    public static final String MAP_LAYER_SPREN = "spren";
    public static final String MAP_LAYER_BACKGROUND = "background";
    public static final String MAP_LAYER_FOREGROUND = "foreground";

    // Rutas de recursos
    public static final String TEXTURES_PATH = "textures/";
    public static final String SOUNDS_PATH = "sounds/";
    public static final String MUSIC_PATH = "music/";
    public static final String MAPS_PATH = "maps/";
    public static final String SKIN_PATH = "skin/";

    // Nombres de archivos
    public static final String KALADIN_ATLAS = "kaladin.atlas";
    public static final String PASHENDI_ATLAS = "parshendi.atlas";
    public static final String SPREN_ATLAS = "spren.atlas";
    public static final String UI_SKIN = "uiskin.json";
    public static final String LEVEL_PREFIX = "level_";
    public static final String LEVEL_EXTENSION = ".tmx";
} 