package ingvar.examples.dictionary.storage;

import android.net.Uri;

import ingvar.android.processor.sqlite.persistence.ExtendedColumns;

/**
 * Created by Igor Zubenko on 2015.04.09.
 */
public class DictionaryContract {

    public static final String AUTHORITY = "ingvar.android.examples.dictionary.provider";
    public static final Uri PROVIDER_URI = Uri.parse("content://" + AUTHORITY);

    public static class Dictionary {

        public static final String TABLE_NAME = "dictionaries";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(PROVIDER_URI, TABLE_NAME);
        public static final String[] PROJECTION = {Col._ID, Col.NAME, Col._CREATION_DATE};

        public static class Col implements ExtendedColumns {
            public static final String NAME = "name";
        }

    }

    public static class Words {

        public static final String TABLE_NAME = "words";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(PROVIDER_URI, TABLE_NAME);

        public static class Col implements ExtendedColumns {
            public static final String DICTIONARY_ID = "dictionary_id";
            public static final String VALUE = "value";
        }

    }

    public static class Meanings {

        public static final String TABLE_NAME = "meanings";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(PROVIDER_URI, TABLE_NAME);

        public static class Col implements ExtendedColumns {
            public static final String DICTIONARY_ID = "dictionary_id";
            public static final String WORD_ID = "word_id";
            public static final String VALUE = "value";
        }

    }

    private DictionaryContract() {}
}
