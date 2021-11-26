package com.ochando.superheroapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText idData;
    TextView getUrl;
    TextView showURL;

    public class QuerySuperHero extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {

            URL buscarUrl = urls[0];
            String buscarResultados = null;

            try {
                buscarResultados = InternetSuperHero.openURL(buscarUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return buscarResultados;
        }

        protected void onPostExecute(String s) {

            if (s != null && !s.equals("")) {
                getUrl.setText(s);
            }
            super.onPostExecute(s);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();
        if (itemID == R.id.launch) {
            URL getDatos = null;
            try {
                if (idData.getText().toString().equals("")) {
                    getDatos = InternetSuperHero.getAllURL();

                } else {
                    getDatos = InternetSuperHero.getDataID(idData.getText().toString());
                }
                new QuerySuperHero().execute(getDatos);
                showURL.setText("la Url es :" +  getDatos);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (itemID == R.id.clear) {
                idData.setText("");
                getUrl.setText("");
                showURL.setText("");
        }
        return  true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idData = (EditText) findViewById(R.id.idData);
        getUrl = (TextView) findViewById(R.id.show_all);
        showURL = (TextView) findViewById(R.id.showURL);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;

    }





}