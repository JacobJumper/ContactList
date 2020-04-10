package jacob.fragile.contactlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contacts> mContacts;
    private RecyclerView mRecyclerView;
    private ContactsAdapter mAdapter;

    //Declare lists to be filled with Data from Add Contact Activity
    public static ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> ageList = new ArrayList<>();
    public static ArrayList<String> colorList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        mContacts = new ArrayList<>();

        mAdapter = new ContactsAdapter(this, mContacts);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();

        //Listen for the intent
        Intent intent = getIntent();
        String nameValue = intent.getStringExtra(AddContact.NAME_MESSAGE);
        String ageValue = intent.getStringExtra(AddContact.AGE_MESSAGE);
        String colorValue = intent.getStringExtra(AddContact.COLOR_MESSAGE);

        nameList.add(nameValue);
        ageList.add(ageValue);
        colorList.add(colorValue);


    }

    private void initializeData() {


        // Get the data from the lists.
        String[] names = nameList.toArray(new String[nameList.size()]);
        String[] age = ageList.toArray(new String[ageList.size()]);
        String[] color = colorList.toArray(new String[colorList.size()]);
        TypedArray imageResources =
                    getResources().obtainTypedArray(R.array.images);
            // Clear the existing data (to avoid duplication).
            mContacts.clear();

            // Create the ArrayList of contacts  with names and
            // ages.
            for(int i=0;i<names.length;i++){
                mContacts.add(new Contacts(names[i],age[i], color[i], imageResources.getResourceId(i, 0)));
            }

            // Notify the adapter of the change.
            mAdapter.notifyDataSetChanged();
            //Recycle the typed array
            imageResources.recycle();

        }

    public void fabClick(View view) {
        Intent intent = new Intent(this, AddContact.class);
        startActivity(intent);
    }

}
