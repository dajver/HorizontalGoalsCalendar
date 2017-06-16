package com.project.piechartcallendarexample.etc;

import java.util.Random;

/**
 * Created by gleb on 6/16/17.
 */

public class RandomUtils {
    public static int getRandomValue() {
        Random rand = new Random();
        return Math.abs(rand.nextInt());
    }
}
