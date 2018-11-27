package edu.northwestern.loanstar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import edu.northwestern.loanstar.Utils.DefaultRate;
import edu.northwestern.loanstar.Utils.JSONParser;
import edu.northwestern.loanstar.Utils.Values;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private WorkerThread workerThreadObject;

    TextView mBadLoansAvoidedTextView;
    TextView mGoodLoansLostTextView;
    TextView mTotalImpactTextView;
    Spinner mDefaultRateChangerSpinner;

    ArrayList<String> defaultRateValues = new ArrayList<>();
    ArrayList<DefaultRate> defaultRateObject = new ArrayList<>();
    DecimalFormat numberFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        workerThreadObject = new WorkerThread(MainActivity.this);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mBadLoansAvoidedTextView = findViewById(R.id.value_bad_loans_avoided);
        mGoodLoansLostTextView = findViewById(R.id.value_good_loans_lost);
        mTotalImpactTextView = findViewById(R.id.value_total_impact);
        mDefaultRateChangerSpinner = findViewById(R.id.spinner_default_rates);
        workerThreadObject.execute();
    }

    @Override
    protected void onStop() {
        super.onStop();
        workerThreadObject.cancel(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            Intent mAboutActivityStarterIntent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(mAboutActivityStarterIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        for (Values values : defaultRateObject.get(position).getValues()) {
            mBadLoansAvoidedTextView.setText(numberFormat.format(values.getBadLoansAvoided()));
            mGoodLoansLostTextView.setText(numberFormat.format(values.getGoodLoansLost()));
            mTotalImpactTextView.setText(numberFormat.format(values.getNetImpact()));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Default value is selected as is.
    }

    private class WorkerThread extends AsyncTask<Void, Void, Void> {
        private WeakReference<MainActivity> mainActivityWeakReference;

        WorkerThread(MainActivity context) {
            mainActivityWeakReference = new WeakReference<>(context);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            JSONParser jsonParser = new JSONParser(getResources(), R.raw.default_rate_values);
            defaultRateObject = jsonParser.constructUsingGson(new TypeToken<ArrayList<DefaultRate>>() {
            }.getType());
            if (defaultRateObject != null) {
                for (DefaultRate defaultRate : defaultRateObject) {
                    defaultRateValues.add(defaultRate.getRate() + "%");
                }
            }
            numberFormat.setNegativePrefix("$" + "(");
            numberFormat.setNegativeSuffix(")");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MainActivity mainActivity = mainActivityWeakReference.get();
            if (mainActivity == null || mainActivity.isFinishing()) return;
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mainActivity, android.R.layout.simple_spinner_item, defaultRateValues);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mDefaultRateChangerSpinner.setAdapter(arrayAdapter);
            mDefaultRateChangerSpinner.setOnItemSelectedListener(mainActivity);
        }
    }
}