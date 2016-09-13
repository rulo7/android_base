package racobos.com.manhattan20.bases;

import android.os.Bundle;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.racobos.rosie.view.RosieSupportFragment;
import racobos.com.manhattan20.di.ComponentReflectionInjector;
import racobos.com.manhattan20.di.components.ActivityComponent;
import racobos.com.manhattan20.di.components.DaggerActivityComponent;

/**
 * Created by raulcobos on 2/9/16.
 */
public abstract class BaseFragment extends RosieSupportFragment {

  private Unbinder unbinder;

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    initInjector();
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);
  }

  private void initInjector() {
    ActivityComponent component = DaggerActivityComponent.builder()
            .applicationComponent(((BaseApplication) getActivity().getApplication()).getApplicationComponent())
            .build();
    ComponentReflectionInjector<ActivityComponent> injector =
            new ComponentReflectionInjector<>(ActivityComponent.class, component);
    injector.inject(this);
  }

  @Override
  public void onDestroyView() {
    unbinder.unbind();
    super.onDestroyView();
  }
}
