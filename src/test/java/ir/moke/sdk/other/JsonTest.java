package ir.moke.sdk.other;

import ir.moke.kafir.utils.JsonUtils;

public class JsonTest {
    public static void main(String[] args) {
        String json = """
                {
                    "market": "CETUSDT",
                    "market_type": "FUTURES",
                    "stop_id": 13400
                }
                """;


        BaseClass.InnerClass innerClass = JsonUtils.toObject(json, BaseClass.InnerClass.class);
        System.out.println(innerClass);

        System.out.println(JsonUtils.toJson(innerClass));


        RecordType recordType = JsonUtils.toObject(json, RecordType.class);
        System.out.println(recordType);


    }
}
