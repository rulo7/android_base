package racobos.com.mannhattan20.bases;

import com.racobos.rosie.domain.usecase.UseCaseHandler;
import com.racobos.rosie.view.RosiePresenter;

/**
 * Created by raulcobos on 5/9/16.
 */
public abstract class BasePresenter<T extends BasePresenter.BaseView> extends RosiePresenter<T> {

  public BasePresenter(UseCaseHandler useCaseHandler) {
    super(useCaseHandler);
  }

  public interface BaseView extends RosiePresenter.View {
  }
}
