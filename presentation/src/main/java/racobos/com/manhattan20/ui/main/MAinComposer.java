package racobos.com.manhattan20.ui.main;

import com.txusballesteros.mara.TraitComposer;
import racobos.com.manhattan20.ui.components.views.paginatedlist.PaginatedListComponent;
import racobos.com.manhattan20.ui.components.views.progressbar.ProgressBarComponent;
import racobos.com.manhattan20.ui.components.views.toolbar.ToolbarComponent;

/**
 * Created by raulcobos on 13/9/16.
 */
@TraitComposer(
        traits = {
                ProgressBarComponent.class, ToolbarComponent.class, PaginatedListComponent.class
        })
public interface MainComposer {
}
