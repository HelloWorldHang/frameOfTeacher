package wang.doug.frame.console.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.doug.frame.common.BaseController;
import wang.doug.frame.common.BaseResult;
import wang.doug.frame.model.StatHourPv;
import wang.doug.frame.model.Sys;
import wang.doug.frame.service.IStatHourPvService;
import wang.doug.frame.service.ISysService;
import wang.doug.frame.vo.ChartVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 登录页面、首页
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final Log logger = LogFactory.getLog(UserController.class);

    @Autowired
    private ISysService sysService;


    @Autowired
    private IStatHourPvService statHourPvService;


    /**
     * 登录页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"", "login.html"})

    public String toLogin(Model model) {

        return "login";

    }

    /**
     * 登录接口
     *
     * @param model
     * @param loginData JSON格式的登录信息，可以使用Java类描述，也可以使用Map
     * @return
     */
    @RequestMapping(value = {"login.json"})
    @ResponseBody
    public BaseResult login(Model model, @RequestBody Map<String, Object> loginData

    ) {
        //TODO： 从loginData中获取账号和密码，进行验证，根据验证结果返回信息

        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(true);
        return baseResult;
    }

    /**
     * 主页
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"index.html"})
    public String index(HttpServletRequest request,
                        HttpServletResponse response) {

        return "index";
    }

    /**
     * 注销页面
     *
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value = {"logout.html"})
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {

        //TODO: 从Session中删除用户信息

        return "login";

    }




    @RequestMapping(value = {"pv_data.json"})
    @ResponseBody
    public List<ChartVo> pvData(Model model) {



        //
        Calendar startCal=Calendar.getInstance();
        startCal.add(Calendar.HOUR_OF_DAY,-7);


        Calendar endCal=Calendar.getInstance();



        List<ChartVo> voList = new ArrayList<ChartVo>();

        List<Sys> sysList = this.sysService.query();


        String[] colors={"#1ba3cd","#3a3f51"};
        for (int i=0;i<sysList.size();i++){
            Sys sys=sysList.get(i);


            ChartVo vo=new ChartVo();

            vo.setLabel(sys.getName());
            vo.setColor(colors[i]);
             List<StatHourPv> statHourPvList=this.statHourPvService.query(sys.getId(),(short)(startCal.get(Calendar.YEAR)),(short)(startCal.get(Calendar.MONTH)+1),(short)startCal.get(Calendar.DATE),(short)startCal.get(Calendar.HOUR_OF_DAY),(short)endCal.get(Calendar.YEAR),(short)(endCal.get(Calendar.MONTH)+1),(short)endCal.get(Calendar.DATE),(short)endCal.get(Calendar.HOUR_OF_DAY));


             List<List<Object>> list=new ArrayList<List<Object>>();
             for(StatHourPv statHourPv :statHourPvList){
              List<Object> item=new ArrayList<Object>();

              item.add(statHourPv.getHour().toString());
              item.add(statHourPv.getNum());

              list.add(item);

             }

             vo.setData(list);

             voList.add(vo);


        }


        return voList;


    }



}
