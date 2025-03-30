package com.svalero.mijuego.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.svalero.mijuego.characters.Parshendi;
import com.svalero.mijuego.characters.Player;
import com.svalero.mijuego.characters.Spren;
import com.svalero.mijuego.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {
    private TiledMap map;
    private TiledMapRenderer renderer;
    private OrthographicCamera camera;
    private List<Rectangle> platforms;
    private List<Parshendi> parshendi;
    private List<Spren> spren;
    private Player player;
    private int currentLevel;
    private boolean isLevelComplete;

    public LevelManager(OrthographicCamera camera) {
        this.camera = camera;
        this.platforms = new ArrayList<>();
        this.parshendi = new ArrayList<>();
        this.spren = new ArrayList<>();
        this.currentLevel = 1;
        this.isLevelComplete = false;
        loadLevel(currentLevel);
    }

    private void loadLevel(int level) {
        // Cargar el mapa Tiled
        map = new TiledMap();
        // TODO: Implementar carga de mapa Tiled
        
        // Configurar el renderer
        renderer = new OrthoCachedTiledMapRenderer(map, 1f / Constants.PPM);
        renderer.setView(camera);
        
        // Cargar plataformas
        loadPlatforms();
        
        // Cargar enemigos y NPCs
        loadEntities();
        
        // Cargar jugador
        loadPlayer();
    }

    private void loadPlatforms() {
        MapObjects objects = map.getLayers().get("platforms").getObjects();
        for (MapObject object : objects) {
            if (object instanceof RectangleMapObject) {
                RectangleMapObject rectangleObject = (RectangleMapObject) object;
                Rectangle rectangle = rectangleObject.getRectangle();
                platforms.add(rectangle);
            }
        }
    }

    private void loadEntities() {
        // Cargar Parshendi
        MapObjects parshendiObjects = map.getLayers().get("parshendi").getObjects();
        for (MapObject object : parshendiObjects) {
            if (object instanceof RectangleMapObject) {
                RectangleMapObject rectangleObject = (RectangleMapObject) object;
                Rectangle rectangle = rectangleObject.getRectangle();
                parshendi.add(new Parshendi(rectangle.x, rectangle.y));
            }
        }

        // Cargar Spren
        MapObjects sprenObjects = map.getLayers().get("spren").getObjects();
        for (MapObject object : sprenObjects) {
            if (object instanceof RectangleMapObject) {
                RectangleMapObject rectangleObject = (RectangleMapObject) object;
                Rectangle rectangle = rectangleObject.getRectangle();
                String type = object.getProperties().get("type", String.class);
                Spren.SprenType sprenType = Spren.SprenType.valueOf(type);
                spren.add(new Spren(rectangle.x, rectangle.y, sprenType));
            }
        }
    }

    private void loadPlayer() {
        MapObjects playerObjects = map.getLayers().get("player").getObjects();
        for (MapObject object : playerObjects) {
            if (object instanceof RectangleMapObject) {
                RectangleMapObject rectangleObject = (RectangleMapObject) object;
                Rectangle rectangle = rectangleObject.getRectangle();
                player = new Player(rectangle.x, rectangle.y);
                break;
            }
        }
    }

    public void update(float delta) {
        // Actualizar jugador
        player.update(delta);
        
        // Actualizar Parshendi
        for (Parshendi p : parshendi) {
            p.update(delta);
        }
        
        // Actualizar Spren
        for (Spren s : spren) {
            s.update(delta);
        }
        
        // Verificar colisiones
        checkCollisions();
        
        // Verificar si el nivel está completo
        checkLevelComplete();
    }

    private void checkCollisions() {
        // Colisiones con plataformas
        for (Rectangle platform : platforms) {
            if (player.getBounds().overlaps(platform)) {
                // TODO: Implementar lógica de colisión con plataformas
            }
        }
        
        // Colisiones con Parshendi
        for (Parshendi p : parshendi) {
            if (player.getBounds().overlaps(p.getBounds())) {
                p.interact(player);
            }
        }
        
        // Colisiones con Spren
        for (Spren s : spren) {
            if (player.getBounds().overlaps(s.getBounds())) {
                s.interact(player);
            }
        }
    }

    private void checkLevelComplete() {
        // TODO: Implementar lógica para verificar si el nivel está completo
        // Por ejemplo, verificar si todos los Parshendi han sido derrotados
        // o si el jugador ha llegado a un punto específico
    }

    public void render(SpriteBatch batch) {
        renderer.render();
        player.render(batch);
        
        for (Parshendi p : parshendi) {
            p.render(batch);
        }
        
        for (Spren s : spren) {
            s.render(batch);
        }
    }

    public void dispose() {
        map.dispose();
        renderer.dispose();
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isLevelComplete() {
        return isLevelComplete;
    }

    public void nextLevel() {
        currentLevel++;
        loadLevel(currentLevel);
    }
} 