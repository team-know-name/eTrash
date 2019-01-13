package mlexpert.tanishqsaluja.etrash;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class greenAdapter extends RecyclerView.Adapter<greenAdapter.greenHolder> {

    private ArrayList<Contribution> characters = new ArrayList<Contribution>();

    greenAdapter(ArrayList<Contribution> contributions) {
        this.characters = contributions;
    }

    @NonNull
    @Override
    public greenAdapter.greenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.contri_row, parent, false);

        return new greenHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull greenAdapter.greenHolder holder, int position) {
        Contribution c = characters.get(position);
        holder.iv.setImageResource(c.getImage());
        holder.score.setText("" + c.getScore());
        holder.tv.setText(c.getStr()+"");
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public class greenHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv, score;

        public greenHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.image);
            tv = itemView.findViewById(R.id.text);
            score = itemView.findViewById(R.id.score);
        }
    }
}
