package id.ac.binus.wiktionic.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ac.binus.wiktionic.Models.Definitions;
import id.ac.binus.wiktionic.R;
import id.ac.binus.wiktionic.ViewHolders.DefinitionsViewHolder;

public class DefinitionsAdapter extends RecyclerView.Adapter<DefinitionsViewHolder> {

    private Context context;
    private List<Definitions> definitionsList;

    public DefinitionsAdapter(Context context, List<Definitions> definitionsList) {
        this.context = context;
        this.definitionsList = definitionsList;
    }

    @Override
    public DefinitionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DefinitionsViewHolder(LayoutInflater.from(context).inflate(R.layout.definitions_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(DefinitionsViewHolder holder, int position) {
        holder.textView_definition.setText("Definitions: " + definitionsList.get(position).getDefinitions());
        Toast.makeText(context, definitionsList.get(position).getDefinitions(), Toast.LENGTH_SHORT).show();
        holder.textView_example.setText("Example: " + definitionsList.get(position).getExample());
        StringBuilder synonyms = new StringBuilder();
        StringBuilder antonyms = new StringBuilder();

        synonyms.append(definitionsList.get(position).getSynonyms());
        antonyms.append(definitionsList.get(position).getAntonyms());

        holder.textView_synonyms.setText(synonyms);
        holder.textView_antonyms.setText(antonyms);

        holder.textView_synonyms.setSelected(true);
        holder.textView_antonyms.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return definitionsList.size();
    }
}
