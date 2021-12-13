package Model.Sounds;

public class HitSound extends Sound {

    public HitSound() {
        this.sound  = "src/Model/Resources/HitBrick.wav";
        setFile(sound);
        play();
    }
}

