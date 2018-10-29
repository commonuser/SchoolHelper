package cn.edu.hytc.schoolhelper.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import cn.edu.hytc.schoolhelper.R;
import cn.edu.hytc.schoolhelper.fragment.CourseListFragment;
import cn.edu.hytc.schoolhelper.fragment.WeekCourseFragment;
import cn.edu.hytc.schoolhelper.helper.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_course_today:
                    replaceFragment(new CourseListFragment());
                    return true;
                case R.id.navigation_course_week:
                    replaceFragment(new WeekCourseFragment());
                    return true;
                case R.id.navigation_notifications:

                    Toast.makeText(MainActivity.this, "You clicked navigation_notifications!", Toast.LENGTH_LONG).show();

                    return true;
//                case R.id.navigation_movie:
//                    Toast.makeText(MainActivity.this, "You clicked navigation_movie!", Toast.LENGTH_LONG).show();
//                    return true;
//                case R.id.navigation_settings:
//                    Toast.makeText(MainActivity.this, "You clicked navigation_settings!", Toast.LENGTH_LONG).show();
//                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

//        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setItemIconTintList(null);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_course_today);
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction =  fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment);
        transaction.commit();
    }
}
