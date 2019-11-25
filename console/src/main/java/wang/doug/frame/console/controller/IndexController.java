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
import wang.doug.frame.model.User;
import wang.doug.frame.service.IStatHourPvService;
import wang.doug.frame.service.ISysService;
import wang.doug.frame.service.IUserService;
import wang.doug.frame.vo.ChartVo;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * 登录页面、首页
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    private static final Log logger = LogFactory.getLog(IndexController.class);

    @Autowired
    private ISysService sysService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IStatHourPvService statHourPvService;


    /**
     * 登录页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"", "login.html"})

    public String toLogin(Model model, HttpSession session, HttpServletResponse response) throws IOException {

        //ImgCode(session,response);
        return "login";

    }

    /**
     * 生成验证码
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/checkCode"})

    public void checkCode(Model model, HttpSession session, HttpServletResponse response) throws IOException {

        ImgCode(session,response);
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
    public BaseResult login(HttpSession session,Model model, @RequestBody Map<String, Object> loginData

    ) {
        BaseResult baseResult = new BaseResult();
        if (!session.getAttribute("piccode").toString().equalsIgnoreCase((String)loginData.get("checkCode"))){
            baseResult.setResultCode("001");
            // baseResult.setResultMsg("验证码错误！");
            return baseResult;
        }

        List<User> users = userService.queryByNicknameAndPwd((String)loginData.get("loginName"),(String)loginData.get("loginPwd"));

        if (users.size() != 0){
            baseResult.setSuccess(true);
            return baseResult;
        }
        // 账号或密码错误！
        baseResult.setResultCode("002");
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

    public void ImgCode(HttpSession session, HttpServletResponse response)  throws IOException {
        //这个方法实现验证码的生成
        BufferedImage bi=new BufferedImage(68, 22,BufferedImage.TYPE_INT_RGB);//创建图像缓冲区
        Graphics g=bi.getGraphics(); //通过缓冲区创建一个画布
        Color c=new Color(	250,240,230); //创建颜色
        /*根据背景画了一个矩形框
         */
        g.setColor(c);//为画布创建背景颜色
        g.fillRect(0, 0,68,22); //fillRect:填充指定的矩形
        // 设置颜色


        char[] ch="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();//转化为字符型的数组
        Random r=new Random();
        int len=ch.length;
        int index; //index用于存放随机数字
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<4;i++)
        {
            index=r.nextInt(len);//产生随机数字
            g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));  //设置颜色
            g.drawString(ch[index]+"",(i*15)+3, 18);//画数字以及数字的位置
            sb.append(ch[index]);
        }
        System.out.println("----------------------------"+sb.toString());
        session.setAttribute("piccode",sb.toString()); //将数字保留在session中，便于后续的使用
        ImageIO.write(bi, "JPG", response.getOutputStream());
    }



}
