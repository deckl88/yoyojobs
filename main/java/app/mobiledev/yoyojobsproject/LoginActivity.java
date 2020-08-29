package app.mobiledev.yoyojobsproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
TextView attempts;
private int counter = 5;
    Button button;
    EditText email;
    EditText pwd;

    TextView textview;
    FirebaseAuth  mFireBaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        attempts = (TextView) findViewById(R.id.textView5);
        attempts.setText("Number of attempts remaining: 5");
        mFireBaseAuth = FirebaseAuth.getInstance();
        textview = (TextView)findViewById(R.id.not_registered);
        email = (EditText)findViewById(R.id.email);
        pwd = (EditText)findViewById(R.id.pwd);
        button = (Button) findViewById(R.id.button3);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFireBaseAuth.getCurrentUser();
                    if(mFirebaseUser != null){
                        Toast.makeText(LoginActivity.this, "You are logged in.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(LoginActivity.this, "Please Login.",Toast.LENGTH_SHORT).show();
                    }
            }
        };
      button.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View view){
              String userEmail = email.getText().toString();
              String userPwd = pwd.getText().toString();


              if(userEmail.isEmpty()){
                  email.setError("Please enter your E-mail.");
                  email.requestFocus();
              }else if (userPwd.isEmpty()) {
                  pwd.setError("Please enter a password.");
                  pwd.requestFocus();
              }else if (userEmail.isEmpty() && userPwd.isEmpty()){
                  Toast.makeText(LoginActivity.this, "Fields are empty!", Toast.LENGTH_LONG).show();
              }else if (!userEmail.isEmpty() && !userPwd.isEmpty()){
                  mFireBaseAuth.signInWithEmailAndPassword(userEmail, userPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login Error, Please Try Again.", Toast.LENGTH_LONG).show();
                                counter--;
                            }else{
                                Intent intToHome = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intToHome);
                            }
                      }
                  });
              }
              else{
                  Toast.makeText(LoginActivity.this, "Error Occurred!", Toast.LENGTH_LONG).show();
              }
          }

      });
        textview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intSignUp = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intSignUp);
            }
        });
    }

    protected void onStart(){
        super.onStart();
        mFireBaseAuth.addAuthStateListener(mAuthStateListener);

    }

    private void validate(String username, String password){  
        if ((username.equals("admin")) && (password.equals("1234"))){
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }else {
            counter--;
            attempts.setText("Number of attempts remaining: " + String.valueOf(counter));

            if (counter == 0) {
                button.setEnabled(false);
            }
        }
    }

}
