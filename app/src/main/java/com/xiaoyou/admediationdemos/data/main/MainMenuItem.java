package com.xiaoyou.admediationdemos.data.main;

import android.content.Intent;

public class MainMenuItem
        implements ListItem {
    private final String title;
    private final Intent intent;
    private final Runnable runnable;

    public MainMenuItem(final String title, final Intent intent) {
        this.title = title;
        this.intent = intent;
        this.runnable = null;
    }

    public MainMenuItem(final String title, final Runnable runnable) {
        this.title = title;
        this.intent = null;
        this.runnable = runnable;
    }

    public String getTitle() {
        return title;
    }

    public Intent getIntent() {
        return intent;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    @Override
    public int getType() {
        return TYPE_AD_ITEM;
    }
}
