package com.apen.simple.activity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.ImageView;

import com.apen.simple.R;
import com.apen.simple.base.BaseActivity;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2019-02-12.
 * GitHub：https://github.com/cxydxpx
 */

public class BitmapAcitivity extends BaseActivity {

    @Override
    protected int layoutResId() {
        return R.layout.activity_bitmap;
    }

    @BindView(R.id.imageview)
    ImageView mImageView;

    @Override
    protected void init() {
        super.init();

//        Bitmap bitmap = decodeSampleBitmapFromSource(getResources(), R.mipmap.dlrb, 140, 180);
//        mImageView.setImageBitmap(bitmap);

        initCache();

    }


    public Bitmap decodeSampleBitmapFromSource(Resources res, int resId, int reqWidth, int reqHeight) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);

    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        int width = options.outWidth;
        int height = options.outHeight;

        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            int halfHeight = height / 2;
            int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    private void initCache() {

//        LruCache<String, Bitmap> mLruCache = new LruCache<String, Bitmap>(20) {
//            @Override
//            protected int sizeOf(String key, Bitmap value) {
//                return value.getRowBytes() * value.getHeight() / 1024;
//            }
//        };
        DiskLruCache mDiskLruCache;
        try {
            File diskLruCache = getDiskCacheDir( "bitmap");
            if (!diskLruCache.exists()) {
                diskLruCache.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(diskLruCache, getAppVersion(this), 1, DISK_CACHE_SIZE);


            nextStep(mDiskLruCache);

            getCache(mDiskLruCache);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getCache(DiskLruCache mDiskLruCache) {
        try {
            String imageUrl = "https://img-my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
            String key = hashKeyForDisk(imageUrl);
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
            if (snapShot != null) {
                InputStream is = snapShot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                mImageView.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void nextStep(final DiskLruCache mDiskLruCache) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String imageUrl = "https://img-my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
                    String key = hashKeyForDisk(imageUrl);
                    DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                    if (editor != null) {
                        OutputStream outputStream = editor.newOutputStream(0);
                        if (downloadUrlToStream(imageUrl, outputStream)) {
                            editor.commit();
                        } else {
                            editor.abort();
                        }
                    }
                    mDiskLruCache.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private File getDiskCacheDir(String fileName) {

        String cachePath;

        // 当SD卡存在或者SD卡不可被移除
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
                cachePath = getExternalCacheDir().getPath();  // /sdcard/Android/data/<application package>/cache
        } else {
            cachePath = getCacheDir().getPath(); // /data/data/<application package>/cache
        }   

        return new File(cachePath + File.separator + fileName);
    }

    private static final long DISK_CACHE_SIZE = 50 * 1024 * 1024;


    /**
     * 每当版本号改变，缓存路径下存储的所有数据都会被清除掉
     *
     * @param context
     * @return
     */
    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {

        HttpURLConnection urlConnection = null;

        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();

            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);

            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            try {
                if (out != null) {
                    out.close();
                }

                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将url 转为 md5值
     *
     * @param key
     * @return
     */
    public String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

}
