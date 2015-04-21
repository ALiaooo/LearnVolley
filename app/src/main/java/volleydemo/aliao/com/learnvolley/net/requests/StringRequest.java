package volleydemo.aliao.com.learnvolley.net.requests;

import volleydemo.aliao.com.learnvolley.net.base.Request;
import volleydemo.aliao.com.learnvolley.net.base.Response;

/**
 * Created by 丽双 on 2015/4/21.
 */
public class StringRequest extends Request<String>{

    public StringRequest(HttpMethod method, String url, RequestListener<String> listener) {
        super(method, url, listener);
    }

    @Override
    public String parseResponse(Response response) {
        return null;
    }
}
