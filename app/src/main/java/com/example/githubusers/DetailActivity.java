package com.example.githubusers;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_GIT = "extra_git";

    ImageView ivDetail;
    TextView tvObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivDetail = findViewById(R.id.iv_detail);
        tvObject = findViewById(R.id.tv_object);

        Git git = getIntent().getParcelableExtra(EXTRA_GIT);
        int photo = git.getPhoto();
        String text = "Name : " + git.getName() + ",\nUsername : " + git.getUsername() + ",\nLocation : " + git.getLocation() + ",\nRepository : " + git.getRepository() + ",\nCompany : " + git.getCompany() + ",\nFollowers : " + git.getFollowers() + ",\nFollowing : " + git.getFollowing();
        tvObject.setText(text);
        ivDetail.setImageResource(photo);
    }
}
