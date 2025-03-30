package mijuego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import mijuego.screen.GameScreen;
import mijuego.screen.MainMenuScreen;
import mijuego.screen.SplashScreen;

public class MiJuego extends Game {
    public SpriteBatch batch;
    private Music mainTheme;
    private Music battleTheme;

    @Override
    public void create() {
        batch = new SpriteBatch();
        mainTheme = Gdx.audio.newMusic(Gdx.files.internal(R.music.MAIN_THEME));
        battleTheme = Gdx.audio.newMusic(Gdx.files.internal(R.music.BATTLE_THEME));
        mainTheme.setLooping(true);
        mainTheme.play();
        setScreen(new SplashScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        mainTheme.dispose();
        battleTheme.dispose();
        super.dispose();
    }

    public void startGame() {
        setScreen(new GameScreen(this));
    }

    public void showMainMenu() {
        setScreen(new MainMenuScreen(this));
    }

    public void playBattleTheme() {
        mainTheme.stop();
        battleTheme.play();
    }

    public void playMainTheme() {
        battleTheme.stop();
        mainTheme.play();
    }
} 