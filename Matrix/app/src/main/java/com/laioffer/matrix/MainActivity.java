package com.laioffer.matrix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity  implements EventFragment.OnItemSelectListener,
        CommentFragment.OnItemSelectListener {
    private EventFragment listFragment;
    private CommentFragment gridFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //add list view
        if (getSupportFragmentManager().findFragmentById(R.id.event_container) == null) {
            listFragment = new EventFragment();
            final int commit = getSupportFragmentManager().beginTransaction().add(R.id.event_container, listFragment).commit();

        }
        //add Gridview
        if (getSupportFragmentManager().findFragmentById(R.id.comment_container) == null && isTablet()) {
            gridFragment = new CommentFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.comment_container, gridFragment).commit();
        }

    }

    private boolean isTablet() {
        return (getApplicationContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }


    /**
     * A dummy function to get fake event names.
     */
    private String[] getEventNames() {
        String[] names = {
                "Event1", "Event2", "Event3",
                "Event4", "Event5", "Event6",
                "Event7", "Event8", "Event9",
                "Event10", "Event11", "Event12"};
        return names;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Life cycle test", "We are at onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Life cycle test", "We are at onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Life cycle test", "We are at onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Life cycle test", "We are at onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Life cycle test", "We are at onDestroy()");
    }

    @Override
    public void onItemSelected(int position) {
        if (!isTablet()) {
            Fragment fragment = CommentFragment.newInstance(position);
            getSupportFragmentManager().beginTransaction().replace(R.id.event_container, fragment).addToBackStack(null).commit();

        } else {
            gridFragment.onItemSelected(position);
        }
    }

    @Override
    public void onCommentSelected(int position) {
        listFragment.onItemSelected(position);
    }
}





