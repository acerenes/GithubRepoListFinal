package com.example.githubrepolist.fragments;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.example.githubrepolist.R;
import com.example.githubrepolist.data.RepoContract;


/**
 * Details fragment.
 */
public class RepoDetailsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String ARG_LOGIN_ID = "login_id";
    public static final String ARG_REPO_NAME = "repo_name";

    private static final int LOADER_ID = 2;

    public static final String[] PROJECTION = {
            RepoContract.RepoEntry._ID,
            RepoContract.RepoEntry.COLUMN_NAME,
            RepoContract.RepoEntry.COLUMN_LOGIN,
            RepoContract.RepoEntry.COLUMN_DESCRIPTION,
            RepoContract.RepoEntry.COLUMN_LANGUAGE,
            RepoContract.RepoEntry.COLUMN_STARGAZERS

    };

    public static final int COL_ID = 0;
    public static final int COL_NAME = 1;
    public static final int COL_LOGIN = 2;
    public static final int COL_DESCRIPTION = 3;
    public static final int COL_LANGUAGE = 4;
    public static final int COL_STARGAZERS = 5;

    private String mLoginId;
    private String mRepoName;
    private View mRootView;

    private Cursor mCursor;


    public RepoDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(ARG_LOGIN_ID)) {

            mLoginId = getArguments().getString(ARG_LOGIN_ID);
            mRepoName = getArguments().getString(ARG_REPO_NAME);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_repo_details, container, false);

        return mRootView;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getLoaderManager().initLoader(LOADER_ID, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {

        String sortOrder=null;

        Uri repoUri = RepoContract.RepoEntry.buildDetailsUri(mLoginId,mRepoName);

        return new CursorLoader(getActivity(),
                repoUri,
                PROJECTION,
                null,
                null,
                sortOrder);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mCursor=data;
        bindViews(mCursor);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @SuppressLint("SetTextI18n")
    private void bindViews(Cursor cursor) {
        if (mRootView == null) {
            return;
        }

        if(cursor!=null)
            cursor.moveToFirst();

        TextView repoNameView = mRootView.findViewById(R.id.repo_details_name);
        TextView ownerView = mRootView.findViewById(R.id.repo_details_owner);
        TextView descriptionView = mRootView.findViewById(R.id.repo_details_description);
        TextView languageView = mRootView.findViewById(R.id.repo_details_language);
        TextView stargazerView = mRootView.findViewById(R.id.repo_details_star_count);

        repoNameView.setText(cursor.getString(RepoDetailsFragment.COL_NAME));
        ownerView.setText(cursor.getString(RepoDetailsFragment.COL_LOGIN));
        descriptionView.setText(cursor.getString(RepoDetailsFragment.COL_DESCRIPTION));
        languageView.setText(cursor.getString(RepoDetailsFragment.COL_LANGUAGE));
        stargazerView.setText(Integer.toString(cursor.getInt(RepoDetailsFragment.COL_STARGAZERS)));

    }

    }
