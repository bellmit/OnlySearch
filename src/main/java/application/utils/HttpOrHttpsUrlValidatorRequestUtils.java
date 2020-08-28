package application.utils;

import application.filter.SysContext;
import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Objects;


/**
 * http、https添加免证书校验的工具类
 *
 * @author wtl
 */
public class HttpOrHttpsUrlValidatorRequestUtils {

    private static HostnameVerifier hostnameVerifier = new HostnameVerifier() {
        @Override
        public boolean verify(String urlHostName, SSLSession session) {
            System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                    + session.getPeerHost());
            return true;
        }
    };

    /**
     * 处理http、https的服务请求
     *
     * @param url        请求网址
     * @param params     参数
     * @param headers    头信息
     * @param encoding   编码
     * @param jsonString jsonString
     * @return byte[]
     */
    private static byte[] retrieveResponseFromServer(
            final String url,
            final Map<String, String> params,
            final Map<String, String> headers,
            final String method,
            final String encoding,
            final String contentType,
            final String jsonString) {

        HttpURLConnection httpURLConnection = null;

        try {
            URL validationUrl = new URL(url);
            trustAllHttpsCertificates();
            HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);

            httpURLConnection = (HttpURLConnection) validationUrl.openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            //设置content-type
            httpURLConnection.setRequestProperty("Content-Type", contentType);

            //如果是post请求，则设置params
            if (StringUtils.isNotEmpty(method) && SysContext.METHOD_POST.equalsIgnoreCase(method)) {
                httpURLConnection.setRequestMethod(SysContext.METHOD_POST);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                if (null != params) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                    }
                    bufferedOutputStream.write(stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1).getBytes(encoding));
                }

                if (StringUtils.isNotEmpty(jsonString)) {
                    bufferedOutputStream.write(jsonString.getBytes(encoding));
                }

                bufferedOutputStream.close();
            }
            else{
                httpURLConnection.setRequestMethod("GET");
            }

            //处理头信息
            if (null != headers) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int length = -1;
            byte[] buffer = new byte[1024];

            while ((length = bufferedInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }

            return byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            return null;
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }

    private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new MiTm();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }

    static class MiTm implements javax.net.ssl.TrustManager,
            javax.net.ssl.X509TrustManager {
        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        @Override
        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        @Override
        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
        }
    }

    public static String requestHttpsGet(
            final String url,
            final Map<String, String> params,
            final Map<String, String> headers,
            final String encoding,
            final String contentType) {
        byte[] bytes = retrieveResponseFromServer(url, params, headers, SysContext.METHOD_GET, encoding, contentType, null);

        try {
            return new String(Objects.requireNonNull(bytes), encoding);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] requestHttpGetThroughBytes(
            final String url,
            final Map<String, String> params,
            final Map<String, String> headers,
            final String encoding,
            final String contentType) {
        return retrieveResponseFromServer(url, params, headers, SysContext.METHOD_GET, encoding, contentType, null);
    }

    public static String requestHttpPost(
            final String url,
            final Map<String, String> params,
            final Map<String, String> headers,
            final String encoding,
            final String contentType) {
        byte[] bytes = retrieveResponseFromServer(url, params, headers, SysContext.METHOD_POST, encoding, contentType, null);
        try {
            return new String(Objects.requireNonNull(bytes),encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 处理http、https的服务请求
     *
     * @param url        请求网址
     * @param params     参数
     * @param headers    头信息
     * @param encoding   编码
     * @param jsonString jsonString
     * @return byte[]
     */
    public static Object [] getRequester(
            final String url,
            final Map<String, String> params,
            final Map<String, String> headers,
            final String method,
            final String encoding,
            final String contentType,
            final String jsonString) {

        HttpURLConnection httpURLConnection = null;

        try {
            URL validationUrl = new URL(url);

            httpURLConnection = (HttpURLConnection) validationUrl.openConnection();
            //设置content-type
            httpURLConnection.setRequestProperty("Content-Type", contentType);

            //如果是post请求，则设置params
            if (StringUtils.isNotEmpty(method) && SysContext.METHOD_POST.equalsIgnoreCase(method)) {
                httpURLConnection.setRequestMethod(SysContext.METHOD_POST);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                if (null != params) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (Map.Entry<String, String> entry : params.entrySet()) {
                        stringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                    }
                    bufferedOutputStream.write(stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1).getBytes(encoding));
                }

                if (StringUtils.isNotEmpty(jsonString)) {
                    bufferedOutputStream.write(jsonString.getBytes(encoding));
                }

                bufferedOutputStream.close();
            }
            else{
                httpURLConnection.setRequestMethod("GET");
            }

            //处理头信息
            if (null != headers) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            return new Object[]{
                    httpURLConnection.getInputStream(),httpURLConnection.getContentLengthLong()
            };

        } catch (Exception e) {
            return null;
        }
    }
}