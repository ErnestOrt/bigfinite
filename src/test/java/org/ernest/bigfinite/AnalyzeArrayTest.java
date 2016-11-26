package org.ernest.bigfinite;

import org.ernest.bigfinite.entities.RecordedValue;
import org.ernest.bigfinite.entities.RepresentativeValuesEnum;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.*;

import java.util.stream.IntStream;

public class AnalyzeArrayTest {

    private JSONArray jsonArray;

    @Before
    public void setUp(){
        jsonArray = new JSONArray();
        IntStream.rangeClosed(1,100).forEach(index-> jsonArray.put(new RecordedValue(System.currentTimeMillis(), Math.random())));
    }

    @After
    public void destroy(){
        jsonArray = null;
    }

    @Test
    public void givenListOfSamplesWhenAnalyzingThenMostRepresentativeInfoReturned(){
        JSONObject representativeInfo = Analyzer.getRepresentativeInfoUsingAggregation(jsonArray);

        Assert.assertTrue((double)representativeInfo.get(RepresentativeValuesEnum.MEAN.getCode())!=0.0);
        Assert.assertTrue((double)representativeInfo.get(RepresentativeValuesEnum.STANDARD_DEVIATION.getCode())!=0.0);

        System.out.println(representativeInfo);
    }
}