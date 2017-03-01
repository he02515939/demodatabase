package com.example.timmy.demodatabae;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText e_name,e_password,e_contact,e_country;
    String name,password,contact,country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        e_name =(EditText) findViewById(R.id.name);
        e_password=(EditText)findViewById(R.id.password);
        e_contact=(EditText)findViewById(R.id.contact);
        e_country=(EditText)findViewById(R.id.country);





    }
  public void reguser(View view){
      name=e_name.getText().toString();
      password=e_password.getText().toString();
      contact=e_contact.getText().toString();
      country=e_country.getText().toString();
      String method="register";
      BackgroundTask backgroundTask=new BackgroundTask(this);
      backgroundTask.execute(method,name,password,contact,country);//AsyncTask 提供了 execute 方法來執行(觸發)非同步工作
      finish();

  }
}
