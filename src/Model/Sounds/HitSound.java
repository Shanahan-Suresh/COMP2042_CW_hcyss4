package Model.Sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class HitSound {

    String hitSound;
    SoundEffect soundEffect = new SoundEffect();

    public HitSound(){
        hitSound = "src/Model/Sounds/HitBrick.wav";
        System.out.println(hitSound);
        soundEffect.setFile(hitSound);
        soundEffect.play();
    }

    public class SoundEffect {

        Clip audioclip;

        public void setFile(String soundFileName){

            try{
                File file = new File(soundFileName);
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);

                audioclip = AudioSystem.getClip();
                audioclip.open(sound);
            }

            catch(Exception e){
                e.printStackTrace();
            }
        }

        public void play(){
            audioclip.setFramePosition(0);
            audioclip.start();
        }
    }
}

