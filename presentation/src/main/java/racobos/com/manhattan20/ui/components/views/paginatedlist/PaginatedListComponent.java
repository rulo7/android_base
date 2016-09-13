package racobos.com.manhattan20.ui.components.views.paginatedlist;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.txusballesteros.mara.Trait;
import java.util.List;
import racobos.com.manhattan20.R;
import racobos.com.manhattan20.ui.components.views.ViewComponent;
import rx.Observable;

/**
 * Created by raulcobos on 13/9/16.
 */
@Trait
public class PaginatedListComponent implements ViewComponent {

  private Context context;
  private RecyclerView recyclerView;
  private ProgressBar progressBar;
  private PaginatedListAdapter paginatedListAdapter;

  public PaginatedListComponent(Context context) {
    this.context = context;
  }

  @Override
  public void initialize() {
    if (context instanceof AppCompatActivity) {
      AppCompatActivity rootActivity = (AppCompatActivity) context;
      ViewGroup holderView = (ViewGroup) rootActivity.findViewById(R.id.recycler_paginated_component);
      if (holderView != null) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_paginated_component_view, holderView, false);
        holderView.addView(v);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
      }
    }
  }

  public void setConfiguration(Renderer renderer) {
    paginatedListAdapter = new PaginatedListAdapter(renderer);
    recyclerView.setLayoutManager(new LinearLayoutManager(context));
    recyclerView.addOnScrollListener(new RecyclerPaginatorScrollListener() {
      @Override
      public void onLoadMore() {
        progressBar.setVisibility(View.VISIBLE);
        renderer.getNextPageItems().subscribe(elements -> {
          renderer.addElements((List) elements);
          paginatedListAdapter.notifyDataSetChanged();
          progressBar.setVisibility(View.GONE);
        }, t -> {
          Log.e(Renderer.class.getSimpleName(), "error on getNextPageItems method");
          progressBar.setVisibility(View.GONE);
        });
      }
    });
    recyclerView.setAdapter(paginatedListAdapter);
  }

  public void notifyDataSetChanged() {
    this.paginatedListAdapter.notifyDataSetChanged();
  }

  public static abstract class Renderer<T, V extends RecyclerView.ViewHolder> {

    private List<T> elements;

    public Renderer(List<T> elements) {
      this.elements = elements;
    }

    public List<T> getElements() {
      return elements;
    }

    public void addElements(List<T> elements) {
      if (elements != null) {
        this.elements.addAll(elements);
      }
    }

    @LayoutRes
    public abstract int getLayoutId();

    private V getViewHolder(ViewGroup parent) {
      return getViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false));
    }

    public abstract V getViewHolder(View v);

    private void render(int position, V holder) {
      render(getElements().get(position), holder);
    }

    public abstract void render(T element, V holder);

    protected abstract Observable<List<T>> getNextPageItems();
  }

  private class PaginatedListAdapter extends RecyclerView.Adapter {

    private Renderer renderer;

    public PaginatedListAdapter(Renderer renderListener) {
      this.renderer = renderListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return renderer.getViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
      renderer.render(position, holder);
    }

    @Override
    public int getItemCount() {
      return renderer.getElements().size();
    }
  }
}