package wang.doug.frame.vo;

import java.util.ArrayList;
import java.util.List;

public class ChartVo {

    private String label;

    private String color;

    private List<List<Object>> data=new ArrayList<List<Object>>();

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<List<Object>> getData() {
        return data;
    }

    public void setData(List<List<Object>> data) {
        this.data = data;
    }
}
