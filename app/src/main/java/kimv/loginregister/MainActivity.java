package kimv.loginregister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //defining view objects
    private Button buttonRegister;
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;

    //loadbar
    private ProgressDialog progressDialog;

    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //als de user al is ingelogt
        if(firebaseAuth.getCurrentUser() != null){
            //profile activity hier
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        //initializing de views
        progressDialog = new ProgressDialog(this);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        editTextName = (EditText) findViewById(R.id.editTextName) ;
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registerUser(){
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            //username is leeg
            Toast.makeText(this, "Voer je username in", Toast.LENGTH_SHORT).show();
            //stopt de functie
            return;
        }

        if(TextUtils.isEmpty(email)){
            //email is leeg
            Toast.makeText(this, "Voer je email in", Toast.LENGTH_SHORT).show();
            //stopt de functie
            return;
        }

        if(TextUtils.isEmpty(password)){
            //wachtwoord is leeg
            Toast.makeText(this, "Voer je wachtwoord in", Toast.LENGTH_SHORT).show();
            //stopt de functie
            return;
        }

        //als authenticatie goed is
        //laat loadingbar zien

        progressDialog.setMessage("Gebruiker registreren...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //als de gebruiker succesvol is geregistreerd en ingelogt
                            //profile activity wordt hier gestart
                            //nu wordt er een toast getoond
                            Toast.makeText(MainActivity.this, "Registratie Succesvol", Toast.LENGTH_SHORT).show();

                            //als de user al is ingelogt
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

                        }else{
                            Toast.makeText(MainActivity.this, "Registratie Mislukt.. Probeer het nog eens", Toast.LENGTH_SHORT).show();
                        }
                        //stop met spinnen!!
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonRegister) {
            registerUser();
        }

        if (view == textViewSignin) {
            //open login activity
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
