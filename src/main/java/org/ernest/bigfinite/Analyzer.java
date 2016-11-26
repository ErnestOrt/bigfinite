package org.ernest.bigfinite;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.ernest.bigfinite.entities.RecordedValue;
import org.ernest.bigfinite.entities.RepresentativeValuesEnum;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyzer {

    public static final String REGULAR_EXP_VALUE_JSON_RECORD = ",([^<]*)}";

    public static JSONObject getRepresentativeInfoUsingAggregation(JSONArray values) {
        DescriptiveStatistics statistics = new DescriptiveStatistics();

        values.forEach(v-> statistics.addValue( ((RecordedValue)v).getValue()));

        return buildRepresentativeInfo(statistics);
    }

    public static JSONObject getRepresentativeInfoUsingAggregation(File file) throws Exception {
        DescriptiveStatistics statistics = new DescriptiveStatistics();

        LineIterator it = FileUtils.lineIterator(file, "UTF-8");
        try {
            while (it.hasNext()) {
                Matcher matcher = Pattern.compile(REGULAR_EXP_VALUE_JSON_RECORD).matcher(it.nextLine());
                matcher.find();
                statistics.addValue(Double.parseDouble(matcher.group(1)));
            }
        } finally {
            LineIterator.closeQuietly(it);
        }

        return buildRepresentativeInfo(statistics);
    }

    private static JSONObject buildRepresentativeInfo(DescriptiveStatistics statistics) {
        JSONObject representativeInfo = new JSONObject();

        representativeInfo.put(RepresentativeValuesEnum.MEAN.getCode(), statistics.getMean());
        representativeInfo.put(RepresentativeValuesEnum.MAX.getCode(), statistics.getMean());
        representativeInfo.put(RepresentativeValuesEnum.MIN.getCode(), statistics.getMean());
        representativeInfo.put(RepresentativeValuesEnum.STANDARD_DEVIATION.getCode(), statistics.getStandardDeviation());

        return representativeInfo;
    }
}
