package volleydemo.aliao.com.learnvolley.net.base;

/**
 * Created by 丽双 on 2015/4/21.
 * http://blog.csdn.net/bboyfeiyu/article/details/43015859
 * 学习这个系列，跟着写。一样的代码，自己敲出来会有新的发现！
 *
 * “写作会进一步帮助你增强逻辑感和条理性。写出来的过程中你会发现你脑袋中的一些【原以为】是没有道理的”
 * ==========================================================
 * Request是一个抽象类，偶们几道抽象类的存在是为继承服务。子类继承父类抽象，那么一定要实现父类中定义的抽象方法。这里为什么一定要用抽象类？普通类是不是足够了？
 * 逆向思维太难，按正常顺序来吧。拿Volley举例，网络请求成功后服务器返回给我们某种数据格式的数据，我们需要通过程序来解析他，而返回的数据格式可能是jasn也可能是xml或者其他类型，
 * 不同的数据格式对应的解析方法是不一样的。所以一个请求，他可能有获取String类型数据的请求StringRequest，可能是获取json数据的请求JsonRequest，或者获取xml数据格式的请求XMLRequest，
 * 这三个网络请求有各自的共同的部分，不同的部分就是对请求结果的解析方法。所以分离变与不变，我们把不变的部分抽出来放到父类，变得部分放到子类来实现（父类就相当于纳入了框架之中）。
 * 而抽象类可以帮助我们实现这一点，抽象类的特性即抽象类只定义抽象方法不实现，其实现放到子类中去。parseResponse()抽象方法即为卡榫函数，连接两个类之间的通讯。
 * 如果不把Request定义成一个抽象类，你需要在父类里实现parseResponse()方法,一个空的方法体，然后通过子类来覆写。每个子类都需要手动来覆写这个方法，就没有像抽象类那种强制实现的提示作用哦。
 * 如果又要支持新的数据格式，只需要继承父类实现抽象方法来扩展，又不会对原有的代码有任何改动。目前为止我们知道抽象类可以提高重用性和可扩展性。
 *
 * request有哪些属性和方法：
 * 请求的数据格式
 * 请求可以被取消
 * 请求是否被取消
 * 序列号，标志在请求队列中的序号
 * 请求有请求头和请求报文
 * 处理请求得到的响应结果
 *
 * 枚举怎么用blog.csdn.net/wgw335363240/article/details/6359614
 * 泛型怎么用
 * 在类内部定义接口
 */
public abstract class Request<T> {

    /**
     * 网络请求方式使用枚举来实现------------------------------------------------------------------>【在Volley源码中使用的是接口】
     */
    public static enum HttpMethod{
        GET,
        POST,
        PUT;
        /*GET("GET"),
        POST("POST"),
        PUT("PUT");

        private String mHttpMethod;

        private HttpMethod(String method){
            mHttpMethod = method;
        }

        @Override
        public String toString() {
            return mHttpMethod;
        }*/
    }

    /**
     * 网络请求的方式，默认是GET请求
     */
    private HttpMethod mHttpMethod = HttpMethod.GET;

    /**
     * 网络请求的url
     */
    private String mUrl;

    private RequestListener<T> mRequestListener;

    private boolean isCancel;

    public Request(HttpMethod method, String url, RequestListener<T> listener){
        mHttpMethod = method;
        mUrl = url;
        mRequestListener = listener;
    }

    /**
     * 处理Response，该方法运行在UI线程------------------------------------------------------------>【在Volley源码中 deliverResponse是抽象方法】
     * @param response
     *
     * deliveryResponse是一个模板函数 template method
     */
    public void deliveryResponse(Response response){
        //解析得到请求结果
        T result = parseResponse(response);

        if (null != mRequestListener){
            int stautCode = response !=null?response.getStatusLine().getStatusCode():-1;
            String msg = response !=null?response.getStatusLine().getReasonPhrase():"unknow error ";
            mRequestListener.onComplete(stautCode, result, msg);
        }
    }

    public abstract T parseResponse(Response response);


    /**
     * 网络请求Listerner，被执行在UI线程
     * @param <T>
     */
    public static interface RequestListener<T>{
        public void onComplete(int statusCode,T response, String msg );
    }

    public void cancel(){
        isCancel = true;
    }

    public boolean isCanceled(){
        return isCancel;
    }

    public String getUrl() {
        return mUrl;
    }
}
