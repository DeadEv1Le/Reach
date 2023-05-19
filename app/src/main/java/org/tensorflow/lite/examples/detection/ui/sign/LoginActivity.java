package org.tensorflow.lite.examples.detection.ui.sign;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.tensorflow.lite.examples.detection.MainAcitvity;
import org.tensorflow.lite.examples.detection.R;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private boolean rememberMe;
    private FirebaseAuth auth;
    private EditText loginEmail, loginPassword;
    private TextView signupRedirectText, forgotPasswordText;
    private Button loginButton, sendmessage;

    private TextView forgotpassEmail;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        forgotpassEmail = findViewById(R.id.reset_email);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        rememberMe = sharedPreferences.getBoolean("rememberMe", false);
        forgotPasswordText = findViewById(R.id.forgotPasswordText);
        CheckBox rememberMeCheckbox = findViewById(R.id.rememberMeCheckbox);
        rememberMeCheckbox.setChecked(rememberMe);
        rememberMeCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rememberMe = isChecked;
                // Save "Remember Me" state in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("rememberMe", rememberMe);
                editor.apply();
            }
        });

        if (rememberMe) {
            String savedEmail = sharedPreferences.getString("email", "");
            String savedPassword = sharedPreferences.getString("password", "");
            loginEmail.setText(savedEmail);
            loginPassword.setText(savedPassword);

            
            if (!savedEmail.isEmpty() && !savedPassword.isEmpty()) {
                auth.signInWithEmailAndPassword(savedEmail, savedPassword)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                FirebaseUser user = auth.getCurrentUser();
                                if (user != null && user.isEmailVerified()) {
                                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, MainAcitvity.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Please verify your email before logging in", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }

        loginEmail.setText(sharedPreferences.getString("email", ""));
        loginPassword.setText(sharedPreferences.getString("password", ""));
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the entered email and password
                String email = loginEmail.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!password.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, password)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        FirebaseUser user = auth.getCurrentUser();
                                        if (user != null && user.isEmailVerified()) {
                                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                            // Save "Remember Me" state and credentials in SharedPreferences
                                            if (rememberMe) {
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString("email", email);
                                                editor.putString("password", password);
                                                editor.apply();
                                            } else {
                                                // Clear saved credentials
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.remove("email");
                                                editor.remove("password");
                                                editor.apply();
                                            }
                                            startActivity(new Intent(LoginActivity.this, MainAcitvity.class));
                                            finish();
                                        } else {
                                            Toast.makeText(LoginActivity.this, "Please verify your email before logging in", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        loginPassword.setError("Password can't be empty");
                    }
                } else if (email.isEmpty()) {
                    loginEmail.setError("Email can't be empty");
                } else {
                    loginEmail.setError("Please enter a valid email");
                }
            }
        });
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
        forgotPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

            }
        });
    }
    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.forget_password_dialog, null);
        builder.setView(dialogView);

        Button sendmessage = dialogView.findViewById(R.id.recovery_button);
        EditText forgotpassEmail = dialogView.findViewById(R.id.reset_email);

        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = forgotpassEmail.getText().toString().trim();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Failed to send password reset email", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    forgotpassEmail.setError("Please enter a valid email");
                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onBackPressed() {
        //nollo
    }
}