package com.luxoft.CJP.April16.akrzos.bankapp.serialization;

import java.util.Map;

/**
 * Created by akrzos on 2016-04-27.
 */
public interface ParsingFeeds {
    void parseFeed(Map<String,String> feed);
}
