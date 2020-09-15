package application.service.tengxun;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: wtl
 * @License: (C) Copyright 2020. Corporation Limited.
 * @Contact: 1050100468@qq.com
 * @Date: 2020-09-15 5:44
 * @Version: 1.0
 * @Description:
 */
@Service
@EnableScheduling
@EnableAsync
public class TenXunScheduler {
    @Resource
    private TenXunService tenXunService;

}
