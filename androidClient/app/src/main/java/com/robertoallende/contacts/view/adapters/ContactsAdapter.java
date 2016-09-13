package com.robertoallende.contacts.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.robertoallende.contacts.R;
import com.robertoallende.contacts.entities.User;
import com.robertoallende.contacts.view.ContactsActivity;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ContactsAdapter extends ArrayAdapter<User> {

    private final ContactsActivity context;
    private final  List<User> values;

    public ContactsAdapter(ContactsActivity context, List<User> users) {
        super(context, -1, users);
        this.context = context;
        this.values = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_contact, parent, false);

        if (rowView == null || values == null) {
            return rowView;
        }

        TextView textView = (TextView) rowView.findViewById(R.id.contact_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.contact_photo);

        final User user = values.get(position);
        String fullName = user.name.toString();
        String imageUrl = user.picture.large;

        textView.setText(fullName);
        Picasso.with(context).load(imageUrl).into(imageView);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.openPersonActivity(user);
            }
        });

        return rowView;
    }
}