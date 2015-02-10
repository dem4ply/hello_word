package org.dem4ply.hello_word.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.TrustManagerFactory;

/**
 * Created by dem4ply on 09/02/2015.
 */
public class Http {
    static public String get(String s_url) {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(s_url);
        HttpResponse response;
        try {
            response = client.execute(get);
            Log.i("HTTP",response.getStatusLine().toString());
            Log.v("HTTPS", TrustManagerFactory.getDefaultAlgorithm());
            HttpEntity entity = response.getEntity();
            if (entity != null)
                return _read_stream(entity.getContent());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    static public void file(String s_url, String file_name) {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(s_url);
        HttpResponse response;
        try {

            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            InputStream input = new BufferedInputStream(entity.getContent());
            if (file_name == "") {
                file_name = entity.getContentType().getName();
            }
            OutputStream output = new FileOutputStream("/sdcard/" + file_name);
            //long len_file = entity.getContentLength();
            byte data[] = new byte[1024];
            //long total = 0;
            int count = 0;
            while ( ( count = input.read(data) ) != -1 ) {
                //total += count;
                //publishProgress((int)(total*100/len_file));
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public boolean is_network_available(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() ) {
            return true;
        }
        return false;
    }

    static private String _read_stream(InputStream in) {
        BufferedReader reader = null;
        String result = "";
        try {
            reader = new BufferedReader(new InputStreamReader(in) );
            String line = "";
            while ((line = reader.readLine() ) != null) {
                result += line;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                    in.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
