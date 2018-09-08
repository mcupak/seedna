package com.mirocupak.seedna.service.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor
@Data
public class OutputMetadata {

    private int outputDimension = 0;
    private Map<String, Color> palette = new HashMap<>();

}
