package com.stormlight.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class ResourceGenerator {
    public static void generateAllResources() {
        // Generar texturas de plataformas
        TextureGenerator.generatePlatformTextures();
        
        // Generar texturas de fondo
        TextureGenerator.generateBackgroundTextures();
        
        // Crear archivo de configuración de tilesets
        createTilesetConfig();
    }
    
    private static void createTilesetConfig() {
        StringBuilder config = new StringBuilder();
        config.append("{\n");
        config.append("  \"tilesets\": {\n");
        config.append("    \"platforms\": {\n");
        config.append("      \"name\": \"platforms\",\n");
        config.append("      \"tilewidth\": 32,\n");
        config.append("      \"tileheight\": 32,\n");
        config.append("      \"spacing\": 0,\n");
        config.append("      \"margin\": 0,\n");
        config.append("      \"tiles\": {\n");
        config.append("        \"normal\": [0, 1, 2, 3],\n");
        config.append("        \"moving\": [4, 5],\n");
        config.append("        \"breakable\": [6, 7, 8],\n");
        config.append("        \"disappearing\": [9, 10],\n");
        config.append("        \"spikes\": [11, 12]\n");
        config.append("      }\n");
        config.append("    },\n");
        config.append("    \"background\": {\n");
        config.append("      \"name\": \"background\",\n");
        config.append("      \"tilewidth\": 64,\n");
        config.append("      \"tileheight\": 64,\n");
        config.append("      \"spacing\": 0,\n");
        config.append("      \"margin\": 0,\n");
        config.append("      \"tiles\": {\n");
        config.append("        \"sky\": [0],\n");
        config.append("        \"clouds\": [1, 2, 3],\n");
        config.append("        \"mountains\": [4, 5]\n");
        config.append("      }\n");
        config.append("    }\n");
        config.append("  }\n");
        config.append("}");
        
        FileHandle file = Gdx.files.local("textures/tilesets/tilesets.json");
        file.writeString(config.toString(), false);
    }
    
    public static void main(String[] args) {
        generateAllResources();
    }
} 