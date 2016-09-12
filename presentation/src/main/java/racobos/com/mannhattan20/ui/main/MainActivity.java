package racobos.com.mannhattan20.ui.main;

import android.os.Bundle;
import racobos.com.mannhattan20.R;
import racobos.com.mannhattan20.bases.BaseActivity;

/**
 * Created by raulcobos on 5/9/16.
 */
public class MainActivity extends BaseActivity {

  @Override
  protected int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().setTitle("Test");
  }
}