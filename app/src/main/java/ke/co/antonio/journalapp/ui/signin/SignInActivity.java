package ke.co.antonio.journalapp.ui.signin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ke.co.antonio.journalapp.R;

public class SignInActivity extends AppCompatActivity implements SignInMvpView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }
}
