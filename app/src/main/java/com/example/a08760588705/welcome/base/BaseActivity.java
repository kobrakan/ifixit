package com.example.a08760588705.welcome.base;

import android.Manifest;
import android.animation.Animator;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.example.a08760588705.welcome.R;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    private TextView mTextMessage;

    private Fragment currentFragment;
    private Fragmento1 fragment1 = new Fragmento1();
    private Fragmento2 fragment2 = new Fragmento2();
    private Fragmento3 fragment3 = new Fragmento3();
    private Fragmento4 fragment4 = new Fragmento4();
    private Fragmento5 fragment5 = new Fragmento5();

    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();

    private BottomViewPagerAdapter bottomViewPagerAdapter;
    private AHBottomNavigationViewPager viewPagerBottom;
    private AHBottomNavigation bottomNavigation;
    private FloatingActionButton floatingActionButton;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.navigation_agenda:
                    mTextMessage.setText(R.string.agenda);
                    return true;
                case R.id.navigation_atendimentos:
                    mTextMessage.setText(R.string.atendimentos);
                    return true;
                case R.id.navigation_contatos:
                    mTextMessage.setText(R.string.contatos);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }



        Stetho.initializeWithDefaults(getApplicationContext());

        setContentView(R.layout.activity_base);
        initUI();
        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initUI() {

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        viewPagerBottom = (AHBottomNavigationViewPager) findViewById(R.id.view_pager_bottom);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);

        //Aqui onde é adicionado os fragments no bottom
        viewPagerBottom.setOffscreenPageLimit(2);
        bottomViewPagerAdapter = new BottomViewPagerAdapter(getSupportFragmentManager());
        bottomViewPagerAdapter.add(fragment1);
        bottomViewPagerAdapter.add(fragment2);
        bottomViewPagerAdapter.add(fragment3);
        bottomViewPagerAdapter.add(fragment4);
        bottomViewPagerAdapter.add(fragment5);
        viewPagerBottom.setAdapter(bottomViewPagerAdapter);

        currentFragment = bottomViewPagerAdapter.getCurrentFragment();

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.inicial, R.drawable.ic_home_black_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.notifica_es, R.drawable.ic_notifications_black_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.atendimentos, R.drawable.ic_local_hospital_black_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.contatos, R.drawable.ic_person_black_24dp, R.color.colorPrimary);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.agenda, R.drawable.ic_event_note_black_24dp, R.color.colorPrimary);

        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigationItems.add(item4);
        bottomNavigationItems.add(item5);


        bottomNavigation.addItems(bottomNavigationItems);


        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        bottomNavigation.setCurrentItem(0);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (currentFragment == null) {
                    currentFragment = bottomViewPagerAdapter.getCurrentFragment();
                }


                if (currentFragment != null) {
                    if (currentFragment instanceof Fragmento1) {

                        fragment1.willBeHidden();

                    } else if (currentFragment instanceof Fragmento2) {

                        fragment2.willBeHidden();

                    } else if (currentFragment instanceof Fragmento3) {

                        fragment3.willBeHidden();

                    } else if (currentFragment instanceof Fragmento4) {

                        fragment4.willBeHidden();
                    } else if (currentFragment instanceof Fragmento5) {

                        fragment5.willBeHidden();
                    }
                }

                //Aqui é onde é setado qual o fragment atual
                //Em seguida é pego o fragment atual e feito o fade dependendo de qual instancia for
                viewPagerBottom.setCurrentItem(position, false);
                currentFragment = bottomViewPagerAdapter.getCurrentFragment();

                if (currentFragment instanceof Fragmento1) {
                    mTextMessage.setText(R.string.title_home);
                    fragment1.willBeDisplayed();

                } else if (currentFragment instanceof Fragmento2) {
                    mTextMessage.setText(R.string.title_notifications);
                    fragment2.willBeDisplayed();

                } else if (currentFragment instanceof Fragmento3) {
                    fragment3.onStart();
                    fragment3.willBeDisplayed();

                } else if (currentFragment instanceof Fragmento4) {

                    mTextMessage.setText(R.string.atendimentos);
                    fragment4.willBeDisplayed();

                } else if (currentFragment instanceof Fragmento5) {
                    mTextMessage.setText(R.string.contatos);
                    fragment5.willBeDisplayed();

                }


                if (position == 1) {
                    bottomNavigation.setNotification("", 1);

                    floatingActionButton.setVisibility(View.VISIBLE);
                    floatingActionButton.setAlpha(0f);
                    floatingActionButton.setScaleX(0f);
                    floatingActionButton.setScaleY(0f);
                    floatingActionButton.animate()
                            .alpha(1)
                            .scaleX(1)
                            .scaleY(1)
                            .setDuration(300)
                            .setInterpolator(new OvershootInterpolator())
                            .setListener(new Animator.AnimatorListener() {
                                @Override
                                public void onAnimationStart(Animator animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    floatingActionButton.animate()
                                            .setInterpolator(new LinearOutSlowInInterpolator())
                                            .start();
                                }

                                @Override
                                public void onAnimationCancel(Animator animation) {

                                }

                                @Override
                                public void onAnimationRepeat(Animator animation) {

                                }
                            })
                            .start();

                } else {
                    if (floatingActionButton.getVisibility() == View.VISIBLE) {
                        floatingActionButton.animate()
                                .alpha(0)
                                .scaleX(0)
                                .scaleY(0)
                                .setDuration(300)
                                .setInterpolator(new LinearOutSlowInInterpolator())
                                .setListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        floatingActionButton.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {
                                        floatingActionButton.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                })
                                .start();
                    }
                }

                return true;
            }
        });


    }
}
