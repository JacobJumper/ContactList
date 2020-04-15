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
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Contacts> mContacts;
    private RecyclerView mRecyclerView;
    private ContactsAdapter mAdapter;
    private Context context;

    //Declare lists to be filled with Data from Add Contact Activity
    public static ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> ageList = new ArrayList<>();
    public static ArrayList<String> colorList = new ArrayList<>();
    public static ArrayList<Uri> imageList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = context;

        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mContacts = new ArrayList<>();

        mAdapter = new ContactsAdapter(this, mContacts);
        mRecyclerView.setAdapter(mAdapter);


        //TODO: Set sharedPref values for save/load

        //Listen for the intent
        Intent intent = getIntent();
        String nameValue = intent.getStringExtra(AddContact.NAME_MESSAGE);
        String ageValue = intent.getStringExtra(AddContact.AGE_MESSAGE);
        String colorValue = intent.getStringExtra(AddContact.COLOR_MESSAGE);
        // Convert the URI String back into an image
        Uri imageUri = intent.getParcelableExtra(AddContact.PHOTO_MESSAGE);
        //TODO: Import byteValue from intent

        nameList.add(nameValue);
        ageList.add(ageValue);
        colorList.add(colorValue);
        imageList.add(imageUri);
        Log.d(TAG, "nameValue = " + nameValue);

        // Run the method
        initializeData();

        // Save ArrayList
    }

    private void initializeData() {

        // Get the data from the lists.
        String[] names = nameList.toArray(new String[nameList.size()]);
        String[] age = ageList.toArray(new String[ageList.size()]);
        String[] color = colorList.toArray(new String[colorList.size()]);
        Uri [] images = imageList.toArray(new Uri[imageList.size()]);
            // Clear the existing data (to avoid duplication).
            mContacts.clear();

            // Create the ArrayList of contacts  with names and
            // ages.
            for(int i=0;i<names.length;i++){
                mContacts.add(new Contacts(names[i],age[i], color[i], images[i]));
            }

            // Notify the adapter of the change.
            mAdapter.notifyDataSetChanged();

        }

    public void fabClick(View view) {
        Intent intent = new Intent(this, AddContact.class);
        startActivity(intent);
    }

}
