package com.arc.me.activitylifecyclepratice;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class PracticeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //Lesson Addition
    @Override
    public void onDestroy() {
        super.onDestroy();  // Always call the superclass

        // Stop method tracing that the activity started during onCreate()
        android.os.Debug.stopMethodTracing();
    }
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences sharedpref =
                this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        if(sharedpref.contains(getString(R.string.level_key))){
            Log.d("PracticeActivity", "We already have the level. Not saving.");
        }
        else {
            SharedPreferences.Editor editor = sharedpref.edit();
            editor.putInt(getString(R.string.level_key),6);
            editor.commit();
        }


if (sharedpref.contains(getString(R.string.best_user_key))){
    Log.d("PracticeActivity", "We already have the best user. not saving");
}
else {
    SharedPreferences activityPref = this.getPreferences(Context.MODE_PRIVATE);
    activityPref.edit().putString(getString(R.string.best_user_key),"Bob").commit();
    //Change bob to "this.editText.getText().toString()" when text box added
}



    }
@Override
    protected void onResume() {
    super.onResume();

    SharedPreferences sharedPref =
            this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    int level = sharedPref.getInt(getString(R.string.level_key), 0 );
    Log.d("PracticeActivity", "level" + level);

    String bestUSer = this.getPreferences(Context.MODE_PRIVATE).getString(getString(R.string.best_user_key),"none");


}
}
