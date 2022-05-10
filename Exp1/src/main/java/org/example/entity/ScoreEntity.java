package org.example.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "score", schema = "mw_exp1", catalog = "")
public class ScoreEntity {
    private String stuId;
    private String courseId;
    private Integer courseScore;

    @Basic
    @Column(name = "stu_id")
    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    @Basic
    @Column(name = "course_id")
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "course_score")
    public Integer getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(Integer courseScore) {
        this.courseScore = courseScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScoreEntity that = (ScoreEntity) o;

        if (stuId != null ? !stuId.equals(that.stuId) : that.stuId != null) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;
        if (courseScore != null ? !courseScore.equals(that.courseScore) : that.courseScore != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stuId != null ? stuId.hashCode() : 0;
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        result = 31 * result + (courseScore != null ? courseScore.hashCode() : 0);
        return result;
    }
}
