package wang.doug.frame.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.doug.frame.dao.ChannelMapper;
import wang.doug.frame.model.Channel;
import wang.doug.frame.model.ChannelExample;
import wang.doug.frame.service.IChannelService;

import java.util.List;


@Service
public class ChannelServiceImpl implements IChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    public List<Channel> query() {
        ChannelExample example = new ChannelExample();


        List<Channel> channelList = this.channelMapper.selectByExample(example);

        //List<Channel> channelList = this.channelMapper.selectByExampleWithBLOBs(example);

        return channelList;
    }

    /**
     * 根据sysId查询
     *
     * @param sysId
     * @return
     */
    @Override
    public List<Channel> queryBySysId(Integer sysId) {

        ChannelExample example = new ChannelExample();
        example.createCriteria().andSysIdEqualTo(sysId);


        List<Channel> channelList = this.channelMapper.selectByExample(example);

        //List<Channel> channelList = this.channelMapper.selectByExampleWithBLOBs(example);

        return channelList;
    }

    /**
     * 总数
     *
     * @return
     */
    @Override
    public long total() {
        ChannelExample example = new ChannelExample();

        return this.channelMapper.countByExample(example);
    }

    /**
     * 查询数量
     *
     * @param nameLike
     * @return
     */
    @Override
    public long countByName(String nameLike) {

        ChannelExample example = new ChannelExample();

        example.createCriteria().andNameLike("%" + nameLike + "%");


        return this.channelMapper.countByExample(example);

    }

    /**
     * 分页查询
     *
     * @param rowIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<Channel> query(long rowIndex, int pageSize) {

        ChannelExample example = new ChannelExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);

        List<Channel> channelList = this.channelMapper.selectByExample(example);

        return channelList;
    }


    /**
     * 分页查询
     *
     * @param nameLike 名称
     * @param rowIndex 开始记录索引
     * @param pageSize 查询数量
     * @return
     */
    @Override
    public List<Channel> queryByName(String nameLike, long rowIndex, int pageSize) {
        ChannelExample example = new ChannelExample();

        example.setRowIndex(rowIndex);
        example.setPageSize(pageSize);
        example.createCriteria().andNameLike("%" + nameLike + "%");

        List<Channel> channelList = this.channelMapper.selectByExample(example);

        return channelList;
    }

    /**
     * 插入
     *
     * @param channel
     */
    @Override
    public int insert(Channel channel) {

        return this.channelMapper.insert(channel);

    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public Channel loadById(int id) {
        ChannelExample example = new ChannelExample();
        example.createCriteria().andIdEqualTo(id);

        List<Channel> channelList = this.channelMapper.selectByExample(example);

        return channelList.isEmpty() ? null : channelList.get(0);

    }

    /**
     * 根据ID更新
     *
     * @param channel
     */
    @Override
    public int update(Channel channel) {
        ChannelExample example = new ChannelExample();
        example.createCriteria().andIdEqualTo(channel.getId());
        return this.channelMapper.updateByExample(channel, example);
    }

    /**
     * 根据ID删除
     *
     * @param id
     */
    @Override
    public int delete(int id) {
        ChannelExample example = new ChannelExample();
        example.createCriteria().andIdEqualTo(id);
        return this.channelMapper.deleteByExample(example);
    }
}