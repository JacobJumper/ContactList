package jacob.fragile.contactlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    public static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Contacts> mContacts;
    private Context mContext;

    public ContactsAdapter(Context context, ArrayList<Contacts> contacts) {
        this.mContacts = contacts;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Log.d(TAG,"On viewHolder: called");
    Contacts contacts = mContacts.get(position);
    
    holder.bindTo(contacts);
    }

    @Override
    public int getItemCount() {return mContacts.size();}

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name;
        TextView age;
        TextView color;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.contact_image);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            color = itemView.findViewById(R.id.color);
        }

        void bindTo(Contacts contacts) {
            //Set the text views
            name.setText(contacts.getName());
            age.setText(contacts.getAge());
            color.setText(contacts.getColor());
            //Load in the images
            Glide.with(mContext).load(contacts.getImageResource()).into(imageView);

        }
    }
}
