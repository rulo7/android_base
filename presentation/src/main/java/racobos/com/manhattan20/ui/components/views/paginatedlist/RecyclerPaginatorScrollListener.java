package racobos.com.manhattan20.ui.components.views.paginatedlist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by http://stackoverflow.com/questions/26543131/how-to-implement-endless-list-with-recyclerview (1st answer)
 */
public abstract class RecyclerPaginatorScrollListener extends RecyclerView.OnScrollListener {

  int firstVisibleItem, visibleItemCount, totalItemCount;
  private int previousTotal = 0;
  private boolean loading = true;
  private int visibleThreshold = 5;

  @Override
  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);
    LinearLayoutManager mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
    visibleItemCount = recyclerView.getChildCount();
    totalItemCount = mLayoutManager.getItemCount();
    firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
    if (loading) {
      if (totalItemCount > previousTotal) {
        loading = false;
        previousTotal = totalItemCount;
      }
    }
    if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
      // Do something
      onLoadMore();
      loading = true;
    }
  }

  public abstract void onLoadMore();
}