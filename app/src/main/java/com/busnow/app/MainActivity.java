package com.busnow.app;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

import com.busnow.entity.StopData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;

import static com.busnow.constants.EntityConstants.HTTP_REQUEST_ID_PREFIX;
import static com.busnow.constants.EntityConstants.HTTP_REQUEST_LINK;


public class MainActivity extends ToolBarActivity {

    private ListView list_one;
    private MyAdapter mAdapter = null;
    private StopData stopData = null;
    private Context mContext = null;
    private TextView test;
    private String Json = "EMPTY";
    private boolean finished = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        test = (TextView) findViewById(R.id.test);
        test.setMovementMethod(ScrollingMovementMethod.getInstance());
        bindViews();
        new RetrieveBusStopData().execute("11319");
        while (!finished){}
        try {
            stopData = new StopData(Json);
            mAdapter = new MyAdapter(stopData.getBusData(),mContext);
            list_one.setAdapter(mAdapter);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter bw = new PrintWriter(sw);
            e.printStackTrace(bw);
            test.append(sw.toString());
        }
    }
    private void bindViews(){
        list_one = (ListView) findViewById(R.id.list_one);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public class RetrieveBusStopData extends AsyncTask<String,Void,Void> {
        protected Void doInBackground(String... stopCodes) {
            try {
                getCurrentData(stopCodes);
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                PrintWriter bw = new PrintWriter(sw);
                e.printStackTrace(bw);
                test.append(sw.toString());
            }
            return null;
        }
    }
    private void getCurrentData(String... stopCodes) {
        String output = "";
        try {
        URL url = new URL(HTTP_REQUEST_LINK+HTTP_REQUEST_ID_PREFIX+stopCodes[0]);
        URLConnection yc = (URLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            output += inputLine;
        }
        in.close();
        } catch (Exception e) {
        }
        Json = output;
        finished = true;
    }

    protected void onPostExecute() {

    }
}
