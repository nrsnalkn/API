package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class GorestbodyPojo {
    private Object meta;
    private GorestDataPojo data;

    public GorestbodyPojo() {
    }

    public GorestbodyPojo(Object meta, GorestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GorestDataPojo getData() {
        return data;
    }

    public void setData(GorestDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GorestMetaPojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
