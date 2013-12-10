package com.example.aci570_db.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.androidfinal.Aplicacion;
import com.androidfinal.Chat;
import com.androidfinal.Persona;
import com.example.aci570_db.model.Mensaje;
import com.example.aci570_db.model.Person;

public class ListViewItemClickListener implements AdapterView.OnItemClickListener {

	private Activity activity;
	
	public ListViewItemClickListener(Activity activity) {
		this.activity = activity;
	}

	
	
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Person p = (Person) parent.getItemAtPosition(position);
		
		if(p != null)
		{
			Intent i = new Intent(this.activity, Persona.class);
			i.putExtra("person", p);
			this.activity.startActivityForResult(i,Aplicacion.REQUEST_CODE_ADD_PERSON);			
		}
	}
	
	public void onItemClick2(AdapterView<?> parent, View view, int position, long id) {
		Mensaje m = (Mensaje) parent.getItemAtPosition(position);
		
		if(m != null)
		{
			Intent in = new Intent(this.activity, Mensaje.class);
			in.putExtra("mensaje", m);
			this.activity.startActivityForResult(in, Chat.REQUEST_CODE_ADD_MENSAJE);			
		}
	}
}
