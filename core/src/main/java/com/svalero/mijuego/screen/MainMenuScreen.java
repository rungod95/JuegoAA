package com.svalero.mijuego.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.svalero.mijuego.MiJuego;
import com.svalero.mijuego.manager.R;
import com.svalero.mijuego.util.Constants;

public class MainMenuScreen implements Screen {
    private final MiJuego game;
    private final Stage stage;
    private final OrthographicCamera camera;

    public MainMenuScreen(MiJuego game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.stage = new Stage(new FitViewport(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, camera));
        
        // Configurar input processor
        Gdx.input.setInputProcessor(stage);
        
        // Crear UI
        createUI();
    }

    private void createUI() {
        // Crear tabla principal
        Table mainTable = new Table();
        mainTable.setFillParent(true);

        // Crear título
        Label titleLabel = new Label("El Archivo de las Tormentas", R.getSkin(), "title");
        mainTable.add(titleLabel).padBottom(50).row();

        // Crear botones
        TextButton playButton = new TextButton("Jugar", R.getSkin());
        TextButton optionsButton = new TextButton("Opciones", R.getSkin());
        TextButton exitButton = new TextButton("Salir", R.getSkin());

        // Añadir botones a la tabla
        mainTable.add(playButton).width(200).height(50).padBottom(20).row();
        mainTable.add(optionsButton).width(200).height(50).padBottom(20).row();
        mainTable.add(exitButton).width(200).height(50).row();

        // Añadir listeners a los botones
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });

        optionsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // TODO: Implementar pantalla de opciones
            }
        });

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        // Añadir tabla al stage
        stage.addActor(mainTable);
    }

    @Override
    public void show() {
        // Iniciar música de menú
        R.getMusic("main_theme.mp3").play();
        R.getMusic("main_theme.mp3").setLooping(true);
    }

    @Override
    public void render(float delta) {
        // Limpiar pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Actualizar y renderizar stage
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // Pausar música
        R.getMusic("main_theme.mp3").pause();
    }

    @Override
    public void resume() {
        // Reanudar música
        R.getMusic("main_theme.mp3").play();
    }

    @Override
    public void hide() {
        // Detener música
        R.getMusic("main_theme.mp3").stop();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
} 