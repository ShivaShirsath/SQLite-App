package ss.SQLiteApp;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.TextView.OnEditorActionListener;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.text.TextWatcher;
import android.text.Editable;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity {
	SQLiteDatabase db;
	MultiAutoCompleteTextView query;
	TextView tv;
	
//	String[] 
//		table = {"TableName"},
//		properties = {"id", "first_name", "last_name"}
//	;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		db = openOrCreateDatabase("dbName", Context.MODE_PRIVATE, null);
//		db.execSQL(
//			"CREATE TABLE IF NOT EXISTS " + table[0] + " (" +
//			properties[0] + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//			properties[1] + " VARCHAR NOT NULL," +
//			properties[2] + " VARCHAR NOT NULL" +
//			")"
//		);
		query = (MultiAutoCompleteTextView)findViewById(R.id.et);
		query.setTokenizer(new SpaceTokenizer());
		query.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.cammands)));
		
		tv = (TextView)findViewById(R.id.tv);
		
		query.addTextChangedListener(new TextWatcher(){
				@Override public void beforeTextChanged(CharSequence str, int s, int c, int a) {}

				@Override public void onTextChanged(CharSequence str, int s, int b, int e) {
					if(str.toString().contains(";")){
						tv.setText("");
						try{
							Cursor res = db.rawQuery(str.toString(), null);
							res.moveToFirst();
							while(!res.isAfterLast()){
								for (int i=0; i<res.getColumnCount(); i++) {	
									tv.append(res.getString(i) + " ");
								}
								tv.append("\n");
								res.moveToNext();
							}
						} catch (Exception ex){
							tv.setText(ex.getMessage());
						}
					}
				}

				@Override public void afterTextChanged(Editable e) {}
			});
			
		
			
	}
//	public void onInsert(View view) {
//		if (true ) {
//			showMessage("Error", "Please enter values");
//		} else {
//			db.execSQL(
//				"INSERT INTO " + table[0] + " (" + 
//				properties[1] + "," + 
//				properties[2] +  
//				") VALUES (" +
//				"'" + first_name.getText().toString() + "'," + 
//				"'" + last_name .getText().toString() + "'" +
//				")"
//			);
//			showMessage("Success", "Record added");
//			clearText();
//		}
//	}
//	public void onDelete(View view) {
//		if (
//			id.getText().toString().isEmpty()
//			) {
//			showMessage("Error", "Please enter id");
//		} else {
//			Cursor c=db.rawQuery("SELECT * FROM " + table[0] + " WHERE " + properties[0] + "=" + id.getText().toString() , null);
//			if (c.moveToFirst()) {
//				db.execSQL("DELETE FROM " + table[0] + " WHERE " + properties[0] +"=" + id.getText().toString() );
//				showMessage("Success", "Record Deleted");
//			} else {
//				showMessage("Error", "Invalid id");
//			}
//			clearText();
//		}
//	}
//	public void onUpdate(View view) {
//		if (
//			id.getText().toString().isEmpty()
//			) {
//			showMessage("Error", "Please enter id");
//		} else {
//			Cursor c=db.rawQuery("SELECT * FROM " + table[0] + " WHERE " + properties[0] + "=" + id.getText() , null);
//			if (c.moveToFirst()) {
//				db.execSQL(
//					"UPDATE " + table[0] + " SET " + 
//					properties[1] + "='" + first_name.getText().toString() + "'," +
//					properties[2] + "='" + first_name.getText().toString() + "' WHERE " + 
//					properties[0] + "=" + id.getText().toString() );
//				showMessage("Success", "Record Modified");
//			} else {
//				showMessage("Error", "Invalid id");
//			}
//			clearText();
//		}
//	}
//	public void onShow(View view) {
//		if (id.getText().toString().isEmpty()) {
//			showMessage("Error", "Please enter id");
//		} else {
//			Cursor c=db.rawQuery("SELECT * FROM " + table[0] + " WHERE " + properties[0] + "=" + id.getText().toString() , null);
//			if (c.moveToFirst()) {
//				first_name.setText(c.getString(1));
//				last_name.setText(c.getString(2));
//			} else {
//				showMessage("Error", "Invalid id");
//				clearText();
//			}
//		}
//	}
//	public void onShowAll(View view) {
//		Cursor c=db.rawQuery("SELECT * FROM " + table[0], null);
//		if (c.getCount() == 0) {
//			showMessage("Error", "No records found");
//		} else {
//			StringBuffer buffer=new StringBuffer();
//			while (c.moveToNext()) {
//				buffer.append("id         : " + c.getInt(0) + "\n");
//				buffer.append("First Name : " + c.getString(1) + "\n");
//				buffer.append("Last Name  : " + c.getString(2) + "\n\n");
//			}
//			showMessage("Details", buffer.toString());
//		}
//	}
//	public void showMessage(String title, String message) {
//		new Builder(this).setCancelable(true).setTitle(title).setMessage(message).show();
//	}
//	public void clearText() {
//		id.setText("");
//		first_name.setText("");
//		last_name.setText("");
//		id.requestFocus();
//	}
}

