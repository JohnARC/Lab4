package com.arc.me.activitylifecyclepratice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.net.URI;


public class PracticeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        this.pickPhoto();
//        Uri uri = Uri.parse("tel:1234567890");
//        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
//        this.startActivity(intent);
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


public void  sendMessage(View view) {
    EditText editText = (EditText) findViewById(R.id.edit_message);
    String addressString = editText.getText().toString();
    Log.d(this.getClass().getName(), "Address is: " + addressString);

    //prepend the protocol and more.
   String uriString = "geo:0,0?q=" + addressString;

    Log.d(this.getClass().getName(), "URI is: " + uriString);


//Replace all spaces with Plus
    uriString.replace(" ","+");

    Uri uri = Uri.parse(uriString);
    Intent intent = new Intent(Intent.ACTION_DIAL, uri);

    //this.startActivity(intent);

    //display a map
    uri =Uri.parse("geo:0,0?q=300+Jay+St,+Brooklyn,NY");
    intent = new Intent(Intent.ACTION_VIEW, uri);

    //Start activity
    this.startActivity(intent);
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

    // 5/4/15 Code


    private enum RequestCode{
        PICK_PHOTO_REQUEST;
        PICK_CONTACT_REQUEST;
    }


private void pickContact(){
    Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
    pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
}

    private void pickPhoto() {
        Intent photoIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(photoIntent, RequestCode.PICK_PHOTO_REQUEST.ordinal());
        ContactsContract.Contacts.CONTENT_URI;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(RequestCode == RequestCode.PICK_PHOTO_REQUEST.ordinal()){
            Uri uri = data.getData();
            Log.d("MyActivity", uri.toString());
        }
            //else if
        }
    }

}
