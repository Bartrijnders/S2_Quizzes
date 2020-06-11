package sevices;

import java.util.Random;

public class RandomNumberService {

    private final Random random;

    public RandomNumberService() {
        this.random = new Random();
    }

    public int getRandomInt(int max) {
        if (max > 1)
            return random.nextInt(max);
        else
            return 0;
    }
}
