package com.example.srishtishikhar.appointmentmanager;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DoctorHomepage extends AppCompatActivity {
 appointmentsDB db = new appointmentsDB(this);
 Cursor cursor;
 ArrayList<Appointments> lists = new ArrayList<Appointments>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_homepage);
        Bundle bundle=getIntent().getExtras();
        String id = bundle.getString("id");
        cursor = db.customQuery(id);
          while(cursor.moveToNext()){
              String a = cursor.getString(0);
              String b = cursor.getString(1);
              String c = cursor.getString(2);
              String d = cursor.getString(3);
              String f = cursor.getString(4);
              int g = cursor.getInt(5);
              int h = cursor.getInt(6);
              Appointments n = new Appointments(a,b,c,d,f,g,h);
              lists.add(n);
          }
          homepageCustomAdapter ca = new homepageCustomAdapter(DoctorHomepage.this,lists);
          ListView lv = (ListView) findViewById(R.id.doctor_homepage_listview);
          lv.setAdapter(ca);
          lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
              @Override
              public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                  db.deleteData(lists.get(position).PatId);
                  lists.remove(position);
                  homepageCustomAdapter ca = new homepageCustomAdapter(DoctorHomepage.this,lists);
                  ListView lv = (ListView) findViewById(R.id.doctor_homepage_listview);
                  lv.setAdapter(ca);
                  return true;
              }
          });
    }
}
