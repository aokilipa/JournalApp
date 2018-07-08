package ke.co.antonio.journalapp.ui.main;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.List;

import ke.co.antonio.journalapp.data.model.Ribot;
import ke.co.antonio.journalapp.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

    //void firebaseAuthWithGoogle(GoogleSignInAccount acct);
}
