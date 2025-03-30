package com.svalero.mijuego.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.svalero.mijuego.MiJuego;
import com.svalero.mijuego.manager.R;
import com.svalero.mijuego.util.Constants;

public class SplashScreen implements Screen {
    private final MiJuego game;
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final Stage stage;
    private final Table table;
    private final ProgressBar progressBar;
    private final Label loadingLabel;
    private float progress;
    private float loadingTime;

    public SplashScreen(MiJuego game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.batch = new SpriteBatch();
        this.stage = new Stage(new FitViewport(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, camera));
        
        // Configurar input processor
        Gdx.input.setInputProcessor(stage);
        
        // Crear UI
        table = new Table();
        table.setFillParent(true);
        
        // Crear barra de progreso
        progressBar = new ProgressBar(0, 1, 0.01f, false, R.getSkin());
        progressBar.setWidth(400);
        progressBar.setHeight(20);
        
        // Crear etiqueta de carga
        loadingLabel = new Label("Cargando...", R.getSkin());
        
        // Añadir elementos a la tabla
        table.add(loadingLabel).padBottom(20).row();
        table.add(progressBar).width(400).height(20);
        
        // Añadir tabla al stage
        stage.addActor(table);
        
        // Inicializar variables
        progress = 0;
        loadingTime = 0;
    }

    @Override
    public void show() {
        // Iniciar carga de recursos
        R.init();
    }

    @Override
    public void render(float delta) {
        // Limpiar pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Actualizar progreso
        loadingTime += delta;
        progress = Math.min(loadingTime / 3f, 1f);
        progressBar.setValue(progress);
        
        // Actualizar etiqueta de carga
        if (progress < 0.3f) {
            loadingLabel.setText("Cargando recursos...");
        } else if (progress < 0.6f) {
            loadingLabel.setText("Preparando el mundo...");
        } else if (progress < 0.9f) {
            loadingLabel.setText("Casi listo...");
        }

        // Actualizar y renderizar stage
        stage.act(delta);
        stage.draw();

        // Verificar si la carga está completa
        if (progress >= 1f) {
            game.setScreen(new MainMenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // No se implementa
    }

    @Override
    public void resume() {
        // No se implementa
    }

    @Override
    public void hide() {
        // No se implementa
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }
} 