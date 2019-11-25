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
import wang.doug.frame.model.Sys;
import wang.doug.frame.service.IChannelService;
import wang.doug.frame.service.ISysService;
import wang.doug.frame.vo.ChannelVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 学校页面
 */
@Controller
@RequestMapping("/channel")
public class ChannelController {


    private static final Log logger = LogFactory
            .getLog(ChannelController.class);


    @Autowired
    private IChannelService channelService;


    @Autowired
    private ISysService sysService;

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @RequestMapping({"index", ""})
    public String index(Model model) {

        return "channel/index";
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
        long total = this.channelService.countByName(search);

        List<Channel> channelList = this.channelService.queryByName(search, pageIndex, queryParam.getPageSize());

        return new BootstrapTable(total, this.convert(channelList));

    }


    List<ChannelVo> convert(List<Channel> channelList){

        List<ChannelVo> voList =new ArrayList<ChannelVo>();
        for(Channel channel:channelList){

            ChannelVo vo = new ChannelVo();

            BeanUtils.copyProperties(channel,vo);

            Sys sys=this.sysService.loadById(channel.getSysId());
            vo.setSysName(sys.getName());

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
        Channel channel = new Channel();
        model.addAttribute("channel", channel);

        model.addAttribute("sysList",this.sysService.query());
        return "channel/new";
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
        Channel channel = this.channelService.loadById(id);
        model.addAttribute("channel", channel);
        return "channel/new";
    }

    /**
     * 保存
     *
     * @param request
     * @param channelponse
     * @param channel   学校信息
     * @return
     */
    @RequestMapping("save.json")
    @ResponseBody
    public BaseResult save(HttpServletRequest request,
                           HttpServletResponse channelponse, @RequestBody Channel channel) {

       //TODO：服务器端验证

        int count = 0;
        //学校ID为0或null，表示新增加,否则根据ID更新
        if (channel.getId() == null || channel.getId() == 0) {
            count = this.channelService.insert(channel);
        } else {

            count = this.channelService.update(channel);
        }

        BaseResult channelult = new BaseResult();
        channelult.setSuccess(count > 0 ? true : false);


        return channelult;

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
        int count = this.channelService.delete(id);

        BaseResult channelult = new BaseResult();
        channelult.setSuccess(count > 0 ? true : false);

        return channelult;

    }


}