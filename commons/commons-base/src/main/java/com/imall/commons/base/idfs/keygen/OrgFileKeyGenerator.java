package com.imall.commons.base.idfs.keygen;

import com.imall.commons.base.idfs.IFileKeyGenerator;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;


public class OrgFileKeyGenerator implements IFileKeyGenerator {
    @Override
    public String create(String name) {
        return getOrgPath(name,null);
    }

    @Override
    public String create(File file,Long orgId) {
        return getOrgPath(file.getName(),orgId);
    }

    private String getOrgPath(String fileName,Long orgId) {
        StringBuffer buffer = new StringBuffer();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        String uuid = UUID.randomUUID().toString();
        /*机构ID作为第一目录*/
        buffer. append(orgId)
                .append("/").append(cal.get(Calendar.YEAR))
                .append("/").append(cal.get(Calendar.MONTH) + 1)
                .append("/").append(cal.get(Calendar.DAY_OF_MONTH))
                .append("/").append(uuid)
                .append(fileName.substring(fileName.lastIndexOf('.')));
        return buffer.toString();
    }
}
