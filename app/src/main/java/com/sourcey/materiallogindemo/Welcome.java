package com.sourcey.materiallogindemo;

/**
 * Created by shobh on 9/27/2016.
 */
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Welcome extends ListActivity
{


    protected TextView eun;
    protected SQLiteDatabase DB;
    protected Cursor cursor;
    protected ListAdapter adapter;
    protected TextView mUname;
    protected TextView mFname;
    protected TextView mAddress;
    protected TextView mEmail;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);


        DB = (new DBHelper(this)).getWritableDatabase();
        // searchText = (EditText) findViewById (R.id.searchText);

        eun = (TextView)findViewById(R.id.textV);
        Bundle bundle = getIntent().getExtras();

        String UName = bundle.getString("UserName");

        eun.setText(UName);



    }

    public void search(View view)
    {
        cursor = DB.rawQuery("SELECT _id, firstname, address, email, gender, username FROM sri1 WHERE username LIKE ?",
                new String[] {"%" + eun.getText().toString() + "%"});
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.dtl,
                cursor,
                new String[] {"firstname", "address", "email", "gender","username"},
                new int[] {R.id.sfname, R.id.saddress, R.id.semail, R.id.sgender , R.id.suname});


        setListAdapter(adapter);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent i = new Intent(Welcome.this, LoginActivity.class);
        startActivity(i);
    }
}