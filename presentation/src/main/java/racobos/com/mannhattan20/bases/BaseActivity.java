package racobos.com.mannhattan20.bases;

import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.racobos.rosie.view.RosieAppCompatActivity;
import racobos.com.mannhattan20.di.ComponentReflectionInjector;
import racobos.com.mannhattan20.di.components.ActivityComponent;
import racobos.com.mannhattan20.di.components.DaggerActivityComponent;
import racobos.com.mannhattan20.ui.components.views.Mara_ViewsComposer;

/**
 * Created by raulcobos on 25/8/16.
 */
public abstract class BaseActivity extends RosieAppCompatActivity {

  private Unbinder unbinder;
  private Mara_ViewsComposer viewsComposer;

  public Mara_ViewsComposer getViewsComposer() {
    return viewsComposer;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    initInjector();
    super.onCreate(savedInstanceState);
    unbinder = ButterKnife.bind(this);
    initComposerConfiguration();
  }

  private void initInjector() {
    ActivityComponent component = DaggerActivityComponent.builder().build();
    ComponentReflectionInjector<ActivityComponent> injector =
            new ComponentReflectionInjector<>(ActivityComponent.class, component);
    injector.inject(this);
  }

  private void initComposerConfiguration() {
    viewsComposer = new Mara_ViewsComposer.Builder().setContext(this).build();
    viewsComposer.initialize();
  }

  @Override
  protected void onDestroy() {
    if (unbinder != null) {
      unbinder.unbind();
    }
    super.onDestroy();
  }
}
