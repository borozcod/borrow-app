package com.example.appleuser.borrow;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SigninActivity extends ActionBarActivity
{
    private Button buttonBack;
    private ImageButton buttonSignin;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private String username;
    private String password;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initializeActivity();
    }

    private void initializeActivity()
    {
        setContentView(R.layout.activity_signin);

        initializeActionBar();

        initializeButtons();
    }

    private void initializeActionBar()
    {
        // set title
        setTitle("Borrow :: Sign In");

        // set icon
        //getActionBar().setIcon(R.drawable.PICTURE_NAME);

        // set back button
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initializeButtons()
    {
        buttonBack = (Button)findViewById(R.id.signinButtonBack);
        buttonSignin = (ImageButton) findViewById(R.id.signinButtonSignin);

        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMainActivity();
            }
        });
    }

    private void signin() {
        getTextFields();

        final ProgressDialog dialog = new ProgressDialog(SigninActivity.this);
        dialog.setMessage("Logging in");
        dialog.show();

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException pe) {
                dialog.dismiss();
                if (pe != null) {
                    toast(pe.getMessage());
                } else {
                    toWelcomeActivity();
                }
            }
        });
    }

    private void getTextFields()
    {
        editTextUsername = (EditText)findViewById(R.id.signinUsernameText);
        editTextPassword = (EditText)findViewById(R.id.signinPasswordText);

        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();
    }

    private void toast(String msg)
    {
        Toast.makeText(SigninActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    private void toHomeActivity()
    {
        finish();
        i = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(i);
    }

    private void toWelcomeActivity()
    {
        finish();
        i = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(i);
    }

    private void toMainActivity()
    {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_signin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        Log.d("Sagev", "Action Bar Start");

        switch (id)
        {
            case R.id.action_settings : {
                Log.d("Sagev", "Settings");
                return true;
            }
            case android.R.id.home : {
                Log.d("Sagev", "Back");
                toMainActivity();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}


