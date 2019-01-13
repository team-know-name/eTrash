package mlexpert.tanishqsaluja.etrash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment, container, false);
        int i = getArguments().getInt("POS");
        if (i == 0) {
            ((ImageView) view.findViewById(R.id.image)).setImageResource(R.drawable.ss1);
        } else if (i == 1) {
            ((ImageView) view.findViewById(R.id.image)).setImageResource(R.drawable.ss2);
        } else if (i == 2) {
            ((ImageView) view.findViewById(R.id.image)).setImageResource(R.drawable.ss3);
        }
        return view;
    }
}
