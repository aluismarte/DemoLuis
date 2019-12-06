package edu.alsjava.courses.demoluis.tasks;

import android.os.AsyncTask;

import com.bumptech.glide.Glide;
import com.linecorp.apng.ApngDrawable;

import java.lang.ref.WeakReference;

import edu.alsjava.courses.demoluis.R;
import edu.alsjava.courses.demoluis.ui.AppActivity;

/**
 * Created by aluis on 12/5/19.
 */
public class LoadAPNG2 extends AsyncTask<Void, Void, ApngDrawable> {

    private WeakReference<AppActivity> appActivityWeakReference;

    public LoadAPNG2(AppActivity appActivity) {
        appActivityWeakReference = new WeakReference<>(appActivity);

    }

    @Override
    protected void onPostExecute(ApngDrawable apngDrawable) {
        AppActivity appActivity = appActivityWeakReference.get();
        if (appActivity != null && !appActivity.isFinishing()) {
            if (apngDrawable != null) {
                Glide.with(appActivity).load(apngDrawable).into(appActivity.getIvLoading());
            }
        }
    }

    @Override
    protected ApngDrawable doInBackground(Void... voids) {
        try {
            AppActivity appActivity = appActivityWeakReference.get();
            if (appActivity != null && !appActivity.isFinishing()) {
                return ApngDrawable.Companion.decode(appActivity.getResources(), R.raw.cat, null, null);
            }
        } catch (Exception ignored) {
        }
        return null;
    }
}
