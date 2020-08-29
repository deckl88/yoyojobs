package app.mobiledev.yoyojobsproject;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText email;
    EditText pwd;
    TextView textview;
    TextView attempts;
    FirebaseAuth mFireBaseAuth;
    private int counter = 5;
    final String TAG = "YoYo Jobs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        mFireBaseAuth = FirebaseAuth.getInstance();
        textview = (TextView)findViewById(R.id.signIn);
        email = (EditText)findViewById(R.id.email);
        pwd = (EditText)findViewById(R.id.pwd);
        button = (Button) findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View view){
                Log.d(TAG, "onCreate(Bundle) called");
                validate(email.getText().toString(), pwd.getText().toString());
                String userEmail = email.getText().toString();
                String userPwd = pwd.getText().toString();

                    if(userEmail.isEmpty()){
                        email.setError("Please enter your E-mail.");
                        email.requestFocus();
                    }else if (userPwd.isEmpty()) {
                        pwd.setError("Please enter a password.");
                        pwd.requestFocus();
                    }else if (userEmail.isEmpty() && userPwd.isEmpty()){
                        Toast.makeText(MainActivity.this, "Fields are empty!", Toast.LENGTH_LONG).show();
                    }else if (!(userEmail.isEmpty() && userPwd.isEmpty())){
                        mFireBaseAuth.createUserWithEmailAndPassword(userEmail, userPwd).addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(MainActivity.this, "SignUp Unsuccessful, Please try again.", Toast.LENGTH_LONG).show();
                                }else{
                                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                }
                            }
                        });
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Error Occurred!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        textview.setOnClickListener(new View.OnClickListener(){
            @Override
                public void onClick(View view){
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }


    private void validate(String email, String password) {


    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter){
        this.counter = counter;
    }


    public TextView getAttempts() {
        return attempts;
    }

    public void setAttempts(TextView attempts) {
        this.attempts = attempts;
    }

    public TextView getTextview() {
        return textview;
    }

    public void setTextview(TextView textview) {
        this.textview = textview;
    }
}
