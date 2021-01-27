package com.example.bilet3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bilet3.db.AutovehiculBD;
import com.example.bilet3.db.AutovehiculDAO;

public class RaportActivity extends AppCompatActivity {
    private float[] countMasiniTotale = new float[2];
    private AutovehiculDAO autovehiculDAO;
    private LinearLayout chartLayout;
    private int[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raport);
        autovehiculDAO = Room.databaseBuilder(getApplicationContext(), AutovehiculBD.class, "BD").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build().getAutovehiculDAO();
        int countPlatite = autovehiculDAO.countMasini(true);
        countMasiniTotale[0] = countPlatite;
        int countNeplatite = autovehiculDAO.countMasini(false);
        countMasiniTotale[1] = countNeplatite;

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final int countPlatite = autovehiculDAO.countMasini(true);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        countMasiniTotale[0] = countPlatite;
//                        Log.i("PLATITE: " , countMasiniTotale[0]+"");
//                    }
//                });
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final int countNeplatite = autovehiculDAO.countMasini(false);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        countMasiniTotale[1] = countNeplatite;
//                        Log.i("NEPLATITE: " , countMasiniTotale[1]+"");
//                    }
//                });
//            }
//        }).start();
        chartLayout = findViewById(R.id.chartLayout);
        chartLayout.addView(new PieChartView(this,calculatePieChartData(countMasiniTotale)));
    }

    private float[] calculatePieChartData(float[] countMasini){
        float total = countMasini[0] + countMasini[1];
        float[] pieValues = new float[countMasini.length];
        for(int i = 0; i < countMasini.length; i++)
            pieValues[i] = 360 * (countMasini[i]/total);

        return pieValues;
    }

    public class PieChartView extends View{
        private float[] valsDegree;
        private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private int[] colors = {Color.RED, Color.GREEN};
        private RectF rectangle = new RectF(10,10,500,500);
        private int tmp = 0;

        public PieChartView(Context context, float[] countMasini) {
            super(context);
            valsDegree  = new float[countMasini.length];
            for(int i = 0; i < countMasini.length; i++){
                valsDegree[i] = countMasini[i];
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            for(int i = 0; i < valsDegree.length; i++){
                paint.setColor(colors[i]);
                if (i == 0) {
                    canvas.drawArc(rectangle,0,valsDegree[i], true,paint);
                }else{
                    tmp+= (int)valsDegree[i - 1];
                    canvas.drawArc(rectangle,tmp,valsDegree[i],true,paint);
                }
            }
        }
    }

}