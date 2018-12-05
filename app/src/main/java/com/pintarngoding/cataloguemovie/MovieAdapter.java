package com.pintarngoding.cataloguemovie;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.security.auth.login.LoginException;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<Movie> arrayListMovie = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<Movie> arrayListMovie){
        this.arrayListMovie.addAll(arrayListMovie);
        notifyDataSetChanged();
    }

    public void addItem(final Movie movie){
        arrayListMovie.add(movie);
        notifyDataSetChanged();
    }

    public void clearData(){
        arrayListMovie.clear();
    }
    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (arrayListMovie == null) return 0;
        return arrayListMovie.size();
    }

    @Override
    public Movie getItem(int position) {
        return arrayListMovie.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder{
        TextView textViewTitle;
        TextView textViewOverview;
        TextView textViewReleaseDate;
        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("TESMOVIE", "test getView");
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.movie_items, null);
            holder.textViewTitle = convertView.findViewById(R.id.tvTitle);
            holder.textViewOverview = convertView.findViewById(R.id.tvOverview);
            holder.textViewReleaseDate = convertView.findViewById(R.id.tvReleaseDate);
            holder.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.d("TESTMOVIE", "masuk adapter");

        holder.textViewTitle.setText(arrayListMovie.get(position).getTitle());
        holder.textViewOverview.setText(arrayListMovie.get(position).getOverview());

        try {
            String tanggal = arrayListMovie.get(position).getRelease_date();
            DateFormat dfasli = new SimpleDateFormat("yyyy-mm-dd");
            Date date = dfasli.parse(tanggal);
            DateFormat dftujuan = new SimpleDateFormat("EEE, MMM dd, yyyy");

            tanggal = dftujuan.format(date);
            holder.textViewReleaseDate.setText(tanggal);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Glide.with(context)
                .load(arrayListMovie.get(position).getPoster())
                .into(holder.imageView);

        return convertView;
    }
}
