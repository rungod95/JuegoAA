package com.stormlight.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;
import java.util.Map;

public class MapManager implements Disposable {
    private static MapManager instance;
    private final Map<String, TiledMap> maps;
    private final Map<String, TiledMapRenderer> renderers;
    private final JsonReader jsonReader;
    private final JsonValue mapsConfig;

    private MapManager() {
        maps = new HashMap<>();
        renderers = new HashMap<>();
        jsonReader = new JsonReader();
        
        // Cargar configuración de mapas
        mapsConfig = jsonReader.parse(Gdx.files.internal("maps/maps.json"));
        
        // Cargar mapas
        loadMaps();
    }

    public static MapManager getInstance() {
        if (instance == null) {
            instance = new MapManager();
        }
        return instance;
    }

    private void loadMaps() {
        for (JsonValue level : mapsConfig.get("levels")) {
            String name = level.getString("name");
            String file = level.getString("file");
            loadMap(name, file);
        }
    }

    private void loadMap(String name, String file) {
        TiledMap map = new TmxMapLoader().load("maps/levels/" + file);
        maps.put(name, map);
        
        // Crear renderer para el mapa
        TiledMapRenderer renderer = new OrthoCachedTiledMapRenderer(map);
        renderers.put(name, renderer);
    }

    public TiledMap getMap(String name) {
        return maps.get(name);
    }

    public TiledMapRenderer getRenderer(String name) {
        return renderers.get(name);
    }

    public MapObjects getObjects(String mapName, String layerName) {
        TiledMap map = maps.get(mapName);
        if (map != null) {
            MapLayer layer = map.getLayers().get(layerName);
            if (layer != null) {
                return layer.getObjects();
            }
        }
        return null;
    }

    public MapProperties getMapProperties(String name) {
        TiledMap map = maps.get(name);
        if (map != null) {
            return map.getProperties();
        }
        return null;
    }

    @Override
    public void dispose() {
        for (TiledMap map : maps.values()) {
            map.dispose();
        }
        maps.clear();
        renderers.clear();
        instance = null;
    }
} 