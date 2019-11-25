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
import wang.doug.frame.model.Channel;
import wang.doug.frame.model.Res;
import wang.doug.frame.model.Sys;
import wang.doug.frame.service.IChannelService;
import wang.doug.frame.service.IResService;
import wang.doug.frame.service.ISysService;
import wang.doug.frame.vo.ChannelVo;
import wang.doug.frame.vo.ResVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 学校页面
 */
@Controller
@RequestMapping("/res")
public class ResController {


    private static final Log logger = LogFactory
            .getLog(ResController.class);


    @Autowired
    private IResService resService;


    @Autowired
    private ISysService sysService;


    @Autowired
    private IChannelService channelService;

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @RequestMapping({"index", ""})
    public String index(Model model) {

        return "res/index";
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
        long total = this.resService.countByName(search);

        List<Res> resList = this.resService.queryByName(search, pageIndex, queryParam.getPageSize());

        return new BootstrapTable(total, convert(resList));

    }


    List<ResVo> convert(List<Res> resList){

        List<ResVo> voList =new ArrayList<ResVo>();
        for(Res res:resList){

            ResVo vo = new ResVo();

            BeanUtils.copyProperties(res,vo);

            Sys sys=this.sysService.loadById(res.getSysId());
            vo.setSysName(sys.getName());

            Channel channel=this.channelService.loadById(res.getChannelId());
            vo.setChannelName(channel.getName());

            voList.add(vo);

        }
        return voList;


    }

    /**
     * 新增页面
     *
     * @param model
     * @return
     */
    @RequestMapping({"new"})
    public String toNew(Model model) {
        Res res = new Res();
        model.addAttribute("res", res);

        List<Sys> sysList =this.sysService.query();
        model.addAttribute("sysList", sysList);

        Sys sys=sysList.get(0);
        List<Channel> channelList=this.channelService.queryBySysId(sys.getId());
        model.addAttribute("channelList",channelList);


        return "res/new";
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
        Res res = this.resService.loadById(id);
        model.addAttribute("res", res);
        return "res/new";
    }

    /**
     * 保存
     *
     * @param request
     * @param response
     * @param res   学校信息
     * @return
     */
    @RequestMapping("save.json")
    @ResponseBody
    public BaseResult save(HttpServletRequest request,
                           HttpServletResponse response, @RequestBody Res res) {

       //TODO：服务器端验证

        int count = 0;
        //学校ID为0或null，表示新增加,否则根据ID更新
        if (res.getId() == null || res.getId() == 0) {
            count = this.resService.insert(res);
        } else {

            count = this.resService.update(res);
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
        int count = this.resService.delete(id);

        BaseResult result = new BaseResult();
        result.setSuccess(count > 0 ? true : false);

        return result;

    }


}