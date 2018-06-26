package ke.co.antonio.journalapp.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ke.co.antonio.journalapp.data.DataManager;
import ke.co.antonio.journalapp.data.SyncService;
import ke.co.antonio.journalapp.data.local.DatabaseHelper;
import ke.co.antonio.journalapp.data.local.PreferencesHelper;
import ke.co.antonio.journalapp.data.remote.RibotsService;
import ke.co.antonio.journalapp.injection.ApplicationContext;
import ke.co.antonio.journalapp.injection.module.ApplicationModule;
import ke.co.antonio.journalapp.util.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    RibotsService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    RxEventBus eventBus();

}
