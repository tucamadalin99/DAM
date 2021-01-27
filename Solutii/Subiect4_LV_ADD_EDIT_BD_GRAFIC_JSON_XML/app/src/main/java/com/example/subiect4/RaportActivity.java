package com.example.subiect4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.subiect4.db.SesizariDAO;
import com.example.subiect4.db.SesizariDB;

import java.util.List;

public class RaportActivity extends AppCompatActivity {
    SesizariDAO sesizariDAO;
    private List<Sesizare> sesizareList;
    private LinearLayout chartLayout;
    private float[] valoriTipuri = new float[6];
    private String[] labels={"Drumuri","Personal","Iluminat","Gaze","Apa","Canalizare"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raport);
        chartLayout = findViewById(R.id.lvChart);
        sesizariDAO = Room.databaseBuilder(getApplicationContext(), SesizariDB.class, "BD")
                .fallbackToDestructiveMigration()
                .build().getSesizariDAO();
        new Thread(new Runnable() {
            @Override
            public void run() {
                sesizareList = sesizariDAO.getAllDB();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for(Sesizare s:sesizareList){
                            if(s.getTip().value == 0){
                                valoriTipuri[0]++;
                            }
                            if(s.getTip().value == 1){
                                valoriTipuri[1]++;
                            }
                            if(s.getTip().value == 2){
                                valoriTipuri[2]++;
                            }
                            if(s.getTip().value == 3){
                                valoriTipuri[3]++;
                            }
                            if(s.getTip().value == 4){
                                valoriTipuri[4]++;
                            }
                            else
                                valoriTipuri[5]++;
                        }
                        chartLayout.addView(new PieChartView(getApplicationContext(),calculatePieChartData(valoriTipuri)));
                    }
                });
            }
        }).start();
    }


    private float[] calculatePieChartData(float[] values){
        float total = 0;
        float[] pieValues = new float[values.length];
        for(int i = 0; i < values.length; i++){
            total += values[i];
        }

        for(int i = 0; i < values.length; i++){
            pieValues[i] = 360 * (values[i]/total);
        }
        return pieValues;
    }



    public class PieChartView extends View {
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            for(int i = 0; i < valuesDegree.length; i++){
                paint.setColor(colors[i]);
                paint.setTextSize(30);
                String text=labels[i];
                int x=600;
                int y=50+(i*50);
                canvas.drawText(text,x,y,paint);
                if(i == 0){
                    canvas.drawArc(rectangle,0,valuesDegree[i], true,paint);
                    paint.setColor(Color.BLACK);
                    canvas.drawText("Titlu", 10,10,paint);
                }else{
                    tmp += (int)valuesDegree[i - 1];
                    canvas.drawArc(rectangle,tmp,valuesDegree[i], true,paint);
                }
            }
        }

        private  float[] valuesDegree;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private int[] colors = {Color.BLUE, Color.GREEN, Color.GRAY, Color.YELLOW, Color.RED, Color.BLACK};
        private RectF rectangle = new RectF(10, 10, 500,500);
        private int tmp = 0;
        public PieChartView(Context context, float[] values) {
            super(context);
            valuesDegree = new float[values.length];
            for(int i = 0; i < values.length; i++){
                valuesDegree[i] = values[i];
            }
        }
    }
}