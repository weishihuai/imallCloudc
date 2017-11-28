package com.imall.iportal.core.weshop.vo;

/**
 * Created by wsh on 2017/8/16.
 * 微店粉丝 相关数量统计
 */
public class FansTotalVo {
    /**
     * 粉丝总数
     */
   private Long fansTotalCount;

    /**
     * 粉丝(会员身份)总数
     */
   private Long fansIsMemberTotalCount;

    /**
     * 粉丝(会员身份)总数
     */
    private Long fansIsNotMemberTotalCount;

    public Long getFansTotalCount() {
        return fansTotalCount;
    }

    public void setFansTotalCount(Long fansTotalCount) {
        this.fansTotalCount = fansTotalCount;
    }

    public Long getFansIsMemberTotalCount() {
        return fansIsMemberTotalCount;
    }

    public void setFansIsMemberTotalCount(Long fansIsMemberTotalCount) {
        this.fansIsMemberTotalCount = fansIsMemberTotalCount;
    }

    public Long getFansIsNotMemberTotalCount() {
        return fansIsNotMemberTotalCount;
    }

    public void setFansIsNotMemberTotalCount(Long fansIsNotMemberTotalCount) {
        this.fansIsNotMemberTotalCount = fansIsNotMemberTotalCount;
    }
}
