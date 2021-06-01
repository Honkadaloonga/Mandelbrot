package com.p5zf2c46j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.nio.file.Paths;

import static com.p5zf2c46j.P3Utils.*;
import static java.lang.Math.*;

public class App {
    static int width = 1920;
    static int height = 1080;
    static int maxIter = 128;
    static int sampleCount = 1;
    static double[] center = {-3, 0};
    static double magnitude = 0.4;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        ColorRamp ramp = new ColorRamp();
        ramp.add(new Color(0, 94, 156), 17/400.0);
        ramp.add(new Color(255, 255, 255), 148/400.0);
        ramp.add(new Color(106, 0, 66), 318/400.0);

        BufferedImage cvs = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int[] pixels = ((DataBufferInt)cvs.getRaster().getDataBuffer()).getData();

        double reachX = ((double)width)/height * 2/magnitude;
        double reachY = 2/magnitude;

        double power = 2;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int index = x + width * y;
                double[] colVals = new double[3];

                for (int i = 0; i < sampleCount; i++) {
                    float[] cmj = CMJ.get(i, sampleCount, index+2137, ((float)width/height));

                    double ka = center[0] + map(x + cmj[0], 0, width, -reachX, reachX);
                    double kb = center[1] + map(y + cmj[1], 0, height, reachY, -reachY);

                    Complex z = new Complex(ka, kb);
                    Complex c = z.copy();

                    for (int j = 0; j < maxIter; j++) {
                        z.set(z.copy().pow(1).div(2).add(z.copy().pow(-power/2).div(2)).pow(2).add(c));
                        //z.set(z.copy().pow(power).add(c));

                        if (z.magSqr() > 1e64) {
                            double delta = log(log(z.magSqr()) * 0.5D) / log(power);
                            double br = j - delta;
                            br += 15;
                            br = sqrt(br);
                            br /= 2.0;
                            br %= 1;
                            Color tempColor = ramp.get(br);
                            colVals[0] += tempColor.getRed();
                            colVals[1] += tempColor.getGreen();
                            colVals[2] += tempColor.getBlue();
                            break;
                        }
                    }
                }
                for (int i = 0; i < 3; i++)
                    colVals[i] /= sampleCount;
                pixels[index] = new Color((int)colVals[0], (int)colVals[1], (int)colVals[2]).getRGB();
            }

            System.out.println((x+1) + "/" + width);
        }

        File outfile = new File(Paths.get("").toAbsolutePath() + "/out.png");
        System.out.println(outfile.getAbsolutePath());
        ImageIO.write(cvs, "png", outfile);
        System.out.println("Done");
    }
}
