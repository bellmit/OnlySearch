package application.utils;

import application.filter.SysContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wtl
 */
public class FileUploadAndDownloadUtil {

    public static Map<String, Object> upload(MultipartFile multipartFile) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(multipartFile.getInputStream());

            String uuid = UUID.getUUID();

            FileOutputStream fileOutputStream = new FileOutputStream(SysContext.UPLOAD_FILE_PATH + uuid + ".png");
            FileChannel channel = fileOutputStream.getChannel();

            int length = -1;
            byte[] buffer = new byte[1024];

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10240);

            while ((length = bufferedInputStream.read(buffer)) != -1) {
                byteBuffer.put(buffer,0,length);
                byteBuffer.flip();
                channel.write(byteBuffer);
                byteBuffer.clear();
            }

            fileOutputStream.close();
            bufferedInputStream.close();
            map.put("uuid", uuid);
            map.put("state", "SUCCESS");

        } catch (Exception e) {
            map.put("status", "FAILED");
        }
        return map;
    }

    public static byte[] getDownloadFile(String uuid) throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(SysContext.UPLOAD_FILE_PATH + uuid));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int length = -1;
        byte[] buffer = new byte[1024];

        while ((length = bufferedInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, length);
        }

        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 根据filePath进行下载网络文件到本地
     * @param url  url
     * @param filePath
     * @return boolean
     */
    public static boolean downloadFile(String url,String filePath){
        boolean flag = false;
        if (StringUtils.isNotEmpty(filePath) && StringUtils.isNotEmpty(url)){
            BufferedOutputStream bufferedOutputStream = null;
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath));

                byte[] bytes = HttpOrHttpsUrlValidatorRequestUtils.requestHttpGetThroughBytes(url, null,null, SysContext.ENCODING, null);

                bufferedOutputStream.write(bytes);
                flag = true;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                try {
                    if (null != bufferedOutputStream){
                        bufferedOutputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
}