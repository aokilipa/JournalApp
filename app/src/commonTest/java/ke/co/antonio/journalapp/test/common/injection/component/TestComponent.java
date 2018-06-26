package ke.co.antonio.journalapp.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import ke.co.antonio.journalapp.injection.component.ApplicationComponent;
import ke.co.antonio.journalapp.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
