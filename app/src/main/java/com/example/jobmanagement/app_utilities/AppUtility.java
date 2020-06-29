package com.example.jobmanagement.app_utilities;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.room.Dao;

import com.example.jobmanagement.R;
import com.google.android.material.textfield.TextInputEditText;

public class AppUtility extends Application
{
    public  static  SharedPreferences sharedpreferences;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public static void ShowToast(Context context, String message, View toastView, Integer results)
    {
        TextView tvMessage = toastView.findViewById(R.id.tvMessage);
        ImageView ivToast = toastView.findViewById(R.id.ivToast);
        tvMessage.setText(message);

        if(results == 1)
        {
            ivToast.setImageResource(R.drawable.check);
            Toast toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
            toast.setView(toastView);
            toast.show();
        }
        else if (results == 2)
        {
            ivToast.setImageResource(R.drawable.error);
            tvMessage.setTextColor(Color.YELLOW);
            Toast toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
            toast.setView(toastView);
            toast.show();
        }
        else if (results == 3)
        {
            ivToast.setImageResource(R.drawable.info);
            tvMessage.setTextColor(Color.blue(225));
            Toast toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
            toast.setView(toastView);
            toast.show();
        }
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
