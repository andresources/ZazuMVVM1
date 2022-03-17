package com.zazumvvm_java;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.zazumvvm_java.di.MyComponent;
import com.zazumvvm_java.di.SharedPrefModule;
import com.zazumvvm_java.di.UserModule;
import com.zazumvvm_java.model.Employee;
import com.zazumvvm_java.room.entities.Note;
import com.zazumvvm_java.view_model.ArticleViewModel;

import java.util.List;

import javax.inject.Inject;

public class LoginDIActivity  extends AppCompatActivity {
    EditText etUserName,etPwd;
    private MyComponent myComponent;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    Employee emp;

    ArticleViewModel articleViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserName = (EditText)findViewById(R.id.etUserName);
        etPwd = (EditText)findViewById(R.id.etPwd);
        /*myComponent = DaggerMyComponent.builder().sharedPrefModule(new SharedPrefModule(this)).userModule(new UserModule("Hariiii")).build();
        myComponent.inject(LoginDIActivity.this);*/
        MyApp.app().appComponent().inject(this);

        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        articleViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
               Toast.makeText(getApplicationContext(),""+notes.size(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void saveData(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", etUserName.getText().toString().trim());
        editor.putString("pwd", etPwd.getText().toString().trim());
        editor.apply();
    }

    public void getData(View view) {
        etUserName.setText(sharedPreferences.getString("username", "default"));
        etPwd.setText(sharedPreferences.getString("pwd", "12345"));
    }

    public void getGragger(View view) {
        String str=emp.getName();
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
    }

    public void roomOPt(View view) {
        articleViewModel.addNote(new Note("Title", "Description"));
    }
}
