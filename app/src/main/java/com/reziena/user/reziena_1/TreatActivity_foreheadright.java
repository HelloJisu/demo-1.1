package com.reziena.user.reziena_1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.RenderScript;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
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
import java.util.Timer;
import java.util.TimerTask;

public class TreatActivity_foreheadright extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference wrinkle_txt;
    private DatabaseReference underrightdata,underleftdata,cheekleftdata,cheekrightdata;
    String wrinkle_string;
    String underleftstring,underrightstring;
    public static Activity underleftativity;
    RenderScript rs;
    Bitmap blurBitMap;
    private static Bitmap bitamp;
    ImageView content1, content2;
    ImageView forehead, underleft, underright, eyeleft, eyeright, cheekl, cheekr, mouth, back, backgroundimg;
    LinearLayout component;
    TextView component_txt,u_tright_txt1,u_tright_txt2,e_tright_txt,u_tleft_txt2,c_tright_txt1,c_tright_txt2,c_tleft_txt1,c_tleft_txt2;
    RelativeLayout treatback, underleft_layout,treat_default;
    int undercount=0, data=0, level=0, timer_sec;
    public static int count_ul=0;
    ImageView f_tright_line1,f_tright_line2,f_tright_line3,f_tright_line4,f_tright_line5,f_tright_line6,
            f_tright_line7,f_tright_line8,f_tright_line9,f_tright_line10,f_tright_line11,f_tright_line12,f_tright_line13,f_tright_line14,
            f_tright_line15,f_tright_line16,f_tright_line17,f_tright_line18,f_tright_line19,question;
    TimerTask second;
    String part;
    AnimationDrawable ftanim1,ftanim2,ftanim3,ftanim4,ftanim5,ftanim6,ftanim7,ftanim8,ftanim9,ftanim10,ftanim11,ftanim12,ftanim13,ftanim14,ftanim15,
            ftanim16,ftanim17,ftanim18,ftanim19;
    static String finish;
    public static String IP_Address = "52.32.36.182";
    String treat;

    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
    Date currentTime = new Date();
    String date = mSimpleDateFormat.format ( currentTime );

    public static void intentpage(String string) {
        finish=string;
    }

    public void animation() {
        Log.e("underleft_init", "animation");
        second = new TimerTask() {
            @Override
            public void run() {
                Log.e("카운터",String.valueOf(count_ul));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count_ul == 1) {
                            String str = "THIS COLUMN HAS 19 LINES.\nPLACE THE DEVICE TO THE COLORED LINE AS\nSHOWN. AND PRESS THE CENTER BUTTON TO\nSTART TREATING ONE LINE";
                            SpannableStringBuilder ssb = new SpannableStringBuilder(str);
                            ssb.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 73, 127, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            component_txt.setText(ssb);
                            f_tright_line1.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim1 = (AnimationDrawable) f_tright_line1.getBackground();
                            ftanim1.start();
                        }
                        if (count_ul == 2) {
                            ftanim1.stop();
                            f_tright_line1.setBackgroundResource(R.drawable.frl1);
                            f_tright_line2.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim2 = (AnimationDrawable) f_tright_line2.getBackground();
                            ftanim2.start();
                            //e_tright_txt.setText("6 left");
                        }
                        if (count_ul == 3) {
                            ftanim2.stop();
                            f_tright_line2.setBackgroundResource(R.drawable.frl1);
                            f_tright_line3.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim3 = (AnimationDrawable) f_tright_line3.getBackground();
                            ftanim3.start();
                            //e_tright_txt.setText("5 left");
                        }
                        if (count_ul == 4) {
                            ftanim3.stop();
                            f_tright_line3.setBackgroundResource(R.drawable.frl1);
                            f_tright_line4.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim4 = (AnimationDrawable) f_tright_line4.getBackground();
                            ftanim4.start();
                            //e_tright_txt.setText("4 left");
                        }
                        if (count_ul == 5) {
                            ftanim4.stop();
                            f_tright_line4.setBackgroundResource(R.drawable.frl1);
                            f_tright_line5.setBackgroundResource(R.drawable.forerightanim2);
                            ftanim5 = (AnimationDrawable) f_tright_line5.getBackground();
                            ftanim5.start();
                           //e_tright_txt.setText("3 left");
                        }
                        if (count_ul == 6) {
                            ftanim5.stop();
                            f_tright_line5.setBackgroundResource(R.drawable.frl21);
                            f_tright_line6.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim6 = (AnimationDrawable) f_tright_line6.getBackground();
                            ftanim6.start();
                            //e_tright_txt.setText("2 left");
                        }
                        if (count_ul == 7) {
                            ftanim6.stop();
                            f_tright_line6.setBackgroundResource(R.drawable.frl1);
                            f_tright_line7.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim7 = (AnimationDrawable) f_tright_line7.getBackground();
                            ftanim7.start();
                            //e_tright_txt.setText("1 left");
                        }
                        if (count_ul == 8) {
                            ftanim7.stop();
                            f_tright_line7.setBackgroundResource(R.drawable.frl1);
                            f_tright_line8.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim8 = (AnimationDrawable) f_tright_line8.getBackground();
                            ftanim8.start();
                            //e_tright_txt.setText("1 left");
                        }
                        if (count_ul == 9) {
                            ftanim8.stop();
                            f_tright_line8.setBackgroundResource(R.drawable.frl1);
                            f_tright_line9.setBackgroundResource(R.drawable.forerightanim3);
                            ftanim9 = (AnimationDrawable) f_tright_line9.getBackground();
                            ftanim9.start();
                            //e_tright_txt.setText("1 left");
                        }
                        if (count_ul == 10) {
                            ftanim9.stop();
                            f_tright_line9.setBackgroundResource(R.drawable.frl3);
                            f_tright_line10.setBackgroundResource(R.drawable.forerightanim4);
                            ftanim10 = (AnimationDrawable) f_tright_line10.getBackground();
                            ftanim10.start();
                            //e_tright_txt.setText("1 left");
                        }
                        if (count_ul == 11) {
                            ftanim10.stop();
                            f_tright_line10.setBackgroundResource(R.drawable.frl4);
                            f_tright_line11.setBackgroundResource(R.drawable.forerightanim5);
                            ftanim11 = (AnimationDrawable) f_tright_line11.getBackground();
                            ftanim11.start();
                            //e_tright_txt.setText("1 left");
                        }
                        if (count_ul == 12) {
                            ftanim11.stop();
                            f_tright_line11.setBackgroundResource(R.drawable.frl5);
                            f_tright_line12.setBackgroundResource(R.drawable.forerightanim6);
                            ftanim12 = (AnimationDrawable) f_tright_line12.getBackground();
                            ftanim12.start();
                            //e_tright_txt.setText("1 left");
                        }
                        if (count_ul == 13) {
                            ftanim12.stop();
                            f_tright_line12.setBackgroundResource(R.drawable.frl6);
                            f_tright_line13.setBackgroundResource(R.drawable.forerightanim7);
                            ftanim13 = (AnimationDrawable) f_tright_line13.getBackground();
                            ftanim13.start();
                            //e_tright_txt.setText("1 left");
                        }
                        if (count_ul == 14) {
                            ftanim13.stop();
                            f_tright_line13.setBackgroundResource(R.drawable.frl7);
                            f_tright_line14.setBackgroundResource(R.drawable.forerightanim8);
                            ftanim14 = (AnimationDrawable) f_tright_line14.getBackground();
                            ftanim14.start();
                            //e_tright_txt.setText("1 left");
                        }
                        if (count_ul == 15) {
                            ftanim14.stop();
                            f_tright_line14.setBackgroundResource(R.drawable.frl1);
                            f_tright_line15.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim15 = (AnimationDrawable) f_tright_line15.getBackground();
                            ftanim15.start();
                            //e_tright_txt.setText("1 left");
                        }
                        if (count_ul == 16) {
                            ftanim15.stop();
                            f_tright_line15.setBackgroundResource(R.drawable.frl8);
                            f_tright_line16.setBackgroundResource(R.drawable.forerightanim9);
                            ftanim16 = (AnimationDrawable) f_tright_line16.getBackground();
                            ftanim16.start();
                            //e_tright_txt.setText("1 left");
                        }
                        if (count_ul == 17) {
                            ftanim16.stop();
                            f_tright_line16.setBackgroundResource(R.drawable.frl9);
                            f_tright_line17.setBackgroundResource(R.drawable.forerightanim10);
                            ftanim17 = (AnimationDrawable) f_tright_line17.getBackground();
                            ftanim17.start();
                            //e_tright_txt.setText("1 left");
                        }
                        if (count_ul == 18) {
                            ftanim17.stop();
                            f_tright_line17.setBackgroundResource(R.drawable.frl10);
                            f_tright_line18.setBackgroundResource(R.drawable.forerightanim11);
                            ftanim18 = (AnimationDrawable) f_tright_line18.getBackground();
                            ftanim18.start();
                            //e_tright_txt.setText("1 left");
                        }
                        if (count_ul == 19) {
                            ftanim18.stop();
                            f_tright_line18.setBackgroundResource(R.drawable.frl11);
                            f_tright_line19.setBackgroundResource(R.drawable.forerightanim1);
                            ftanim19 = (AnimationDrawable) f_tright_line19.getBackground();
                            ftanim19.start();
                            //e_tright_txt.setText("1 left");
                        }
                        if (count_ul == 20) {
                            ftanim19.stop();
                            f_tright_line19.setBackgroundResource(R.drawable.frl1);
                            //e_tright_txt.setText("DONE");
                            //e_tright_txt.setTextColor(Color.parseColor("#9E0958"));
                        }
                        if(count_ul==21){
                            second.cancel();
                            getDataTreat();
                            GetData task = new GetData();
                            task.execute("http://"+IP_Address+"/callingTreathome.php", "");

                            Log.e("underleft", "save");
                            if (! TreatActivity_foreheadright.this.isFinishing()) {
                                Intent intent = new Intent(getApplicationContext(),DoneActivity.class);
                                intent.putExtra("stringlist","underleft");
                                startActivity(intent);
                                new Handler().postDelayed(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        screenshot();
                                    }
                                }, 20);
                            }
                        }
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(second, 0, 1);
    }

    private void getDataTreat() {
        SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
        SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
        String tDate = treaat_date.getString("tDate", "tDate=none");
        treat = treat_zone.getString("tZone", "");
        Log.e("treaat_date", tDate);
        Log.e("treat_zone", treat);

        setDataTreat();
    }

    private void setDataTreat() {
        SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
        SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
        SharedPreferences.Editor editor1 = treaat_date.edit();
        SharedPreferences.Editor editor2 = treat_zone.edit();
        editor1.putString("tDate", date);
        editor2.putString("tZone", treat+"/under_l");
        editor1.commit();
        editor2.commit();
    }

    class setData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) { // 모르겠어// 유...
            super.onPostExecute(getResult);
            Log.e("gfdesetrdf",getResult);
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];
            String where = params[1];

            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
            Date currentTime = new Date();
            String date = mSimpleDateFormat.format ( currentTime );

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "");
            String postParameters = "date="+date+"&id="+userID+"&where="+where;
            Log.e("sdffghrfhgyughj", "update/"+postParameters);

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
                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                    Log.e("gfhgfyghjgyj", "code - HTTP_OK - " + responseStatusCode);
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                    Log.e("hjfrdsrtrth", "code - HTTP_NOT_OK - " + responseStatusCode);
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
                Log.e("hjhkjhukhtyrdfh", e.getMessage());
            }
            return null;

        }
    }

    class GetData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String getResult) { // 모르겠어// 유...
            super.onPostExecute(getResult);

            Log.e("쉬발",getResult);

            if (getResult.contains("No_results")) {
                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/saveTreat.php", "under_l");

                SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
                SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = treaat_date.edit();
                SharedPreferences.Editor editor2 = treat_zone.edit();
                editor1.putString("tDate", date);
                editor2.putString("tZone", "under_l");
                editor1.commit();
                editor2.commit();

            } else {
                showResult(getResult);
                setData task = new setData();
                task.execute("http://"+HomeActivity.IP_Address+"/updateTreat.php", treat+"/under_l");

                SharedPreferences treaat_date = getSharedPreferences("tDate", MODE_PRIVATE);
                SharedPreferences treat_zone = getSharedPreferences("tZone", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = treaat_date.edit();
                SharedPreferences.Editor editor2 = treat_zone.edit();
                editor1.putString("tDate", date);
                editor2.putString("tZone", treat+"/under_l");
                editor1.commit();
                editor2.commit();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String serverURL = params[0];

            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
            Date currentTime = new Date();
            String date = mSimpleDateFormat.format ( currentTime );

            Log.e("으악!!!!!!!!!!!!!!!",date);

            SharedPreferences sp_userID = getSharedPreferences("userID", MODE_PRIVATE);
            String userID = sp_userID.getString("userID", "");
            String postParameters = "id="+userID+"&date="+date;

            try {
                URL url = new URL(serverURL);

                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                Log.e("moisture-postParameters", postParameters);
                outputStream.flush();
                outputStream.close();

                InputStream inputStream;
                int responseStatusCode = httpURLConnection.getResponseCode();
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                    Log.e("moisture-response", "code - HTTP_OK - " + responseStatusCode);
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                    Log.e("moisture-response", "code - HTTP_NOT_OK - " + responseStatusCode);
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
                Log.e("moisture-error-stream", e.getMessage());
            }
            return null;
        }

        private void showResult(String result){
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("getData");

                for(int i=0;i<jsonArray.length();i++){
                    JSONObject item = jsonArray.getJSONObject(i);
                    treat=item.getString("value");
                }

                Log.e("databse",treat);
            } catch (JSONException e) {
                Log.d("treat-JSON", "showResult : "+e.getMessage());
            }
        }
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treat_foreright2);
        underleftativity=TreatActivity_foreheadright.this;

        underrightdata = databaseReference.child("result").child("underrightstring");
        underleftdata = databaseReference.child("result").child("underleftstring");

        Resources res = getResources();
        final Drawable  cheekrightimg= res.getDrawable(R.drawable.cheekrightimg);
        final Drawable  cheekleftimg= res.getDrawable(R.drawable.cheekleftimg);
        final Drawable  cheekunderrightimg= res.getDrawable(R.drawable.cheekunderimg);
        final Drawable  cheekunderleftimg= res.getDrawable(R.drawable.cheekunderleft);
        //값 받아오기
        question=findViewById(R.id.question_fr);
        f_tright_line1=(ImageView)findViewById(R.id.f_tright_line1);
        f_tright_line2=(ImageView)findViewById(R.id.f_tright_line2);
        f_tright_line3=(ImageView)findViewById(R.id.f_tright_line3);
        f_tright_line4=(ImageView)findViewById(R.id.f_tright_line4);
        f_tright_line5=(ImageView)findViewById(R.id.f_tright_line5);
        f_tright_line6=(ImageView)findViewById(R.id.f_tright_line6);
        f_tright_line7=(ImageView)findViewById(R.id.f_tright_line7);
        f_tright_line8=(ImageView)findViewById(R.id.f_tright_line8);
        f_tright_line9=(ImageView)findViewById(R.id.f_tright_line9);
        f_tright_line10=(ImageView)findViewById(R.id.f_tright_line10);
        f_tright_line11=(ImageView)findViewById(R.id.f_tright_line11);
        f_tright_line12=(ImageView)findViewById(R.id.f_tright_line12);
        f_tright_line13=(ImageView)findViewById(R.id.f_tright_line13);
        f_tright_line14=(ImageView)findViewById(R.id.f_tright_line14);
        f_tright_line15=(ImageView)findViewById(R.id.f_tright_line15);
        f_tright_line16=(ImageView)findViewById(R.id.f_tright_line16);
        f_tright_line17=(ImageView)findViewById(R.id.f_tright_line17);
        f_tright_line18=(ImageView)findViewById(R.id.f_tright_line18);
        f_tright_line19=(ImageView)findViewById(R.id.f_tright_line19);
        component_txt=findViewById(R.id.componenttxt_fr);
        backgroundimg=findViewById(R.id.background);
        wrinkle_txt = databaseReference.child("result").child("winkle");

        animation();
    }

    public void onPause() {
        super.onPause();
        second.cancel();
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
    }
}
