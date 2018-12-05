package com.pintarngoding.cataloguemovie;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movie>> {

    ListView listView;
    MovieAdapter movieAdapter;
    EditText editTextCari;
    Button buttonCari;

    public static final String EXTRA_DETAIL_MOVIE = "extra_detail_movie";
    static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieAdapter = new MovieAdapter(this);
        movieAdapter.notifyDataSetChanged();

        listView = findViewById(R.id.listViewMovie);
        editTextCari = findViewById(R.id.editTextCari);

        buttonCari = findViewById(R.id.buttonCari);
        buttonCari.setOnClickListener(myListener);

        String cari = "hulk";

        listView.setAdapter(movieAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainActivity.this, ActivityDetailMovie.class);
                intent.putExtra(EXTRA_DETAIL_MOVIE, movieAdapter.getItem(position));
                startActivity(intent);
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_MOVIE, cari);

        getLoaderManager().initLoader(0, bundle, this);
    }

    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int i, Bundle bundle) {
        String title = "";
        if (bundle != null){
            title = bundle.getString(EXTRA_MOVIE);
        }
        return new MyAsyncTaskLoader(this, title);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Movie>> loader, ArrayList<Movie> movies) {
        movieAdapter.setData(movies);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Movie>> loader) {
        movieAdapter.setData(new ArrayList<Movie>());
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String title = editTextCari.getText().toString();
            if (TextUtils.isEmpty(title))return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_MOVIE, title);
            getLoaderManager().restartLoader(0, bundle, MainActivity.this);
        }
    };
}
