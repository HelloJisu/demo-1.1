package com.reziena.user.reziena_1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ExplainActivity extends AppCompatActivity {
    TextView okay, txtpop;
    int a=0;
    ImageView popup,e1,e2,e3,e4;
    public static Activity explainactivity;
    HomeActivity homeactivity = (HomeActivity)HomeActivity.homeactivity;
    TreatActivity_forehead treatforehead = (TreatActivity_forehead) TreatActivity_forehead.treatforehead;
    TreatActivity_underleft treatunderleft = (TreatActivity_underleft) TreatActivity_underleft.treatunderleft;
    TreatActivity_underright treatunderright = (TreatActivity_underright) TreatActivity_underright.treatunderright;
    TreatActivity_cheekright treatcheekright = (TreatActivity_cheekright) TreatActivity_cheekright.treatcheekright;
    TreatActivity_cheekleft treatcheekleft = (TreatActivity_cheekleft) TreatActivity_cheekleft.treatcheekleft;
    TreatActivity treatActivity = (TreatActivity) TreatActivity.treatactivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);
        explainactivity=ExplainActivity.this;



        // popupt창 사이즈 지정

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.0f;
        getWindow().setAttributes(lpWindow);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = (int) (display.getWidth() * 0.9); //Display 사이즈의 100%
        int height = (int) (display.getHeight() * 0.55);
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        okay = findViewById(R.id.okay);
        popup = findViewById(R.id.guideimg);
        e1 = findViewById(R.id.guide1);
        e2 = findViewById(R.id.guide2);
        e3 = findViewById(R.id.guide3);
        e4 = findViewById(R.id.guide4);
        txtpop = findViewById(R.id.textpop);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.okay:
                        treatforehead.back.setImageResource(0);
                        treatcheekleft.back.setImageResource(0);
                        treatcheekright.back.setImageResource(0);
                        treatunderright.back.setImageResource(0);
                        treatunderleft.back.setImageResource(0);
                        treatActivity.back.setImageResource(0);
                        finish();
                        break;
                    case R.id.guideimg:
                        if(a==4){
                            a=0;
                        }
                        a++;
                        Log.e("야야야",String.valueOf(a));
                        if(a==0){
                            txtpop.setText("Press tne button once\nto change the level.");
                            popup.setImageResource(R.drawable.guide1);
                            e1.setImageResource(R.drawable.gellipse1);
                            e2.setImageResource(R.drawable.gellipse2);
                            e3.setImageResource(R.drawable.gellipse2);
                            e4.setImageResource(R.drawable.gellipse2);
                        }
                       if(a==1){
                            txtpop.setText("Press the button while touching\nthe skin to perform a single care.");
                           popup.setImageResource(R.drawable.guide2);
                           e1.setImageResource(R.drawable.gellipse2);
                           e2.setImageResource(R.drawable.gellipse1);
                           e3.setImageResource(R.drawable.gellipse2);
                           e4.setImageResource(R.drawable.gellipse2);
                       }
                        if(a==2){
                            txtpop.setText("Follow the care guide and\nmove a little bit to treat each zone.");
                            popup.setImageResource(R.drawable.guide3);
                            e1.setImageResource(R.drawable.gellipse2);
                            e2.setImageResource(R.drawable.gellipse2);
                            e3.setImageResource(R.drawable.gellipse1);
                            e4.setImageResource(R.drawable.gellipse2);
                        }
                        if(a==3){
                            txtpop.setText("Give your skin a rest\nby treating only one area a day.");
                            popup.setImageResource(R.drawable.guide4);
                            e1.setImageResource(R.drawable.gellipse2);
                            e2.setImageResource(R.drawable.gellipse2);
                            e3.setImageResource(R.drawable.gellipse2);
                            e4.setImageResource(R.drawable.gellipse1);
                        }
                }
            }
        };
        popup.setOnClickListener(onClickListener);
        okay.setOnClickListener(onClickListener);
    }

    public boolean dispatchTouchEvent(MotionEvent ev){
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if(!dialogBounds.contains((int)ev.getX(),(int) ev.getY())){
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        treatforehead.back.setImageResource(0);
        treatcheekleft.back.setImageResource(0);
        treatcheekright.back.setImageResource(0);
        treatunderright.back.setImageResource(0);
        treatunderleft.back.setImageResource(0);
        treatActivity.back.setImageResource(0);
    }
}