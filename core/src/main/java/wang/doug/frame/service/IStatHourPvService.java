package wang.doug.frame.service;

import wang.doug.frame.model.StatHourPv;
import wang.doug.frame.model.StatMonthPv;
import wang.doug.frame.model.StatResPv;

import java.util.List;

public interface IStatHourPvService {
    /**
     * 查询所有
     *
     * @return
     */
    List<StatHourPv> query();

    /**
     * 总数
     *
     * @return
     */
    long total();

    /**
     * 查询数量
     *
     * @param nameLike
     * @return
     */
    long countByName(String nameLike);

    /**
     * 分页查询
     *
     * @param rowIndex 开始记录索引
     * @param pageSize 查询数量
     * @return
     */
    List<StatHourPv> query(long rowIndex, int pageSize);


    /**
     * 分页查询
     *
     * @param nameLike 名称
     * @param rowIndex 开始记录索引
     * @param pageSize 查询数量
     * @return
     */
    List<StatHourPv> queryByName(String nameLike, long rowIndex, int pageSize);

    /**
     * 插入
     *
     * @param statHourPv
     */
    int insert(StatHourPv statHourPv);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    StatHourPv loadById(int id);

    /**
     * 根据ID更新
     *
     * @param statHourPv 系统
     * @return 更新的记录数
     */
    int update(StatHourPv statHourPv);

    /**
     * 根据ID删除
     *
     * @param id ID
     * @return 删除的记录数
     */
    int delete(int id);

    /**
     *
     * @param sysId
     * @param year
     * @param month
     * @param day
     * @param hour
     * @return
     */
    StatHourPv load(int sysId, short year, short month, short day, short hour);



    /**
     * 查询
     * @param sysId
     * @param startYear
     * @param startMonth
     * @param startDay
     * @param startHour
     * @param endYear
     * @param endMonth
     * @param endDay
     * @param endHour
     * @return
     */
    List<StatHourPv> query(int sysId, short startYear, short startMonth, short startDay, short startHour, short endYear, short endMonth, short endDay, short endHour);
}
