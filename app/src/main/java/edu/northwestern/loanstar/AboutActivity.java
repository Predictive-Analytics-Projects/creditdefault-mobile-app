package edu.northwestern.loanstar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.Objects;

public class AboutActivity extends AppCompatActivity {

    MaterialCardView mCardEditor;
    MaterialCardView mCardManager;
    MaterialCardView mCardLead;
    MaterialCardView mCardSme;
    MaterialCardView mCardFinance;

    TextView emailEditor;
    TextView emailManager;
    TextView emailLead;
    TextView emailSme;
    TextView emailFinance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar mToolbarAbout = findViewById(R.id.toolbar2);
        mToolbarAbout.setTitle("About");
        setSupportActionBar(mToolbarAbout);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mCardEditor = findViewById(R.id.card_editor);
        mCardManager = findViewById(R.id.card_manager);
        mCardLead = findViewById(R.id.card_lead);
        mCardSme = findViewById(R.id.card_sme);
        mCardFinance = findViewById(R.id.card_finance);

        emailEditor = findViewById(R.id.email_editor);
        emailManager = findViewById(R.id.email_manager);
        emailLead = findViewById(R.id.email_lead);
        emailSme = findViewById(R.id.email_sme);
        emailFinance = findViewById(R.id.email_finance);

        mCardEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailTeamMember(emailEditor.getText().toString());
            }
        });

        mCardManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailTeamMember(emailManager.getText().toString());
            }
        });

        mCardLead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailTeamMember(emailLead.getText().toString());
            }
        });

        mCardSme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailTeamMember(emailSme.getText().toString());
            }
        });

        mCardFinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailTeamMember(emailFinance.getText().toString());
            }
        });
    }

    public void emailTeamMember(String emailAddress) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {emailAddress});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Loan$tar Feedback");
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }
}
