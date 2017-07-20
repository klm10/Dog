package ru.bk.klim9.dog.content;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * @author Ivan
 */
public class Author extends RealmObject {

    @SerializedName("name")
    private String mAuthorName;

    @NonNull
    public String getAuthorName() {
        return mAuthorName;
    }

    public void setAuthorName(@NonNull String authorName) {
        mAuthorName = authorName;
    }
}
