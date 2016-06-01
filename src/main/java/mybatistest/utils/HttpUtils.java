package mybatistest.utils;




import mybatistest.dao.WxMaterialImgMapper;
import mybatistest.service.IWxMaterialImgService;
import mybatistest.service.WxMaterialImgServiceImpl;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by sunj on 2016/5/30.
 */
public class HttpUtils {
    private boolean proxyEnable;
    private String proxyHost;
    private int proxyPort=80;
    private String proxyUser;
    private String proxyPwd;
    private CloseableHttpClient httpClient;

    public HttpUtils(boolean proxyEnable, String proxyHost, int proxyPort, String proxyUser, String proxyPwd) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        this.proxyEnable = proxyEnable;
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
        this.proxyUser = proxyUser;
        this.proxyPwd = proxyPwd;

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(120000);
        // 设置读取超时
        configBuilder.setSocketTimeout(120000);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(120000);
        // 在提交请求之前 测试连接是否可用
        configBuilder.setStaleConnectionCheckEnabled(true);
        RequestConfig  requestConfig = configBuilder.build();
        if (proxyEnable){
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(new AuthScope(proxyHost, proxyPort),//认证范围
                    new UsernamePasswordCredentials(proxyUser, proxyPwd));//用户名和密码
            this.httpClient = HttpClients.custom()
//                    .setSSLSocketFactory(createSSLConnSocketFactory())
                    .setDefaultCredentialsProvider(credsProvider)
                    .setDefaultRequestConfig(requestConfig).build();
        }else{
            this.httpClient = HttpClients.createDefault();
        }
    }


    public String get(String url) throws Exception {
        String result;
        HttpGet get = new HttpGet(url);
        HttpResponse response = httpClient.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode == HttpStatus.SC_OK){
            result = EntityUtils.toString(response.getEntity());
        }else{
            throw new Exception("HttpUtils请求出错！错误码："+statusCode);
        }
        return result;
    }

    public InputStream getResponseAsInputStream(String url) throws Exception {
        HttpGet get = new HttpGet(url);
        HttpResponse response = httpClient.execute(get);
        InputStream in;
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode == HttpStatus.SC_OK){
             in = response.getEntity().getContent();

        }else{
            throw new Exception("HttpUtils请求出错！错误码："+statusCode);
        }
        return in;
    }

    public String post(String url, String postString) throws Exception {
        String result;
        StringEntity entity  = new StringEntity(postString, ContentType.create("text/json", "UTF-8"));
        HttpPost post = new HttpPost(url);
        post.setEntity(entity);
        HttpResponse response = httpClient.execute(post);
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode==HttpStatus.SC_OK){
            result = EntityUtils.toString(response.getEntity(),"UTF-8");
        }else {
            throw new Exception("HttpUtils请求出错！错误码："+statusCode);
        }
        return result;
    }

//    private SSLConnectionSocketFactory createSSLConnSocketFactory() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
//        SSLConnectionSocketFactory sslsf = null;
//        try {
//            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
//
//                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//                    return true;
//                }
//            }).build();
//            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
//
//                @Override
//                public boolean verify(String arg0, SSLSession arg1) {
//                    return true;
//                }
//
//                @Override
//                public void verify(String host, SSLSocket ssl) throws IOException {
//                }
//
//                @Override
//                public void verify(String host, X509Certificate cert) throws SSLException {
//                }
//
//                @Override
//                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
//                }
//            });
//        } catch (GeneralSecurityException e) {
//            e.printStackTrace();
//        }
//        return sslsf;
//    }



    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        HttpUtils httpUtils = (HttpUtils) context.getBean("httpUtils");
//        String s = httpUtils.post(
//                "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=8OyyymunlFzCciOp1IbYmalPYD_Oyr3u_cDwxSjQ8DRanifaTegnZ4-zquQ65Gx6DTYtPDcxfT7mfcWs5sm95FVKoJJU14a08TPiZm97gVhxPlHz4v7VprnZwdgvbYcIZFEhABAEKJ"
//        ,"{\"type\":\"image\",\"offset\":0,\"count\":50}");
//        String s = httpUtils.get("https://api.weixin.qq.com/cgi-bin/user/get?access_token=sSUJ-DnCGO3j5py5yiKl90RNF6pVzHj3NRzd5N3rSzhnUOsVu00C-w-EM_FrVYIQPD2hN4y8xUBtcP-lEW_57thL99vMv1CtUnFYNPMH7_DJ3y-94BwrIVxYl-JIYCuZMABjAAAGUV&next_openid=");
        IWxMaterialImgService service = context.getBean(WxMaterialImgServiceImpl.class);
//        WxMaterialImgMapper dao = context.getBean(WxMaterialImgMapper.class);
        System.out.println(service.selectByPrimaryKey("obEwhAMbIZHNzrkdrNqplaFpnAUd2u7xawFhpxqJE4k").getName());
    }


}
