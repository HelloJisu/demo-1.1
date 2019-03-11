package com.reziena.user.reziena_1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.renderscript.RenderScript;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.reziena.user.reziena_1.utils.RSBlurProcessor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimerTask;

public class TreatActivity_eye extends AppCompatActivity {

  String treatResult="";

  ImageView forehead, underleft, underright, eyeleft, eyeright, cheekl, cheekr, mouth, back, question,backgroundimg;
  LinearLayout component;
  RenderScript rs;
  Bitmap blurBitMap;
  Animation alphaback;
  private long mLastClickTime = 0;
  private static Bitmap bitamp;
  String underrightstring,underleftstring,cheekrightstring,cheekleftstring;
  TextView component_txt,u_tright_txt1,u_tright_txt2,u_tleft_txt1,u_tleft_txt2,c_tright_txt1,c_tright_txt2,c_tleft_txt1,c_tleft_txt2;
  RelativeLayout treatback, underright_layout, underleft_layout,treat_default,cheekright_layout,cheekleft_layout;
  int cheekcount=0, undercount=0, foreheadcount=0, level=0, timer_sec, count=0;
  ImageView u_tright_line1,u_tright_line2,u_tright_line3,u_tright_line4,u_tright_line5,u_tright_line6,
          u_tright_line7,u_tright_line8,u_tright_line9,u_tright_line10,u_tright_line11,u_tright_line12,u_tright_line13,
          u_tleft_line1,u_tleft_line2,u_tleft_line3,u_tleft_line4,u_tleft_line5,u_tleft_line6,
          u_tleft_line7,u_tleft_line8,u_tleft_line9,u_tleft_line10,u_tleft_line11,u_tleft_line12,u_tleft_line13
          ,c_tright_line1,c_tright_line2,c_tright_line3,c_tright_line4,c_tright_line5,c_tright_line6,c_tright_line7,c_tright_line8
          ,c_tright_line9,c_tright_line10,c_tright_line11,c_tright_line12,c_tright_line13,c_tright_line14,c_tright_line15,c_tright_line16,c_tright_line17
          ,c_tright_line18,c_tright_line19,c_tright_line20,c_tright_line21,c_tright_line22,c_tleft_line1,c_tleft_line2,c_tleft_line3,c_tleft_line4,c_tleft_line5,c_tleft_line6,c_tleft_line7,c_tleft_line8
          ,c_tleft_line9,c_tleft_line10,c_tleft_line11,c_tleft_line12,c_tleft_line13,c_tleft_line14,c_tleft_line15,c_tleft_line16,c_tleft_line17
          ,c_tleft_line18,c_tleft_line19,c_tleft_line20,c_tleft_line21,c_tleft_line22;
  TimerTask second;
  String part,wrinkle_string;
  public static Activity treateye;
  ImageView content1, content2;
  Intent home;
  int wrinkleresult;

  private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
  private DatabaseReference databaseReference = firebaseDatabase.getReference();
  private DatabaseReference underrightdata,underleftdata,cheekleftdata,cheekrightdata,wrinkle_txt;


  @SuppressLint("WrongViewCast")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_treat_eye);
    treateye=TreatActivity_eye.this;
    home = getIntent();

    underrightdata = databaseReference.child("result").child("underrightstring");
    underleftdata = databaseReference.child("result").child("underleftstring");
    cheekleftdata = databaseReference.child("result").child("cheekleftstring");
    cheekrightdata = databaseReference.child("result").child("cheekrightstring");
    wrinkle_txt = databaseReference.child("result").child("winkle");
    alphaback = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_alpha_back);

    Resources res = getResources();
    final Drawable  cheekrightimg= res.getDrawable(R.drawable.cheekrightimg);
    final Drawable  cheekleftimg= res.getDrawable(R.drawable.cheekleftimg);
    final Drawable  cheekunderrightimg= res.getDrawable(R.drawable.cheekunderimg);
    final Drawable  cheekunderleftimg= res.getDrawable(R.drawable.cheekunderleft);
    //값 받아오기

    backgroundimg = findViewById(R.id.background);
    question = findViewById(R.id.question_e);
    forehead =  (ImageView)findViewById(R.id.forehead_er);
    underleft =  (ImageView)findViewById(R.id.underleft_er);
    underright =  (ImageView)findViewById(R.id.underright_er);
    cheekl =  (ImageView)findViewById(R.id.cheek_left_er);
    cheekr =  (ImageView)findViewById(R.id.cheek_right_er);
    mouth =  (ImageView)findViewById(R.id.mouth_er);
    eyeleft = (ImageView)findViewById(R.id.eyeleft_er);
    eyeright=(ImageView)findViewById(R.id.eyeright_er);
    component_txt=(TextView)findViewById(R.id.componenttxt_er);
    back=(ImageView)findViewById(R.id.backw_er);
    content1 = findViewById(R.id.treatup_er);
    content2 = findViewById(R.id.treatdown_er);
    wrinkleresult=home.getIntExtra("wrinkle",wrinkleresult);
    question=findViewById(R.id.question_e);
    View.OnClickListener onClickListener = new View.OnClickListener() {
      Intent intent;

      @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
      public void onClick(View v) {

        level=1;

        switch (v.getId()) {
          case R.id.backw_er:
            intent = new Intent(getBaseContext(), TreatActivity.class);
            startActivity(intent);
            finish();
            break;

          case R.id.eyeright_er:
            HomeActivity.send("uneye_r->start");
            intent = new Intent(getBaseContext(), TreatActivity_eyeright2.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
            break;

          case R.id.eyeleft_er:
            HomeActivity.send("uneye_l->start");
            intent = new Intent(getBaseContext(), TreatActivity_eyerleft2.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
            break;

          case R.id.question_e:
            if (SystemClock.elapsedRealtime() - mLastClickTime < 2000){
              Log.e("중복터치","하지마세유");
              return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            intent = new Intent(getApplicationContext(), ExplainActivity.class);
            startActivity(intent);
            overridePendingTransition(0,0);
            new Handler().postDelayed(new Runnable()
            {
              @Override
              public void run()
              {
                screenshot();
              }
            }, 20);
            break;
        }
      }
    };
    question.setOnClickListener(onClickListener);
    eyeright.setOnClickListener(onClickListener);
    back.setOnClickListener(onClickListener);
    eyeleft.setOnClickListener(onClickListener);
  }

  public void onStart() { super.onStart(); }

  public void onResume() {
    super.onResume();

    getDataTreat();
    getDataWrinkle();
  }

  private void getDataWrinkle() {
    SharedPreferences now_wrinkle = getSharedPreferences("now_w", MODE_PRIVATE);
    wrinkle_string = now_wrinkle.getString("now_w", "level=none");
    Log.e("level", wrinkle_string);

    if (wrinkle_string.equals("level=none")) {
      GetData2 task2 = new GetData2();
      task2.execute("http://"+HomeActivity.IP_Address+"/callingWrinkle.php", "");
    } else setResult();
  }

  private void setResult() {
    if (wrinkle_string.equals("100")||wrinkle_string.equals("95")) {
      level = 1;
    }
    if (wrinkle_string.equals("90")||wrinkle_string.equals("85")) {
      level = 2;
    }
    if (wrinkle_string.equals("80")||wrinkle_string.equals("75")) {
      level = 3;
    }
    if (level == 1) {
      // underleft
      if (treatResult.contains("under_l")) {
        eyeleft.setEnabled(false);
        eyeleft.setImageResource(R.drawable.underleftdone);
      } else {
        eyeleft.setImageResource(R.drawable.eyeleftlevel1);
      }

      // underright
      if (treatResult.contains("under_r")) {
        underright.setEnabled(false);
        underright.setImageResource(R.drawable.underrightdone);
      } else {
        eyeright.setImageResource(R.drawable.eyerightlevel1);
      }
      component_txt.setText("PLEASE SET THE DEVICE\nON LEVEL 1,\nAND SELECT STARTIG AREA");
    }
    if (level == 2) {
      if (treatResult.contains("under_l")) {
        underleft.setEnabled(false);
        underleft.setImageResource(R.drawable.underleftdone);
      } else {
        eyeleft.setImageResource(R.drawable.eyeleftlevel2);
      }

      // underright
      if (treatResult.contains("under_r")) {
        underright.setEnabled(false);
        underright.setImageResource(R.drawable.underrightdone);
      } else {
        eyeright.setImageResource(R.drawable.eyerightlevel2);
      }
      component_txt.setText("PLEASE SET THE DEVICE\nON LEVEL 2,\nAND SELECT STARTIG AREA");
    } else {

    }
    if (level == 3) {
      if (treatResult.contains("under_l")) {
        underleft.setEnabled(false);
        underleft.setImageResource(R.drawable.underleftdone);
      } else {
        eyeleft.setImageResource(R.drawable.eyeleftlevel3);
      }

      // under_r
      if (treatResult.contains("under_r")) {
        underright.setEnabled(false);
        underright.setImageResource(R.drawable.underrightdone);
      } else {
        eyeright.setImageResource(R.drawable.eyerightlevel3);
      }
      component_txt.setText("PLEASE SET THE DEVICE\nON LEVEL 3,\nAND SELECT STARTIG AREA");
    }
  }

  private void getDataTreat() {
    SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
    SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
    String tDate = treaat_date.getString("tDate", "tDate=none");
    treatResult = treat_zone.getString("tZone", "tZone=none");
    Log.e("treaat_date", tDate);
    Log.e("treat_zone", treatResult);

    if (tDate.equals("tDate=none")) {
      GetData task = new GetData();
      task.execute("http://"+HomeActivity.IP_Address+"/callingTreathome.php", "");
    } else checkResult();
  }

  private void setLevel(String date) {
    SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
    SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
    SharedPreferences.Editor editor1 = treaat_date.edit();
    SharedPreferences.Editor editor2 = treat_zone.edit();
    editor1.putString("tDate", date);
    editor2.putString("tZone", treatResult);
    editor1.commit();
    editor2.commit();
  }

  class GetData extends AsyncTask<String, Void, String> {

    @Override
    protected void onPostExecute(String getResult) {
      super.onPostExecute(getResult);

      Log.e("treat3-", "onPostExecute - " + getResult);

      if (getResult == null) {
      } else {
        showResult(getResult);
        checkResult();
      }
    }

    @Override
    protected String doInBackground(String... params) {
      String serverURL = params[0];

      SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
      Date currentTime = new Date();
      String date = mSimpleDateFormat.format ( currentTime );

      SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
      String userID = sp_userID.getString("userID", "");
      String postParameters = "date="+date+"&id="+userID;
      Log.e("moisture-userID", userID);

      try {
        URL url = new URL(serverURL);

        HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setConnectTimeout(5000);

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.connect();

        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(postParameters.getBytes("UTF-8"));
        Log.e("treat2-postParameters", postParameters);
        outputStream.flush();
        outputStream.close();

        InputStream inputStream;
        int responseStatusCode = httpURLConnection.getResponseCode();
        if(responseStatusCode == HttpURLConnection.HTTP_OK) {
          inputStream = httpURLConnection.getInputStream();
          Log.e("treat3-response", "code - HTTP_OK - " + responseStatusCode);
        }
        else{
          inputStream = httpURLConnection.getErrorStream();
          Log.e("treat3-response", "code - HTTP_NOT_OK - " + responseStatusCode);
        }

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder sb = new StringBuilder();
        String line;

        while((line = bufferedReader.readLine()) != null){
          sb.append(line);
        }
        bufferedReader.close();

        return sb.toString().trim();

      } catch (Exception e) {
        Log.e("treat3-error-stream", e.getMessage());
      }
      return null;
    }

    private void showResult(String result){
      try {
        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("getData");

        for(int i=0;i<jsonArray.length();i++){

          JSONObject item = jsonArray.getJSONObject(i);
          treatResult+=item.getString("value");

          Log.e("treatResult: ", treatResult+"");
        }

      } catch (JSONException e) {
        Log.d("treatResult-JSON", "showResult : ", e);
      }
    }
  }

  class GetData2 extends AsyncTask<String, Void, String> {

    @Override
    protected void onPostExecute(String getResult) {
      super.onPostExecute(getResult);

      if (getResult == null) {}
      else if (getResult.contains("No_results")) {}
      else {
        showResult(getResult);
        setResult();
      }
    }

    @Override
    protected String doInBackground(String... params) {
      String serverURL = params[0];

      SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
      String userID = sp_userID.getString("userID", "");
      String postParameters = "id="+userID;

      try {
        URL url = new URL(serverURL);

        HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setRequestMethod("POST");
        //httpURLConnection.setDoInput(true);
        httpURLConnection.connect();
        Log.e("wrinkle-Connect", "complete");

        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(postParameters.getBytes("UTF-8"));
        Log.e("wrinkle-postParameters", postParameters);
        outputStream.flush();
        outputStream.close();

        InputStream inputStream;
        int responseStatusCode = httpURLConnection.getResponseCode();
        Log.d("wrinkle-response", "code - " + responseStatusCode);

        if(responseStatusCode == HttpURLConnection.HTTP_OK) {
          inputStream = httpURLConnection.getInputStream();
        }
        else{
          inputStream = httpURLConnection.getErrorStream();
        }

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder sb = new StringBuilder();
        String line;

        while((line = bufferedReader.readLine()) != null){
          sb.append(line);
        }
        bufferedReader.close();

        return sb.toString().trim();

      } catch (Exception e) {
        Log.e("wrinkle-error", e.getMessage());
      }
      return null;
    }

    private void showResult(String result){
      try {
        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = jsonObject.getJSONArray("getData");

        for(int i=0;i<jsonArray.length();i++){

          JSONObject item = jsonArray.getJSONObject(i);

          wrinkle_string = item.getString("level");
        }

      } catch (JSONException e) {
        Log.d("wrinkle-JSON", "showResult : ", e);
      }

    }
  }

  public void screenshot(){
    rs = RenderScript.create(this);
    View view=getWindow().getDecorView();
    view.setDrawingCacheEnabled(false);
    view.setDrawingCacheEnabled(true);
    bitamp = view.getDrawingCache();
    RSBlurProcessor rsBlurProcessor = new RSBlurProcessor(rs);
    blurBitMap = rsBlurProcessor.blur(bitamp, 20f, 3);
    backgroundimg.setImageBitmap(blurBitMap);
    backgroundimg.startAnimation(alphaback);
  }

  private void checkResult() {
    // cheekl
    if (treatResult.contains("uneye_l")) {
      underleft.setEnabled(false);
      underleft.setImageResource(R.drawable.underleftdone);
    }

    // cheekr
    if (treatResult.contains("uneye_r")) {
      underright.setEnabled(false);
      underright.setImageResource(R.drawable.underrightdone);
    }
  }

}