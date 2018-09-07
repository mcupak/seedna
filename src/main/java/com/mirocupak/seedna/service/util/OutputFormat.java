package com.mirocupak.seedna.service.util;

public enum OutputFormat {

    PNG("PNG");

    private final String format;

    public static OutputFormat fromString(String format) {
        if (format != null) {
            for (OutputFormat b : OutputFormat.values()) {
                if (format.equalsIgnoreCase(b.toString())) {
                    return b;
                }
            }
        }
        return null;
    }

    OutputFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return format;
    }

}
