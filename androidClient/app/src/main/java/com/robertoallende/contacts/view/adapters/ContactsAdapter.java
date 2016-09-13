package com.robertoallende.contacts.view.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.robertoallende.contacts.R;
import com.robertoallende.contacts.entities.User;
import com.robertoallende.contacts.view.ContactListActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class ContactsAdapter extends ArrayAdapter<User> {

    private final ContactListActivity context;
    private final  List<User> values;

    public ContactsAdapter(ContactListActivity context, List<User> users) {
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
        textView.setText(fullName);

        if (user.picture != null) {
            String imageUrl = "";
            if (! user.picture.large.isEmpty()) {
                imageUrl = user.picture.large;
            } else {
                imageUrl = "http://impactspace.com/images/uploads/person-default.png";
            }

            Transformation transformation = new RoundedTransformationBuilder()
                    .borderColor(Color.WHITE)
                    .borderWidthDp(3)
                    .cornerRadiusDp(30)
                    .oval(false)
                    .build();

            Picasso.with(context).load(imageUrl).transform(transformation).into(imageView);
        }

        /* rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.openPersonActivity(user);
            }
        });
        */
        return rowView;
    }

    public User getUser(int position) {
        return values.get(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyDataSetChanged();
    }
}