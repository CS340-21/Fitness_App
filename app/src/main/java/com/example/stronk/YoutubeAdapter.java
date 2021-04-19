package com.example.stronk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.ViewHolder> {
    /*
        Variable Declarations
     */
    private ArrayList<String> keys;
    private final Context context;
    Map<String, ?> allEntries;

    /* Constructor for Youtube Recyclerview adapter */
    public YoutubeAdapter(Context context, ArrayList<String> keys) {
        this.context = context;
        this.keys = keys;
    }

    /* Sets the correct card view for the recyclerview */
    @NonNull
    @Override
    public YoutubeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.linkcard, parent, false);
        return new ViewHolder(view);
    }

    /* Sets the correct link name in the recycler view */
    @Override
    public void onBindViewHolder(YoutubeAdapter.ViewHolder holder, final int position) {
        SharedPreferences sp = context.getSharedPreferences("Links", MODE_PRIVATE);
        final SharedPreferences.Editor editor = sp.edit();
        allEntries = sp.getAll();

        /* If the save name is clicked then the link will be opened. */
        holder.textView.setText(keys.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = (String) allEntries.get(keys.get(position));
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                Toast.makeText(context, "Clicked on " + url, Toast.LENGTH_SHORT).show();
            }
        });

        /* If the delete button is pressed then that link will be deleted */
        holder.delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.remove(keys.get(position));
                keys.remove(position);
                allEntries.remove(position);
                editor.apply();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return keys.size();
    }

    /* Sets the Recyclerview to look at the right components */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Button delButton;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.contact_name);
            delButton = itemView.findViewById(R.id.button3);
        }
    }
}
