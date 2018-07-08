package ke.co.antonio.journalapp.ui.signin;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

import ke.co.antonio.journalapp.ui.base.MvpView;

public interface SignInMvpView extends MvpView {
    void signIn();
    void handleSignInResult(Task<GoogleSignInAccount> completedTask);
    void firebaseAuthWithGoogle(GoogleSignInAccount acct);
    void updateUI(FirebaseUser user);

}
