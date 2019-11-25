package wang.doug.frame.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.doug.frame.model.Res;
import wang.doug.frame.model.SitePv;
import wang.doug.frame.service.IResService;
import wang.doug.frame.service.ISitePvService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.Calendar;

@Controller
@RequestMapping("/api")
public class ApiController {


    @Autowired
    private IResService resService;


    @Autowired
    private ISitePvService sitePvService;


    @RequestMapping(value = {"{resCode}/pv"})
    @ResponseBody
    public void login(Model model, HttpServletRequest request, @PathVariable final String resCode

    ) {


        Res res = this.resService.loadByCode(resCode);

        if (null == res) {
            return;
        }
        // 根据资源ID生成sitePv对象
        SitePv sitePv = new SitePv();
        sitePv.setSysId(res.getSysId());
        sitePv.setChannelId(res.getChannelId());
        sitePv.setResId(res.getId());

        Calendar cal = Calendar.getInstance();
        sitePv.setYear((short) cal.get(Calendar.YEAR));
        sitePv.setMonth((short) (cal.get(Calendar.MONTH) + 1));
        sitePv.setDay((short) cal.get(Calendar.DATE));
        sitePv.setHour((short) cal.get(Calendar.HOUR_OF_DAY));
        sitePv.setMinute((short) cal.get(Calendar.MINUTE));
        sitePv.setSecond((short) cal.get(Calendar.SECOND));
        //IP
        sitePv.setIp(request.getRemoteAddr());

        sitePv.setCreateTime(new Date(System.currentTimeMillis()));

        // 插入到数据库
        this.sitePvService.insert(sitePv);


    }
}
