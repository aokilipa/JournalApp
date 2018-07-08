package ke.co.antonio.journalapp.ui.signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import ke.co.antonio.journalapp.R;

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    @BindView(R.id.et_firstname)
    EditText mFname;
    @BindView(R.id.et_lastname)
    EditText mLname;
    @BindView(R.id.et_email)
    EditText mEmail;
    @BindView(R.id.et_password)
    EditText mPassword;
    @BindView(R.id.et_confirm_pass)
    EditText mConfirmPassword;


    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /*initialize firebaseAuth instance*/
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentuser = mAuth.getCurrentUser();

    }

    public void createAccount(){

    }

    @Override
    public void signUp() {

    }

    @Override
    public void validateForm() {

    }
}
