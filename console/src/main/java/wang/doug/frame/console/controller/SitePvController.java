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
import wang.doug.frame.model.SitePv;
import wang.doug.frame.model.Sys;
import wang.doug.frame.service.IResService;
import wang.doug.frame.service.ISitePvService;
import wang.doug.frame.vo.ResVo;
import wang.doug.frame.vo.SitePvVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 学校页面
 */
@Controller
@RequestMapping("/site_pv")
public class SitePvController {


    private static final Log logger = LogFactory
            .getLog(SitePvController.class);


    @Autowired
    private ISitePvService sitePvService;


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

        return "site_pv/index";
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
        long total = this.sitePvService.countByName(search);

        String sort=queryParam.getSortName()+" "+queryParam.getSortOrder();
        List<SitePv> sitePvList = this.sitePvService.queryByIp(search, sort,pageIndex, queryParam.getPageSize());

        return new BootstrapTable(total, this.convert(sitePvList));

    }

    List<SitePvVo> convert(List<SitePv> sitePvList){

        List<SitePvVo> voList =new ArrayList<SitePvVo>();
        for(SitePv sitePv:sitePvList){

            SitePvVo vo = new SitePvVo();

            BeanUtils.copyProperties(sitePv,vo);

            Res res=this.resService.loadById(sitePv.getResId());
            vo.setResName(res.getName());


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
        SitePv sitePv = new SitePv();
        model.addAttribute("sitePv", sitePv);
        return "site_pv/new";
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
        SitePv sitePv = this.sitePvService.loadById(id);
        model.addAttribute("sitePv", sitePv);
        return "site_pv/new";
    }

    /**
     * 保存
     *
     * @param request
     * @param response
     * @param sitePv   学校信息
     * @return
     */
    @RequestMapping("save.json")
    @ResponseBody
    public BaseResult save(HttpServletRequest request,
                           HttpServletResponse response, @RequestBody SitePv sitePv) {

       //TODO：服务器端验证

        int count = 0;
        //学校ID为0或null，表示新增加,否则根据ID更新
        if (sitePv.getId() == null || sitePv.getId() == 0) {
            count = this.sitePvService.insert(sitePv);
        } else {

            count = this.sitePvService.update(sitePv);
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
        int count = this.sitePvService.delete(id);

        BaseResult result = new BaseResult();
        result.setSuccess(count > 0 ? true : false);

        return result;

    }


}