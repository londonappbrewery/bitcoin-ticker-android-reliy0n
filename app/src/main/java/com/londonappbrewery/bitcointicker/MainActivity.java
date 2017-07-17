package com.londonappbrewery.bitcointicker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;
import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    TextView update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.currency_spinner);
        update  = (TextView) findViewById(R.id.priceLabel);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = spinner.getSelectedItem().toString();
                System.out.println(text);
                update(text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    // TODO: MY http code

    private void update(String MONEY) {
        update.setText("Updating ...");
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        String url = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTC"+MONEY;
        client.get(url, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject res) {
                        // called when response HTTP status is "200 OK"
                        try {
                            String a = (String) res.getString("ask");
                            update.setText(a);
                        } catch (JSONException e) {
                            update.setText("There was an error");
                        }


                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        update.setText("There was an error");
                    }
                }
        );
    }

//https://apiv2.bitcoinaverage.com/indices/global/ticker/BTCUSD

}


