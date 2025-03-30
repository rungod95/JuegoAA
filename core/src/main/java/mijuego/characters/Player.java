package mijuego.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import mijuego.R;

public class Player {
    private Vector2 position;
    private Vector2 velocity;
    private float width;
    private float height;
    private Rectangle bounds;
    private float health;
    private float maxHealth;
    private boolean isAlive;
    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> jumpAnimation;
    private Animation<TextureRegion> lashAnimation;
    private float stateTime;
    private boolean isJumping;
    private boolean isLashing;
    private float lashTime;
    private float stormlight;
    private float maxStormlight;
    private float stormlightRegenRate;
    private Sound jumpSound;
    private Sound lashSound;
    private Sound hurtSound;

    public Player(float x, float y) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2();
        this.width = 32;
        this.height = 64;
        this.bounds = new Rectangle(x, y, width, height);
        this.health = 100;
        this.maxHealth = 100;
        this.isAlive = true;
        this.stateTime = 0;
        this.isJumping = false;
        this.isLashing = false;
        this.lashTime = 0;
        this.stormlight = 100;
        this.maxStormlight = 100;
        this.stormlightRegenRate = 5;
        this.jumpSound = Gdx.audio.newSound(Gdx.files.internal(R.sounds.kaladin_jump));
        this.lashSound = Gdx.audio.newSound(Gdx.files.internal(R.sounds.kaladin_lash));
        this.hurtSound = Gdx.audio.newSound(Gdx.files.internal(R.sounds.kaladin_hurt));
    }

    public void update(float delta) {
        // Actualizar posición
        position.add(velocity.x * delta, velocity.y * delta);
        bounds.setPosition(position.x, position.y);

        // Gravedad
        if (!isLashing) {
            velocity.y -= 500 * delta;
        }

        // Actualizar tiempo de estado para animaciones
        stateTime += delta;

        // Actualizar tiempo de Lash
        if (isLashing) {
            lashTime += delta;
            if (lashTime >= 5.0f) {
                isLashing = false;
                lashTime = 0;
            }
        }

        // Regenerar Stormlight
        if (stormlight < maxStormlight) {
            stormlight = Math.min(stormlight + stormlightRegenRate * delta, maxStormlight);
        }

        // Input
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velocity.x = -200;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velocity.x = 200;
        } else {
            velocity.x = 0;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !isJumping && !isLashing) {
            velocity.y = 400;
            isJumping = true;
            jumpSound.play();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.L) && stormlight >= 20 && !isLashing) {
            isLashing = true;
            lashTime = 0;
            stormlight -= 20;
            lashSound.play();
        }

        // Detectar aterrizaje
        if (velocity.y < 0 && position.y <= 0) {
            position.y = 0;
            velocity.y = 0;
            isJumping = false;
        }
    }

    public void render(SpriteBatch batch) {
        TextureRegion currentFrame;
        if (isLashing) {
            currentFrame = lashAnimation.getKeyFrame(stateTime, true);
        } else if (isJumping) {
            currentFrame = jumpAnimation.getKeyFrame(stateTime, true);
        } else if (Math.abs(velocity.x) > 0) {
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        } else {
            currentFrame = idleAnimation.getKeyFrame(stateTime, true);
        }
        batch.draw(currentFrame, position.x, position.y, width, height);
    }

    public void takeDamage(float damage) {
        health -= damage;
        hurtSound.play();
        if (health <= 0) {
            health = 0;
            isAlive = false;
        }
    }

    public void heal(float amount) {
        health = Math.min(health + amount, maxHealth);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public float getHealth() {
        return health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public float getStormlight() {
        return stormlight;
    }

    public float getMaxStormlight() {
        return maxStormlight;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean isLashing() {
        return isLashing;
    }
} 