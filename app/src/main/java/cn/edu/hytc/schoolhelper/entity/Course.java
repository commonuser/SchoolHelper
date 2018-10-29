package cn.edu.hytc.schoolhelper.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by admin on 2018/10/9.
 */
@Entity
public class Course implements Serializable{
    private static final long serialVersionUID=1;

    @Id
    private Long course_id;
    private int course_week;
    private String course_name;
    private int course_seq;
    private String course_room;
    private String course_teacher;

    public String getCourse_time(){
        switch (course_seq){
            case 1:
                return "08:30-10:05";
            case 2:
                return "10:25-12:00";
            case 3:
                return "14:00-15:35";
            case 4:
                return "15:55-17:30";
            default:
                return "";
        }
    }
    @Generated(hash = 1125551427)
    public Course(Long course_id, int course_week, String course_name,
            int course_seq, String course_room, String course_teacher) {
        this.course_id = course_id;
        this.course_week = course_week;
        this.course_name = course_name;
        this.course_seq = course_seq;
        this.course_room = course_room;
        this.course_teacher = course_teacher;
    }
    @Generated(hash = 1355838961)
    public Course() {
    }
    public Long getCourse_id() {
        return this.course_id;
    }
    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }
    public int getCourse_week() {
        return this.course_week;
    }
    public void setCourse_week(int course_week) {
        this.course_week = course_week;
    }
    public String getCourse_name() {
        return this.course_name;
    }
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }
    public int getCourse_seq() {
        return this.course_seq;
    }
    public void setCourse_seq(int course_seq) {
        this.course_seq = course_seq;
    }
    public String getCourse_room() {
        return this.course_room;
    }
    public void setCourse_room(String course_room) {
        this.course_room = course_room;
    }
    public String getCourse_teacher() {
        return this.course_teacher;
    }
    public void setCourse_teacher(String course_teacher) {
        this.course_teacher = course_teacher;
    }

}
