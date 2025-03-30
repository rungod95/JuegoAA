package mijuego.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import mijuego.R;

public class Parshendi extends NPC {
    private float attackCooldown;
    private float currentCooldown;
    private float damage;
    private boolean isInWarform;
    private Sound attackSound;
    private Sound transformSound;

    public Parshendi(float x, float y) {
        super(x, y, 32, 48, 100, "Parshendi", "¡Por las tormentas!");
        this.attackCooldown = 2.0f;
        this.currentCooldown = 0;
        this.damage = 20;
        this.isInWarform = false;
        this.attackSound = Gdx.audio.newSound(Gdx.files.internal(R.sounds.parshendi_attack));
        this.transformSound = Gdx.audio.newSound(Gdx.files.internal(R.sounds.parshendi_transform));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (currentCooldown > 0) {
            currentCooldown -= delta;
        }
    }

    @Override
    public void interact(Player player) {
        if (currentCooldown <= 0) {
            player.takeDamage(damage);
            currentCooldown = attackCooldown;
            attackSound.play();
        }
    }

    public void transformToWarform() {
        if (!isInWarform) {
            damage *= 2;
            maxHealth *= 2;
            health = maxHealth;
            isInWarform = true;
            transformSound.play();
        }
    }

    public boolean isInWarform() {
        return isInWarform;
    }
} 