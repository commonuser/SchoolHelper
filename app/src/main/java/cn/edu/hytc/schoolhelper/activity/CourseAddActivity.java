package cn.edu.hytc.schoolhelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import cn.edu.hytc.schoolhelper.R;
import cn.edu.hytc.schoolhelper.common.GreenDaoManager;
import cn.edu.hytc.schoolhelper.entity.Course;
import cn.edu.hytc.schoolhelper.gen.CourseDao;

public class CourseAddActivity extends AppCompatActivity {
    private CourseDao courseDao;

    private EditText course_name_view;
    private EditText course_room_view;
    private EditText course_teacher_view;
    private Spinner course_week_view;
    private Spinner course_seq_view;

    private Button save_view ;
    private Button back_view ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        course_name_view = (EditText)findViewById(R.id.course_name);
        course_room_view = (EditText)findViewById(R.id.course_room);
        course_teacher_view = (EditText)findViewById(R.id.course_teacher);
        course_week_view = (Spinner)findViewById(R.id.course_week);
        course_seq_view = (Spinner)findViewById(R.id.course_seq);

        save_view = (Button)findViewById(R.id.course_save);
        back_view = (Button)findViewById(R.id.backBtn);

        Intent intent = getIntent();
        if(intent!=null){
            int week = intent.getIntExtra("selected_week",0);
            int seq = intent.getIntExtra("selected_seq",0);
            if(week>0){
                course_week_view.setSelection(week-1);
                course_seq_view.setSelection(seq-1);
            }
        }
        courseDao = GreenDaoManager.getInstance().getSession().getCourseDao();

        save_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Course course = new Course();
                course.setCourse_week(new Integer(course_week_view.getSelectedItem().toString()));
                course.setCourse_name(course_name_view.getText().toString());
                course.setCourse_seq(new Integer(course_seq_view.getSelectedItem().toString()));
                course.setCourse_room(course_room_view.getText().toString());
                course.setCourse_teacher(course_teacher_view.getText().toString());
                //check duplication record by course_week,course_seq
                //TODO
                courseDao.insert(course);

                course_name_view.setText("");
                course_seq_view.setSelected(false);
                course_room_view.setText("");
                course_teacher_view.setText("");
            }
        });
        back_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });

    }
}
