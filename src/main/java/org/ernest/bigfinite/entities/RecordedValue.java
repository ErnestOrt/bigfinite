package org.ernest.bigfinite.entities;

public class RecordedValue {

    private long timestamp;
    private double value;

    public RecordedValue() {}

    public RecordedValue(long timestamp, double value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
