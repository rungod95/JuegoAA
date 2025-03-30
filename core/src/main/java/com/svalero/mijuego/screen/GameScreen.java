package com.svalero.mijuego.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.svalero.mijuego.MiJuego;
import com.svalero.mijuego.manager.LevelManager;
import com.svalero.mijuego.manager.R;
import com.svalero.mijuego.util.Constants;

public class GameScreen implements Screen {
    private final MiJuego game;
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final LevelManager levelManager;
    private final Stage stage;
    private final Table hudTable;
    private Label healthLabel;
    private Label stormlightLabel;
    private Label levelLabel;

    public GameScreen(MiJuego game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.batch = new SpriteBatch();
        this.levelManager = new LevelManager(camera);
        
        // Configurar HUD
        this.stage = new Stage(new FitViewport(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.hudTable = new Table();
        this.hudTable.setFillParent(true);
        setupHUD();
        stage.addActor(hudTable);
        
        // Configurar input processor
        Gdx.input.setInputProcessor(stage);
    }

    private void setupHUD() {
        // Etiquetas de estado
        healthLabel = new Label("Salud: 100", R.getSkin());
        stormlightLabel = new Label("Stormlight: 100", R.getSkin());
        levelLabel = new Label("Nivel: 1", R.getSkin());

        // Añadir etiquetas a la tabla
        hudTable.top().left();
        hudTable.add(healthLabel).pad(10);
        hudTable.add(stormlightLabel).pad(10);
        hudTable.row();
        hudTable.add(levelLabel).pad(10);
    }

    @Override
    public void show() {
        // Iniciar música de batalla
        R.getMusic("battle_theme.mp3").play();
        R.getMusic("battle_theme.mp3").setLooping(true);
    }

    @Override
    public void render(float delta) {
        // Limpiar pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Actualizar cámara
        camera.update();

        // Actualizar nivel
        levelManager.update(delta);

        // Renderizar nivel
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        levelManager.render(batch);
        batch.end();

        // Actualizar HUD
        updateHUD();
        stage.act(delta);
        stage.draw();

        // Verificar si el nivel está completo
        if (levelManager.isLevelComplete()) {
            levelManager.nextLevel();
        }
    }

    private void updateHUD() {
        // Actualizar etiquetas con el estado actual del jugador
        healthLabel.setText("Salud: " + (int)levelManager.getPlayer().getHealth());
        stormlightLabel.setText("Stormlight: " + (int)levelManager.getPlayer().getStormlight());
        levelLabel.setText("Nivel: " + levelManager.getCurrentLevel());
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Pausar música
        R.getMusic("battle_theme.mp3").pause();
    }

    @Override
    public void resume() {
        // Reanudar música
        R.getMusic("battle_theme.mp3").play();
    }

    @Override
    public void hide() {
        // Detener música
        R.getMusic("battle_theme.mp3").stop();
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        levelManager.dispose();
    }
} 