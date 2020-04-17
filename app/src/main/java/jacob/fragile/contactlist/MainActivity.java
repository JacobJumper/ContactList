package jacob.fragile.contactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "RecyclerViewAdapter";

    private ArrayList mContacts;
    private RecyclerView mRecyclerView;
    private ContactsAdapter mAdapter;
    private Context mContext;

    //Declare lists to be filled with Data from Add Contact Activity
    public static ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> ageList = new ArrayList<>();
    public static ArrayList<String> colorList = new ArrayList<>();
    public static ArrayList<String> imageList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = mContext;
        onLoad();
        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mContacts = new ArrayList<>();

        // Set the adapter
        mAdapter = new ContactsAdapter(this, mContacts);
        mRecyclerView.setAdapter(mAdapter);

        // Set the visibility of cards when lists contain no data

        //Listen for the intent
        Intent intent = getIntent();
        String nameValue = intent.getStringExtra(AddContact.NAME_MESSAGE);
        String ageValue = intent.getStringExtra(AddContact.AGE_MESSAGE);
        String colorValue = intent.getStringExtra(AddContact.COLOR_MESSAGE);
        String imageString = intent.getStringExtra(AddContact.PHOTO_MESSAGE);
        // Add values to ArrayLists
        nameList.add(nameValue);
        ageList.add(ageValue);
        colorList.add(colorValue);
        imageList.add(imageString);

        Log.d(TAG, "nameValue = " + nameValue);

        // Run the method
        initializeData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onSave();
    }

    private void onSave(){
        SharedPreferences prefs = getSharedPreferences("List", Context.MODE_PRIVATE);
        //save the user list to preference
        SharedPreferences.Editor editor = prefs.edit();
        try {
            editor.putString("ContactList", ObjectSerializer.serialize(mContacts));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();

    }
    private void onLoad(){
    // Load user List from preferences
        SharedPreferences prefs = getSharedPreferences("User", Context.MODE_PRIVATE);
        try {
            mContacts = (ArrayList) ObjectSerializer.deserialize(prefs.getString("ContactList", ObjectSerializer.serialize(new ArrayList())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void initializeData() {

        // Get the data from the lists.
        String[] names = nameList.toArray(new String[nameList.size()]);
        String[] age = ageList.toArray(new String[ageList.size()]);
        String[] color = colorList.toArray(new String[colorList.size()]);
        String [] images = imageList.toArray(new String[imageList.size()]);
            // Clear the existing data (to avoid duplication).
            mContacts.clear();

            // Create the ArrayList of contacts  with names and
            // ages.
            for(int i=0;i<names.length;i++){
                mContacts.add(new Contacts(names[i], age[i], color[i], images[i]));
            }

            // Notify the adapter of the change.
            mAdapter.notifyDataSetChanged();
            mRecyclerView.setVisibility(View.VISIBLE);

        }

    public void fabClick(View view) {
        Intent intent = new Intent(this, AddContact.class);
        startActivity(intent);
    }


}
