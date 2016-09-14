package racobos.com.manhattan20.ui.main;

import com.racobos.manhattan20.usecases.ListElements;
import com.racobos.rosie.domain.usecase.UseCaseHandler;
import com.racobos.rosie.domain.usecase.annotation.Success;
import com.racobos.rosie.domain.usecase.callback.OnSuccessCallback;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import racobos.com.manhattan20.bases.BasePresenter;
import racobos.com.manhattan20.di.scopes.PerActivity;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by raulcobos on 13/9/16.
 */
@PerActivity
public class MainPresenter extends BasePresenter<MainPresenter.MainView> {

  private int nextPage = 0;
  private ListElements listElements;

  @Inject
  public MainPresenter(UseCaseHandler useCaseHandler, ListElements listElements) {
    super(useCaseHandler);
    this.listElements = listElements;
  }

  @Override
  public void initialize() {
    super.initialize();
  }

  @Override
  public void update() {
    super.update();
    createUseCaseCall(listElements).args(nextPage).onSuccess(new OnSuccessCallback() {
      @Success
      public void onElementsLoaded(List<String> elements, int nextPage, int maxPages) {
        getView().configureAdapter(elements, nextPage, maxPages);
        MainPresenter.this.nextPage = nextPage;
      }
    }).execute();
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
    return Observable.create(new Observable.OnSubscribe<List<String>>() {
      @Override
      public void call(Subscriber subscriber) {
        callUseCase(subscriber);
      }
    }).delay(3, TimeUnit.SECONDS);
  }

  private void callUseCase(Observer<List<String>> subscriber) {
    createUseCaseCall(listElements).args(nextPage).onSuccess(new OnSuccessCallback() {
      @Success
      public void onElementsLoaded(List<String> elements, int nextPage, int maxPages) {
        subscriber.onNext(elements);
        MainPresenter.this.nextPage = nextPage;
        subscriber.onCompleted();
      }
    }).execute();
  }

  public interface MainView extends BasePresenter.BaseView {
    void configureAdapter(List<String> elements, int startPage, int maxPages);
  }
}