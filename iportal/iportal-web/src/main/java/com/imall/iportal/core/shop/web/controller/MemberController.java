package com.imall.iportal.core.shop.web.controller;

import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.shop.entity.Member;
import com.imall.iportal.core.shop.vo.MemberSaveVo;
import com.imall.iportal.core.shop.vo.MemberSearchParam;
import com.imall.iportal.core.shop.vo.MemberUpdateVo;
import com.imall.iportal.core.shop.vo.SalesPosMemberSaveVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class MemberController extends BaseRestSpringController {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Object save(@RequestBody MemberSaveVo memberSaveVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        memberSaveVo.setShopId(currUserVo.getShopId());
        ServiceManager.memberService.save(memberSaveVo,currUserVo);
        return getSuccess();
    }

    @RequestMapping(value = "/checkMemberMobileIsExist", method = RequestMethod.POST)
    @ResponseBody
    public Object checkMemberMobileIsExist(@RequestBody Member member, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.memberService.checkMemberMobileIsExist(member.getMobile(), member.getId(), currUserVo.getShopId());
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public Object findById(Long id, @CurrUser CurrUserVo currUserVo) {
        return ServiceManager.memberService.findById(id, currUserVo.getShopId());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object query(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, MemberSearchParam memberSearchParam, @CurrUser CurrUserVo currUserVo) {
        memberSearchParam.setShopId(currUserVo.getShopId());
        return ServiceManager.memberService.query(pageable, memberSearchParam);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@RequestBody MemberUpdateVo memberUpdateVo, @CurrUser CurrUserVo currUserVo) throws ParseException {
        memberUpdateVo.setShopId(currUserVo.getShopId());
        ServiceManager.memberService.update(memberUpdateVo);
        return getSuccess();
    }

    @RequestMapping(value = "/findMemberAndConvertCart", method = RequestMethod.GET)
    @ResponseBody
    public Object findMemberAndConvertCart(ModelMap model, String searchText, String uniqueKey, @CurrUser CurrUserVo currUserVo) {
        Member member = ServiceManager.memberService.findMemberAndConvertCart(searchText, uniqueKey, currUserVo.getShopId());
        Map<String, Object> map = new HashMap<>();
        if (member != null) {
            map.put("member", member);
            map.put("cart", ServiceManager.salesPosShoppingFlowService.getCart(uniqueKey));
        }
        return map;
    }

    @RequestMapping(value = "/saveSalesPosMember", method = RequestMethod.POST)
    @ResponseBody
    public Object saveSalesPosMember(@RequestBody SalesPosMemberSaveVo salesPosMemberSaveVo, @CurrUser CurrUserVo currUserVo) {
        salesPosMemberSaveVo.setShopId(currUserVo.getShopId());
        ServiceManager.memberService.saveSalesPosMember(salesPosMemberSaveVo);
        return getSuccess();
    }

}

