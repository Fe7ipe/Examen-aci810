package com.androidfinal;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import android.widget.EditText;
import android.widget.RadioButton;


public class Settings extends Activity {

	public final static String NAME_VALUE = "com.example.control33.NAME_VALUE";
	public final static String CONSULTA_VALUE = "com.example.control33.NAME_VALUE";
	public final static String RADIOBUTTON01_VALUE = "com.example.control33.RADIOBUTTON01_VALUE";
	public final static String RADIOBUTTON02_VALUE = "com.example.control33.RADIOBUTTON02_VALUE";
	public final static String RADIOBUTTON03_VALUE = "com.example.control33.RADIOBUTTON03_VALUE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	public void pasosiguiente(View view) {

		Intent i = new Intent(this, Settings2.class);

		EditText nombreEditText = (EditText) findViewById(R.id.editTextnombre);
		String name = nombreEditText.getText().toString();

		EditText consultaEditText = (EditText) findViewById(R.id.EditTextconsulta);
		String consulta = consultaEditText.getText().toString();

		RadioButton rbEditText01 = (RadioButton) findViewById(R.id.r0);
		Boolean rbclick0 = rbEditText01.isChecked();

		RadioButton rbEditText02 = (RadioButton) findViewById(R.id.r1);
		Boolean rbclick1 = rbEditText02.isChecked();

		RadioButton rbEditText03 = (RadioButton) findViewById(R.id.r2);
		Boolean rbclick2 = rbEditText03.isChecked();

		SharedPreferences sharedPref = getSharedPreferences("app",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();

		editor.putString(NAME_VALUE, name);
		editor.putString(CONSULTA_VALUE, consulta);
		editor.putBoolean(RADIOBUTTON01_VALUE, rbclick0);
		editor.putBoolean(RADIOBUTTON02_VALUE, rbclick1);
		editor.putBoolean(RADIOBUTTON03_VALUE, rbclick2);

		editor.commit();

		startActivity(i);
	}

}