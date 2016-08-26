package com.namazed.officecrime;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.view.View;

import java.io.IOException;

public class PictureUtils {
    public static Bitmap getScaledBitmap(String path, Activity activity) throws IOException {
//        Point size = new Point();
//        activity.getWindowManager().getDefaultDisplay().getSize(size);
//
//        return getScaledBitmap(path, size.x, size.y);
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        Bitmap scaledBitmap = getScaledBitmap(path, size.x, size.y);

        ExifInterface exifInterface = new ExifInterface(path);
        String orientationString = exifInterface.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientationTag = orientationString != null ? Integer.parseInt(orientationString) :
                ExifInterface.ORIENTATION_NORMAL;
        int rotationAngle = 0;


        switch (orientationTag) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                rotationAngle = 90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                rotationAngle = 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                rotationAngle = 270;
                break;
            default:
                rotationAngle = 0;

        }
        Matrix matrix = new Matrix();
        matrix.postRotate(rotationAngle);
        Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(),
                scaledBitmap.getHeight(), matrix, true);
        return rotatedBitmap;

    }

    public static Bitmap getScaledBitmap(String path, int destWidth, int destHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        int inSampleSize = 1;
        if (srcHeight > destHeight || srcWidth > destWidth) {
            if (srcHeight > srcWidth) {
                inSampleSize = Math.round(srcHeight / destHeight);
            } else {
                inSampleSize = Math.round(srcWidth / destWidth);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        return BitmapFactory.decodeFile(path, options);
    }

    public static Bitmap getScaledBitmap(String path, View container) {
        return getScaledBitmap(path, container.getWidth(), container.getHeight());
    }
}
