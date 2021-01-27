package com.example.bilet3autobuz;

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

import com.example.bilet3autobuz.db.AutobuzDAO;
import com.example.bilet3autobuz.db.AutobuzDB;

import java.util.List;

public class ChartActivity extends AppCompatActivity {
    private AutobuzDAO autobuzDAO;
    private LinearLayout chartLayout;
    private int[] colors;
    float[] valoriLinie = new float[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        autobuzDAO = (AutobuzDAO) Room.databaseBuilder(getApplicationContext(), AutobuzDB.class,"BD1").fallbackToDestructiveMigration().allowMainThreadQueries().build().getAutobuzDAO();
        List<Autobuz> autobuzeDb = autobuzDAO.getAutobuzeDB();
        Log.v("AUTOBUZE DB", autobuzeDb.toString());
        chartLayout = findViewById(R.id.chart_layout);
        for(Autobuz bus : autobuzeDb){
            valoriLinie[bus.getLinie()]++;
        }
        chartLayout.addView(new PieChartView(this, calculatePieChartData(valoriLinie)));

    }
    private float[] calculatePieChartData(float[] values){
        float total = 0;
        float[] pieVals = new float[values.length];
        for(int i = 0; i < values.length; i++){
            total += values[i];
        }
        for(int i = 0; i < values.length; i++){
            pieVals[i] = 360 * (values[i] / total);
        }
        return pieVals;
    }

    public class PieChartView extends View {
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            for(int i = 0; i < valuesDegree.length; i++){
                paint.setColor(colors[i]);
                paint.setTextSize(30);
                String text = "Linia " + i;
                int x = 600;
                int y = 50 + (i * 50);
                canvas.drawText(text,x,y,paint);
                if(i == 0){
                    canvas.drawArc(rectangle,0,valuesDegree[i], true,paint);
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