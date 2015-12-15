package com.morristaedt.mirror.aspect;

import android.util.Log;

import com.morristaedt.mirror.requests.YahooStockResponse;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * Property 6
 * If there is a stock symbol set in ConfigurationSettings, then the stock ticker view is shown.
 *      (a) If (Pre): ConfigurationSettings.showStock() == true
 *      (b) Then (Post): YahooFinanceModule.onNewStockPrice(YahooQuoteResponse r) is called and r != null.
 *
 */
@Aspect
public class StockPriceListenerAspect {
    public static final String TAG = StockPriceListenerAspect.class.toString();

    @After("execution(* com.morristaedt.mirror.modules.YahooFinanceModule.StockListener+." +
            "onNewStockPrice(com.morristaedt.mirror.requests.YahooStockResponse.YahooQuoteResponse))" +
            "&& args(r)")
    public void afterOnNewStockPrice(YahooStockResponse.YahooQuoteResponse r) {
        if (r == null) {
            Log.e(TAG, "The YahooQuoteResponse was null.");
        }
        Log.d(TAG, "onNewStockPrice() got called.");
    }
}
