package com.example.jobmanagement.app_utilities;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
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
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class AppUtility extends Application
{
    public  static  SharedPreferences sharedpreferences;
    public  static Boolean isEdit = false;

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
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
            toast.setView(toastView);
            toast.show();
        }
        else if (results == 3)
        {
            ivToast.setImageResource(R.drawable.info);
            tvMessage.setTextColor(Color.BLUE);
            Toast toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
            toast.setView(toastView);
            toast.show();
        }
    }

    public static void setOnFocusChangeListener(final TextInputEditText textInputEditText, final String text)
    {
        textInputEditText.setHint("");
        textInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    textInputEditText.setHint(text);
                }
                else
                {
                    textInputEditText.setHint(R.string.empty_string);
                }
            }
        });
    }
    public static void setDropdownText(final AutoCompleteTextView autoCompleteTextView, final String text)
    {
        autoCompleteTextView.setHint("");
        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    autoCompleteTextView.setHint(text);
                }
                else
                {
                    autoCompleteTextView.setHint(R.string.empty_string);
                }
            }
        });

    }
    public static void validateInput(final TextInputLayout textInputLayout, TextInputEditText textInputEditText, Pattern pattern, String text)
    {
        String validates = "";
        if(textInputEditText != null && !TextUtils.isEmpty(textInputEditText.getText()))
        {
            validates = textInputEditText.getText().toString().trim();
        }

        if(!pattern.matcher(validates).matches()) {
            textInputLayout.setError(text);
        }
        else
        {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
            textInputLayout.setErrorIconDrawable(R.drawable.error_outline);
        }

    }

    public static void validateNumInput(final TextInputLayout textInputLayout, TextInputEditText textInputEditText, Pattern pattern, String text)
    {
        String validates = "", number = "";
        if(textInputEditText != null && !TextUtils.isEmpty(textInputEditText.getText()))
        {
            validates = textInputEditText.getText().toString().trim();
        }

        if(textInputEditText != null && !TextUtils.isEmpty(textInputEditText.getText()))
        {
            number = textInputEditText.getText().toString().trim();
        }


        if(!pattern.matcher(validates).matches()) {
            textInputLayout.setError(number + text);
        }
        else
        {
            textInputLayout.setError(null);
            textInputLayout.setErrorEnabled(false);
            textInputLayout.setErrorIconDrawable(R.drawable.error_outline);
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
