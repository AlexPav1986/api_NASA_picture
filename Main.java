import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {


// создаем строку - константу. На эту ссылку будем отправлять запрос.
// static - принадлежит классу, final - неизменный
    public static final String URI = "https://api.nasa.gov/planetary/apod?api_key=dlFoKBvsRHsqjMHgqW8Boh9tgk1gDUS8K8pHIQbY";

    //Сущность, которая будет преобразовывать ответ в наш объект NASA
    public static final ObjectMapper mapper = new ObjectMapper();

// создаем метод, который будет запускать прогу
    public static void main(String[] args) throws IOException {

//Создаем метод в который добавим и настроим класс CloseableHttpClient например с помощью builder

        // closeable - закрывающий. Будет автоматически закрываться, когда дойдет до сборщика мусора
        // Это все объект, с помощью которого мы будем делать наши запросы
        // Настраиваем HTTP клиент, который будет отправлять запросы
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        // Отправляем запрос и получаем ответ
        // execute - выполнить
        CloseableHttpResponse response = httpClient.execute(new HttpGet(URI));
        // Получим ответ, теперь его необходимо обработать, чтобы перевести в читаемый вид. Для этого создаем новый класс NasaObject



        // теперь создадим объект, который будет обрабатывать пришедший ответ
        //Преобразуем ответ в Java-объект NasaObject
        NasaObject nasaObject = mapper.readValue(response.getEntity().getContent(), NasaObject.class);
        System.out.println(nasaObject);

        // создадим объект, который будет получать ссылку (Url) на картинку из главного ответа
        // Отправляем запрос и получаем ответ с нашей картинкой
        CloseableHttpResponse pictureResponse = httpClient.execute(new HttpGet(nasaObject.getUrl()));

        // будем автоматически переименовывать картинку по разделенному названию ссылки
        // Формируем автоматически название для файла
        String[] arr = nasaObject.getUrl().split("/");  // создаем массив строк
        String file = arr[6];

        //Проверяем что наш ответ не null
        HttpEntity entity = pictureResponse.getEntity();
        if (entity != null) {
            //сохраняем в файл
            FileOutputStream fos = new FileOutputStream(file); // объект для записи байтов в файл, если байты есть и передаем в него наше название file
            entity.writeTo(fos);
            fos.close();
        }
    }
}


