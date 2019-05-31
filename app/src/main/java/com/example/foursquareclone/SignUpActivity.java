package com.example.foursquareclone;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    EditText usernameText, passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameText = findViewById(R.id.username_text_signup_activity);
        passwordText = findViewById(R.id.password_text_signup_activity);

        ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser != null) {
            Intent intent = new Intent(getApplicationContext(), LocationsActivity.class);
            startActivity(intent);
        }
    }

    public void signUp(View view) {

        final ParseUser user = new ParseUser();

        if (!usernameText.getText().toString().matches("") && !passwordText.getText().toString().matches("")) {

            user.setUsername(usernameText.getText().toString());
            user.setPassword(passwordText.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {

                    if (e != null) {
                        ParseUser.logOut();
                        alertDisplayer("Kayıt Başarısız", e.getLocalizedMessage() + "!!!");
                    } else {
                        Toast.makeText(getApplicationContext(), "Kayıt Olundu.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), LocationsActivity.class);
                        startActivity(intent);
                    }
                }
            });
        } else
            alertDisplayer("Kayıt Başarısız", "Gerekli alanları doldurunuz...");
    }

    public void signIn(View view) {

        if (!usernameText.getText().toString().matches("") && !passwordText.getText().toString().matches("")) {

            System.out.println("Username: " + usernameText.getText());

            ParseUser.logInInBackground(usernameText.getText().toString(), passwordText.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Toast.makeText(getApplicationContext(), "Hoşgeldiniz:  " + user.getUsername(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LocationsActivity.class);
                        startActivity(intent);
                    } else {
                        ParseUser.logOut();
                        alertDisplayer("Giriş Başarısız", e.getLocalizedMessage() + "!!!");
                    }
                }
            });
        } else
            alertDisplayer("Giriş Başarısız", "Gerekli alanları doldurunuz...");
    }

    private void alertDisplayer(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }
}
