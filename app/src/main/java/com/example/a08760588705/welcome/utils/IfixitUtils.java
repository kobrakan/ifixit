package com.example.a08760588705.welcome.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by 08760588705 on 05/10/17.
 */

public class IfixitUtils {

    public static void alertas(Activity activity, String alerta){

        Context context = activity.getApplicationContext();
        Toast toast = Toast.makeText(context,alerta,Toast.LENGTH_SHORT);
        toast.show();
    }


}

