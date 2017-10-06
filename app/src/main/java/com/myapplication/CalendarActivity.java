package com.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.myapplication.ui.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {
    private Context mContext;
    private CalendarPickerView calendarPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mContext = this;

        /*final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 0);

        calendarPicker = (CalendarPickerView) findViewById(R.id.calendar_view);

        Calendar calendar = Calendar.getInstance();
        calendarPicker.init(new Date(), getLastDateOfMonth(new Date()))
                .withSelectedDate(new Date())
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE);
//        initButtonListeners(nextYear, lastYear);

        calendarPicker.setOnTouchListener(new OnSwipeTouchListener(mContext) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Toast.makeText(mContext, "Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Toast.makeText(mContext, "Right", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    private Date getLastDateOfMonth(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();
    }
}
