package wang.doug.frame.console.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wang.doug.frame.common.BaseResult;
import wang.doug.frame.common.BootstrapTable;
import wang.doug.frame.common.QueryParam;
import wang.doug.frame.model.School;
import wang.doug.frame.model.Student;
import wang.doug.frame.service.ISchoolService;
import wang.doug.frame.service.IStudentService;
import wang.doug.frame.vo.StudentVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 学校页面
 */
@Controller
@RequestMapping("/student")
public class StudentController {


    private static final Log logger = LogFactory
            .getLog(StudentController.class);


    @Autowired
    private IStudentService studentService;

    @Autowired
    private ISchoolService schoolService;

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @RequestMapping({"index", ""})
    public String index(Model model) {

        return "student/index";
    }

    /**
     * 分页查询
     *
     * @param queryParam 查询条件
     * @return
     */

    @RequestMapping("query_by_page.json")
    @ResponseBody
    public BootstrapTable queryByPage(@RequestBody(required = false) final QueryParam queryParam

    ) {
        //查询的关键词
        String search = String.valueOf(queryParam.getSearch().get("searchLike"));
        //根据页码计算开始记录索引
        long pageIndex = (queryParam.getPageNumber() - 1) * queryParam.getPageSize();
        //根据当前查询条件，查询符合条件的记录数
        long total = this.studentService.countByName(search);

        List<Student> studentList = this.studentService.queryByName(search, pageIndex, queryParam.getPageSize());

        return new BootstrapTable(total, convert(studentList));

    }

    /**
     * 新增页面
     *
     * @param model
     * @return
     */
    @RequestMapping({"new"})
    public String toNew(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        List<School> schoolList = schoolService.query();
        model.addAttribute("schoolList", schoolList);
        return "student/new";
    }

    /**
     * 编辑页面
     *
     * @param model
     * @param id    ID
     * @return
     */
    @RequestMapping({"{id}/edit"})
    public String toEdit(Model model, @PathVariable final int id) {
        Student student = this.studentService.loadById(id);
        model.addAttribute("student", student);
        List<School> schoolList = schoolService.query();
        model.addAttribute("schoolList", schoolList);
        return "student/new";
    }

    /**
     * 保存
     *
     * @param request
     * @param response
     * @param student   学校信息
     * @return
     */
    @RequestMapping("save.json")
    @ResponseBody
    public BaseResult save(HttpServletRequest request,
                           HttpServletResponse response, @RequestBody Student student) {

       //TODO：服务器端验证

        int count = 0;
        //学校ID为0或null，表示新增加,否则根据ID更新
        if (student.getId() == null || student.getId() == 0) {
            count = this.studentService.insert(student);
        } else {

            count = this.studentService.update(student);
        }

        BaseResult result = new BaseResult();
        result.setSuccess(count > 0 ? true : false);


        return result;

    }

    /**
     * 根据ID删除
     *
     * @param id
     * @return
     */
    @RequestMapping("delete.json")
    @ResponseBody
    public BaseResult delete(@RequestParam final int id) {

        //TODO：服务器端验证id的权限

        //删除
        int count = this.studentService.delete(id);

        BaseResult result = new BaseResult();
        result.setSuccess(count > 0 ? true : false);

        return result;

    }

    // 将studentList转换为studentVoList
    List<StudentVo> convert(List<Student> studentList){
        List<StudentVo> studentVos = new ArrayList<>();

        for(Student student:studentList){
            StudentVo studentVo = new StudentVo();
            BeanUtils.copyProperties(student,studentVo);

            Integer schoolId = student.getSchoolId();
            if (schoolId!=null){
                School school = schoolService.loadById(schoolId);
                if (school!=null)
                    studentVo.setSchoolName(school.getName());
                else
                    studentVo.setSchoolName("--");
            }
            else
                studentVo.setSchoolName("--");
            studentVos.add(studentVo);
        }
        return studentVos;
    }


}