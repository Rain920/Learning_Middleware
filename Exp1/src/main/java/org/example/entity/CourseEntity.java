package org.example.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course", schema = "mw_exp1", catalog = "")
public class CourseEntity {
    private String courseId;
    private String courseName;
    private Integer courseHour;
    private Integer credits;
    private Set<StudentEntity> studentEntitySet = new HashSet<>();

    @Id
    @Column(name = "course_id")
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "course_name")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "course_hour")
    public Integer getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(Integer courseHour) {
        this.courseHour = courseHour;
    }

    @Basic
    @Column(name = "credits")
    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity that = (CourseEntity) o;

        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;
        if (courseName != null ? !courseName.equals(that.courseName) : that.courseName != null) return false;
        if (courseHour != null ? !courseHour.equals(that.courseHour) : that.courseHour != null) return false;
        if (credits != null ? !credits.equals(that.credits) : that.credits != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = courseId != null ? courseId.hashCode() : 0;
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + (courseHour != null ? courseHour.hashCode() : 0);
        result = 31 * result + (credits != null ? credits.hashCode() : 0);
        return result;
    }

    @Transient

    public Set<StudentEntity> getStudentEntitySet() {
        return studentEntitySet;
    }

    public void setStudentEntitySet(Set<StudentEntity> studentEntitySet) {
        this.studentEntitySet = studentEntitySet;
    }
}
