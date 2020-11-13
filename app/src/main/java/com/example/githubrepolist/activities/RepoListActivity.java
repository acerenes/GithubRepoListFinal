package com.example.githubrepolist.activities;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.githubrepolist.R;
import com.example.githubrepolist.fragments.RepoListActivityFragment;


public class RepoListActivity extends AppCompatActivity {

    private String mLoginId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Add a fragment:
        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            mLoginId=getIntent().getStringExtra(RepoListActivityFragment.ARG_LOGIN_ID);
            arguments.putString(RepoListActivityFragment.ARG_LOGIN_ID, mLoginId);

            RepoListActivityFragment fragment = new RepoListActivityFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.list_fragment, fragment)
                    .commit();
        }




    }


}
