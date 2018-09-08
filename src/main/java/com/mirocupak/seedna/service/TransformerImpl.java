package com.mirocupak.seedna.service;

import com.mirocupak.seedna.service.util.InputFormat;
import com.mirocupak.seedna.service.util.OutputFormat;
import com.mirocupak.seedna.service.util.OutputMetadata;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@Service
@NoArgsConstructor
public class TransformerImpl implements Transformer {

    public static final int MAX_OUTPUT_SIZE = 2000;
    private static final int COLUMN_INDEX = 3;
    private Logger logger = LoggerFactory.getLogger(TransformerImpl.class);
    private Random rand = new Random();

    @Autowired
    private OutputMetadata imageMetadata;

    private int computeImageSize(int numberOfInputLines) {
        // compute square root and round down to int
        int result = (int) Math.sqrt((double) numberOfInputLines);

        if (result < 0) {
            throw new IllegalStateException("Could not compute output image dimensions");
        } else if (result > MAX_OUTPUT_SIZE) {
            throw new IllegalStateException("Max output image size exceeded");
        }

        logger.debug("Image size: " + result);
        return result;
    }

    private Color computeRandomColor() {
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        Color color = new Color(r, g, b);

        return color;
    }

    private void computeOutputProperties(File input) {
        TsvParser parser = getParser();
        // call beginParsing to read records one by one, iterator-style.
        parser.beginParsing(input);

        int numberOfRows = 0;
        String[] symbol;
        while ((symbol = parser.parseNext()) != null) {
            numberOfRows++;

            // generate pixel
            imageMetadata.getPalette().putIfAbsent(symbol[0], computeRandomColor());
        }

        // The resources are closed automatically when the end of the input is reached,
        // or when an error happens, but you can call stopParsing() at any time.

        // You only need to use this if you are not parsing the entire content.
        // But it doesn't hurt if you call it anyway.
        parser.stopParsing();
        logger.debug("Image metadata: " + imageMetadata);

        imageMetadata.setOutputDimension(computeImageSize(numberOfRows));
    }

    private TsvParser getParser() {
        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("\n");
        settings.selectIndexes(COLUMN_INDEX);

        // creates a TSV parser
        return new TsvParser(settings);
    }

    @Override
    public void transform(InputFormat inputFormat, OutputFormat outputFormat, String inputPath, String outputPath) throws IOException {
        // compute image dimensions and color palette
        File input = new File(inputPath);
        File output = new File(outputPath);
        computeOutputProperties(input);

        // initialize parser and writer
        TsvParser parser = getParser();
        BufferedImage image = new BufferedImage(imageMetadata.getOutputDimension(),
                                                imageMetadata.getOutputDimension(),
                                                BufferedImage.TYPE_INT_ARGB);

        // compute image
        parser.beginParsing(input);
        for (int y = 0; y < imageMetadata.getOutputDimension(); y++) {
            for (int x = 0; x < imageMetadata.getOutputDimension(); x++) {
                String symbol = parser.parseNextRecord().getString(0);
                // obtain pixel for a given symbol
                Color color = imageMetadata.getPalette().get(symbol);

                // set pixel in the image
                image.setRGB(x, y, color.getRGB());
            }
        }
        parser.stopParsing();

        // write image to a file
        ImageIO.write(image, outputFormat.toString(), output);
    }
}
