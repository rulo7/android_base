package racobos.com.manhattan20.ui.main;

import com.racobos.rosie.domain.usecase.UseCaseHandler;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import racobos.com.manhattan20.bases.BasePresenter;
import racobos.com.manhattan20.di.scopes.PerActivity;
import rx.Observable;

/**
 * Created by raulcobos on 13/9/16.
 */
@PerActivity
public class MainPresenter extends BasePresenter<MainPresenter.MainView> {

  private String[][] elements = new String[][] {
          { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l" },
          { "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w" }
  };
  private int i = 0;

  @Inject
  public MainPresenter(UseCaseHandler useCaseHandler) {
    super(useCaseHandler);
  }

  @Override
  public void initialize() {
    super.initialize();
  }

  @Override
  public void update() {
    super.update();
    getView().configureAdapter(Arrays.asList(elements[i]));
  }

  @Override
  public void pause() {
    super.pause();
  }

  @Override
  public void destroy() {
    super.destroy();
  }

  public Observable<List<String>> nextPage() {
    i++;
    if (i < elements.length) {
      return Observable.just(Arrays.asList(elements[i])).delay(3, TimeUnit.SECONDS);
    }
    return Observable.empty();
  }

  public interface MainView extends BasePresenter.BaseView {
    void configureAdapter(List<String> elements);
  }
}
