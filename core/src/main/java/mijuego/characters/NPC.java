package mijuego.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import mijuego.R;

public abstract class NPC {
    protected Vector2 position;
    protected Vector2 velocity;
    protected float width;
    protected float height;
    protected Rectangle bounds;
    protected float health;
    protected float maxHealth;
    protected boolean isAlive;
    protected Animation<TextureRegion> idleAnimation;
    protected float stateTime;
    protected String name;
    protected String dialogue;

    public NPC(float x, float y, float width, float height, float health, String name, String dialogue) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2();
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(x, y, width, height);
        this.health = health;
        this.maxHealth = health;
        this.isAlive = true;
        this.stateTime = 0;
        this.name = name;
        this.dialogue = dialogue;
    }

    public void update(float delta) {
        stateTime += delta;
        position.add(velocity.x * delta, velocity.y * delta);
        bounds.setPosition(position.x, position.y);
    }

    public void render(SpriteBatch batch) {
        if (idleAnimation != null) {
            TextureRegion currentFrame = idleAnimation.getKeyFrame(stateTime, true);
            batch.draw(currentFrame, position.x, position.y, width, height);
        }
    }

    public abstract void interact(Player player);

    public void takeDamage(float damage) {
        health -= damage;
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

    public String getName() {
        return name;
    }

    public String getDialogue() {
        return dialogue;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }
} 