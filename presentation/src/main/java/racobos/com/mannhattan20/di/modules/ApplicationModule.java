package racobos.com.mannhattan20.di.modules;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import racobos.com.mannhattan20.bases.BaseApplication;

/**
 * Created by raulcobos on 2/9/16.
 */
@Module
public class ApplicationModule {
  private final BaseApplication baseApplication;

  public ApplicationModule(BaseApplication baseApplication) {
    this.baseApplication = baseApplication;
  }

  @Provides
  @Singleton
  Context provideContext() {
    return baseApplication.getApplicationContext();
  }
}