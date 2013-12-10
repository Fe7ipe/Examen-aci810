package com.androidfinal;

import com.example.aci570_db.db.MyAppDataSource;
import com.example.aci570_db.model.Mensaje;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Msje extends Activity {
	
	private MyAppDataSource ds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_msje);
		
   
		
		ds = new MyAppDataSource(this);
	    ds.open();
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.msje, menu);
		return true;
		}
	
	
	public void enviarchat(View view) {
		
		EditText firstSendField = (EditText) this.findViewById(R.id.enviar_chat);
		@SuppressWarnings("unused")
		String firstSend = firstSendField.getText().toString();
		
		Toast.makeText(this, "Send Complete", Toast.LENGTH_LONG).show();
		
		Mensaje m=null;
		
		Intent i = new Intent();
		i.putExtra(Chat.EXTRA_MENSAJE, m);
		i.putExtra(Chat.EXTRA_REMOVE_MENSAJE, false);
		this.setResult(RESULT_OK, i);
		
		this.finish();
	}
	
	@Override
	protected void onResume() {
		ds.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		ds.close();
		super.onPause();
	}

}
