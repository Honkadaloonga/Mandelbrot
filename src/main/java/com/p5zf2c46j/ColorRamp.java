package com.p5zf2c46j;

import java.awt.*;
import java.util.ArrayList;

import static com.p5zf2c46j.P3Utils.*;

public class ColorRamp {
    private final ArrayList<Color> colors;
    private final ArrayList<Double> positions;

    public ColorRamp() {
        colors = new ArrayList<>();
        positions = new ArrayList<>();
    }

    public Color get(double fac) {
        if (colors.size() == 0) {
            return null;
        }

        if (fac < 0 || fac >= 1) {
            throw new IllegalArgumentException("Position out of bounds (" + fac + ")");
        }

        for (int i = 0; i < positions.size(); i++) {
            if (fac == positions.get(i)) {
                return colors.get(i);
            }
        }

        int size = colors.size();
        int index = getIndex(fac);

        int indexA = (index+size-1)%size;
        int indexB = (index+size)%size;

        double posA = positions.get(indexA);
        double posB = positions.get(indexB);

        if (indexB < indexA) {
            posB += 1;
            if (fac < posA) {
                fac += 1;
            }
        }

        double facMapped = ((fac - posA) / (posB - posA));

        Color colorA = colors.get(indexA);
        Color colorB = colors.get(indexB);

        return lerp(colorA, colorB, facMapped);
    }

    private static Color lerp(Color a, Color b, double fac) {
        int newR = (int) P3Utils.lerp(a.getRed(), b.getRed(), fac);
        int newG = (int) P3Utils.lerp(a.getGreen(), b.getGreen(), fac);
        int newB = (int) P3Utils.lerp(a.getBlue(), b.getBlue(), fac);
        newR = constrain(newR, 0, 255);
        newG = constrain(newG, 0, 255);
        newB = constrain(newB, 0, 255);
        return new Color(newR, newG, newB);
    }

    public void add(Color color, double position) throws IllegalArgumentException {
        if (position < 0 || position >= 1) {
            throw new IllegalArgumentException("Position out of bounds (" + position + ")");
        }

        for (double d : positions) {
            if (d == position) {
                throw new IllegalArgumentException("Duplicate color at " + d);
            }
        }

        int index = getIndex(position);
        positions.add(index, position);
        colors.add(index, color);
    }

    public void remove(int index) {
        colors.remove(index);
        positions.remove(index);
    }

    public String getColor(int index) {
        return "Color: #" + Integer.toHexString(colors.get(index).getRGB()) + " at position " + positions.get(index);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < colors.size(); i++) {
            out.append(getColor(i)).append("\n");
        }
        return out.deleteCharAt(out.length()-1).toString();
    }

    private int getIndex(double fac) {
        int i = 0;
        if (!positions.isEmpty()) {
            try {
                while (positions.get(i) < fac) {
                    i++;
                }
            } catch (Exception ignored) {}
        }
        return i;
    }
}
