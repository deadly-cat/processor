package ingvar.android.processor.examples.dictionary.task;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import ingvar.android.literepo.builder.UriBuilder;
import ingvar.android.processor.examples.dictionary.persistence.MeaningConverter;
import ingvar.android.processor.examples.dictionary.pojo.Meaning;
import ingvar.android.processor.examples.dictionary.pojo.Meanings;
import ingvar.android.processor.examples.dictionary.pojo.Word;
import ingvar.android.processor.examples.dictionary.storage.DictionaryContract;
import ingvar.android.processor.observation.IObserverManager;
import ingvar.android.processor.persistence.Time;
import ingvar.android.processor.sqlite.source.SqliteSource;
import ingvar.android.processor.task.SingleTask;

/**
 * Created by Igor Zubenko on 2015.04.16.
 */
public class RequestMeaningsTask extends SingleTask<Word, Meanings, SqliteSource> {

    public RequestMeaningsTask(Word key, boolean refresh) {
        super(key, Meanings.class, SqliteSource.class, refresh ? Time.ALWAYS_EXPIRED : Time.ALWAYS_RETURNED);
    }

    @Override
    public Meanings process(IObserverManager observerManager, SqliteSource source) {
        Meanings result = new Meanings();

        ContentResolver resolver = source.getContentResolver();
        MeaningConverter converter = (MeaningConverter) source.<Meaning>getConverter(Meaning.class);
        converter.setWord(getTaskKey());

        Uri uri = new UriBuilder()
            .authority(DictionaryContract.AUTHORITY)
            .table(DictionaryContract.Meanings.TABLE_NAME)
            .eq(DictionaryContract.Meanings.Col.DICTIONARY_ID, getTaskKey().getDictionary().getId())
            .eq(DictionaryContract.Meanings.Col.WORD_ID, getTaskKey().getId())
        .build();
        Cursor cursor = resolver.query(uri, DictionaryContract.Meanings.PROJECTION, null, null, null);
        while(cursor.moveToNext()) {
            result.add(converter.convert(cursor));
        }
        cursor.close();

        return result;
    }

}
