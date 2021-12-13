package Model.Sounds;

/**
 * Hit Sound Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class HitSound extends Sound {

    /**
     * Hit Sound constructor to be called when playing the audio clip when brick is destroyed
     */
    public HitSound() {
        this.sound  = "src/Model/Resources/HitBrick.wav";
        setFile(sound);
        play();
    }
}

