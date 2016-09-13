package racobos.com.manhattan20.bases;

import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.racobos.rosie.view.RosieAppCompatActivity;
import racobos.com.manhattan20.di.ComponentReflectionInjector;
import racobos.com.manhattan20.di.components.ActivityComponent;
import racobos.com.manhattan20.di.components.DaggerActivityComponent;

/**
 * Created by raulcobos on 25/8/16.
 */
public abstract class BaseActivity extends RosieAppCompatActivity {

  private Unbinder unbinder;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    initInjector();
    super.onCreate(savedInstanceState);
    unbinder = ButterKnife.bind(this);
  }

  private void initInjector() {
    ActivityComponent component = DaggerActivityComponent.builder()
            .applicationComponent(((BaseApplication) getApplication()).getApplicationComponent())
            .build();
    ComponentReflectionInjector<ActivityComponent> injector =
            new ComponentReflectionInjector<>(ActivityComponent.class, component);
    injector.inject(this);
  }

  @Override
  protected void onDestroy() {
    if (unbinder != null) {
      unbinder.unbind();
    }
    super.onDestroy();
  }
}
