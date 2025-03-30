package mijuego.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import mijuego.R;

public class Spren extends NPC {
    private float healAmount;
    private float healCooldown;
    private float currentCooldown;
    private Sound healSound;

    public Spren(float x, float y) {
        super(x, y, 16, 16, 50, "Spren", "¡Kaladin, recuerda las palabras!");
        this.healAmount = 10;
        this.healCooldown = 5.0f;
        this.currentCooldown = 0;
        this.healSound = Gdx.audio.newSound(Gdx.files.internal(R.sounds.spren_heal));
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
            player.heal(healAmount);
            currentCooldown = healCooldown;
            healSound.play();
        }
    }
} 