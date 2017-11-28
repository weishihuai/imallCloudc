
package com.imall.iportal.core.main.repository;
import com.imall.commons.base.dao.IBaseRepository;
import com.imall.iportal.core.main.entity.SmsQueue;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface SmsQueueRepository extends IBaseRepository<SmsQueue, Long>,SmsQueueRepositoryCustom {

    void deleteBySendStatCode(String sendStatCode);

    @Query("select max(position) from SmsQueue")
    Long getMaxPosition();

    /**
     * 统计一天内同一手机号的短信验证码条数
     * @param mobile
     * @param start
     * @param end
     * @return
     */
    @Query("select count(*) from SmsQueue s where s.receiverMobile =?1 and s.smsType='VALIDATE_CODE' and s.sendStatCode = 'SUCCESS' and s.sendTime > ?2 and s.sendTime < ?3")
    Integer countDaySendNumByMobile(String mobile, Date start, Date end);

    /**
     * 统计一天内同一IP的短信验证码条数
     * @param reqIp
     * @param start
     * @param end
     * @return
     */
    @Query("select count(*) from SmsQueue s where s.reqIp =?1 and s.smsType='VALIDATE_CODE' and s.sendStatCode = 'SUCCESS' and s.sendTime > ?2 and s.sendTime < ?3")
    Integer countDaySendNumByReqIp(String reqIp, Date start, Date end);

    /**
     * 根据手机号查出最新创建的时间
     * @param mobile
     * @return
     */
    @Query("select max(s.createDate) from SmsQueue s where s.receiverMobile =?1 and s.smsType='VALIDATE_CODE'")
    Date getLastCreateDateByMobile(String mobile);
}

