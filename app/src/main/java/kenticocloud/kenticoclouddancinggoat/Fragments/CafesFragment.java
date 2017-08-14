package kenticocloud.kenticoclouddancinggoat.Fragments;

/**
 * Created by RichardS on 14. 8. 2017.
 */

import android.support.v4.app.Fragment;

        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import kenticocloud.kenticoclouddancinggoat.R;

public class CafesFragment extends Fragment {

    ListView cafesListView;

    // Defined Array values to show in ListView
    String[] values = new String[] {
            "Android List View",
            "Adapter implementation",
            "Simple List View In Android",
            "Create List View Android",
            "Android Example",
            "List View Source Code",
            "List View Array Adapter",
            "Android Example List View"
    };


    ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // get and prepare view
        View view = inflater.inflate(R.layout.fragment_cafes, container, false);

        // Get ListView object
        this.cafesListView = (ListView) view.findViewById(R.id.cafesListView);

        // set adapter
        this.adapter = new ArrayAdapter<String>(getActivity(), R.layout.layout_cafes_list, R.id.myTextView, values);

        // apply adapter on cafe's list
        this.cafesListView.setAdapter(this.adapter);

        //returning layout
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle(R.string.main_menu_cafes);
    }


}