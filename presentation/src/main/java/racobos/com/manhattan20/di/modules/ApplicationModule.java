package racobos.com.manhattan20.di.modules;

import android.content.Context;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.racobos.rosie.domain.usecase.TaskScheduler;
import com.racobos.rosie.domain.usecase.UseCaseHandler;
import com.racobos.rosie.domain.usecase.callback.MainThreadCallbackScheduler;
import com.racobos.rosie.domain.usecase.error.ErrorFactory;
import com.racobos.rosie.domain.usecase.error.ErrorHandler;
import com.racobos.rosie.domain.usecase.jobqueue.TaskSchedulerJobQueue;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import racobos.com.manhattan20.bases.BaseApplication;

/**
 * Created by raulcobos on 2/9/16.
 */
@Module
public class ApplicationModule {
  private static final int MIN_CONSUMER_COUNT = 1;
  private static final int MAX_CONSUMER_COUNT = 5;
  private static final int LOAD_FACTOR = 1;
  private final BaseApplication baseApplication;

  public ApplicationModule(BaseApplication baseApplication) {
    this.baseApplication = baseApplication;
  }

  @Provides
  @Singleton
  Context provideContext() {
    return baseApplication.getApplicationContext();
  }

  @Provides
  @Singleton
  public UseCaseHandler provideUseCaseHandler(TaskScheduler taskScheduler, ErrorHandler errorHandler) {
    return new UseCaseHandler(taskScheduler, errorHandler);
  }

  @Provides
  @Singleton
  public TaskScheduler provideTaskScheduler(JobManager jobManager) {
    return new TaskSchedulerJobQueue(jobManager);
  }

  @Provides
  @Singleton
  public JobManager provideJobManager(Context context) {
    Configuration config = new Configuration.Builder(context).minConsumerCount(MIN_CONSUMER_COUNT)
            .maxConsumerCount(MAX_CONSUMER_COUNT)
            .loadFactor(LOAD_FACTOR)
            .build();
    return new JobManager(context, config);
  }

  @Provides
  @Singleton
  public ErrorHandler provideErrorHandler() {
    return new ErrorHandler(new ErrorFactory(), new MainThreadCallbackScheduler());
  }
}