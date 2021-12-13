package Model.Sounds;

public class CrackSound extends Sound {

    public CrackSound() {
        this.sound = "src/Model/Resources/CrackBrick.wav";
        setFile(sound);
        play();
    }
}
