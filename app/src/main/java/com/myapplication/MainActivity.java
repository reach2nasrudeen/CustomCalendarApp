package com.myapplication;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.WeekDayFormatter;

import java.time.Month;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    MaterialCalendarView calendarView;
    TextView textMonth;
    String[] selectableDates = new String[] {"08-10-2017","26-10-2017"};
    String[] disbledDates = new String[] {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textMonth = (TextView) findViewById(R.id.textMonth);
        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        calendarView.setTopbarVisible(false);
        int currentMonth = calendarView.getCurrentDate().getMonth();
        int currentYear = calendarView.getCurrentDate().getYear();

        updateMonthTitle(currentMonth, currentYear);

        calendarView.setWeekDayFormatter(new WeekDayFormatter() {
            @Override
            public CharSequence format(int dayOfWeek) {
                return null;
            }
        });
        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                updateMonthTitle(date.getMonth(), date.getYear());
            }
        });
        calendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .commit();

        calendarView.addDecorator(new DisableDateDecorator(selectableDates));
    }

    @SuppressLint("DefaultLocale")
    private void updateMonthTitle(int month, int year) {
        textMonth.setText(String.format("%s %d", getMonthName(month), year));
    }

    public static String getMonthName(int month) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month];
    }

    private static class DisableDateDecorator implements DayViewDecorator {
        String[] selectableDate;
        public DisableDateDecorator(String[] selectableDate) {
            this.selectableDate = selectableDate;
        }
        @Override
        public boolean shouldDecorate(CalendarDay day) {
            Log.e("Day==>", day.toString());
            Log.e("Date-->",day.getDate().toString());
            String currentDate = String.format("%d-%d-%d",day.getDay(),day.getMonth(),day.getYear());
            Log.e("currentDate-->",currentDate);
            for(String date : selectableDate) {
                if(date.equalsIgnoreCase(currentDate)) {
                    Log.e("Came","came");
                    return true;
                } else {
                    Log.e("Came","came else");
                    return false;
                }
            }
            return false;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setDaysDisabled(true);
        }
    }
}
