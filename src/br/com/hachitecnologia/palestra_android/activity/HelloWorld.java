package br.com.hachitecnologia.palestra_android.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import br.com.hachitecnologia.palestra_android.R;

public class HelloWorld extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_hello_world, menu);
        return true;
    }

    
}
