package volleydemo.aliao.com.learnvolley.net.base;

import org.apache.http.ProtocolVersion;
import org.apache.http.ReasonPhraseCatalog;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpResponse;

import java.util.Locale;

/**
 * Created by 丽双 on 2015/4/21.
 */
public class Response extends BasicHttpResponse {

    public byte[] rawData = new byte[0];

    public Response(StatusLine statusline) {
        super(statusline);
    }

    public Response(ProtocolVersion ver, int code, String reason) {
        super(ver, code, reason);
    }

}
