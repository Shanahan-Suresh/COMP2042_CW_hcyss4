package Model.Sounds;

/**
 * Sound Factory Class
 * @author Shanahan
 * @since 09/12/2021
 */
public class SoundFactory  {

    /**
     * A factory class to instantiate new Sound objects
     * @param soundType the name for the type of sound to be created
     * @return the object created by the constructor of the corresponding called Sound class object
     */
    public static Sound getSoundType(String soundType){

        if (soundType == null)
            return null;

        if(soundType.equalsIgnoreCase("BRICK"))
            return new HitSound();

        else if(soundType.equalsIgnoreCase("CRACK"))
            return new CrackSound();

        else
            return null;
    }
}
