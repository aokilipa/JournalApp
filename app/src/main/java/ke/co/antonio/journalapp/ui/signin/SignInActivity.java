package ke.co.antonio.journalapp.ui.signin;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import ke.co.antonio.journalapp.R;
import ke.co.antonio.journalapp.ui.signup.SignUpActivity;

public class SignInActivity extends AppCompatActivity implements SignInMvpView, View.OnClickListener {

    private static final int RC_SIGN_IN =9001 ;
    private static final String TAG = "Journal App";
    GoogleSignInClient mGoogleSignInClient;
    SignInButton btSignIn;
    Button btnSignUp;

    private FirebaseAuth mAuth;
    private LinearLayout linearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //configure sign-in to request user ID,email and basic profile.
        //ID and basic profile are included in DEFAULT_SIGN_IN
        btSignIn = findViewById(R.id.sign_in_button);
        btnSignUp = findViewById(R.id.btn_sign_up);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .build();

        /* Build a GoogleSignInClient with options specified in gso */
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        btSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        /*initialize auth*/
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    protected void onStart() {
        super.onStart();
        /*
        check for existing google sign-in account
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if the user is already signed in the GoogleSignInAccount will be non-null.*/
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Result returned from launching the intent
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }
    @Override
    public void updateUI(FirebaseUser user) {
        //hideProgressDialog();
        if (user!=null){
            btSignIn.setVisibility(View.GONE);
        }else {
            btSignIn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:"+ acct.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //updateUI with signed-in users information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }else {
                            //if sign-in fails, display a message to the user
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Snackbar.make(linearLayout, "Authentication Failed",Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    @Override
    public void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signUp(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            //Google sign in was successful, authenticated with firebase.
            firebaseAuthWithGoogle(account);
            Toast.makeText(this,"Signed in succesfully",Toast.LENGTH_SHORT).show();
            //updateUI(null);
        }catch (ApiException e){
            Log.w(TAG, "signInResult:failed code="+e.getStatusCode());
            Toast.makeText(this,"Failed to sign in code: "+e.getStatusCode(),Toast.LENGTH_SHORT).show();
            //updateUI(null);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.btn_sign_up:
                signUp();
                break;
        }
    }
}
