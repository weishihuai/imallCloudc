
package com.imall.iportal.core.weshop.repository;

import com.imall.iportal.core.weshop.entity.*;
import com.imall.commons.base.dao.IBaseRepository;
import org.springframework.data.jpa.repository.Query;
/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface WeChatUserRepository extends  IBaseRepository<WeChatUser, Long>,WeChatUserRepositoryCustom {

    WeChatUser findByOpenId(String openId);

    WeChatUser findByMobile(String mobile);
}

