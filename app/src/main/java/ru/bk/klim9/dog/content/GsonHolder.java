package ru.bk.klim9.dog.content;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

/**
 * @author Ivan
 */
public final class GsonHolder {

    private GsonHolder() {
    }

    @NonNull
    public static Gson getGson() {
        return Holder.GSON;
    }

    public static final class Holder {
        private static final Gson GSON = new Gson();
    }

}
