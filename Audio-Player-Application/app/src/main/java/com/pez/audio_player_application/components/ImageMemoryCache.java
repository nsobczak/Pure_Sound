package com.pez.audio_player_application.components;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.LruCache;

public class ImageMemoryCache {

    // The cache object
    private LruCache<String, Bitmap> mMemoryCache;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    public ImageMemoryCache(int maxCacheSize){
        mMemoryCache = new LruCache<String, Bitmap>(maxCacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return (bitmap.getRowBytes() * bitmap.getHeight()) / 1024;
            }
        };
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

}