package org.ernest.bigfinite.entities;

public enum RepresentativeValuesEnum {
    MEAN("mean"),
    MAX("max"),
    MIN("min"),
    STANDARD_DEVIATION("standard_deviation");

    private String code;

    RepresentativeValuesEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
