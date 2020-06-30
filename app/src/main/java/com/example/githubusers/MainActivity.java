package com.example.githubusers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvGits;
    private ArrayList<Git> list = new ArrayList<>();
    private String title = "Mode List";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarTitle(title);

        rvGits = findViewById(R.id.rv_gits);
        rvGits.setHasFixedSize(true);

        list.addAll(getListGits());
        showRecyclerList();
    }

    public ArrayList<Git> getListGits() {
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.avatar);
        String[] dataName = getResources().getStringArray(R.array.name);
        String[] dataUsername = getResources().getStringArray(R.array.username);
        String[] dataLocation = getResources().getStringArray(R.array.location);
        String[] dataRepository = getResources().getStringArray(R.array.repository);
        String[] dataCompany = getResources().getStringArray(R.array.company);
        String[] dataFollowers = getResources().getStringArray(R.array.followers);
        String[] dataFollowing = getResources().getStringArray(R.array.following);





        ArrayList<Git> listGit = new ArrayList<>();
        for (int i = 0; i < dataName.length; i++) {
            Git git = new Git();
            git.setPhoto(dataPhoto.getResourceId(i, -1));
            git.setName(dataName[i]);
            git.setUsername(dataUsername[i]);
            git.setLocation(dataLocation[i]);
            git.setRepository(dataRepository[i]);
            git.setCompany(dataCompany[i]);
            git.setFollowers(dataFollowers[i]);
            git.setFollowing(dataFollowing[i]);
            listGit.add(git);
        }
        return listGit;
    }

    private void showRecyclerList(){
        rvGits.setLayoutManager(new LinearLayoutManager(this));
        ListGitAdapter listGitAdapter = new ListGitAdapter(list);
        rvGits.setAdapter(listGitAdapter);

        listGitAdapter.setOnItemClickCallback(new ListGitAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Git data) {
                showSelectedGit(data);
            }
        });
    }

    private void showRecyclerGrid(){
        rvGits.setLayoutManager(new GridLayoutManager(this, 2));
        GridGitAdapter gridGitAdapter = new GridGitAdapter(list);
        rvGits.setAdapter(gridGitAdapter);

        gridGitAdapter.setOnItemClickCallback(new GridGitAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Git data) {
                showSelectedGit(data);
            }
        });
    }

    private void showRecyclerCardView() {
        rvGits.setLayoutManager(new LinearLayoutManager(this));
        CardViewGitAdapter cardViewGitAdapter = new CardViewGitAdapter(list);
        rvGits.setAdapter(cardViewGitAdapter);

        cardViewGitAdapter.setOnItemClickCallback(new CardViewGitAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Git data) {
                showSelectedGit(data);
            }
        });
    }

    private void showAboutMe(){
        Toast.makeText(this, "About Me is clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_list:
                title = "Mode List";
                showRecyclerList();
                break;
            case R.id.action_grid:
                title = "Mode Grid";
                showRecyclerGrid();
                break;
            case R.id.action_cardview:
                title = "Mode CardView";
                showRecyclerCardView();
                break;
            case R.id.action_about_me:
                title = "About Me";
                showAboutMe();
                break;
        }
        setActionBarTitle(title);
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void showSelectedGit(Git git) {
        Toast.makeText(this, "Kamu memilih " + git.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DetailActivity.class);

        //ini problemnya git.setPhoto(dataPhoto.getResourceId(i, -1)); saya ganti jadi ini bias git.setPhoto(git.getPhoto());
        git.setPhoto(git.getPhoto());
        git.setName(git.getName());
        git.setUsername(git.getUsername());
        git.setLocation(git.getLocation());
        git.setRepository(git.getRepository());
        git.setCompany(git.getCompany());
        git.setFollowers(git.getFollowers());
        git.setFollowing(git.getFollowing());
        intent.putExtra(DetailActivity.EXTRA_GIT, git);
        startActivity(intent);
    }
}
