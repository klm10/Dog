package ru.bk.klim9.dog.api;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import ru.bk.klim9.dog.utils.PreferenceUtils;

/**
 * @author Ivanv
 */
public final class ApiKeyInterceptor implements Interceptor {

    private final String mToken;

    private ApiKeyInterceptor() {
        mToken = PreferenceUtils.getToken();
    }

    @NonNull
    public static Interceptor create() {
        return new ApiKeyInterceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (TextUtils.isEmpty(mToken)) {
            return chain.proceed(chain.request());
        }
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", String.format("%s %s", "token", mToken))
                .build();
        return chain.proceed(request);
    }
}
