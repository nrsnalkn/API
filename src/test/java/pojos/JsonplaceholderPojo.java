package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


public class JsonplaceholderPojo {
    /*
    1 tum keyler icin private verabile'lar olusturuyoruz
    2-tum parametreler ile ve parametresiz constructorlarimizi olusturuyoruz
    3- getters ve Setterslerimizi olusturuyoruz
    4-toString methodumuzu olusturuyoruz

     */
   // 1 tum keyler icin private verabile'lar olusturuyoruz
    private Integer UserId;
    private String title;
    private Boolean completed;

    //    2-tum parametreler ile ve parametresiz constructorlarimizi olusturuyoruz


    public JsonplaceholderPojo() {
    }

    public JsonplaceholderPojo(Integer userId, String title, Boolean completed) {
        UserId = userId;
        this.title = title;
        this.completed = completed;

    }
    // 3- getters ve Setterslerimizi olusturuyoruz


    public int getUserId() {
        return UserId;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "JsonplaceholderPojo{" +
                "UserId=" + UserId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
    //Farkli key-value ikililerin uyusmazligini @JsonIgnoreProperties(ignoreUnknown = true)
//annotation ini pojo classimizin basina yazarak cozebiliriz
}
