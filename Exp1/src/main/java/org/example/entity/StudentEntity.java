package org.example.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student", schema = "mw_exp1", catalog = "")
public class StudentEntity {
    private String stuId;
    private String stuName;
    private String sex;
    private Date birthday;
    private String classId;
    private ClazzEntity clazzEntity;
    private Set<CourseEntity> courseEntitySet = new HashSet<>();

    @Id
    @Column(name = "stu_id")
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Basic
    @Column(name = "stu_name")
    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "class_id")
    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        if (stuId != null ? !stuId.equals(that.stuId) : that.stuId != null) return false;
        if (stuName != null ? !stuName.equals(that.stuName) : that.stuName != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stuId != null ? stuId.hashCode() : 0;
        result = 31 * result + (stuName != null ? stuName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        return result;
    }

    @Transient
    public Set<CourseEntity> getCourseEntitySet() {
        return courseEntitySet;
    }

    public void setCourseEntitySet(Set<CourseEntity> courseEntitySet) {
        this.courseEntitySet = courseEntitySet;
    }
}
