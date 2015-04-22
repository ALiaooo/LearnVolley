package volleydemo.aliao.com.learnvolley.net.httpstacks;

import java.io.IOException;

import volleydemo.aliao.com.learnvolley.net.base.Request;
import volleydemo.aliao.com.learnvolley.net.base.Response;

/**
 * Created by 丽双 on 2015/4/21.
 */
public interface HttpStack {

    public Response performRequest(Request<?> request) throws IOException;
}
