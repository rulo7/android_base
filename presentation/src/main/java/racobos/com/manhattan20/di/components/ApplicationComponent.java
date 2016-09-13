package racobos.com.manhattan20.di.components;

import android.content.Context;
import com.racobos.rosie.domain.usecase.UseCaseHandler;
import dagger.Component;
import javax.inject.Singleton;
import racobos.com.manhattan20.bases.BaseApplication;
import racobos.com.manhattan20.di.modules.ApplicationModule;

/**
 * Created by raulcobos on 2/9/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(BaseApplication baseApplication);

  Context getContext();

  UseCaseHandler getUseCaseHandler();
}
