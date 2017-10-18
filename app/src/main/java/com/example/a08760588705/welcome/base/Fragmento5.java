package com.example.a08760588705.welcome.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CalendarView;
import android.widget.FrameLayout;

import com.example.a08760588705.welcome.R;
import com.example.a08760588705.welcome.utils.IfixitUtils;


public class Fragmento5 extends Fragment {
    private FrameLayout fragmentContainer;
    CalendarView simpleCalendarView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragmento5, container, false);
        fragmentContainer = (FrameLayout) view.findViewById(R.id.fragmento5);

        showCalendar(view);
        return view;
    }

    /**
     * Called when a fragment will be displayed
     */
    public void willBeDisplayed() {
        // Do what you want here, for example animate the content
        if (fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }
    }

    /**
     * Called when a fragment will be hidden
     */
    public void willBeHidden() {
        if (fragmentContainer != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);
            fragmentContainer.startAnimation(fadeOut);
        }

    }

    public void showCalendar(View root){

        simpleCalendarView = (CalendarView) root.findViewById(R.id.calendarView); // get the reference of CalendarView
//        simpleCalendarView.setFocusedMonthDateColor(Color.RED); // set the red color for the dates of  focused month
//        simpleCalendarView.setUnfocusedMonthDateColor(Color.BLUE); // set the yellow color for the dates of an unfocused month
//        simpleCalendarView.setSelectedWeekBackgroundColor(Color.RED); // red color for the selected week's background
//        simpleCalendarView.setWeekSeparatorLineColor(Color.GREEN); // green color for the week separator line
        // perform setOnDateChangeListener event on CalendarView

        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                // display the selected date by using a toast
                IfixitUtils.alertas(getActivity(), dayOfMonth + "/" + month + "/" + year);
            }


        });
    }



}
