package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.global.Global;
import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.BigDecimalUtil;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.DateTimeUtils;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.commons.dicts.MarriageStateCodeEnum;
import com.imall.commons.dicts.PayWayTypeCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.SysJob;
import com.imall.iportal.core.main.entity.SysOrg;
import com.imall.iportal.core.main.entity.SysUser;
import com.imall.iportal.core.main.entity.SysUserOrgJob;
import com.imall.iportal.core.main.events.event.ModifyPasswordEvent;
import com.imall.iportal.core.main.repository.SysUserRepository;
import com.imall.iportal.core.main.service.SysUserService;
import com.imall.iportal.core.main.valid.SysUserOrgJobSaveValid;
import com.imall.iportal.core.main.valid.SysUserSaveValid;
import com.imall.iportal.core.main.valid.SysUserUpdateValid;
import com.imall.iportal.core.main.vo.*;
import com.imall.iportal.core.platform.entity.Shop;
import com.imall.iportal.core.salespos.entity.ShiftRecord;
import com.imall.iportal.core.shop.commons.ResGlobalExt;
import com.imall.iportal.core.shop.entity.StaffHealthDoc;
import com.imall.iportal.core.shop.entity.WeShop;
import com.imall.iportal.core.shop.service.impl.DateSerialGenerator;
import com.imall.iportal.dicts.UserSexCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * T_SYS_USER【用户】(服务层实现)
 *
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysUserServiceImpl extends AbstractBaseService<SysUser, Long> implements SysUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @SuppressWarnings("unused")
    private SysUserRepository getSysUserRepository() {
        return (SysUserRepository) baseRepository;
    }

    @Autowired
    private ApplicationContext applicationContext;


    @Override
    public SysUserDetailVo findById(Long id, Long orgId) {
        SysUser sysUser = findOne(id);
        if (sysUser == null || !sysUser.getOrgId().equals(orgId)) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobalExt.COMMON_OBJECT_NO_FOUND);
            throw new BusinessException(ResGlobalExt.COMMON_OBJECT_NO_FOUND, message);
        }
        //基本信息
        SysUserDetailVo sysUserDetailVo = CommonUtil.copyProperties(sysUser, new SysUserDetailVo());
        sysUserDetailVo.setMarriageStatName(StringUtils.isNotBlank(sysUser.getMarriageStatCode()) ? MarriageStateCodeEnum.fromCode(sysUser.getMarriageStatCode()).toName() : null);
        sysUserDetailVo.setSexName(StringUtils.isNotBlank(sysUser.getSex()) ? UserSexCodeEnum.fromCode(sysUser.getSex()).toName() : null);
        sysUserDetailVo.setBirthday(sysUser.getBirthdayString());
        //教育经历
        sysUserDetailVo.setGraduationTime(sysUser.getGraduationTimeString());
        sysUserDetailVo.setJoinWorkTime(sysUser.getJoinWorkTimeString());
        List<SysUserOrgJob> sysUserOrgJobVos = ServiceManager.sysUserOrgJobService.findByUserId(id);
        //岗位
        List jobList = new ArrayList();
        for (SysUserOrgJob sysUserOrgJobVo : sysUserOrgJobVos) {
            jobList.add(sysUserOrgJobVo.getJobId());
        }
        sysUserDetailVo.setJobSelectList(jobList);
        //行政信息
        sysUserDetailVo.setEntryJobTime(sysUser.getEntryJobTimeString());
        sysUserDetailVo.setLeaveTime(sysUser.getLeaveTimeString());
        //账号与密码
        sysUserDetailVo.setUserName(sysUser.getUserName());
        return sysUserDetailVo;
    }

    @Override
    public SysUser findOneBySafe(Long paramID) {
        SysUser sysUser = findOne(paramID);
        if (sysUser == null) {
            return null;
        }
        sysUser.setPassword("");
        sysUser.setSalt("");
        return sysUser;
    }

    @Override
    public Page<SysUserPageVo> query(Pageable pageable, UserParamsVo paramsVo) throws ParseException {
        Page<SysUser> page = getSysUserRepository().query(pageable, paramsVo);
        List<SysUserPageVo> sysUserPageVoList = new ArrayList<>();
        for (SysUser user : page.getContent()) {
            SysUserPageVo sysUserPageVo = CommonUtil.copyProperties(user, new SysUserPageVo());
            sysUserPageVo.setSex(StringUtils.isNotBlank(user.getSex()) ? UserSexCodeEnum.fromCode(user.getSex()).toName() : null);
            sysUserPageVo.setCreateTimeString(DateTimeUtils.convertDateToString(user.getCreateDate()));
            sysUserPageVoList.add(sysUserPageVo);
        }
        return new PageImpl<>(sysUserPageVoList, pageable, page.getTotalElements());
    }

    @Override
    public SysUser getUser(String loginIdEmailMobile) {
        return getSysUserRepository().findByUserNameOrEmailOrMobile(loginIdEmailMobile, loginIdEmailMobile, loginIdEmailMobile);
    }

    @Override
    public SysUser getByLoginId(String loginId) {
        return getSysUserRepository().findByUserName(loginId);
    }

    private SysUser saveOrUpdate(SysUser sysUser) throws BusinessException {
        //判断登录 ID不能重复
        SysUser tempUser = getByLoginId(sysUser.getUserName());
        if (tempUser != null && (sysUser.getId() == null || !sysUser.getId().equals(tempUser.getId()))) {
            String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.USER_NAME_IS_EXIST);
            throw new BusinessException(ResGlobal.USER_NAME_IS_EXIST, String.format(message, new Object[]{sysUser.getUserName()}));
        }

        if (sysUser.getId() == null) {
            //保存
            sysUser.setIsDeleted(BoolCodeEnum.NO.toCode());
            //加盐 加密
            sysUser.setOrgId(sysUser.getOrgId() == null ? Global.ADMIN_DEFAULT_ORG_ID : sysUser.getOrgId());
            save(sysUser);
            return sysUser;
        } else {
            //修改
            SysUser dbUser = getSysUserRepository().findOne(sysUser.getId());
            dbUser.setRealName(sysUser.getRealName());
            dbUser.setEmail(sysUser.getEmail());
            dbUser.setMobile(sysUser.getMobile());
            dbUser.setSex(sysUser.getSex());
            dbUser.setMark(sysUser.getMark());
            dbUser.setIdentityCard(sysUser.getIdentityCard());
            dbUser.setNativePlace(sysUser.getNativePlace());
            dbUser.setMarriageStatCode(sysUser.getMarriageStatCode());
            dbUser.setBirthday(sysUser.getBirthday());
            dbUser.setHomeAddr(sysUser.getHomeAddr());
            dbUser.setSchoolNm(sysUser.getSchoolNm());
            dbUser.setMajor(sysUser.getMajor());
            dbUser.setAcademicQualificati(sysUser.getAcademicQualificati());
            dbUser.setGraduationTime(sysUser.getGraduationTime());
            dbUser.setJoinWorkTime(sysUser.getJoinWorkTime());
            dbUser.setTechnologyPostTitle(sysUser.getTechnologyPostTitle());
            dbUser.setEntryJobTime(sysUser.getEntryJobTime());
            dbUser.setIsPostsTrain(BoolCodeEnum.fromCode(sysUser.getIsPostsTrain()).toCode());
            dbUser.setLeaveTime(sysUser.getLeaveTime());
            dbUser.setLeaveReason(sysUser.getLeaveReason());
            save(dbUser);
            return dbUser;
        }
    }

    @Transactional
    @Override
    public SysUser save(SysUserSaveValid sysUserSaveValid) {

        SysUser sysUser = CommonUtil.copyProperties(sysUserSaveValid, new SysUser());
        sysUser.setEmployeeCode(DateSerialGenerator.genSerialCode(DateSerialGenerator.EMPLOYEE_PREFIX));

        sysUser.setIsEnable(BoolCodeEnum.YES.toCode());
        sysUser.setMarriageStatCode(StringUtils.isNotBlank(sysUserSaveValid.getMarriageStatCode()) ? MarriageStateCodeEnum.fromCode(sysUserSaveValid.getMarriageStatCode()).toCode() : null);
        Long id = saveOrUpdate(sysUser).getId();

        //给用户分配岗位
        if (sysUserSaveValid.getJobSelectList() != null) {
            for (int i = 0; i < sysUserSaveValid.getJobSelectList().size(); i++) {
                SysUserOrgJobSaveValid sysUserOrgJob = new SysUserOrgJobSaveValid();
                sysUserOrgJob.setUserId(id);
                sysUserOrgJob.setOrgId(sysUserSaveValid.getOrgId());
                sysUserOrgJob.setJobId(sysUserSaveValid.getJobSelectList().get(i));
                sysUserOrgJob.setIsmain(i == 0 ? BoolCodeEnum.YES.toCode() : BoolCodeEnum.NO.toCode());
                ServiceManager.sysUserOrgJobService.save(sysUserOrgJob);
            }
        }
        //新增档案信息
        StaffHealthDoc staffHealthDoc = new StaffHealthDoc();
        staffHealthDoc.setShopId(sysUserSaveValid.getShopId());
        staffHealthDoc.setStaffId(id);
        ServiceManager.staffHealthDocService.saveStaffHealthDoc(staffHealthDoc);

        return sysUser;

    }

    @Transactional
    @Override
    public SysUser update(SysUserUpdateValid sysUserUpdateValid) {
        SysUser sysUser = CommonUtil.copyProperties(sysUserUpdateValid, new SysUser());
        //清除旧 岗位信息
        ServiceManager.sysUserOrgJobService.deleteByUserId(sysUserUpdateValid.getId());
        //给用户分配岗位
        for (int i = 0; i < sysUserUpdateValid.getJobSelectList().size(); i++) {
            SysUserOrgJobSaveValid sysUserOrgJob = new SysUserOrgJobSaveValid();
            sysUserOrgJob.setUserId(sysUserUpdateValid.getId());
            sysUserOrgJob.setJobId(sysUserUpdateValid.getJobSelectList().get(i));
            sysUserOrgJob.setOrgId(sysUserUpdateValid.getOrgId());
            sysUserOrgJob.setIsmain(i == 0 ? BoolCodeEnum.YES.toCode() : BoolCodeEnum.NO.toCode());
            ServiceManager.sysUserOrgJobService.save(sysUserOrgJob);

        }
        return saveOrUpdate(sysUser);
    }

    @Transactional
    @Override
    public void updateIsEnable(List<Long> ids, String isEnable) throws BusinessException {
        String userState = BoolCodeEnum.fromCode(isEnable).toCode();
        for (Long id : ids) {
            SysUser user = baseRepository.findOne(id);
            user.setIsEnable(BoolCodeEnum.fromCode(userState).toCode());
            save(user);
        }
    }

    @Transactional
    @Override
    public void modifyPassword(Long id, String password, String salt) throws BusinessException {
        SysUser dbUser = baseRepository.findOne(id);
        dbUser.setPassword(password);
        dbUser.setSalt(salt);
        save(dbUser);
        //修改密码触发事件发Email或短信通知用户
        applicationContext.publishEvent(new ModifyPasswordEvent(dbUser));
    }

    @Override
    public Boolean existsUserByOrgId(Long orgId) {
        return getSysUserRepository().existsUserByOrgId(orgId);
    }

    @Override
    @Transactional
    public void deleteUser(List<Long> userIdList) {
        for (Long id : userIdList) {
            /*Boolean isExistsRel =sysUserOrgJobService.existsUserByUserId(id);
			if(isExistsRel){
				SysUser sysUser = findOne(id);
				String message = sysExceptionCodeService.getMessageByCode(ResGlobal.USER_ORG_JOR_RELATE_DELETE_ERROR);
				throw new BusinessException(ResGlobal.USER_ORG_JOR_RELATE_DELETE_ERROR,String.format(message,new Object[]{sysUser.getUserName()}));
			}*/
            SysUser sysUser = findOne(id);
            sysUser.setUserName(sysUser.getUserName() + Global.DELETE_SIGN_SUFFIX);
            sysUser.setRealName(sysUser.getRealName() + Global.DELETE_SIGN_SUFFIX);
            sysUser.setEmail(sysUser.getEmail() + Global.DELETE_SIGN_SUFFIX);
            sysUser.setMobile(sysUser.getMobile() + Global.DELETE_SIGN_SUFFIX);
            sysUser.setIsDeleted(BoolCodeEnum.YES.toCode());
            save(sysUser);
        }

    }

    @Override
    public List<SysUserVo> findVoByServiceOrgId(Long serviceOrgId) {
        return getSysUserRepository().findVoByServiceOrgId(serviceOrgId);
    }

    @Override
    public List<SysUser> findByServiceOrgIdNotDeleted(Long serviceOrgId) {
        return getSysUserRepository().findByServiceOrgIdNotDeleted(serviceOrgId);
    }

    @Override
    public SysUser findByUserName(String userName) {
        return getSysUserRepository().findByUserName(userName);
    }

    @Override
    public Boolean findByName(String userName) {
        SysUser sysUser = getSysUserRepository().findByUserName(userName);
        if (sysUser != null) {
            return BoolCodeEnum.NO.toCode().equals(sysUser.getIsEnable());
        } else {
            return false;
        }

    }

    @Override
    public SysUserLoginVo findCurrentLoginUserMessage(CurrUserVo currUser) {
        SysUserLoginVo sysUserLoginVo = new SysUserLoginVo();
        sysUserLoginVo.setShopId(sysUserLoginVo.getShopId());
        sysUserLoginVo.setId(this.findOne(currUser.getUserId()).getId());
        sysUserLoginVo.setRealName(this.findOne(currUser.getUserId()).getRealName());
        return sysUserLoginVo;
    }

    @Override
    public CurrUserVo getCurrUserVo(Long userId, Long userOrgJobId) {
        SysUser sysUser = ServiceManager.sysUserService.findOne(userId);
        SysUserOrgJob userOrgJob = ServiceManager.sysUserOrgJobService.findOne(userOrgJobId);
        SysOrg sysOrg = ServiceManager.sysOrgService.findOne(userOrgJob.getOrgId());
        SysJob sysJob = ServiceManager.sysJobService.findOne(userOrgJob.getJobId());

        CurrUserVo currUserVo = new CurrUserVo();
        currUserVo.setUserOrgJobId(userOrgJob.getId());
        currUserVo.setUserId(sysUser.getId());
        currUserVo.setOrgId(userOrgJob.getOrgId());
        currUserVo.setJobId(userOrgJob.getJobId());

        currUserVo.setUserName(sysUser.getUserName());
        currUserVo.setOrgName(sysOrg.getOrgName());
        currUserVo.setJobName(sysJob.getJobName());

        Shop shop = ServiceManager.shopService.findByOrgId(currUserVo.getOrgId());
        if (shop != null) {
            currUserVo.setShopId(shop.getId());
            currUserVo.setShopEntName(shop.getEntNm());
            WeShop weShop = ServiceManager.weShopService.findByShopId(shop.getId());
            if (weShop != null) {
                currUserVo.setWeShopId(weShop.getId());
                currUserVo.setWeShopName(weShop.getShopNm());
            }
        }

        return currUserVo;
    }

    @Override
    public Boolean checkUserNameIsExist(String name, Long id) {
        SysUser sysUser = getSysUserRepository().findByUserName(name);
        return (sysUser == null && id == null) || sysUser == null || sysUser.getId().equals(id);
    }

    @Override
    public SysUserShopVo findByShopIdAndIsDefaultAdminAndOrgId(Long shopId, String isDefaultAdmin, Long orgId) {
        SysUser sysUserList = getSysUserRepository().findByShopIdAndIsDefaultAdminAndOrgId(shopId, isDefaultAdmin, orgId);
        if (sysUserList != null) {
            return CommonUtil.copyProperties(sysUserList, new SysUserShopVo());
        }
        return new SysUserShopVo();
    }

    @Override
    public Long updateMobileAndEmail(Long id, String moblile, String email) {
        SysUser sysUser = findOne(id);
        if (StringUtils.isNotBlank(email)) {
            sysUser.setEmail(email);
        }
        if (StringUtils.isNotBlank(moblile)) {
            sysUser.setMobile(moblile);
        }
        return save(sysUser).getId();
    }

    @Override
    public List<SysUser> findByShopId(Long shopId) {
        return getSysUserRepository().findByShopId(shopId);
    }

    @Override
    public List<SysUser> findNormalByShopId(Long shopId) {
        return getSysUserRepository().findByShopIdAndIsEnable(shopId, BoolCodeEnum.YES.toCode());
    }

    @Override
    public LoginCashierVo findByLoginCashier(Long shopId, Long sysUserId) {
        ShiftRecord record = ServiceManager.shiftRecordService.findLastNotShift(shopId, sysUserId);
        LoginCashierVo loginCashierVo = new LoginCashierVo();
        SysUser posManUser = ServiceManager.sysUserService.findOne(sysUserId);
        loginCashierVo.setUserName(posManUser.getRealName());
        Date now = new Date();

        if(record == null) {
            loginCashierVo.setSucceedTimeString("");
            loginCashierVo.setAddUpAmount(0D);
            loginCashierVo.setCashAmount(0D);
            loginCashierVo.setReturnedPurchaseAmount(0D);
            return loginCashierVo;
        }

        loginCashierVo.setSucceedTimeString(record.getSucceedTimeString());
        //收款金额
        Double receiptAmount = ServiceManager.orderService.sumOrderTotalAmountByShopIdAndPosMan(shopId, sysUserId, record.getSucceedTime(), now);
        //合计金额 = 收款金额
        loginCashierVo.setAddUpAmount(receiptAmount);;
        //退货金额
        Double returnedPurchaseAmount = ServiceManager.orderService.sumOrderReturnedPurchaseAmountByShopIdAndPosMan(shopId, sysUserId, record.getSucceedTime(), now);
        loginCashierVo.setReturnedPurchaseAmount(returnedPurchaseAmount);
        //现金收款
        Double cashTotalAmount = ServiceManager.orderService.sumOrderPayWayAmountByShopIdAndPosMan(shopId, sysUserId, record.getSucceedTime(), now, PayWayTypeCodeEnum.CASH_PAY);
        //找零
        Double returnSmallAmount = ServiceManager.orderService.sumOrderReturnSmallAmountByShopIdAndPosMan(shopId, sysUserId, record.getSucceedTime(), now);
        //现金 = 现金收款 - 现金找零
        loginCashierVo.setCashAmount(BigDecimalUtil.subtract(cashTotalAmount, returnSmallAmount));
        return loginCashierVo;
    }

}