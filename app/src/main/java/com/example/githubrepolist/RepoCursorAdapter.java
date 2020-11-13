package com.example.githubrepolist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class RepoCursorAdapter extends CursorAdapter {

    static class ViewHolder {
        final TextView repoName;
        final TextView repoLanguage;
        final TextView repoStargazers;

        ViewHolder(View view) {
            repoName = view.findViewById(R.id.repo_list_name);
            repoLanguage = view.findViewById(R.id.repo_list_language);
            repoStargazers = view.findViewById(R.id.repo_list_star_count);
        }
    }

    public RepoCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.repo_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

        viewHolder.repoName.setText(cursor.getString(RepoListActivityFragment.COL_NAME));
        viewHolder.repoLanguage.setText(cursor.getString(RepoListActivityFragment.COL_LANGUAGE));
        viewHolder.repoStargazers.setText(Integer.toString(cursor.getInt(RepoListActivityFragment.COL_STARGAZERS)));

    }
}
