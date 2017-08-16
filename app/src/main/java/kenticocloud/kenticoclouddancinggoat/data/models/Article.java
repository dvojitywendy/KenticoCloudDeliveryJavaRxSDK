package kenticocloud.kenticoclouddancinggoat.data.models;

import android.support.annotation.Nullable;

/**
 * Created by RichardS on 15. 8. 2017.
 */

public final class Article {
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Article(@Nullable String title){
        this.title = title;
    }
}
