package itstep.learning.services.generators;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generators implements GeneratorService {
    @Override
    public String getNameFile() {
        String result = new String();
        for(int i = 0; i < ThreadLocalRandom.current().nextInt(2, 51); i++){
            char randChar = (char)ThreadLocalRandom.current().nextInt('a', 'z');
            result += randChar;
        }
        return result;
    }

    @Override
    public String getCryptoSalt() {
        String result = new String();
        for(int i = 0; i < ThreadLocalRandom.current().nextInt(20, 35); i++){
            char randChar = (char)ThreadLocalRandom.current().nextInt(0, 128);
            result += randChar;
        }
        return result;
    }

    @Override
    public String getOTP() {
        String result = new String();
        for(int i = 0; i < 6; i++){
            int randNumber = ThreadLocalRandom.current().nextInt(0,10 );
            result += randNumber;
        }
        return result;
    }

    @Override
    public String getPP() {
        String result = new String();
        for(int i = 0; i < ThreadLocalRandom.current().nextInt(20, 35); i++){
            char randChar = (char)ThreadLocalRandom.current().nextInt(33, 127);
            result += randChar;
        }
        return result;
    }
}
