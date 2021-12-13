package Model.Sounds;

/**
 * Crack Sound Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class CrackSound extends Sound {

    /**
     * Crack Sound constructor to be called when playing the cracked brick audio clip
     */
    public CrackSound() {
        this.sound = "src/Model/Resources/CrackBrick.wav";
        setFile(sound);
        play();
    }
}
