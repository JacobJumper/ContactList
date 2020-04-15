package jacob.fragile.contactlist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okio.Utf8;


public class AddContact extends AppCompatActivity{

    // Bundle messages
    private static final String TAG = "MyActivity";
    private Context mContext;
    public static final String AGE_MESSAGE =
            "com.jacob.fragile.contactlist.extra.NAME_MESSAGE";
    public static final String NAME_MESSAGE =
            "com.jacob.fragile.contactlist.extra.AGE_MESSAGE";
    public static final String COLOR_MESSAGE =
            "com.jacob.fragile.contactlist.extra.COLOR_MESSAGE";
    public static final String PHOTO_MESSAGE =
            "com.jacob.fragile.contactlist.extra.PHOTO_MESSAGE";


    // Gallery Load static variable
    private static int RESULT_LOAD_IMAGE = 100;
    Uri imageUri;

    //TODO: Convert image as bytecode and send with activity

    private EditText nameEdit;
    private EditText ageEdit;
    private Spinner colorSpinner;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        //Initialize the variables
        nameEdit = findViewById(R.id.nameEdit);
        ageEdit = findViewById(R.id.ageEdit);
        colorSpinner = findViewById(R.id.colorSpinner);
        imageView = findViewById(R.id.imageSelect);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.colors, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        colorSpinner.setAdapter(adapter);

        // Set onClick listener for images
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }

        });
    }

    // Open gallery method
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMAGE) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);

        }
    }

    public void saveClick(View view) {

        // Take the editText values and convert to usable strings
        String nameValue = nameEdit.getText().toString();
        String ageValue = ageEdit.getText().toString();
        String colorSpinnerValue = colorSpinner.getSelectedItem().toString();

        // Bundle usable strings and send to MainActivity
       Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(NAME_MESSAGE, nameValue);
        intent.putExtra(AGE_MESSAGE, ageValue);
        intent.putExtra(COLOR_MESSAGE, colorSpinnerValue);
        intent.putExtra(PHOTO_MESSAGE, imageUri.toString());

        Log.d(TAG, "nameValue = " + nameValue);
        startActivity(intent);

    }

    public void deleteClick(View view) {
    }

    public void newImage(View view) {
    }
}
