package Model.Sounds;

public class HitSound extends Sound {

    public HitSound() {
        this.sound  = "src/Model/Sounds/HitBrick.wav";
        setFile(sound);
        play();
    }
}

