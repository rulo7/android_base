package racobos.com.manhattan20.ui.main;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.racobos.rosie.view.Presenter;
import java.util.List;
import javax.inject.Inject;
import racobos.com.manhattan20.R;
import racobos.com.manhattan20.bases.BaseActivity;
import racobos.com.manhattan20.ui.components.views.paginatedlist.PaginatedListComponent;
import rx.Observable;

/**
 * Created by raulcobos on 5/9/16.
 */
public class MainActivity extends BaseActivity implements MainPresenter.MainView {

  @Inject
  @Presenter
  MainPresenter mainPresenter;
  private Mara_MainComposer mainComposer;

  @Override
  protected int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initComposerConfiguration();
    mainComposer.enableHomeAsUp();
    mainComposer.hideProgress();
    getSupportActionBar().setTitle("Test");
  }

  private void initComposerConfiguration() {
    mainComposer = new Mara_MainComposer.Builder().setContext(this).build();
    mainComposer.initialize();
  }

  @Override
  public void configureAdapter(List<String> elements) {
    mainComposer.setConfiguration(new MainRenderer(elements));
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  protected class MainRenderer extends PaginatedListComponent.Renderer<String, MainRenderer.MainItemViewHolder> {

    public MainRenderer(List<String> elements) {
      super(elements);
    }

    @Override
    public int getLayoutId() {
      return R.layout.item_example;
    }

    @Override
    public MainItemViewHolder getViewHolder(View v) {
      return new MainItemViewHolder(v);
    }

    @Override
    public void render(String element, MainItemViewHolder holder) {
      holder.textViewLabel.setText(element);
    }

    @Override
    protected Observable<List<String>> getNextPageItems() {
      return mainPresenter.nextPage();
    }

    protected class MainItemViewHolder extends RecyclerView.ViewHolder {
      @BindView(R.id.text_view_label)
      TextView textViewLabel;

      public MainItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
      }
    }
  }
}