package id.ac.binus.wiktionic.Adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ac.binus.wiktionic.Models.Phonetics;
import id.ac.binus.wiktionic.R;
import id.ac.binus.wiktionic.ViewHolders.PhoneticsViewHolder;

public class PhoneticsAdapter extends RecyclerView.Adapter<PhoneticsViewHolder> {

    private Context context;
    private List<Phonetics> phoneticsList;

    public PhoneticsAdapter(Context context, List<Phonetics> phoneticsList) {
        this.context = context;
        this.phoneticsList = phoneticsList;
    }

    @NonNull
    @Override
    public PhoneticsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhoneticsViewHolder(LayoutInflater.from(context).inflate(R.layout.phonetics_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(PhoneticsViewHolder holder, int position) {
        holder.textView_phonetics.setText(phoneticsList.get(position).getText());
        holder.imageButton_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MediaPlayer player = new MediaPlayer();
                try{
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource(phoneticsList.get(position).getAudio());
                    player.prepare();
                    player.start();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(context, "Couldn't play audio", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return phoneticsList.size();
    }
}
