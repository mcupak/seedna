package com.mirocupak.seedna.application;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.mirocupak.seedna.service.util.InputFormat;
import com.mirocupak.seedna.service.util.OutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SeeDnaApplication implements CommandLineRunner {

    public static final String APPLICATION_STARTED = "Application started with arguments: ";
    private Logger logger = LoggerFactory.getLogger(SeeDnaApplication.class);

    private class Arguments {

        @Parameter(names = {"--inputPath", "-i"}, description = "Input path")
        private String inputPath = "input.txt";

        @Parameter(names = {"--outputPath", "-o"}, description = "Output path")
        private String outputPath = "output.png";

        @Parameter(names = {"--inputFormat", "-if"}, description = "Input format")
        private InputFormat inputFormat = InputFormat.SNP;

        @Parameter(names = {"--outputFormat", "-of"}, description = "Output format")
        private OutputFormat outputFormat = OutputFormat.PNG;

    }

    public static void main(String[] args) {
        SpringApplication.run(SeeDnaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Arguments arguments = new Arguments();
        JCommander.newBuilder().addObject(arguments).build().parse(args);
        logger.debug(APPLICATION_STARTED + Arrays.toString(args));
    }
}
