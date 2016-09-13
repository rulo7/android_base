package racobos.com.manhattan20.di.modules;

import dagger.Module;
import dagger.Provides;
import racobos.com.manhattan20.bases.BaseActivity;
import racobos.com.manhattan20.di.scopes.PerActivity;

/**
 * Created by raulcobos on 2/9/16.
 */
@Module
public class ActivityModule {
  private final BaseActivity baseActivity;

  public ActivityModule(BaseActivity baseActivity) {
    this.baseActivity = baseActivity;
  }

  @Provides
  @PerActivity
  BaseActivity provideBaseActivity() {
    return this.baseActivity;
  }
}
