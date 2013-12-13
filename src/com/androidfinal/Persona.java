package com.androidfinal;


import com.example.aci570_db.db.MyAppDataSource;
import com.example.aci570_db.model.Person;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Persona extends Activity {

	private MyAppDataSource ds;
	private Person personToUpdate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_persona);
		// Show the Up button in the action bar.
		setupActionBar();
		
		ds = new MyAppDataSource(this);
	    ds.open();
	    
	    Intent i = this.getIntent();
	    
	    if(i.hasExtra(Aplicacion.EXTRA_PERSON))
	    {
	    	Person p = (Person) i.getSerializableExtra(Aplicacion.EXTRA_PERSON);
	    	
	    	EditText firstNameField = (EditText) this.findViewById(R.id.firstNamePerson);
			firstNameField.setText(p.getFirstName());
			
			EditText lastNameField = (EditText) this.findViewById(R.id.lastNamePerson);
			lastNameField.setText(p.getLastName());
			
			EditText emailField = (EditText) this.findViewById(R.id.emailPerson);
			emailField.setText(p.getEmail());
			
			Button saveButton = (Button) this.findViewById(R.id.saveButton);
			saveButton.setText("Update");
			
			Button deleteButton = (Button) this.findViewById(R.id.deleteButton);
			
			deleteButton.setVisibility(Button.VISIBLE);
                        deleteButton.setText("Delete");
			
			this.setTitle("Update Person");
			
			this.personToUpdate = p;
	    }
	    else
	    {
	    	Button saveButton = (Button) this.findViewById(R.id.saveButton);
	    	saveButton.setText("Create");
	    	
	    	Button deleteButton = (Button) this.findViewById(R.id.deleteButton);
                deleteButton.setVisibility(Button.INVISIBLE);
	    	
	    	this.setTitle("Create Person");
	    	
	    	this.personToUpdate = null;
	    }
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	@SuppressLint("NewApi")
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.aplicacion, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void agregaramigo(View view) {
		EditText firstNameField = (EditText) this.findViewById(R.id.firstNamePerson);
		String firstName = firstNameField.getText().toString();
		
		EditText lastNameField = (EditText) this.findViewById(R.id.lastNamePerson);
		String lastName = lastNameField.getText().toString();
		
		EditText emailField = (EditText) this.findViewById(R.id.emailPerson);
		String email = emailField.getText().toString();
		
		if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty())
		{
			Toast.makeText(this, "Complete the form before saving", Toast.LENGTH_LONG).show();
			return;
		}
		
		Person p = null;
		
		if(this.personToUpdate != null)
		{
			p = ds.updatePerson(this.personToUpdate, firstName, lastName, email);
		}
		else
		{
			p = ds.createPerson(firstName, lastName, email);
		}
		
		Intent i = new Intent();
		i.putExtra(Aplicacion.EXTRA_PERSON, p);
		i.putExtra(Aplicacion.EXTRA_REMOVE, false);
		this.setResult(RESULT_OK, i);
		
		this.finish();
	}
	
	public void borraramigo(View view) {
		
		Person p = ds.deletePerson(this.personToUpdate);
		
		Intent i = new Intent();
		i.putExtra(Aplicacion.EXTRA_PERSON, p);
		i.putExtra(Aplicacion.EXTRA_REMOVE, true);
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
