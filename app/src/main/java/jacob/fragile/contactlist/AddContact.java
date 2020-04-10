package jacob.fragile.contactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddContact extends AppCompatActivity{

    public static final String AGE_MESSAGE =
            "com.jacob.fragile.contactlist.extra.MESSAGE";
    public static final String NAME_MESSAGE =
            "com.jacob.fragile.contactlist.extra.MESSAGE";
    public static final String COLOR_MESSAGE =
            "com.jacob.fragile.contactlist.extra.MESSAGE";


    private ArrayList<Contacts> mContacts;
    private EditText nameEdit;
    private EditText ageEdit;
    private Spinner colorSpinner;
    private ImageView imageSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        //Set the contact arraylist
        mContacts = new ArrayList<>();

        //Initialize the variables
        nameEdit = findViewById(R.id.nameEdit);
        ageEdit = findViewById(R.id.ageEdit);
        colorSpinner = findViewById(R.id.colorSpinner);
        imageSelect = findViewById(R.id.imageSelect);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colors, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        colorSpinner.setAdapter(adapter);

    }

    public void saveClick(View view) {
        String nameValue = nameEdit.getText().toString();
        String ageValue = ageEdit.getText().toString();
        String colorSpinnerValue = colorSpinner.getSelectedItem().toString();

       Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(NAME_MESSAGE, nameValue);
        intent.putExtra(AGE_MESSAGE, ageValue);
        intent.putExtra(COLOR_MESSAGE, colorSpinnerValue);
        startActivity(intent);

    }

    public void deleteClick(View view) {
    }
}
