package com.svalero.mijuego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.svalero.mijuego.manager.R;
import com.svalero.mijuego.screen.GameScreen;
import com.svalero.mijuego.screen.MainMenuScreen;
import com.svalero.mijuego.screen.SplashScreen;

public class MiJuego extends Game {

    private SpriteBatch batch;
    public boolean pause;
    public int currentLevel;
    public int score;
    public int energy;
    public String playerName;

    public MiJuego() {
        this.pause = false;
        this.currentLevel = 1;
        this.score = 0;
        this.energy = 100;
        this.playerName = "Jugador";
    }

    @Override
    public void create() {
        // Inicializar SpriteBatch
        batch = new SpriteBatch();
        
        // Inicializar recursos
        R.init();
        
        // Establecer pantalla inicial
        setScreen(new SplashScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        R.dispose();
    }

    public void resetGame() {
        this.score = 0;
        this.energy = 100;
        this.currentLevel = 1;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public boolean isPaused() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
} 