package com.example.bilet1;

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

import com.example.bilet1.db.CurseDAO;
import com.example.bilet1.db.CurseDB;

public class ChartActivity extends AppCompatActivity {
    private LinearLayout chartLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        chartLayout = findViewById(R.id.chartLayout);
        CurseDAO curseDAO = Room.databaseBuilder(getApplicationContext(), CurseDB.class, "BD")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build().getCurseDAO();
        float[] costuri = curseDAO.costuri();
        chartLayout.addView(new PieChartView(this,calculatePieChartData(costuri)));
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
        private int[] colors = {Color.BLUE, Color.GREEN, Color.GRAY, Color.YELLOW, Color.RED};
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