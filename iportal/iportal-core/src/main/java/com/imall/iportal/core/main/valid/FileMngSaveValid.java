package com.imall.iportal.core.main.valid;

/**
 * Created by hwj on 2017/08/12
 */
public class FileMngSaveValid implements Cloneable, java.io.Serializable{

    public static final long serialVersionUID = -15263788L;

    private String objectTypeCode;
    private Long objectId;
    private Long sysFileLibId;
    private String fileId;

    public String getObjectTypeCode() {
        return objectTypeCode;
    }

    public void setObjectTypeCode(String objectTypeCode) {
        this.objectTypeCode = objectTypeCode;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getSysFileLibId() {
        return sysFileLibId;
    }

    public void setSysFileLibId(Long sysFileLibId) {
        this.sysFileLibId = sysFileLibId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

}
