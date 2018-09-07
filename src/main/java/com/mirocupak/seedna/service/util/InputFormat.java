package com.mirocupak.seedna.service.util;

public enum InputFormat {

    SNP("SNP");

    private final String format;

    public static InputFormat fromString(String format) {
        if (format != null) {
            for (InputFormat b : InputFormat.values()) {
                if (format.equalsIgnoreCase(b.toString())) {
                    return b;
                }
            }
        }
        return null;
    }

    InputFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return format;
    }

}
