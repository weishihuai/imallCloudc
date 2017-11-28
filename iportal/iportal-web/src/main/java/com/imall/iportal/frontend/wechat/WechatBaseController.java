package com.imall.iportal.frontend.wechat;
import com.imall.commons.base.vo.ResultVo;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;

public class WechatBaseController {
    protected static final String CONTENT = "content";
    protected static final String TOTAL_ELEMENTS = "totalElements";
    protected static final String SUCCESS = "success";
    protected static final String RESULT = "result";
    protected static final String MESSAGE = "message";
    protected static final String DATE_FORMAT = "yyyy-MM-dd";
    protected static final String ERROR_CODE = "errorCode";
    protected static final ResultVo success = new ResultVo(true);
    protected static final ResultVo failure = new ResultVo(false);

    public final ResultVo getSuccess(){
        return success;
    }
    public void renderPage(ModelMap model, Page page){
        model.put(CONTENT, page.getContent());
        model.put(TOTAL_ELEMENTS, page.getTotalElements());
    }

    public void setSuccess(ModelMap model) {
        model.put(SUCCESS, true);
    }

    public void setFailure(ModelMap model) {
        model.put(SUCCESS,false);
    }
}
