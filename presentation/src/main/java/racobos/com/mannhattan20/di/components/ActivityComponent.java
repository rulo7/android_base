package racobos.com.mannhattan20.di.components;

import dagger.Component;
import racobos.com.mannhattan20.di.modules.ActivityModule;
import racobos.com.mannhattan20.di.scopes.PerActivity;
import racobos.com.mannhattan20.ui.main.MainActivity;

/**
 * Created by raulcobos on 2/9/16.
 */
@PerActivity
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
  void inject(MainActivity mainActivity);
  //Rellenar con las activities que se generen
}
