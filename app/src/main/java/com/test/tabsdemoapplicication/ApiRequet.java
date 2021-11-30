package com.test.tabsdemoapplicication;

import java.util.List;

public interface ApiRequet {
    public void onSuccess(List<String> items);
    public void onFail();
}
