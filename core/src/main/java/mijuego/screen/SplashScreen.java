package mijuego.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import mijuego.MiJuego;

public class SplashScreen implements Screen {
    private final MiJuego game;
    private Stage stage;
    private Viewport viewport;
    private float timeElapsed;
    private static final float SPLASH_DURATION = 2.0f;

    public SplashScreen(MiJuego game) {
        this.game = game;
        this.viewport = new FitViewport(800, 480, new OrthographicCamera());
        this.stage = new Stage(viewport);
        this.timeElapsed = 0;
        createUI();
    }

    private void createUI() {
        Table table = new Table();
        table.setFillParent(true);

        Image splashImage = new Image(new Texture(Gdx.files.internal("textures/splash.png")));
        table.add(splashImage).size(400, 300);

        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        timeElapsed += delta;
        if (timeElapsed >= SPLASH_DURATION) {
            game.showMainMenu();
        }

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
} 