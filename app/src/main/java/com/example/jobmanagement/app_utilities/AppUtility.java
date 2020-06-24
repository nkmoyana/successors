package com.example.jobmanagement.app_utilities;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class AppUtility extends Application
{
    public static void ShowToast(Context context, String message)
    {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show();
    }
}
