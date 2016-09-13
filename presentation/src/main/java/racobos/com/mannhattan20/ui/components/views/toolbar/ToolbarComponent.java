package racobos.com.mannhattan20.ui.components.views.toolbar;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.txusballesteros.mara.Trait;
import racobos.com.mannhattan20.R;
import racobos.com.mannhattan20.ui.components.views.BaseViewComponent;

/**
 * Created by raulcobos on 5/9/16.
 */
@Trait
public class ToolbarComponent implements BaseViewComponent {
  private Context context;
  private Toolbar toolbar;

  public ToolbarComponent(Context context) {
    this.context = context;
  }

  @Override
  public void initialize() {
    if (context instanceof AppCompatActivity) {
      AppCompatActivity rootActivity = (AppCompatActivity) context;
      ViewGroup holderView = (ViewGroup) rootActivity.findViewById(R.id.toolbar_component);
      if (holderView != null) {
        toolbar = (Toolbar) LayoutInflater.from(context).inflate(R.layout.toolbar_component_view, holderView, false);
        holderView.addView(toolbar);
        rootActivity.setSupportActionBar(toolbar);
        rootActivity.getSupportActionBar().setDisplayShowTitleEnabled(true);
        rootActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> rootActivity.onBackPressed());
      }
    }
  }
}
