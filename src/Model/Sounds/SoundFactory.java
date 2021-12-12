package Model.Sounds;

public class SoundFactory  {

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
