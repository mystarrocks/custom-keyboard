package com.example.customkeyboard;

import android.app.Application;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Utility to initialize and maintain global state.
 *
 * <p>
 * Developer note: Probably an old-fashioned way of doing things, but
 * still works.
 * </p>
 */
public class CustomKeyboardApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (isExternalStorageWritable()) {
            File appDirectory = new File(Environment.getExternalStorageDirectory() + "/CustomKeyboard");
            File logDirectory = new File(appDirectory + "/log");
            File logFile = new File(logDirectory, "logcat" + System.currentTimeMillis() + ".txt");

            // create app folder
            if (!appDirectory.exists()) {
                appDirectory.mkdir();
            }

            // create log folder
            if (!logDirectory.exists()) {
                logDirectory.mkdir();
            }

            // clear the previous logcat and then write the new one to the file
            try {
                Runtime.getRuntime().exec("logcat -c");
                Runtime.getRuntime().exec("logcat -f " + logFile + " MainActivity*:D *:S"); // limit logs to specified activities if needed
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (isExternalStorageReadable()) {
            // FIXME only readable; have a secondary location?
        } else {
            // FIXME location not accessible at all; require that it be so?
        }
    }

    /**
     * Checks if external storage is available for read and write.
     *
     * @return {@code true} if external storage is available for read and write;
     * {@code false} otherwise
     */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /**
     * Checks if external storage is available to at least read.
     *
     * @return {@code true} if external storage is available to at least read;
     * {@code false} otherwise
     */
    private boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }
}
