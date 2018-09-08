package com.mirocupak.seedna.service;

import com.mirocupak.seedna.service.util.InputFormat;
import com.mirocupak.seedna.service.util.OutputFormat;

import java.io.IOException;

public interface Transformer {

    void transform(InputFormat inputFormat, OutputFormat outputFormat, String inputPath, String outputPath) throws IOException;
}
