package racobos.com.manhattan20.di.components;

import dagger.Component;
import racobos.com.manhattan20.di.modules.FragmentModule;
import racobos.com.manhattan20.di.scopes.PerFragment;

/**
 * Created by raulcobos on 2/9/16.
 */
@PerFragment
@Component(
        dependencies = ApplicationComponent.class,
        modules = FragmentModule.class)
public interface FragmentComponent {
}
