package wang.doug.frame.vo;

import wang.doug.frame.model.Student;

/**
 * @description: *
 * @author: 司云航
 * @create: 2019-07-18 10:42
 */
public class StudentVo extends Student {
    private String schoolName;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
