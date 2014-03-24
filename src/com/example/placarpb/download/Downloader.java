    package com.example.placarpb.download;

    import android.content.Context;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.os.AsyncTask;
    import android.util.Log;
    import android.widget.*;

    import java.io.IOException;
    import java.io.InputStream;
    import java.net.HttpURLConnection;
    import java.net.URL;

    public class Downloader extends AsyncTask<String, Integer, Bitmap> {

        private ProgressBar mProgressBar;
        private Context mContext;
        private ImageView mImageView;
        private TextView mPercent;

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

    }
