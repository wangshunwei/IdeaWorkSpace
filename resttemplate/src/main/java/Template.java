import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class Template {



    @Autowired
    RestTemplate restTemplate;

  /*  public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
                                          ParameterizedTypeReference<T> responseType) throws RestClientException {

        Type type = responseType.getType();
        RequestCallback requestCallback = httpEntityCallback(requestEntity, type);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = responseEntityExtractor(type);
        return nonNull(execute(url, method, requestCallback, responseExtractor));
    }*/



}
