package com.siddharth.massengerapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.siddharth.massengerapp.Models.Users;
import com.siddharth.massengerapp.R;
import com.siddharth.massengerapp.databinding.ActivityLogInBinding;

public class LogInActivity extends AppCompatActivity {

    private ActivityLogInBinding binding;
    private ProgressDialog progressDialog;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private SignInClient oneTapClient;
    private   GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;


    private static final  int RC_SIGN_IN=17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

      getSupportActionBar().hide();

      auth = FirebaseAuth.getInstance();
      database = FirebaseDatabase.getInstance();

      progressDialog = new ProgressDialog(LogInActivity.this);
      progressDialog.setTitle("Login");
      progressDialog.setMessage("Please Wait \n checking Validity");


        //Configure Google Sign In
                 gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.your_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);   // its holding our request


        binding.btnLogIn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if(!binding.txtEmail.getText().toString().isEmpty() && !binding.txtPassword.getText().toString().isEmpty())
              {
                  progressDialog.show();
                  auth.signInWithEmailAndPassword(binding.txtEmail.getText().toString(),binding.txtPassword.getText().toString())
                          .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                              @Override
                              public void onComplete(@NonNull Task<AuthResult> task) {

                                  progressDialog.dismiss();

                                  if(task.isSuccessful())
                                  {

                                      Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                                      startActivity(intent);

                                  }

                                  else
                                  {
                                      Toast.makeText(LogInActivity.this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                  }

                              }
                          });

                  Toast.makeText(LogInActivity.this , "Login Successful", Toast.LENGTH_SHORT).show();

              }
              else {

                  Toast.makeText(LogInActivity.this, "Enter Credential", Toast.LENGTH_SHORT).show();
              }

          }
      });


        if(auth.getCurrentUser()!=null)    //this give use the current state of user , is any user is login or not, if current user is not null then directed to main activity
        {

            Intent intent =new  Intent(LogInActivity.this, MainActivity.class);
            startActivity(intent);

        }

        binding.txtClickSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LogInActivity.this,SinghUpActivity.class);
                startActivity(intent);
            }
        });


        //google sign button
        binding.btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               SignIn();
            }
        });

    }

     void SignIn() {

        //  this basically sending our request to google
        // due to this a pop is showed and gives some result
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {   // when that pop give some result its work on it
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            try {
                GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);
                  googleAuthFirebase(account.getIdToken());

            } catch (ApiException e) {

                Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            }
        }
    }




    private void googleAuthFirebase(String idToken ) {    // this will call when user data is received from the google bt intent

        if (idToken !=  null) {
            // Got an ID token from Google. Use it to authenticate
            // with Firebase.
            AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
            auth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("TAG", "signInWithCredential:success");
                                FirebaseUser Cuser = auth.getCurrentUser();  // here we have our current user details in Cuser


                                //now we make user class object  with current user details to send it our firebase database
                                Users users = new Users();
                                users.setUserId(Cuser.getUid());
                                users.setUserName(Cuser.getDisplayName());
                                users.setProfilePic(Cuser.getPhotoUrl().toString());

                                // here we send the data or you can say set the data of user to firebase realtime database

                                database.getReference().child("Users").child(Cuser.getUid()).setValue(users);



                                Intent intent = new Intent(LogInActivity.this,MainActivity.class);
                                startActivity(intent);

                                Toast.makeText(LogInActivity.this,"SignUp with Google Successfully",Toast.LENGTH_SHORT).show();

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("TAG", "signInWithCredential:failure", task.getException());
                                Toast.makeText(LogInActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
}

}


/// For SHA code
//just type in find ( on gradle tab , click on small elephant)
//  " gradle signingreport "   and then copy SHA1