package com.jd.em.base.service.log;

import com.jd.em.base.domain.PageData;
import com.jd.em.base.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author KING
 *
 * <p>Description:日志service</p>
 *
 * @date 2019-06-26
 */
@Service
public class LogService {

    @Autowired
    private LogMapper logMapper;

    @Async("taskExecutor")
    public void saveLog(PageData pageData) {
        try {
            this.logMapper.saveLog(pageData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
