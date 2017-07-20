package ru.bk.klim9.dog.content;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * @author Ivan
 */
public class CommitResponse extends RealmObject{

    @SerializedName("commit")
    private Commit mCommit;

    @NonNull
    public Commit getCommit() {
        return mCommit;
    }
}