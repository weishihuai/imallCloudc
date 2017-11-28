package com.imall.commons.base.util.excel;

import com.imall.commons.base.util.CommonUtil;
import com.imall.commons.dicts.BoolCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.record.RecordFormatException;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ygw on 2017/8/24.
 */
public abstract class BaseExcelReader<T>{

    protected abstract Map<String, Object> convert(Map<String, Object> valueMap);

    protected abstract boolean verifyExt(int lineNumber, Collection<String> protocolList, Map<String, Object> valueMap, List<ErrorLog> errorLogList);

    protected boolean verify(int lineNumber, Collection<String> protocolList, Map<String, Object> valueMap, List<ErrorLog> errorLogList) {
        boolean isVerifyOk = true;
        for(String protocol: protocolList){
            if(StringUtils.isNotBlank(protocol) && protocol.split(":").length == 4){
                String code = protocol.split(":")[0].trim();
                String type = protocol.split(":")[1];
                String isNull = protocol.split(":")[2];
                String length = protocol.split(":")[3].trim();

                Object value = valueMap.get(code);

                if("NotNull".equals(isNull)){
                    if(value==null || "String".equals(type) && StringUtils.isBlank((String)value)){
                        errorLogList.add(new ErrorLog(lineNumber, code + "列不能为空"));
                        isVerifyOk = false;
                    }
                }

                if(value==null){
                    continue;
                }

                if("String".equals(type)){
                    String val = (String)value;
                    if(val.length() > Integer.valueOf(length)){
                        errorLogList.add(new ErrorLog(lineNumber, code + "列不能多于" + length + "个字符"));
                        isVerifyOk = false;
                    }
                }
            }
        }

        boolean verifyExt = verifyExt(lineNumber, protocolList, valueMap, errorLogList);
        return isVerifyOk ? verifyExt : isVerifyOk;
    }

    protected abstract Class getBeanClass();

    /**
     *
     * @param localFileId 物理路径下的文件名
     * @param errorLogList
     * @return
     */
    public List<T> importData(String localFileId, List<ErrorLog> errorLogList) {
        Workbook workbook = loadExcel(localFileId);
        Sheet sheet = workbook.getSheetAt(0);
        Row protocolRow = sheet.getRow(0);   //第一行固定为协议行
        Map<Integer, String> protocolMap = readFromProtocolCell(protocolRow);
        int r = 2;
        List<T> result = new ArrayList<T>();
        for (; r <= sheet.getLastRowNum(); r++) {  //第三行才是真正的数据行
            Row row = sheet.getRow(r);
            T bean = null;
            try {
                bean = (T)getBeanClass().newInstance();
                Map<String, Object> valueMap = readFromDataCell(row, protocolMap);
                if(valueMap.size() > 0){
                    if(verify(r + 1, protocolMap.values(), valueMap, errorLogList)){
                        valueMap = convert(valueMap);
                        CommonUtil.copyProperties(valueMap, bean);
                        result.add(bean);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private Workbook loadExcel(String localFileId) {
        try {
            return new HSSFWorkbook(new POIFSFileSystem(new File(localFileId)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (RecordFormatException rfe){
            throw new RuntimeException(rfe);
        }
    }

    private Map<Integer, String> readFromProtocolCell(Row row) {
        Map<Integer, String> protocolMap = new HashMap<Integer, String>();
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if(cell!=null){
//                cell.setCellType(Cell.CELL_TYPE_STRING);
                String cellValue = (String) getCellValue(cell, "String");
                if(StringUtils.isNotBlank(cellValue)){
                    protocolMap.put(i, cellValue.trim());
                }
            }
        }
        return protocolMap;
    }

    private Map<String, Object> readFromDataCell(Row row, Map<Integer, String> protocolMap) {
        Map<String, Object> valueMap = new HashMap<String, Object>();
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if(cell!=null){
//                cell.setCellType(Cell.CELL_TYPE_STRING);
                String protocol = protocolMap.get(i);  //例：supplierCode:String:Null:32
                if (StringUtils.isNotBlank(protocol) && protocol.split(":").length==4) {
                    String code = protocol.split(":")[0].trim();
                    String type = protocol.split(":")[1];
                    Object cellValue = getCellValue(cell, type);
                    valueMap.put(code, cellValue);
                }
            }
        }
        return valueMap;
    }

    private Object getCellValue(Cell cell, String type){
        Object value = null;
        if(cell==null){
            return value;
        }
        if("Integer".equals(type)){
            value = getIntegerCellValue(cell);
        }
        else if("Double".equals(type)){
            value = getDoubleCellValue(cell);
        }
        else if("Long".equals(type)){
            value = getLongCellValue(cell);
        }
        else {
            value = getStringCellValue(cell);
        }
        return value;
    }

    public Double getDoubleCellValue(Cell cell){
        Double value = 0d;
        if(cell==null){
            return value;
        }
        switch (cell.getCellTypeEnum()){
            case STRING:
                if(StringUtils.isNotBlank(cell.getStringCellValue())){
                    value = Double.valueOf(cell.getStringCellValue().trim());
                }
                break;
            case NUMERIC:
                value = cell.getNumericCellValue();
                break;
            default:
                break;
        }
        return value==null ? 0d : value;
    }

    public Long getLongCellValue(Cell cell){
        Long value = 0L;
        if(cell==null){
            return value;
        }
        switch (cell.getCellTypeEnum()){
            case STRING:
                if(StringUtils.isNotBlank(cell.getStringCellValue())){
                    value = Long.valueOf(cell.getStringCellValue().trim());
                }
                break;
            case NUMERIC:
                value = Double.valueOf(cell.getNumericCellValue()).longValue();
                break;
            default:
                break;
        }
        return value==null ? 0L : value;
    }

    public Integer getIntegerCellValue(Cell cell){
        Integer value = 0;
        if(cell==null){
            return value;
        }
        switch (cell.getCellTypeEnum()){
            case STRING:
                if(StringUtils.isNotBlank(cell.getStringCellValue())){
                    value = Integer.valueOf(cell.getStringCellValue().trim());
                }
                break;
            case NUMERIC:
                value = Double.valueOf(cell.getNumericCellValue()).intValue();
                break;
            default:
                break;
        }
        return value==null ? 0 : value;
    }

    public String getStringCellValue(Cell cell){
        String value = "";
        if(cell==null){
            return value;
        }
        switch (cell.getCellTypeEnum()){
            case STRING:
                if(StringUtils.isNotBlank(cell.getStringCellValue())){
                    value = cell.getStringCellValue().trim();
                }
                break;
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        // 时间
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {
                        // 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    value = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double valued = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(valued);
                    value = sdf.format(date);
                } else {
                    long longVal = Math.round(cell.getNumericCellValue());
                    if(Double.parseDouble(longVal + ".0") == Double.valueOf(cell.getNumericCellValue()))
                        value = String.valueOf(longVal);
                    else
                        value = String.valueOf(Double.valueOf(cell.getNumericCellValue()));
                }
                break;
            default:
                break;
        }
        return value==null ? "" : value;
    }


}
