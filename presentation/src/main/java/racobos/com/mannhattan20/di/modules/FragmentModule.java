package racobos.com.mannhattan20.di.modules;

import dagger.Module;
import dagger.Provides;
import racobos.com.mannhattan20.bases.BaseFragment;
import racobos.com.mannhattan20.di.scopes.PerActivity;

/**
 * Created by raulcobos on 2/9/16.
 */
@Module
public class FragmentModule {
  private final BaseFragment baseFragment;

  public FragmentModule(BaseFragment baseFragment) {
    this.baseFragment = baseFragment;
  }

  @Provides
  @PerActivity
  BaseFragment provideBaseFragment() {
    return this.baseFragment;
  }
}