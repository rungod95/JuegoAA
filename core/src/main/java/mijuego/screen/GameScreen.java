package mijuego.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import mijuego.MiJuego;
import mijuego.R;
import mijuego.characters.Parshendi;
import mijuego.characters.Player;
import mijuego.characters.Spren;
import mijuego.manager.LevelManager;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
    private final MiJuego game;
    private OrthographicCamera camera;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private TiledMap map;
    private OrthoCachedTiledMapRenderer mapRenderer;
    private Player player;
    private List<Parshendi> parshendiList;
    private List<Spren> sprenList;
    private LevelManager levelManager;
    private int currentLevel;

    public GameScreen(MiJuego game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.world = new World(new Vector2(0, -9.81f), true);
        this.debugRenderer = new Box2DDebugRenderer();
        this.parshendiList = new ArrayList<>();
        this.sprenList = new ArrayList<>();
        this.currentLevel = 1;
        setupLevel(currentLevel);
    }

    private void setupLevel(int level) {
        // Cargar mapa
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

        // Configurar cámara
        camera.setToOrtho(false, map.getProperties().get("width", Integer.class) * 32,
                map.getProperties().get("height", Integer.class) * 32);
        camera.update();

        // Crear jugador
        player = new Player(100, 100);

        // Crear enemigos y NPCs
        createEnemies();
        createNPCs();

        // Iniciar música de batalla
        game.playBattleTheme();
    }

    private void createEnemies() {
        // Crear Parshendi en posiciones específicas
        parshendiList.add(new Parshendi(200, 100));
        parshendiList.add(new Parshendi(400, 100));
        parshendiList.add(new Parshendi(600, 100));
    }

    private void createNPCs() {
        // Crear Spren en posiciones específicas
        sprenList.add(new Spren(300, 200));
        sprenList.add(new Spren(500, 200));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Actualizar física
        world.step(1/60f, 6, 2);

        // Actualizar entidades
        player.update(delta);
        for (Parshendi parshendi : parshendiList) {
            parshendi.update(delta);
        }
        for (Spren spren : sprenList) {
            spren.update(delta);
        }

        // Renderizar mapa
        mapRenderer.setView(camera);
        mapRenderer.render();

        // Renderizar entidades
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        player.render(game.batch);
        for (Parshendi parshendi : parshendiList) {
            parshendi.render(game.batch);
        }
        for (Spren spren : sprenList) {
            spren.render(game.batch);
        }
        game.batch.end();

        // Renderizar debug
        debugRenderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        map.dispose();
        mapRenderer.dispose();
        world.dispose();
        debugRenderer.dispose();
    }
} 