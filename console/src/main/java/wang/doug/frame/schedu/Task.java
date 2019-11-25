package wang.doug.frame.schedu;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wang.doug.frame.model.*;
import wang.doug.frame.service.*;

import java.util.Calendar;
import java.util.List;

@Component
public class Task {


    private static final Log logger = LogFactory
            .getLog(Task.class);


    @Autowired
    private IStatYearPvService statYearPvService;


    @Autowired
    private IStatMonthPvService statMonthPvService;

    @Autowired
    private IStatHourPvService statHourPvService;

    @Autowired
    private IStatChannelPvService statChannelPvService;


    @Autowired
    private IStatResPvService statResPvService;


    @Autowired
    private ISitePvService sitePvService;


    @Autowired
    private ISysService sysService;


    @Autowired
    private IChannelService channelService;


    @Autowired
    private IResService resService;



    /**
     * 按年统计
     */
    @Scheduled(cron = "0/50 * * * * *")
    public void statYear() {

        Calendar cal = Calendar.getInstance();
        short year = (short) cal.get(Calendar.YEAR);

        List<Sys> sysList = this.sysService.query();

        for (Sys sys : sysList) {

            int num = (int) this.sitePvService.count(sys.getId(), year);

            StatYearPv statYearPv = new StatYearPv();
            statYearPv.setNum(num);
            statYearPv.setYear(year);
            statYearPv.setSysId(sys.getId());


            StatYearPv existStatYearPv = this.statYearPvService.load(sys.getId(), year);

            if (null == existStatYearPv) {

                this.statYearPvService.insert(statYearPv);
            } else {
                statYearPv.setId(existStatYearPv.getId());
                this.statYearPvService.update(statYearPv);
            }
        }


    }




    /**
     * 按月统计
     */
    @Scheduled(cron = "0/40 * * * * *")
    public void statMonth() {

        Calendar cal = Calendar.getInstance();
        short year = (short) cal.get(Calendar.YEAR);
        short month = (short) (cal.get(Calendar.MONTH) + 1);

        List<Sys> sysList = this.sysService.query();

        for (Sys sys : sysList) {

            int num = (int) this.sitePvService.count(sys.getId(), year, month);

            StatMonthPv statMonthPv = new StatMonthPv();
            statMonthPv.setNum(num);
            statMonthPv.setSysId(sys.getId());
            statMonthPv.setYear(year);
            statMonthPv.setMonth(month);

            StatMonthPv existStatMonthPv = this.statMonthPvService.load(sys.getId(), year, month);
            if (null == existStatMonthPv) {

                this.statMonthPvService.insert(statMonthPv);
            } else {
                statMonthPv.setId(existStatMonthPv.getId());
                this.statMonthPvService.update(statMonthPv);
            }
        }
    }




    /**
     * 按照栏目统计
     */
    @Scheduled(cron = "0/30 * * * * *")
    public void statChannel() {

        Calendar cal = Calendar.getInstance();
        short year = (short) cal.get(Calendar.YEAR);
        short month = (short) (cal.get(Calendar.MONTH) + 1);



        List<Channel> channelList = this.channelService.query();


        for (Channel channel : channelList) {

            int num = (int) this.sitePvService.count(channel.getSysId(), year, month, channel.getId());

            StatChannelPv statChannelPv = new StatChannelPv();
            statChannelPv.setNum(num);
            statChannelPv.setSysId(channel.getSysId());
            statChannelPv.setChannelId(channel.getId());
            statChannelPv.setYear(year);
            statChannelPv.setMonth(month);

            StatChannelPv existStatChannelPv = this.statChannelPvService.load(channel.getSysId(), year, month, channel.getId());

            if (null == existStatChannelPv) {

                this.statChannelPvService.insert(statChannelPv);
            } else {
                statChannelPv.setId(existStatChannelPv.getId());
                this.statChannelPvService.update(statChannelPv);
            }
        }
    }



    /**
     * 按资源统计
     */
    @Scheduled(cron = "0/20 * * * * *")
    public void statRes() {

        Calendar cal = Calendar.getInstance();
        short year = (short) cal.get(Calendar.YEAR);
        short month = (short) (cal.get(Calendar.MONTH) + 1);



        List<Res> resList = this.resService.query();


        for (Res res : resList) {

            int num = (int) this.sitePvService.count(res.getSysId(), year, month, res.getChannelId(),res.getId());

            StatResPv statResPv = new StatResPv();
            statResPv.setNum(num);
            statResPv.setSysId(res.getSysId());

            statResPv.setYear(year);
            statResPv.setMonth(month);
            statResPv.setChannelId(res.getChannelId());
            statResPv.setResId(res.getId());


            StatResPv existStatResPv = this.statResPvService.load(res.getSysId(), year, month, res.getChannelId(), res.getId());

            if (null == existStatResPv) {

                this.statResPvService.insert(statResPv);
            } else {
                statResPv.setId(existStatResPv.getId());
                this.statResPvService.update(statResPv);
            }
        }
    }



    /**
     * 按小时统计
     */
    @Scheduled(cron = "0/10 * * * * *")
    public void statHour() {

        Calendar cal = Calendar.getInstance();
        short year = (short) cal.get(Calendar.YEAR);
        short month = (short) (cal.get(Calendar.MONTH) + 1);
        short day = (short) cal.get(Calendar.DATE);
        short hour = (short) cal.get(Calendar.HOUR_OF_DAY);



        List<Sys> sysList = this.sysService.query();


        for (Sys sys : sysList) {

            int num = (int) this.sitePvService.countByHour(sys.getId(), year, month, hour);

            StatHourPv statHourPv = new StatHourPv();
            statHourPv.setNum(num);
            statHourPv.setSysId(sys.getId());

            statHourPv.setYear(year);
            statHourPv.setMonth(month);
            statHourPv.setDay(day);
            statHourPv.setHour(hour);


            StatHourPv existStatHourPv = this.statHourPvService.load(sys.getId(), year, month, day, hour);

            if (null == existStatHourPv) {

                this.statHourPvService.insert(statHourPv);
            } else {
                statHourPv.setId(existStatHourPv.getId());
                this.statHourPvService.update(statHourPv);
            }
        }
    }


}
