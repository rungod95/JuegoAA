package mijuego.util;

public class Constants {
    // Configuración de la ventana
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 480;

    // Configuración del jugador
    public static final float PLAYER_WIDTH = 32;
    public static final float PLAYER_HEIGHT = 64;
    public static final float PLAYER_SPEED = 200;
    public static final float PLAYER_JUMP_FORCE = 400;
    public static final float PLAYER_MAX_HEALTH = 100;
    public static final float PLAYER_MAX_STORMLIGHT = 100;
    public static final float PLAYER_STORMLIGHT_REGEN_RATE = 5;
    public static final float PLAYER_LASH_COST = 20;
    public static final float PLAYER_LASH_DURATION = 5.0f;

    // Configuración de los Parshendi
    public static final float PASHENDI_WIDTH = 32;
    public static final float PASHENDI_HEIGHT = 48;
    public static final float PASHENDI_HEALTH = 100;
    public static final float PASHENDI_DAMAGE = 20;
    public static final float PASHENDI_ATTACK_COOLDOWN = 2.0f;

    // Configuración de los Spren
    public static final float SPREN_WIDTH = 16;
    public static final float SPREN_HEIGHT = 16;
    public static final float SPREN_HEALTH = 50;
    public static final float SPREN_HEAL_AMOUNT = 10;
    public static final float SPREN_HEAL_COOLDOWN = 5.0f;

    // Configuración de la física
    public static final float GRAVITY = -9.81f;
    public static final float PLATFORM_FRICTION = 0.3f;
    public static final float PLATFORM_RESTITUTION = 0.0f;

    // Configuración de la cámara
    public static final float CAMERA_ZOOM = 1.0f;
    public static final float CAMERA_LERP = 0.1f;

    // Configuración de las capas del mapa
    public static final String MAP_LAYER_PLATFORMS = "platforms";
    public static final String MAP_LAYER_BACKGROUND = "background";
    public static final String MAP_LAYER_FOREGROUND = "foreground";

    // Configuración de las animaciones
    public static final float ANIMATION_FRAME_DURATION = 0.1f;
} 