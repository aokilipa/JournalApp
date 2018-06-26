package ke.co.antonio.journalapp.injection.component;

import dagger.Subcomponent;
import ke.co.antonio.journalapp.injection.PerActivity;
import ke.co.antonio.journalapp.injection.module.ActivityModule;
import ke.co.antonio.journalapp.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
