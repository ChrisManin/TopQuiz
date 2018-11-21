package com.example.tofgi.topquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TopScores extends AppCompatActivity {
    private TextView scoreList;
    private TextView rank1;
    private TextView rank2;
    private TextView rank3;
    private TextView rank4;
    private TextView rank5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_scores);

        scoreList = findViewById(R.id.tvScoreList);
        rank1 = findViewById(R.id.tvRank1);
        rank2 = findViewById(R.id.tvRank2);
        rank3 = findViewById(R.id.tvRank3);
        rank4 = findViewById(R.id.tvRank4);
        rank5 = findViewById(R.id.tvRank5);
    }
}
