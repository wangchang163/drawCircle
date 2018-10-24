package com.example.android.testdrawcircle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArcPointLoadingView view=findViewById(R.id.circleview);
        view.startAnimation();

        MultiView multiView=findViewById(R.id.multiview);
        multiView.startAnimation(50);

        PathView pathView=findViewById(R.id.pathview);
        pathView.startAnimation();

        PaintCircleView paintCircleView=findViewById(R.id.pathcircleview);
        paintCircleView.startAnimation();
    }
}
