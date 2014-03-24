    package com.example.placarpb.download;

    import android.content.Context;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.os.AsyncTask;
    import android.os.Environment;
    import android.util.Log;
    import android.widget.*;

    import java.io.*;
    import java.net.HttpURLConnection;
    import java.net.URL;

    public class Downloader extends AsyncTask<String, Integer, Bitmap> {

        private final static int FI_URL_SIZE = 32;
        private final static String FOLDER = "PlacarPB/images";

        private final ProgressBar mProgressBar;
        private final Context mContext;
        private final ImageView mImageView;
        private final TextView mPercent;
        private String mUrls;

        public Downloader(ProgressBar progressBar, ImageView imageView, TextView percent,
                          Context context) {
            this.mProgressBar = progressBar;
            this.mContext = context;
            this.mImageView = imageView;
            this.mPercent = percent;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            int count = urls.length;
            Bitmap bmp = null;
            for (int i = 0; i < count; i++) {
                mUrls = urls[i];
                bmp = getBitmapFromURL(urls[i]);
                publishProgress((int) ((i / (float) count) * 100));
                if (isCancelled()) break;
            }
            return bmp;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            if (null != mProgressBar) {
                mProgressBar.setProgress(progress[0]);
            }
            if (null != mPercent) {
                mPercent.setText(progress[0] + "%");
            }

            super.onProgressUpdate(progress);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            String[] splitUrl = mUrls.split("/");
            String filename = splitUrl[splitUrl.length - 1];
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory + "/" + FOLDER);
            if (createFolder(extStorageDirectory, FOLDER)) {
                Log.e("OK", "!");
            }
            File file = new File(folder, filename);

            if (!file.exists()) {
                try {
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("HA", "HA");
            }

            if (null != mImageView) {
                mImageView.setImageBitmap(bitmap);
            }

            Toast.makeText(mContext, "download complete", Toast.LENGTH_SHORT).show();

            super.onPostExecute(bitmap);
        }

        public static Bitmap getBitmapFromURL(String link) {
            try {
                URL url = new URL(link);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(input);

                return bitmap;

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("getBmpFromUrl error: ", e.getMessage().toString());
                return null;
            }
        }

        private boolean createFolder(String path, String folderName) {
            String[] foldersName = folderName.split("/");
            File folder = new File(path + "/" + foldersName[0]);
            boolean created = false;
            if (!folder.exists()) {
                if (folder.mkdir()) {
                    created = true;
                }
            }
            if (foldersName.length > 1) {
                created = createFolder(folder.toString(), foldersName[1]);
            }
            return created;
        }
    }
