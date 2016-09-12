package racobos.com.mannhattan20.di.components;

import dagger.Component;
import racobos.com.mannhattan20.di.modules.FragmentModule;
import racobos.com.mannhattan20.di.scopes.PerFragment;

/**
 * Created by raulcobos on 2/9/16.
 */
@PerFragment
@Component(modules = FragmentModule.class)
public interface FragmentComponent {
}
