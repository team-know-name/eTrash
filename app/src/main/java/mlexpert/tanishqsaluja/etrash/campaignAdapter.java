package mlexpert.tanishqsaluja.etrash;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class campaignAdapter extends RecyclerView.Adapter<campaignAdapter.campaignHolder> {

    ArrayList<Item> arrayList;

    campaignAdapter(ArrayList<Item> list) {
        this.arrayList = list;
    }

    @NonNull
    @Override
    public campaignAdapter.campaignHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new campaignAdapter.campaignHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull campaignAdapter.campaignHolder holder, int position) {
        Item data = arrayList.get(position);
        holder.category.setText(data.getGarbage_category());
        holder.price.setText(data.getPrice());
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class campaignHolder extends RecyclerView.ViewHolder {
        TextView category, price;

        public campaignHolder(View view) {
            super(view);
            category = view.findViewById(R.id.category);
            price = view.findViewById(R.id.price);

        }
    }

}

