import com.fasterxml.jackson.annotation.JsonProperty;

public class NasaObject {

    // поля из json ответа
    private final String copyright;
    private final String date;
    private final String explanation;
    private final String hdurl;
    private final String mediaType;
    private final String serviceVersion;
    private final String title;
    private final String url;

    // Добавим конструктор, в который передадим все наши поля (которые выше) с АННОТАЦИЯМИ  @JsonProperty(...)
    // Мы так объясняем java, что эти поля выше - это поля из Json, а не тяп-ляп
    // Property - свойство/характеристика

    public NasaObject(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdurl,
            @JsonProperty("media_type") String mediaType,
            @JsonProperty("service_version") String serviceVersion,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url)
    {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
        this.url = url;
    }
// гетеры (alt + insert   -  getter (получатель)  -  все выделяем)


    public String getCopyright() {
        return copyright;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdurl() {
        return hdurl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

// переопределим to String, чтобы вывести в консоль то, что к нам пришло
// (alt + insert   -  toString  -  все выделяем)

    @Override // переопределение
    public String toString() {
        return "NasaObject{" +
                "copyright='" + copyright + '\'' +
                ", date='" + date + '\'' +
                ", explanation='" + explanation + '\'' +
                ", hdurl='" + hdurl + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", serviceVersion='" + serviceVersion + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
