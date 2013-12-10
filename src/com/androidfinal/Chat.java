package com.androidfinal;

import java.util.List;

import com.example.aci570_db.db.MyAppDataSource;
import com.example.aci570_db.listeners.ListViewItemClickListener;
import com.example.aci570_db.model.Mensaje;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Chat extends Activity {
	
	public static final int REQUEST_CODE_ADD_MENSAJE = 1;
	public static final int REQUEST_CODE_UPDATE_MENSAJE = 2;
	
	public static final String EXTRA_MENSAJE= "mensaje";
	public static final String EXTRA_REMOVE_MENSAJE = "remove";
	
	private MyAppDataSource ds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		
		ds = new MyAppDataSource(this);
		ds.open();

		List<Mensaje> values = ds.getMessage();
		
		// use the SimpleCursorAdapter to show the elements in a ListView
		ArrayAdapter<Mensaje> adapter = new ArrayAdapter<Mensaje>(this,
				android.R.layout.simple_list_item_1, values);

		this.setListAdapter(adapter);

		ListView lv = (ListView) this.findViewById(android.R.id.list);
		lv.setOnItemClickListener(new ListViewItemClickListener(this));
	}

	

	private void setListAdapter(ArrayAdapter<Mensaje> adapter) {
		// TODO Auto-generated method stub
		
	}
	




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chat, menu);
		return true;
	}

	public void enviarchat(View view) {
		Intent i = new Intent(this, Msje.class);
		this.startActivityForResult(i, REQUEST_CODE_ADD_MENSAJE);
	}

}
