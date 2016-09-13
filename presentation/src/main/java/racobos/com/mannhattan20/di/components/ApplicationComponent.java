package racobos.com.mannhattan20.di.components;

import android.content.Context;
import dagger.Component;
import javax.inject.Singleton;
import racobos.com.mannhattan20.bases.BaseApplication;
import racobos.com.mannhattan20.di.modules.ApplicationModule;

/**
 * Created by raulcobos on 2/9/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(BaseApplication baseApplication);

  Context getContext();
}
