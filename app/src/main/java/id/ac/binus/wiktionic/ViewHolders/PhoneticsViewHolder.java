package id.ac.binus.wiktionic.ViewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.binus.wiktionic.R;

public class PhoneticsViewHolder extends RecyclerView.ViewHolder {

    public TextView textView_phonetics;
    public ImageButton imageButton_audio;

    public PhoneticsViewHolder(View itemView) {
        super(itemView);
        textView_phonetics = itemView.findViewById(R.id.textView_phonetics);
        imageButton_audio = itemView.findViewById(R.id.imageButton_audio);
    }
}
