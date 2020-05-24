package com.example.customkeyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText mMyEditText = (EditText) findViewById(R.id.editText2);
        mMyEditText.addTextChangedListener(new TextWatcher()
               {
                   //This works for on-screen keyboard, but does not really give - keyup, keydown events. instead gives char by char, with position.
                   //Could be combined with timestamp but again
                   public void afterTextChanged(Editable s)
                   {   long millis=System.currentTimeMillis();
                       Log.d("afterTextChanged", String.valueOf(millis) + "\t" + s.toString());
                   }
                   public void beforeTextChanged(CharSequence s, int start, int count, int after)
                   {
                       long millis=System.currentTimeMillis();
                       /*This method is called to notify you that, within s, the count characters beginning at start are about to be replaced by new text with length after. It is an error to attempt to make changes to s from this callback.*/
                       Log.d("beforeTextChanged", String.valueOf(millis) + "\t" + s.toString());
                   }
                   public void onTextChanged(CharSequence s, int start, int before, int count)
                   {
                       long millis=System.currentTimeMillis();
                       Log.d("onTextChanged", String.valueOf(millis) + "\t" + s.toString());
                   };
                });

        mMyEditText.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event){
                //keyCode => an int representing which key was pressed
                long millis=System.currentTimeMillis();
                java.util.Date date = new java.util.Date(millis);

                if ( event.getAction() == KeyEvent.ACTION_DOWN ) {
                    //keydown down
                    Log.d("KeyDown", String.valueOf(millis));
                }

                if ( event.getAction() == KeyEvent.ACTION_UP) {
                    //key up.
                    Log.d("KeyUp", String.valueOf(millis));
                }

                return false;
            }
        });

    }


    //both the below callbacks work only for physical keyboard. not for on-screen keyboard.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long millis=System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);

        Log.d("onKeyDown KEYCODE_any", String.valueOf(keyCode));
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            default:
                Log.d("onKeyUp KEYCODE_any", String.valueOf(keyCode));
                return super.onKeyUp(keyCode, event);
        }
    }



}
