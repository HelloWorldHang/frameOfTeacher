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
import wang.doug.frame.model.*;
import wang.doug.frame.service.*;
import wang.doug.frame.vo.StatChannelPvVo;
import wang.doug.frame.vo.StatResPvVo;
import wang.doug.frame.vo.StatYearPvVo;


import java.util.ArrayList;
import java.util.List;

/**
 * 学校页面
 */
@Controller
@RequestMapping("/stat")
public class StatController {


    private static final Log logger = LogFactory
            .getLog(StatController.class);


    @Autowired
    private IStatYearPvService statYearPvService;

    @Autowired
    private IStatMonthPvService statMonthPvService;


    @Autowired
    private IStatChannelPvService statChannelPvService;

    @Autowired
    private IStatResPvService statResPvService;


    @Autowired
    private ISysService sysService;
    @Autowired
    private IChannelService channelService;

    @Autowired
    private IResService resService;

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @RequestMapping({"index", ""})
    public String index(Model model) {

        return "stat/index";
    }



    @RequestMapping({"{sysId}/{year}/month"})
    public String toMonth(Model model,@PathVariable final int sysId,@PathVariable final short year) {

        model.addAttribute("year",year);
        //model.addAttribute("sysId",sysId);

        Sys sys=this.sysService.loadById(sysId);

        model.addAttribute("sys",sys);

        return "stat/month";
    }

    @RequestMapping({"{sysId}/{year}/{month}/channel"})
    public String toMonth(Model model,@PathVariable final int sysId,@PathVariable final short year,@PathVariable final short month) {

        model.addAttribute("year",year);
        //model.addAttribute("sysId",sysId);

        model.addAttribute("month",month);

        Sys sys=this.sysService.loadById(sysId);

        model.addAttribute("sys",sys);
        return "stat/channel";
    }


    @RequestMapping({"{sysId}/{year}/{month}/{channelId}/res"})
    public String toMonth(Model model,@PathVariable final int sysId,@PathVariable final short year,@PathVariable final short month,@PathVariable final int channelId) {

        model.addAttribute("year",year);
        model.addAttribute("sysId",sysId);

        model.addAttribute("month",month);

        //model.addAttribute("channelId",channelId);


        Channel channel=this.channelService.loadById(channelId);

        model.addAttribute("channel",channel);
        return "stat/res";
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
        long total = this.statYearPvService.countByName(search);

        List<StatYearPv> statList = this.statYearPvService.queryByName(search, pageIndex, queryParam.getPageSize());

        return new BootstrapTable(total, this.convert(statList));

    }

    List<StatYearPvVo> convert(List<StatYearPv> statYearPvList){
        List<StatYearPvVo> voList = new ArrayList<StatYearPvVo>();
        for(StatYearPv statYearPv:statYearPvList){

            StatYearPvVo vo =new StatYearPvVo();
            BeanUtils.copyProperties(statYearPv,vo);

            Sys sys=this.sysService.loadById(statYearPv.getSysId());
            vo.setSysName(sys.getName());

            voList.add(vo);

        }

        return voList;



    }


    /**
     * 分页查询
     *
     * @param queryParam 查询条件
     * @return
     */

    @RequestMapping("{sysId}/{year}/query_by_page.json")
    @ResponseBody
    public BootstrapTable queryMonthByPage(
            @PathVariable final int sysId,
            @PathVariable final short year,
            @RequestBody(required = false) final QueryParam queryParam

    ) {
        //查询的关键词
        String search = String.valueOf(queryParam.getSearch().get("searchLike"));
        //根据页码计算开始记录索引
        long pageIndex = (queryParam.getPageNumber() - 1) * queryParam.getPageSize();
        //根据当前查询条件，查询符合条件的记录数
        long total = this.statMonthPvService.count(sysId,year);

        List<StatMonthPv> statList = this.statMonthPvService.query(sysId,year, pageIndex, queryParam.getPageSize());

        return new BootstrapTable(total, statList);

    }

    /**
     * 分页查询
     *
     * @param queryParam 查询条件
     * @return
     */

    @RequestMapping("{sysId}/{year}/{month}/query_by_page.json")
    @ResponseBody
    public BootstrapTable queryByPage(
            @PathVariable final int sysId,
            @PathVariable final short year,
            @PathVariable final short month,
            @RequestBody(required = false) final QueryParam queryParam

    ) {
        //查询的关键词
        String search = String.valueOf(queryParam.getSearch().get("searchLike"));
        //根据页码计算开始记录索引
        long pageIndex = (queryParam.getPageNumber() - 1) * queryParam.getPageSize();
        //根据当前查询条件，查询符合条件的记录数
        long total = this.statChannelPvService.count(sysId,year,month);

        List<StatChannelPv> statList = this.statChannelPvService.query(sysId,year,month, pageIndex, queryParam.getPageSize());

        return new BootstrapTable(total, this.convertChannel(statList));

    }


    List<StatChannelPvVo> convertChannel(List<StatChannelPv> statChannelPvList){
        List<StatChannelPvVo> voList = new ArrayList<StatChannelPvVo>();
        for(StatChannelPv statChannelPv:statChannelPvList){

            StatChannelPvVo vo =new StatChannelPvVo();
            BeanUtils.copyProperties(statChannelPv,vo);

            Channel channel=this.channelService.loadById(statChannelPv.getChannelId());
            vo.setChannelName(channel.getName());

            voList.add(vo);

        }

        return voList;



    }



    /**
     * 分页查询
     *
     * @param queryParam 查询条件
     * @return
     */

    @RequestMapping("{sysId}/{year}/{month}/{channelId}/query_by_page.json")
    @ResponseBody
    public BootstrapTable queryResByPage(
            @PathVariable final short year,
            @PathVariable final short month,
            @PathVariable final int sysId,
            @PathVariable final int channelId,

            @RequestBody(required = false) final QueryParam queryParam

    ) {
        //查询的关键词
        String search = String.valueOf(queryParam.getSearch().get("searchLike"));
        //根据页码计算开始记录索引
        long pageIndex = (queryParam.getPageNumber() - 1) * queryParam.getPageSize();
        //根据当前查询条件，查询符合条件的记录数
        long total = this.statResPvService.count(sysId,channelId,year,month);

        List<StatResPv> statList = this.statResPvService.query(sysId,channelId,year,month, pageIndex, queryParam.getPageSize());

        return new BootstrapTable(total, this.convertRes(statList));

    }

    List<StatResPvVo> convertRes(List<StatResPv> statResPvList){
        List<StatResPvVo> voList = new ArrayList<StatResPvVo>();
        for(StatResPv statResPv:statResPvList){

            StatResPvVo vo =new StatResPvVo();
            BeanUtils.copyProperties(statResPv,vo);

            Res res=this.resService.loadById(statResPv.getResId());
            vo.setResName(res.getName());

            voList.add(vo);

        }

        return voList;



    }




}
