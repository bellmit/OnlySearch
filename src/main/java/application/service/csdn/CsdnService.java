package application.service.csdn;


import application.utils.HttpOrHttpsUrlValidatorRequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: wtl
 * @License: (C) Copyright 2020
 * @Contact: 1050100468@qq.com
 * @Date: 2020/7/18 12:57
 * @Version: 1.0
 * @Description:
 */
@Service
@Slf4j
public class CsdnService {

    private List<String> urls = null;
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(5, AVAILABLE_PROCESSORS * 2 + 1,
            100, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5), new ThreadPoolExecutor.CallerRunsPolicy());

    public CsdnService() throws Exception {
        InputStream resourceAsStream = CsdnService.class.getResourceAsStream("/reader.txt");

        int length = -1;
        byte[] buffer = new byte[10240];

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        while ((length = resourceAsStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, length);
        }

        String[] split = new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8).split("\n");

        urls = Arrays.asList(split);
    }

    @PostConstruct
    public void run(){
        THREAD_POOL_EXECUTOR.execute(()->{
            while (true){
                urls.forEach(url ->{
                    HttpOrHttpsUrlValidatorRequestUtils.requestHttpsGet(url,null,null,"utf-8","text/html");
                    log.info(url);
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
    }

}
