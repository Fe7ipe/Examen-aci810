package com.androidfinal;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.support.v4.app.NavUtils;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Settings2 extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_settings2);
        
        
         SharedPreferences sharedPref = getSharedPreferences("app", Context.MODE_PRIVATE);        
     
     String name = sharedPref.getString(Settings.NAME_VALUE, "not set");
     String consulta = sharedPref.getString(Settings.CONSULTA_VALUE, "not set");
     Boolean  rbclick0 = (Boolean) sharedPref.getBoolean(Settings.RADIOBUTTON01_VALUE, true);
     Boolean  rbclick1 = (Boolean) sharedPref.getBoolean(Settings.RADIOBUTTON02_VALUE, true);
     Boolean rbclick2 = (Boolean) sharedPref.getBoolean(Settings.RADIOBUTTON03_VALUE, true);
   

     TextView nameTextView = (TextView) findViewById(R.id.textView1nombre);
     nameTextView.setText(name);


     TextView consultaTextView = (TextView) findViewById(R.id.textView2consulta);
     consultaTextView.setText(consulta);

             

     
     RadioButton radiobutton01  = (RadioButton) findViewById(R.id.r0result);
     radiobutton01.setChecked(rbclick0);
     

     RadioButton radiobutton02  = (RadioButton) findViewById(R.id.r1result);
     radiobutton02.setChecked(rbclick1);
     
     RadioButton rd03  = (RadioButton) findViewById(R.id.r2result);
     rd03.setChecked(rbclick2);
     
}

     
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.main_activity2, menu);
                return true;
        }
        public void volveratras2(View view) {

    		Intent i = new Intent(this, Aplicacion.class);
    		Toast.makeText(this, "Consulta Realizada", Toast.LENGTH_LONG).show();
    		startActivity(i);
        }
         @Override
     public boolean onOptionsItemSelected(MenuItem item) {
             switch (item.getItemId()) {
             case android.R.id.home:
                     
                     NavUtils.navigateUpFromSameTask(this);
                     return true;
             }
             return super.onOptionsItemSelected(item);
     }
}