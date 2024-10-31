package ir.moke.sdk.other;

import ir.moke.kafir.utils.JsonUtils;
import ir.moke.coinex.model.response.MarketResponse;
import ir.moke.coinex.model.Response;

import java.util.List;

public class JsonTest {
    public static void main(String[] args) {
        String json = """
                {
                  "code": 0,
                  "data": [
                    {
                      "base_ccy": "BTC",
                      "base_ccy_precision": 4,
                      "contract_type": "linear",
                      "is_copy_trading_available": true,
                      "is_market_available": true,
                      "leverage": [
                        "1",
                        "2",
                        "3",
                        "5",
                        "8",
                        "10",
                        "15",
                        "20",
                        "30",
                        "50",
                        "100"
                      ],
                      "maker_fee_rate": "0.0003",
                      "market": "BTCUSDT",
                      "min_amount": "0.0001",
                      "open_interest_volume": "1820.709",
                      "quote_ccy": "USDT",
                      "quote_ccy_precision": 2,
                      "taker_fee_rate": "0.0005",
                      "tick_size": "0.01"
                    }
                  ]
                }
                """;


        Response<List<MarketResponse>> response = JsonUtils.toObject(json, Response.class);
        System.out.println(response);
    }
}
