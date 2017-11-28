package com.imall.iportal.core.main.service.impl;


import com.imall.commons.base.global.Global;
import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.service.impl.AbstractBaseService;
import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.base.util.Node;
import com.imall.commons.dicts.BoolCodeEnum;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.entity.*;
import com.imall.iportal.core.main.repository.SysOrgRepository;
import com.imall.iportal.core.main.service.SysOrgService;
import com.imall.iportal.core.main.valid.*;
import com.imall.iportal.core.main.vo.OrgAdminUserAuthInfoVo;
import com.imall.iportal.core.main.vo.OrgUserParamsVo;
import com.imall.iportal.core.main.vo.SysOrgVo;
import com.imall.iportal.dicts.UserSexCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * T_PT_SYS_ORG【组织】(服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class SysOrgServiceImpl extends AbstractBaseService<SysOrg, Long> implements SysOrgService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
	private SysOrgRepository getSysOrgRepository() {
		return (SysOrgRepository) baseRepository;
	}

	@Override
	public Page<SysOrgVo> query(Pageable pageable, Long parentId,String orgName,String orgCode,String phone) {
		Page<SysOrgVo> sysOrgList = getSysOrgRepository().query(pageable,parentId,orgName,orgCode,phone);
		for (SysOrgVo sysOrgVo:sysOrgList){
			if(sysOrgVo.getZoneCode()!=null) {
				StringBuffer stringBuffer= new StringBuffer();
				getZoneNames(sysOrgVo.getZoneCode(),stringBuffer);
				sysOrgVo.setZoneNames(stringBuffer.toString());
			}
		}
		return sysOrgList;
	}

	@Transactional
	@Override
	public SysOrg save(SysOrgSaveValid sysOrgSaveValid) {
		sysOrgSaveValid.setNodeCode(ServiceManager.treeCodeGenerateBuilder.generate8Code(SysOrg.class,sysOrgSaveValid.getParentId()));
		sysOrgSaveValid.setIsInternal(BoolCodeEnum.NO.toCode());
		return save(CommonUtil.copyProperties(sysOrgSaveValid, new SysOrg()));
	}

	@Transactional
	@Override
	public SysOrg update(SysOrgUpdateValid sysOrgUpdateValid) {
		sysOrgUpdateValid.setIsInternal(BoolCodeEnum.NO.toCode());
		return save(CommonUtil.copyProperties(sysOrgUpdateValid, new SysOrg()));
	}

	private void getZoneNames(String zoneCode,StringBuffer stringBuffer){
		SysZone sysSubZone = ServiceManager.sysZoneService.findByZoneCode(zoneCode);
		if(sysSubZone!=null){
			stringBuffer.insert(0, sysSubZone.getZoneName());
		}
		if(sysSubZone!=null){
			SysZone sysZone = ServiceManager.sysZoneService.findOne(sysSubZone.getParentId());
			if(!Global.TREE_DEFAULT_ID.equals(sysZone.getId())) {
				getZoneNames(sysZone.getZoneCode(), stringBuffer);
			}
		}
	}

	@Override
	public List<Node> buildOrgTree(Long loginOrgId, Long parentId, Boolean isIncludeRoot) {
		List<Long> allIds = getTreeIds(loginOrgId);

		List<SysOrg> sysOrgList = getSysOrgRepository().findByParentId(parentId);
		List<Node> nodeList = new ArrayList<>();

		for(SysOrg sysOrg : sysOrgList){
			if(allIds.contains(sysOrg.getId())){
				nodeList.add(buildNode(sysOrg));
			}
		}
		if(isIncludeRoot&& parentId.equals(loginOrgId)){ //Global.TREE_DEFAULT_ID
			List<Node> nodes = new ArrayList<>();
			SysOrg sysOrg = getSysOrgRepository().findOne(loginOrgId); //Global.TREE_DEFAULT_ID
			Node node = buildNode(sysOrg);
			node.setOpen(true);
			node.setChildren(nodeList);
			nodes.add(node);
			return nodes;
		}
		return nodeList;
	}

	private List<Long> getTreeIds(Long id){
		SysOrg sysOrg = getSysOrgRepository().findOne(id);
		List<Long> subIds = getSysOrgRepository().findIdsByParentNodeCode(sysOrg.getNodeCode());
		return getParentIds(id, subIds);
	}

	private List<Long> getParentIds(Long id, List<Long> idList){
		SysOrg sysOrg = getSysOrgRepository().findOne(id);
		idList.add(sysOrg.getParentId());
		if(sysOrg.getParentId() > Global.TREE_DEFAULT_ID){
			return getParentIds(sysOrg.getParentId(), idList);
		}
		return idList;
	}

	@Transactional
	@Override
	public void deleteSysOrg(List<Long> sysOrgIds) {
		for (Long orgId:sysOrgIds){
			Boolean  existsUser =  ServiceManager.sysUserService.existsUserByOrgId(orgId);
			SysOrg sysOrg = findOne(orgId);
			if(existsUser){
				String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.ORG_RELATE_USER_DELETE_ERROR);
				throw new BusinessException(ResGlobal.ORG_RELATE_USER_DELETE_ERROR,String.format(message,new Object[]{sysOrg.getOrgName()}));
			}else{
				Boolean  existsOrgUser = ServiceManager.sysUserOrgJobService.existsUserByOrgId(orgId);
				if (existsOrgUser){
					String message = ServiceManager.sysExceptionCodeService.getMessageByCode(ResGlobal.ORG_RELATE_SERVICE_DELETE_ERROR);
					throw new BusinessException(ResGlobal.ORG_RELATE_SERVICE_DELETE_ERROR,String.format(message,new Object[]{sysOrg.getOrgName()}));
				}

			}
		}
		delete(sysOrgIds);
	}

	private Long saveOrUpdate(SysOrg sysOrg) {
		if(sysOrg.getId()==null){
			sysOrg.setNodeCode(ServiceManager.treeCodeGenerateBuilder.generate8Code(SysOrg.class, sysOrg.getParentId()));
			sysOrg.setIsInternal(BoolCodeEnum.NO.toCode());
		}
		SysOrg dbSysOrg =  save(sysOrg);
		return dbSysOrg.getId();
	}

	@Transactional
	@Override
	public void updateOrgAdminUserAuthInfo(OrgAdminUserAuthInfoVo authInfoVo){
		if(authInfoVo.getUserId()==null||authInfoVo.getOrgId() == null){
			throw new RuntimeException("userId和orgId不能为空");
		}
		SysUserOrgJob userOrgJob = ServiceManager.sysUserOrgJobService.getIsmainByUserId(authInfoVo.getUserId());
		SysOrg sysOrg = findOne(authInfoVo.getOrgId());
		SysUser sysUser = ServiceManager.sysUserService.findOne(userOrgJob.getUserId());
		SysJob sysJob = ServiceManager.sysJobService.findOne(userOrgJob.getJobId());
		List<Long> roleIds = ServiceManager.sysAuthService.findRoleIdsByJobId(userOrgJob.getJobId());

		//更新用户信息
		SysUserUpdateValid sysUserUpdateValid = CommonUtil.copyProperties(sysUser, new SysUserUpdateValid());
		sysUserUpdateValid.setMark(authInfoVo.getOrgName() + "#法人#默认管理员");
		if(StringUtils.isNotBlank(authInfoVo.getMobile())){
			sysUserUpdateValid.setMobile(authInfoVo.getMobile());
		}
		sysUserUpdateValid.setRealName(authInfoVo.getRealName());
		ServiceManager.sysUserService.update(sysUserUpdateValid);

		// 更新机构信息
		sysOrg.setOrgCode(authInfoVo.getOrgCode());
		sysOrg.setOrgName(authInfoVo.getOrgName());
		sysOrg.setZoneCode(authInfoVo.getZoneCode());
		if(StringUtils.isNotBlank(authInfoVo.getAddress())){
			sysOrg.setAddress(authInfoVo.getAddress());
		}
		if(StringUtils.isNotBlank(authInfoVo.getPhone())){
			sysOrg.setPhone(authInfoVo.getPhone());
		}
		if(StringUtils.isNotBlank(authInfoVo.getFax())){
			sysOrg.setFax(authInfoVo.getFax());
		}
		save(sysOrg);

		SysJobUpdateValid sysJobUpdateValid = CommonUtil.copyProperties(sysJob, new SysJobUpdateValid());
		// 更新岗位信息
		sysJobUpdateValid.setJobCode(authInfoVo.getJobCode());
		sysJobUpdateValid.setJobName(authInfoVo.getJobName());
		sysJobUpdateValid.setDescription(authInfoVo.getOrgName() + "#默认主岗位");
		ServiceManager.sysJobService.update(sysJobUpdateValid);

		//更新角色
		if(!roleIds.isEmpty()){
			//查询角色授权时根据ID从小到大排序，因此直接取第一个角色即可
			SysRole sysRole = ServiceManager.sysRoleService.findOne(roleIds.get(0));
			SysRoleUpdateValid sysRoleUpdateValid =  CommonUtil.copyProperties(sysRole, new SysRoleUpdateValid());
			sysRoleUpdateValid.setRoleCode(authInfoVo.getRoleCode());
			sysRoleUpdateValid.setDescription(authInfoVo.getOrgName() + "#默认角色");
			ServiceManager.sysRoleService.update(sysRoleUpdateValid);
		}
	}


	@Transactional
	@Override
	public void createOrgAdminUserAuth(OrgUserParamsVoSaveValid orgUserParamsVoSaveValid) {
		OrgUserParamsVo orgUserParamsVo = CommonUtil.copyProperties(orgUserParamsVoSaveValid, new OrgUserParamsVo());


		//创建岗位
		SysJobSaveValid sysJob = new SysJobSaveValid();
		sysJob.setOrgId(orgUserParamsVoSaveValid.getOrgId());
		sysJob.setJobCode(orgUserParamsVo.getOrgCode());
		sysJob.setJobName("总经理");
		sysJob.setDescription(orgUserParamsVo.getOrgName() + "#默认主岗位");
		sysJob.setOrderby(100l);
		sysJob.setIsAvailable(BoolCodeEnum.YES.toCode());
		sysJob.setIsDefaultAdmin(BoolCodeEnum.YES.toCode());
		SysJob dbSysJob = ServiceManager.sysJobService.save(sysJob);
		//创建角色
		SysRoleSaveValid sysRole = new SysRoleSaveValid();
		sysRole.setOrgId(orgUserParamsVoSaveValid.getOrgId());
		sysRole.setRoleCode(orgUserParamsVo.getOrgCode());
		sysRole.setRoleName("系统管理员");
		sysRole.setDescription(orgUserParamsVo.getOrgName() + "#默认角色");
		sysRole.setOrderby(100l);
		sysRole.setIsAvailable(BoolCodeEnum.YES.toCode());
		sysRole.setIsDefaultAdmin(BoolCodeEnum.YES.toCode());
		SysRole dbSysRole = ServiceManager.sysRoleService.save(sysRole);
		//给岗位分配角色
		SysAuth sysAuth = new SysAuth();
		sysAuth.setJobId(dbSysJob.getId());
		sysAuth.setRoleId(dbSysRole.getId());
		ServiceManager.sysAuthService.save(sysAuth);
		//创建管理员用户
		SysUserSaveValid sysUser = new SysUserSaveValid();
		sysUser.setOrgId(orgUserParamsVoSaveValid.getOrgId());
		sysUser.setShopId(orgUserParamsVoSaveValid.getShopId());
		sysUser.setPassword(orgUserParamsVo.getPassword());
		sysUser.setUserName(orgUserParamsVo.getUserName());
		sysUser.setRealName(orgUserParamsVo.getRealName());
		sysUser.setEmail(orgUserParamsVo.getEmail());
		sysUser.setMobile(orgUserParamsVo.getMobile());
		sysUser.setSalt(orgUserParamsVo.getSalt());
		sysUser.setSex(UserSexCodeEnum.SECRET.toCode());
		sysUser.setIsPostsTrain(BoolCodeEnum.YES.toCode());
		sysUser.setMark(orgUserParamsVo.getOrgName() + "#法人#默认管理员");
		sysUser.setIsDefaultAdmin(BoolCodeEnum.YES.toCode());
		SysUser dbSysUser = ServiceManager.sysUserService.save(sysUser);
		//给用户分配岗位
		SysUserOrgJobSaveValid sysUserOrgJob = new SysUserOrgJobSaveValid();
		sysUserOrgJob.setUserId(dbSysUser.getId());
		sysUserOrgJob.setOrgId(orgUserParamsVoSaveValid.getOrgId());
		sysUserOrgJob.setJobId(dbSysJob.getId());
		sysUserOrgJob.setIsmain(BoolCodeEnum.YES.toCode());
		SysUserOrgJob dbSysUserOrgJob = ServiceManager.sysUserOrgJobService.save(sysUserOrgJob);
		if(orgUserParamsVo.getSysMenuIdList()!=null){
			//组角色分配菜单
			for(Long sysMenuId: orgUserParamsVo.getSysMenuIdList()){
				SysRoleMenu sysRoleMenu = new SysRoleMenu();
				sysRoleMenu.setRoleId(dbSysRole.getId());
				sysRoleMenu.setMenuId(sysMenuId);
				ServiceManager.sysRoleMenuService.save(sysRoleMenu);
			}
		}
		if(orgUserParamsVo.getSysResourceIdList()!=null){
		//组角色分配资源
		for(Long sysResourceId: orgUserParamsVo.getSysResourceIdList()){
			SysRolePermission sysRolePermission = new SysRolePermission();
			sysRolePermission.setRoleId(dbSysRole.getId());
			sysRolePermission.setResourceId(sysResourceId);
			ServiceManager.sysRolePermissionService.save(sysRolePermission);
		}

	}

	}

	@Transactional
	@Override
	public Long saveOrgAdminUserAuth(OrgUserParamsVo orgUserParamsVo){
		SysOrg sysOrg = new SysOrg();
		sysOrg.setParentId(Global.TREE_DEFAULT_ID);
		sysOrg.setOrgCode(orgUserParamsVo.getOrgCode());
		sysOrg.setOrgName(orgUserParamsVo.getOrgName());
		sysOrg.setZoneCode(orgUserParamsVo.getZoneCode());
		sysOrg.setAddress(orgUserParamsVo.getAddress());
		sysOrg.setPhone(orgUserParamsVo.getPhone());
		sysOrg.setFax(orgUserParamsVo.getFax());
		sysOrg.setOrderby(100l);
		sysOrg.setIsAvailable(BoolCodeEnum.YES.toCode());
		return saveOrUpdate(sysOrg);
	}
	/**
	 * 创建树
	 * @param sysOrg
	 * @return
	 */
	private Node buildNode(SysOrg sysOrg){
		long subSysOrgCount = getSysOrgRepository().countByParentId(sysOrg.getId());
		Node node = new Node();
		node.setId(sysOrg.getId());
		node.setName(sysOrg.getOrgName());
		node.setNodeCode(sysOrg.getNodeCode());
		node.setOpen(false);
		node.setIsParent(subSysOrgCount>0);
		return node;
	}
}