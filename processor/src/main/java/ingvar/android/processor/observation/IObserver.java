package ingvar.android.processor.observation;

/**
 * Created by Igor Zubenko on 2015.03.18.
 */
public interface IObserver<R> {

    String KEY_GROUP = "observer.group";
    float MIN_PROGRESS = 0;
    float MAX_PROGRESS = 100;

    String getGroup();

    void progress(float progress);

    void completed(R result);

    void cancelled();

    void failed(Exception exception);

}
