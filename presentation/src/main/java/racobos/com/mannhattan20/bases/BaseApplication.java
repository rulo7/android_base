package racobos.com.mannhattan20.bases;

import android.app.Application;
import racobos.com.mannhattan20.di.components.ApplicationComponent;
import racobos.com.mannhattan20.di.components.DaggerApplicationComponent;
import racobos.com.mannhattan20.di.modules.ApplicationModule;

/**
 * Created by raulcobos on 2/9/16.
 */
public abstract class BaseApplication extends Application {

  private ApplicationComponent applicationComponent;

  @Override
  public void onCreate() {
    initInjector();
    super.onCreate();
  }

  private void initInjector() {
    applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    applicationComponent.inject(this);
  }
}
