package com.example.a08760588705.welcome.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by 08760588705 on 06/09/17.
 */

public class Notes implements BaseColumns {

    public static final Uri CONTENT_URI = Uri.parse("content://"+ AtendimentoProvider.AUTHORITY + "/notes");

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/"+ AtendimentoProvider.AUTHORITY;

    public static final String NOTE_ID = "_id";

    public static final String TEXT = "text";


}
