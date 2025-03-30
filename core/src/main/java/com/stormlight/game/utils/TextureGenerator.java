package com.stormlight.game.utils;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap.Format;

public class TextureGenerator {
    private static final int PLATFORM_SIZE = 32;
    private static final int BACKGROUND_SIZE = 64;

    public static void generatePlatformTextures() {
        Pixmap pixmap = new Pixmap(128, 104, Pixmap.Format.RGBA8888);
        
        // Plataformas normales (4 variantes)
        generateNormalPlatforms(pixmap, 0, 0);
        
        // Plataformas móviles (2 variantes)
        generateMovingPlatforms(pixmap, 0, 32);
        
        // Plataformas rompibles (3 variantes)
        generateBreakablePlatforms(pixmap, 0, 64);
        
        // Plataformas que desaparecen (2 variantes)
        generateDisappearingPlatforms(pixmap, 96, 0);
        
        // Plataformas con picos (2 variantes)
        generateSpikePlatforms(pixmap, 96, 32);
        
        // Guardar el archivo
        FileHandle file = Gdx.files.local("textures/tilesets/platforms.png");
        file.parent().mkdirs();
        file.writeBytes(pixmap.getPixels(), false);
        
        pixmap.dispose();
    }
    
    public static void generateBackgroundTextures() {
        Pixmap pixmap = new Pixmap(192, 128, Pixmap.Format.RGBA8888);
        
        // Cielo
        generateSkyTile(pixmap, 0, 0);
        
        // Nubes (3 variantes)
        generateCloudTiles(pixmap, 64, 0);
        
        // Montañas (2 variantes)
        generateMountainTiles(pixmap, 0, 64);
        
        // Guardar el archivo
        FileHandle file = Gdx.files.local("textures/tilesets/background.png");
        file.parent().mkdirs();
        file.writeBytes(pixmap.getPixels(), false);
        
        pixmap.dispose();
    }
    
    private static void generateNormalPlatforms(Pixmap pixmap, int x, int y) {
        Color baseColor = new Color(0.6f, 0.4f, 0.2f, 1); // Marrón
        Color topColor = new Color(0.7f, 0.5f, 0.3f, 1); // Marrón claro
        
        for (int i = 0; i < 4; i++) {
            drawPlatform(pixmap, x + (i * 32), y, baseColor, topColor);
        }
    }
    
    private static void generateMovingPlatforms(Pixmap pixmap, int x, int y) {
        Color baseColor = new Color(0.4f, 0.4f, 0.8f, 1); // Azul
        Color topColor = new Color(0.5f, 0.5f, 0.9f, 1); // Azul claro
        
        for (int i = 0; i < 2; i++) {
            drawPlatform(pixmap, x + (i * 32), y, baseColor, topColor);
        }
    }
    
    private static void generateBreakablePlatforms(Pixmap pixmap, int x, int y) {
        Color baseColor = new Color(0.8f, 0.4f, 0.4f, 1); // Rojo
        Color topColor = new Color(0.9f, 0.5f, 0.5f, 1); // Rojo claro
        
        for (int i = 0; i < 3; i++) {
            drawPlatform(pixmap, x + (i * 32), y, baseColor, topColor);
            drawCracks(pixmap, x + (i * 32), y, Color.BLACK);
        }
    }
    
    private static void generateDisappearingPlatforms(Pixmap pixmap, int x, int y) {
        Color baseColor = new Color(0.4f, 0.8f, 0.4f, 1); // Verde
        Color topColor = new Color(0.5f, 0.9f, 0.5f, 1); // Verde claro
        
        for (int i = 0; i < 2; i++) {
            drawPlatform(pixmap, x, y + (i * 32), baseColor, topColor);
            drawFade(pixmap, x, y + (i * 32), new Color(1, 1, 1, 0.5f));
        }
    }
    
    private static void generateSpikePlatforms(Pixmap pixmap, int x, int y) {
        Color baseColor = new Color(0.3f, 0.3f, 0.3f, 1); // Gris oscuro
        Color topColor = new Color(0.4f, 0.4f, 0.4f, 1); // Gris
        Color spikeColor = new Color(0.2f, 0.2f, 0.2f, 1); // Gris más oscuro
        
        for (int i = 0; i < 2; i++) {
            drawPlatform(pixmap, x, y + (i * 32), baseColor, topColor);
            drawSpikes(pixmap, x, y + (i * 32), spikeColor);
        }
    }
    
    private static void drawPlatform(Pixmap pixmap, int x, int y, Color baseColor, Color topColor) {
        // Base de la plataforma
        pixmap.setColor(baseColor);
        pixmap.fillRectangle(x, y + 8, 32, 24);
        
        // Parte superior de la plataforma
        pixmap.setColor(topColor);
        pixmap.fillRectangle(x, y, 32, 8);
    }
    
    private static void drawCracks(Pixmap pixmap, int x, int y, Color color) {
        pixmap.setColor(color);
        // Dibujar grietas aleatorias
        for (int i = 0; i < 5; i++) {
            int startX = x + (int)(Math.random() * 32);
            int startY = y + (int)(Math.random() * 32);
            int endX = startX + (int)(Math.random() * 10) - 5;
            int endY = startY + (int)(Math.random() * 10) - 5;
            pixmap.drawLine(startX, startY, endX, endY);
        }
    }
    
    private static void drawFade(Pixmap pixmap, int x, int y, Color fadeColor) {
        // Crear un efecto de desvanecimiento
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++) {
                if ((i + j) % 4 == 0) {
                    pixmap.setColor(fadeColor);
                    pixmap.drawPixel(x + i, y + j);
                }
            }
        }
    }
    
    private static void drawSpikes(Pixmap pixmap, int x, int y, Color color) {
        pixmap.setColor(color);
        // Dibujar picos en la parte superior
        for (int i = 0; i < 4; i++) {
            int spikeX = x + (i * 8) + 4;
            pixmap.fillTriangle(spikeX, y, spikeX + 4, y - 4, spikeX + 8, y);
        }
    }
    
    private static void generateSkyTile(Pixmap pixmap, int x, int y) {
        Color skyColor = new Color(0.5f, 0.7f, 1.0f, 1); // Azul cielo
        pixmap.setColor(skyColor);
        pixmap.fillRectangle(x, y, 64, 64);
    }
    
    private static void generateCloudTiles(Pixmap pixmap, int x, int y) {
        Color cloudColor = new Color(1.0f, 1.0f, 1.0f, 0.8f); // Blanco semi-transparente
        
        for (int i = 0; i < 3; i++) {
            drawCloud(pixmap, x + (i * 64), y, cloudColor);
        }
    }
    
    private static void generateMountainTiles(Pixmap pixmap, int x, int y) {
        Color mountainColor = new Color(0.4f, 0.3f, 0.2f, 1); // Marrón oscuro
        Color snowColor = new Color(1.0f, 1.0f, 1.0f, 1); // Blanco
        
        for (int i = 0; i < 2; i++) {
            drawMountain(pixmap, x + (i * 64), y, mountainColor, snowColor);
        }
    }
    
    private static void drawCloud(Pixmap pixmap, int x, int y, Color color) {
        pixmap.setColor(color);
        // Dibujar una nube simple con círculos
        pixmap.fillCircle(x + 32, y + 32, 20);
        pixmap.fillCircle(x + 22, y + 32, 15);
        pixmap.fillCircle(x + 42, y + 32, 15);
    }
    
    private static void drawMountain(Pixmap pixmap, int x, int y, Color mountainColor, Color snowColor) {
        // Base de la montaña
        pixmap.setColor(mountainColor);
        pixmap.fillTriangle(x + 32, y, x, y + 64, x + 64, y + 64);
        
        // Nieve en la cima
        pixmap.setColor(snowColor);
        pixmap.fillTriangle(x + 32, y, x + 22, y + 20, x + 42, y + 20);
    }
} 