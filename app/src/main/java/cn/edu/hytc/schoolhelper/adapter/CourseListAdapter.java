package cn.edu.hytc.schoolhelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.edu.hytc.schoolhelper.R;
import cn.edu.hytc.schoolhelper.entity.Course;

/**
 * Created by admin on 2018/10/9.
 */

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.ViewHolder>{
    private List<Course> courseList;

    public CourseListAdapter(List<Course> courseList){
        this.courseList = courseList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.course_seq_view.setText(String.valueOf(position+1));
        holder.course_tm_view.setText(course.getCourse_time());
        holder.course_name_view.setText(course.getCourse_name());
        holder.course_room_view.setText(course.getCourse_room());
        holder.course_teacher_view.setText(course.getCourse_teacher());
    }

    @Override
    public int getItemCount() {
        return this.courseList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
       TextView course_seq_view;
       TextView course_tm_view;
       TextView course_name_view;
       TextView course_room_view;
       TextView course_teacher_view;

       public ViewHolder(View itemView) {
           super(itemView);
           course_seq_view = itemView.findViewById(R.id.course_seq);
           course_tm_view = itemView.findViewById(R.id.course_tm);
           course_name_view = itemView.findViewById(R.id.course_name);
           course_room_view = itemView.findViewById(R.id.course_room);
           course_teacher_view = itemView.findViewById(R.id.course_teacher);
       }
   }
}
