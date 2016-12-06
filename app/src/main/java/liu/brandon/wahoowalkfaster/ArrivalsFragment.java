package liu.brandon.wahoowalkfaster;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Brandon on 11/30/16.
 */

public class ArrivalsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_arrivals, container, false);
    }

    public void displayInfo() {

    }
}
