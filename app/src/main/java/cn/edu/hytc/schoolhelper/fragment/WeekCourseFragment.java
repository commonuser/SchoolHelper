package cn.edu.hytc.schoolhelper.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

import cn.edu.hytc.schoolhelper.R;
import cn.edu.hytc.schoolhelper.activity.CourseAddActivity;
import cn.edu.hytc.schoolhelper.common.GreenDaoManager;
import cn.edu.hytc.schoolhelper.entity.Course;
import cn.edu.hytc.schoolhelper.gen.CourseDao;
import cn.edu.hytc.schoolhelper.widget.BorderTextView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by admin on 2018/10/9.
 */

public class WeekCourseFragment extends Fragment {
    private CourseDao courseDao;
    private Calendar calendar = Calendar.getInstance();
    private View view_holder;

    private final int REQUEST_CODE_ADD_COURSE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.week_course_frag, container, false);
        view_holder = view;
        ImageView imageView = view.findViewById(R.id.add_course);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CourseAddActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_COURSE);
            }
        });
        clear(view);
        refresh(view);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ADD_COURSE && resultCode == RESULT_OK) {
            clear(view_holder);
            refresh(view_holder);
        }
    }

    private void clear(View view) {
        int[] ids = new int[]{R.id.week1_1, R.id.week1_2, R.id.week1_3, R.id.week1_4,
                R.id.week2_1, R.id.week2_2, R.id.week2_3, R.id.week2_4,
                R.id.week3_1, R.id.week3_2, R.id.week3_3, R.id.week3_4,
                R.id.week4_1, R.id.week4_2, R.id.week4_3, R.id.week4_4,
                R.id.week5_1, R.id.week5_2, R.id.week5_3, R.id.week5_4};
        for (int id : ids) {
            BorderTextView borderTextView = view.findViewById(id);
            borderTextView.setText("");
            borderTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(final View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("询问");
                    builder.setIcon(R.drawable.course_add);
                    builder.setMessage("是否新增课程？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int id = view.getId();
                            int[] week_seq = getWeekAndSeq(id);
                            Intent intent = new Intent(getContext(), CourseAddActivity.class);
                            intent.putExtra("selected_week",week_seq[0]);
                            intent.putExtra("selected_seq",week_seq[1]);
                            startActivityForResult(intent, REQUEST_CODE_ADD_COURSE);
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    builder.create().show();

                    return true;
                }
            });
        }
    }

    private int[] getWeekAndSeq(int id) {
        switch (id){
            case R.id.week1_1:
                return new int[]{1,1};
            case R.id.week1_2:
                return new int[]{1,2};
            case R.id.week1_3:
                return new int[]{1,3};
            case R.id.week1_4:
                return new int[]{1,4};
            case R.id.week2_1:
                return new int[]{2,1};
            case R.id.week2_2:
                return new int[]{2,2};
            case R.id.week2_3:
                return new int[]{2,3};
            case R.id.week2_4:
                return new int[]{2,4};
            case R.id.week3_1:
                return new int[]{3,1};
            case R.id.week3_2:
                return new int[]{3,2};
            case R.id.week3_3:
                return new int[]{3,3};
            case R.id.week3_4:
                return new int[]{3,4};
            case R.id.week4_1:
                return new int[]{4,1};
            case R.id.week4_2:
                return new int[]{4,2};
            case R.id.week4_3:
                return new int[]{4,3};
            case R.id.week4_4:
                return new int[]{4,4};
            case R.id.week5_1:
                return new int[]{5,1};
            case R.id.week5_2:
                return new int[]{5,2};
            case R.id.week5_3:
                return new int[]{5,3};
            case R.id.week5_4:
                return new int[]{5,4};
            default:
                return new int[]{1,1};
        }
    }

    private void refresh(View view) {
        courseDao = GreenDaoManager.getInstance().getSession().getCourseDao();
        List<Course> courseList = getCourseList();
        for (final Course course : courseList) {
            final BorderTextView target = findTargetView(view, course);
            target.setText(course.getCourse_name() + "@" + course.getCourse_room());
            target.setTextColor(Color.MAGENTA);
            target.setPadding(0, 10, 0, 0);
            target.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("警告");
                    builder.setIcon(R.drawable.delete);
                    builder.setMessage("是否删除该课程？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            courseDao.deleteByKey(course.getCourse_id());
                            target.setText("");
                            target.setOnLongClickListener(null);
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    builder.create().show();

                    return true;
                }
            });
        }
    }

    private BorderTextView findTargetView(View view, Course course) {
        int week = course.getCourse_week();
        int seq = course.getCourse_seq();
        String idstr = "week" + week + "_" + seq;
        return view.findViewById(getResId(idstr, R.id.class));
    }

    private List<Course> getCourseList() {
        return courseDao.queryBuilder().list();
    }

    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
