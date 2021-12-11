package Model.Sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    String sound;
    Clip audioclip;

    protected void setFile(String soundFileName){

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

    protected void play(){
        audioclip.setFramePosition(0);
        audioclip.start();
    }
}