package com.reziena.user.reziena_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WrinkleResultActivity extends AppCompatActivity {

  private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
  private DatabaseReference databaseReference = firebaseDatabase.getReference();
  ImageButton imageButton;
  TextView okay, result_grade, result_per;
  String grade, per;
  HomeActivity homeactivity = (HomeActivity)HomeActivity.homeactivity;
  MainActivity mainactivity = (MainActivity) MainActivity.mainnactivity;
  Intent home;

  public static int wrinkRand=8;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wrinkle_result);
    mainactivity.finish();
    homeactivity.screenshotdash();

    WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
    lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
    lpWindow.dimAmount = 0.0f;
    getWindow().setAttributes(lpWindow);

    Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    int width = (int) (display.getWidth() * 0.9); //Display 사이즈의 100%
    int height = (int) (display.getHeight() * 0.9);
    getWindow().getAttributes().width = width;
    getWindow().getAttributes().height = height;

    imageButton = findViewById(R.id.imageButton);
    okay = findViewById(R.id.okay);
    result_grade = findViewById(R.id.result_grade);
    result_per = findViewById(R.id.result_per);

    //Random rand = new Random();
    //int r = rand.nextInt(6);

    try {
      Thread.sleep(1000);
    } catch (Exception e) {
      Log.e("thread", "오류//"+e.getMessage());
    }

    if (wrinkRand!=8) {
      switch (wrinkRand) {
        case 0:
          grade = "A+"; per = "100"; break;
        case 1:
          grade = "A"; per = "95"; break;
        case 2:
          grade = "B+"; per = "90"; break;
        case 3:
          grade = "B"; per = "85"; break;
        case 4:
          grade = "C+"; per = "80"; break;
        case 5:
          grade = "C"; per = "75";break;
      }
    }

    setData task = new setData();
    task.execute("http://"+HomeActivity.IP_Address+"/saveWrinkle.php", per);

    String bef_w="";
    // 원래 wrinkle 가져오기
    SharedPreferences bef_wrinkles = getSharedPreferences("now_w", MODE_PRIVATE);
    bef_w = bef_wrinkles.getString("now_w", "bef_w=none");
    Log.e("bef_w", bef_w);

    // 새로운 wrinkle 저장하기
    SharedPreferences now_wrinkle = getSharedPreferences("now_w", MODE_PRIVATE);
    SharedPreferences bef_wrinkle = getSharedPreferences("bef_w", MODE_PRIVATE);
    SharedPreferences.Editor editor1 = now_wrinkle.edit();
    SharedPreferences.Editor editor2 = bef_wrinkle.edit();
    editor1.putString("now_w", per);
    editor2.putString("bef_w", bef_w);
    editor1.commit();
    editor2.commit();
    Log.e("bef_w ", bef_w+"퍼센트");
    Log.e("now_w ", per+"퍼센트");

    Log.e("Wrinkle-grade", grade);

    result_grade.setText(grade);
    result_per.setText(per +"% of wrinkle \n detected");

    databaseReference.child("result").child("winkle").setValue(per);

    View.OnClickListener onClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        switch (v.getId()) {
          case R.id.okay: case R.id.imageButton:
            // Dashboard로 넘어가게 하기
            homeactivity.dashback.setImageResource(0);

            finish();
            break;

            //Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            //startActivity(intent);
            //homeactivity.finish();
        }
      }
    };
    imageButton.setOnClickListener(onClickListener);
    okay.setOnClickListener(onClickListener);
  }

  class setData extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
      String serverURL = params[0];
      String per = params[1];
      SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
      Date currentTime = new Date();
      String date = mSimpleDateFormat.format ( currentTime );

      SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
      String userID = sp_userID.getString("userID", "");
      String postParameters = "date="+date+"&id="+userID+"&per="+per;
      Log.e("wrinkle-postParameters", "/////"+postParameters);

      try {
        URL url = new URL(serverURL);

        HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setConnectTimeout(5000);;

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.connect();

        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(postParameters.getBytes("UTF-8"));
        Log.e("postParameters", postParameters);
        outputStream.flush();
        outputStream.close();

        // response
        int responseStatusCode = httpURLConnection.getResponseCode();
        String responseStatusMessage = httpURLConnection.getResponseMessage();
        Log.e("response-moisture", "POST response Code - " + responseStatusCode);
        Log.e("response-moisture", "POST response Message - "+ responseStatusMessage);

      } catch (Exception e) {
        Log.e("ERROR", "InsertDataError ", e);
      }
      return null;
    }
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
    homeactivity.dashback.setImageResource(0);
  }
}