package com.example.aci570_db.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.aci570_db.db.MyAppContract.Message;
import com.example.aci570_db.db.MyAppContract.People;
import com.example.aci570_db.model.Mensaje;
import com.example.aci570_db.model.Person;

public class MyAppDataSource {

	private MyAppDbHelper dbHelper;
	private SQLiteDatabase db;
	
	private String[] allColumns = {
		    People._ID,
		    People.COLUMN_NAME_FIRST_NAME,
		    People.COLUMN_NAME_LAST_NAME,
		    People.COLUMN_NAME_EMAIL
		    };
	private String[] allColumnsmessage = {
		    Message._ID,
		    Message.COLUMN_NAME_NOMBRE_MENSAJE,
		    };
	

	public MyAppDataSource(Context context) {
		this.dbHelper = new MyAppDbHelper(context);
	}
	
	public void open() throws SQLException {
		this.db = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}

	public Person createPerson(String firstName, String lastName, String email) {
		ContentValues values = new ContentValues();
		values.put(People.COLUMN_NAME_FIRST_NAME, firstName);
		values.put(People.COLUMN_NAME_LAST_NAME, lastName);
		values.put(People.COLUMN_NAME_EMAIL, email);
		
	    long insertId = db.insert(People.TABLE_NAME, null, values);
	    
	    Cursor c = db.query(
	    		People.TABLE_NAME,
	    		this.allColumns, People._ID + " = " + insertId, 
	    		null,
	    		null, 
	    		null, 
	    		null
	    	);
	    c.moveToFirst();
	    
	    Person p = cursorToPerson(c);
	    c.close();
	    
	    return p;
	}
	
	public Person updatePerson(Person p, String firstName, String lastName, String email) {
		ContentValues values = new ContentValues();
		values.put(People.COLUMN_NAME_FIRST_NAME, firstName);
		values.put(People.COLUMN_NAME_LAST_NAME, lastName);
		values.put(People.COLUMN_NAME_EMAIL, email);
		
	    db.update(People.TABLE_NAME, values, People._ID + " = " + p.getId(), null);
	    
	    p.setFirstName(firstName);
	    p.setLastName(lastName);
	    p.setEmail(email);
	    
	    return p;
	}
	
	public List<Person> getPeople() {
	    List<Person> people = new ArrayList<Person>();
	    
	    String sortOrder = People.COLUMN_NAME_FIRST_NAME + " DESC";
	    
	    Cursor c = db.query(
			    People.TABLE_NAME,	// The table to query
			    this.allColumns,			// The columns to return
			    null,				// The columns for the WHERE clause
			    null,				// The values for the WHERE clause
			    null,				// don't group the rows
			    null,				// don't filter by row groups
			    sortOrder			// The sort order
		    );

	    c.moveToFirst();
	    while (!c.isAfterLast()) {
	      Person p = cursorToPerson(c);
	      people.add(p);
	      c.moveToNext();
	    }
	    
	    // make sure to close the cursor
	    c.close();
	    
	    return people;
	}
	
	public Person deletePerson(Person p) {
	    long id = p.getId();
	    db.delete(People.TABLE_NAME, People._ID + " = " + id, null);
	    p.setId(0);
	    return p;
	}

	
	private Person cursorToPerson(Cursor cursor) {
		Person p = new Person();
	    p.setId(cursor.getLong(0));
	    p.setFirstName(cursor.getString(1));
	    p.setLastName(cursor.getString(2));
	    p.setEmail(cursor.getString(3));
	    return p;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	
	public Mensaje createMensaje(String nombreMensaje) {
		ContentValues values = new ContentValues();
		values.put(Message.COLUMN_NAME_NOMBRE_MENSAJE, nombreMensaje);
	
		
	    long insertId = db.insert(Message.TABLE_NAME, null, values);
	    
	    Cursor me = db.query(
	    		Message.TABLE_NAME,
	    		this.allColumnsmessage, People._ID + " = " + insertId, 
	    		null,
	    		null, 
	    		null, 
	    		null
	    	);
	    me.moveToFirst();
	    
	    Mensaje m = cursorToMsje(me);
	    me.close();
	    
	    return m;
	}
	
	
	public Mensaje updateMensaje(Mensaje m, String NombreMensaje) {
		ContentValues values = new ContentValues();
		values.put(People.COLUMN_NAME_FIRST_NAME, NombreMensaje);
		
	    db.update(People.TABLE_NAME, values, People._ID + " = " + m.getId(), null);
	    
	    m.setNombreMensaje(NombreMensaje);
	   
	    
	    return m;
	}
	
	public List<Mensaje> getMessage() {
	    List<Mensaje> mensaje = new ArrayList<Mensaje>();
	    
	    String sortOrder = Message.COLUMN_NAME_NOMBRE_MENSAJE + " DESC";
	    
	    Cursor me = db.query(
			    Message.TABLE_NAME,	// The table to query
			    this.allColumnsmessage,			// The columns to return
			    null,				// The columns for the WHERE clause
			    null,				// The values for the WHERE clause
			    null,				// don't group the rows
			    null,				// don't filter by row groups
			    sortOrder			// The sort order
		    );

	    me.moveToFirst();
	    while (!me.isAfterLast()) {
	      Mensaje m = cursorToMsje(me);
	      mensaje.add(m);
	      me.moveToNext();
	    }
	    
	    // make sure to close the cursor
	    me.close();
	    
	    return mensaje;
	}
	
	public Mensaje deleteMensaje(Mensaje m) {
	    long id = m.getId();
	    db.delete(Message.TABLE_NAME, Message._ID + " = " + id, null);
	    m.setId(0);
	    return m;
	}
	
	private Mensaje cursorToMsje(Cursor cursor) {
		Mensaje m = new Mensaje();
	    m.setId(cursor.getLong(0));
	    m.setNombreMensaje(cursor.getString(1));
	   return m;
	}
}
