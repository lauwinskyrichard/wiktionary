package id.ac.binus.wiktionic.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ac.binus.wiktionic.Models.Meanings;
import id.ac.binus.wiktionic.R;
import id.ac.binus.wiktionic.ViewHolders.MeaningsViewHolder;

public class MeaningsAdapter extends RecyclerView.Adapter<MeaningsViewHolder> {

    private Context context;
    protected List<Meanings> meaningsList;

    public MeaningsAdapter(Context context, List<Meanings> meaningsList) {
        this.context = context;
        this.meaningsList = meaningsList;
    }

    @Override
    public MeaningsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MeaningsViewHolder(LayoutInflater.from(context).inflate(R.layout.meanings_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(MeaningsViewHolder holder, int position) {
        holder.textView_partOfSpeech.setText("Parts of Speech: " + meaningsList.get(position).getPartOfSpeech());
        holder.recyclerView_definitions.setHasFixedSize(true);
        holder.recyclerView_definitions.setLayoutManager(new GridLayoutManager(context, 1));

        DefinitionsAdapter definitionsAdapter = new DefinitionsAdapter(context, meaningsList.get(position).getDefinitions());
        holder.recyclerView_definitions.setAdapter(definitionsAdapter);
    }

    @Override
    public int getItemCount() {
        return meaningsList.size();
    }
}
