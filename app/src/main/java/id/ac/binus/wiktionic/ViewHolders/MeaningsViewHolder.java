package id.ac.binus.wiktionic.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.binus.wiktionic.R;

public class MeaningsViewHolder extends RecyclerView.ViewHolder {

    public TextView textView_partOfSpeech;
    public RecyclerView recyclerView_definitions;
    public MeaningsViewHolder(View itemView) {
        super(itemView);
        textView_partOfSpeech = itemView.findViewById(R.id.textView_partOfSpeech);
        recyclerView_definitions = itemView.findViewById(R.id.recycler_definitions);
    }
}
