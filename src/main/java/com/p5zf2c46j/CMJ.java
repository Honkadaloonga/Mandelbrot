package com.p5zf2c46j;

/**
 * An implementation of Pixar's Correlated Multi-Jittered Sampling
 */
public class CMJ {
    /*
      Most of the below code was copied from this paper:
      https://graphics.pixar.com/library/MultiJitteredSampling/paper.pdf
      It's definitely worth reading
     */

    /**
     * @param s Sample index
     * @param t Total sample count
     * @param r Random seed
     * @param a Aspect ratio
     */
    public static float[] get(int s, int t, int r, float a) {
        int m = (int) Math.sqrt(t * a);
        int n = (t + m - 1) / m;
        s = permute(s, t, r * 0x51633e2d);
        int sx = permute(uMod(s, m), m, r * 0x68bc21eb);
        int sy = permute(s / m, n, r * 0x02e5be93);
        float jx = randFloat(s, r * 0x967a889b);
        float jy = randFloat(s, r * 0x368cc8b7);
        return new float[]{ (sx + (sy + jx) / n) / m, (s + jy) / t };
    }

    private static int permute(int i, int l, int p) {
        int w = l - 1;
        w |= w >>> 1;
        w |= w >>> 2;
        w |= w >>> 4;
        w |= w >>> 8;
        w |= w >>> 16;
        do {
            i ^= p;
            i *= 0xe170893d;
            i ^= p >> 16;
            i ^= (i & w) >> 4;
            i ^= p >> 8;
            i *= 0x0929eb3f;
            i ^= p >> 23;
            i ^= (i & w) >> 1;
            i *= 1 | p >> 27;
            i *= 0x6935fa69;
            i ^= (i & w) >> 11;
            i *= 0x74dcb303;
            i ^= (i & w) >> 2;
            i *= 0x9e501cc3;
            i ^= (i & w) >> 2;
            i *= 0xc860a3df;
            i &= w;
            i ^= i >> 5;
        } while (Integer.compareUnsigned(i, l) >= 0);
        return uMod(i + p, l);
    }

    private static float randFloat(int i, int p) {
        i ^= p;
        i ^= i >>> 17;
        i ^= i >>> 10;
        i *= 0xb36534e5;
        i ^= i >>> 12;
        i ^= i >>> 21;
        i *= 0x93fc4795;
        i ^= 0xdf6e307f;
        i ^= i >>> 17;
        i *= 1 | p >>> 18;
        return (i & 0xffffffffL) * (1.0f / 4294967808.0f);
    }

    // this is here because the paper required the modulo operation on unsigned ints
    private static int uMod(int n, int m) {
        return n < 0 ? (n + ((Integer.MIN_VALUE - n) / m - 1) * m) % m : n % m;
    }
}
