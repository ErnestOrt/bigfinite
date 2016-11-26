package org.ernest.bigfinite;

import org.ernest.bigfinite.entities.RepresentativeValuesEnum;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class AnalyzeFileTest {

    @Test
    public void givenListOfSamplesWhenAnalyzingThenMostRepresentativeInfoReturned() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("samplesFileSmall.txt").getFile());
        JSONObject representativeInfo = Analyzer.getRepresentativeInfoUsingAggregation(file);

        Assert.assertTrue((double)representativeInfo.get(RepresentativeValuesEnum.MEAN.getCode())!=0.0);
        Assert.assertTrue((double)representativeInfo.get(RepresentativeValuesEnum.STANDARD_DEVIATION.getCode())!=0.0);

        System.out.println(representativeInfo);
    }
}