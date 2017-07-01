package com.example.leochris.launcher;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.R.attr.name;
import static com.example.leochris.launcher.R.array.uri;

public class SettingActivity extends AppCompatActivity {

    private ListView mListView;
    ArrayList<FragmentInfo> listItems;
    SettingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mListView = (ListView) findViewById(R.id.list_fragment);
        listItems = new ArrayList<FragmentInfo>();
        int change = 0;

        String[] list = getResources().getStringArray(R.array.fragment_list);
        for(int i = 0; i < list.length; i++) {
            listItems.add(new FragmentInfo(list[i]));
        }

        adapter = new SettingAdapter(this, listItems);
        mListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(this, AddActivity.class);
                startActivity(intent);
                // User chose the "Add" item
            return true;

            case R.id.action_remove:
                // User chose the "Remove" item
                return true;

            case R.id.action_done:
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                String uri = data.getStringExtra("uri");
                System.out.println(uri);
                System.out.println(name);
                listItems.add(new FragmentInfo(name, uri));
                adapter.notifyDataSetChanged();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("new fragment", new FragmentInfo(name, uri));
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        }
    }*/
}
