package org.orecruncher.dsurround.lib.random;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

/**
 * Implementation of IRandomizer that wraps a Java RandomGenerator
 */
@SuppressWarnings("unused")
final class JavaRandomizer implements IRandomizer {

    // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/random/package-summary.html
    public static final String XOROSHIRO_128_PLUS_PLUS = "Xoroshiro128PlusPlus";

    private final RandomGenerator generator;

    public JavaRandomizer(String algorithm) {
        this.generator = RandomGeneratorFactory.of(algorithm).create();
    }

    public JavaRandomizer(String algorithm, final long seed) {
        this.generator = RandomGeneratorFactory.of(algorithm).create(seed);
    }

    @Override
    public int nextInt() {
        return this.generator.nextInt();
    }

    @Override
    public int nextInt(int bound) {
        return this.generator.nextInt(bound);
    }

    @Override
    public boolean nextBoolean() {
        return this.generator.nextBoolean();
    }

    @Override
    public double nextDouble() {
        return this.generator.nextDouble();
    }

    @Override
    public float nextFloat() {
        return this.generator.nextFloat();
    }

    @Override
    public double nextGaussian() {
        return this.generator.nextGaussian();
    }

    @Override
    public long nextLong() {
        return this.generator.nextLong();
    }
}
