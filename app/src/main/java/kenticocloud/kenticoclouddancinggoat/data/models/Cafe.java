package kenticocloud.kenticoclouddancinggoat.data.models;

import android.support.annotation.Nullable;

import kenticocloud.kenticoclouddancinggoat.kentico_cloud.models.ContentItem;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public final class Cafe extends ContentItem{
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
