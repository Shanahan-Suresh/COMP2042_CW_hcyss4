package com.comp2042_cw_hcyss4.Model.Sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Sound Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class Sound {

    /**
     * Defines a placeholder variable to hold the sound file
     */
    public String sound;

    /**
     * Defines an object of type Clip to be used to open the sound file
     */
    public Clip audioclip;

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