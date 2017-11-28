package com.imall.iportal.core.main.web.controller;

import com.imall.commons.base.global.Global;
import com.imall.commons.base.global.ResGlobal;
import com.imall.commons.base.interceptor.BusinessException;
import com.imall.commons.base.web.BaseRestSpringController;
import com.imall.iportal.core.main.commons.ServiceManager;
import com.imall.iportal.core.main.vo.CurrUserVo;
import com.imall.iportal.core.main.vo.SysFileLibSearchParamVo;
import com.imall.iportal.core.main.vo.UploadFileVo;
import com.imall.iportal.core.main.web.vo.FileRenameVo;
import com.imall.iportal.shiro.bind.annotation.CurrUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/sysFileLib")
public class SysFileLibController extends BaseRestSpringController {
    public static final String JSON = "application/json";
    private static final String PICT_TMP_PERFIX = "picttmp";

    private static Logger logger = Logger.getLogger(SysFileLibController.class);

    @ResponseBody
    @RequestMapping(value = "/findFileLib", method = {RequestMethod.GET, RequestMethod.POST})
    public Object findFileLib(ModelMap model, @PageableDefault(page = 0, size = 10) Pageable pageable, @CurrUser CurrUserVo currUserVo, Integer fileCategoryId, String fileTypeCode, String searchContent) {
        SysFileLibSearchParamVo paramMsg = new SysFileLibSearchParamVo();
        paramMsg.setFileCategoryId(fileCategoryId == null ? Global.TREE_DEFAULT_ID : fileCategoryId);
        paramMsg.setFileTypeCode(fileTypeCode);
        paramMsg.setSearchContent(searchContent);
        return ServiceManager.sysFileLibService.findFileLib(pageable, paramMsg, currUserVo.getOrgId());

    }

    @ResponseBody
    @RequestMapping(value = "/upload.html", method = RequestMethod.POST)
    public Object upload(HttpServletRequest request, @CurrUser CurrUserVo currUserVo) throws IOException {
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = null;
        try {
            multipartRequest = resolver.resolveMultipart(request);
        } catch (Exception e) {
            logger.error("AdminAccountController submitVerifyFile 非常的请求");
        }

        List<UploadFileVo> fileList = new ArrayList<>();
        assert multipartRequest != null;
        MultipartFile uploadFile = multipartRequest.getFile("fileList");
        String categoryId = multipartRequest.getParameter("categoryId");
        String fileName = uploadFile.getOriginalFilename();

        File tmpFile = File.createTempFile(PICT_TMP_PERFIX + System.currentTimeMillis(), fileName);
        uploadFile.transferTo(tmpFile);

        UploadFileVo fileVo = new UploadFileVo();
        fileVo.setFileNm(fileName);
        fileVo.setFile(tmpFile);
        fileList.add(fileVo);

        List<String> fieldIdList = ServiceManager.sysFileLibService.saveFiles(fileList, StringUtils.isBlank(categoryId) ? 1 : Long.valueOf(categoryId), currUserVo.getOrgId());
        if (fieldIdList.isEmpty()) {
            throw new BusinessException(ResGlobal.WEB_UPLOAD_FILE_ERROR, "文件上传出错");
        }
        logger.info(StringUtils.join(fieldIdList, ","));
        return getSuccess();
    }

    /**
     * 删除文件分类
     */
    @RequestMapping(value = "/renameFileName", method = RequestMethod.POST)
    @ResponseBody
    public Object renameFileName(@RequestBody FileRenameVo fileRenameVo, @CurrUser CurrUserVo currUserVo) {
        ServiceManager.sysFileLibService.renameFileName(fileRenameVo.getId(), fileRenameVo.getFileNm(), currUserVo.getOrgId());
        return getSuccess();
    }

    /**
     * 修改文件的分类
     */
    @RequestMapping(value = "/changeFileCategory", method = RequestMethod.POST)
    @ResponseBody
    public Object changeFileCategory(@RequestBody List<Long> ids, Long fileCategoryId, @CurrUser CurrUserVo currUserVo) {
        ServiceManager.sysFileLibService.changeFileCategory(ids, fileCategoryId, currUserVo.getOrgId());
        return getSuccess();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestBody List<Long> ids, @CurrUser CurrUserVo currUserVo) {
        ServiceManager.sysFileLibService.delete(ids, currUserVo.getOrgId());
        return getSuccess();
    }

    @RequestMapping(value = "/downloadFiles", method = RequestMethod.POST)
    @ResponseBody
    public Object downloadFiles(@RequestBody List<Long> ids, @CurrUser CurrUserVo currUserVo) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("fileUrl", ServiceManager.sysFileLibService.downloadFiles(ids, currUserVo.getOrgId()));
        return modelMap;
    }

    @RequestMapping(value = "/downloadFile",method = RequestMethod.GET)
    @ResponseBody
    public  void downloadFile(Long id, HttpServletResponse response){
        ServiceManager.sysFileLibService.downloadFile(id, response);
    }
}

