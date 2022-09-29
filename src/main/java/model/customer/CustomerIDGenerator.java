package model.customer;

import java.util.Random;

// Temporary ID generator just to get things going

public class CustomerIDGenerator implements IDGenerator<Integer>{

    private Random r = new Random();
    private final Integer MAX = (int) Math.pow(2, 32) - 1;

    @Override
    public Integer generateID() {
        return r.nextInt(MAX);
    }
}
