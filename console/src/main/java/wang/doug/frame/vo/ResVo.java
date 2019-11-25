package wang.doug.frame.vo;

import wang.doug.frame.model.Res;

public class ResVo extends Res {

    private String sysName;

    private String channelName;

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
