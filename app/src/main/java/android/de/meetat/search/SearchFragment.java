package android.de.meetat.search;


import android.app.SearchManager;
import android.content.Context;
import android.de.meetat.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener{
    private SearchView searchView;

    public SearchFragment() {
        // Required empty public constructor
    }

        ListView listView ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rooView =inflater.inflate(R.layout.fragment_search, container, false);
        setHasOptionsMenu(true);
        listView = (ListView)rooView.findViewById(R.id.my_search_list_view);
        listView.setAdapter(new ListViewAdapter(getActivity().getApplicationContext()));
        return rooView;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        }
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("query",query);
        return false;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        Log.d("new text",newText);
        return false;
    }
}
