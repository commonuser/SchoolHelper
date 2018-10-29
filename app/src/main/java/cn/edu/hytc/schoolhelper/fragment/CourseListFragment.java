package cn.edu.hytc.schoolhelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import cn.edu.hytc.schoolhelper.R;
import cn.edu.hytc.schoolhelper.adapter.CourseListAdapter;
import cn.edu.hytc.schoolhelper.common.DateUtil;
import cn.edu.hytc.schoolhelper.common.GreenDaoManager;
import cn.edu.hytc.schoolhelper.entity.Course;
import cn.edu.hytc.schoolhelper.gen.CourseDao;

/**
 * Created by admin on 2018/10/9.
 */

public class CourseListFragment extends Fragment{
    private CourseDao courseDao;
    private Calendar calendar = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.today_course_frag, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.course_list_view);
        TextView today_view = view.findViewById(R.id.today_view);
        TextView today_week_view = view.findViewById(R.id.today_week_view);

        today_view.setText(DateUtil.formatDate(calendar.getTime(), "yyyy.MM.dd"));
        today_week_view.setText(DateUtil.getDisplayWeek(calendar.getTime()));

        courseDao = GreenDaoManager.getInstance().getSession().getCourseDao();
        List<Course> courseList = getCourseList();
        //create layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        CourseListAdapter adapter = new CourseListAdapter(courseList);
        //set adapter and layout manager
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    private List<Course> getCourseList() {
        int week = calendar.get(Calendar.DAY_OF_WEEK);
//        courseDao.insert(new Course(1L,2,"移动APP应用设计与开发",1,"理工南楼407","朱劲松"));
//        courseDao.insert(new Course(2L,2,"虚拟化与云计算",2,"理工南楼404","朱劲松"));
//        courseDao.insert(new Course(3L,2,"移动电商JAVA后台",3,"理工南楼407","李星宇"));
        return courseDao.queryBuilder().where(CourseDao.Properties.Course_week.eq(week - 1)).list();
        /*
        List<Course> list = new ArrayList<Course>();
        list.add(new Course(1L,null,"移动APP应用设计与开发","08:30-10:05","理工南楼407","朱劲松"));
        list.add(new Course(2L,null,"虚拟化与云计算","10:25-12:00","理工南楼404","朱劲松"));
        list.add(new Course(3L,null,"移动电商JAVA后台","14:00-15:35","理工南楼407","李星宇"));
        return list;
        */
    }
}
