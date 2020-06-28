package com.example.jobmanagement.app_utilities;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.room.Dao;

import com.google.android.material.textfield.TextInputEditText;

public class AppUtility extends Application
{
    public static void ShowToast(Context context, String message)
    {
        Toast.makeText(context,message, Toast.LENGTH_LONG).show();
    }

    public static boolean TextInputEditTextHasText(TextInputEditText... textInputEditTexts)
    {
        for (TextInputEditText textInputEditText : textInputEditTexts)
        {
            if(textInputEditText.getText() != null)
            {
                if(textInputEditText.getText().toString().isEmpty())
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }

        return true;
    }

    public static boolean AutoCompleteTextViewHasText(AutoCompleteTextView... autoCompleteTextViews)
    {
        for (AutoCompleteTextView autoCompleteTextView : autoCompleteTextViews)
        {
            if(autoCompleteTextView.getText() != null)
            {
                if(autoCompleteTextView.getText().toString().isEmpty())
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }

        return true;
    }

}
