package mijuego.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import mijuego.R;
import mijuego.characters.Parshendi;
import mijuego.characters.Player;
import mijuego.characters.Spren;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {
    private TiledMap map;
    private OrthoCachedTiledMapRenderer mapRenderer;
    private World world;
    private List<Body> bodies;
    private int currentLevel;

    public LevelManager(World world) {
        this.world = world;
        this.bodies = new ArrayList<>();
        this.currentLevel = 1;
    }

    public void loadLevel(int level) {
        // Limpiar cuerpos anteriores
        for (Body body : bodies) {
            world.destroyBody(body);
        }
        bodies.clear();

        // Cargar nuevo mapa
        String mapPath = "";
        switch (level) {
            case 1:
                mapPath = R.maps.LEVEL_1;
                break;
            case 2:
                mapPath = R.maps.LEVEL_2;
                break;
            case 3:
                mapPath = R.maps.LEVEL_3;
                break;
        }

        map = new TmxMapLoader().load(mapPath);
        mapRenderer = new OrthoCachedTiledMapRenderer(map);

        // Crear cuerpos físicos para las plataformas
        createPlatformBodies();
    }

    private void createPlatformBodies() {
        for (TiledMapTileMapObject platform : map.getLayers().get("platforms").getObjects().getByType(TiledMapTileMapObject.class)) {
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(platform.getX() * 1/32f, platform.getY() * 1/32f);

            Body body = world.createBody(bodyDef);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(platform.getWidth() * 1/64f, platform.getHeight() * 1/64f);

            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.density = 1.0f;
            fixtureDef.friction = 0.3f;
            fixtureDef.restitution = 0.0f;

            body.createFixture(fixtureDef);
            shape.dispose();

            bodies.add(body);
        }
    }

    public void render() {
        mapRenderer.render();
    }

    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
} 