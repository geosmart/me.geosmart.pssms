package me.geosmart.pssms.client.front.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.geosmart.pssms.client.front.service.IDataExchangeService;
import me.geosmart.pssms.rpcs.entity.TbBackOrder;
import me.geosmart.pssms.rpcs.entity.TbBackOrderLog;
import me.geosmart.pssms.rpcs.entity.TbSaleOrder;
import me.geosmart.pssms.rpcs.service.ITbBackOrderLogService;
import me.geosmart.pssms.rpcs.service.ITbBackOrderService;
import me.geosmart.pssms.rpcs.service.ITbSaleOrderService;

/**
 * <p>
 * 数据交换实现：excel数据导入导出
 * </p>
 *
 * @author geosmart
 * @since 2017-03-09
 */
@Service
public class DataExchangeServiceImpl implements IDataExchangeService {
    static Map<String, String> saleOrderMap = new HashMap<String, String>();
    static Map<String, String> backOrderMap = new HashMap<String, String>();
    static Map<String, String> backOrderLogMap = new HashMap<String, String>();
    static Map<Integer, String> titleMap = new HashMap();

    static {
        saleOrderMap.put("交易类型", "order_type");
        saleOrderMap.put("日期", "order_date");
        saleOrderMap.put("单号", "order_id");
        saleOrderMap.put("日期", "order_date");
        saleOrderMap.put("客户", "customer_code");
        saleOrderMap.put("货号", "product_code");
        saleOrderMap.put("款号", "product_code");
        saleOrderMap.put("数量", "number");
        saleOrderMap.put("价格", "price");
        saleOrderMap.put("单价", "price");
        saleOrderMap.put("金额", "amount");
        saleOrderMap.put("备注", "memo");

        backOrderMap.put("日期", "order_date");
        backOrderMap.put("退单编号", "back_order_id");
        backOrderMap.put("客户", "customer_code");
        backOrderMap.put("退单金额", "amount");
        backOrderMap.put("父退单号", "parent_back_order_id");
        backOrderMap.put("父退单", "parent_back_order_id");
        backOrderMap.put("使用情况", "back_order_status");
        backOrderMap.put("备注", "memo");

        backOrderLogMap.put("日期", "order_date");
        backOrderLogMap.put("销售单号", "sale_order_id");
        backOrderLogMap.put("退单编号", "back_order_id");
        backOrderLogMap.put("客户", "customer_code");
        backOrderLogMap.put("退单使用金额", "back_amount");
        backOrderLogMap.put("备注", "memo");
    }

    @Autowired
    private ITbSaleOrderService saleOrderService;
    @Autowired
    private ITbBackOrderService backOrderService;
    @Autowired
    private ITbBackOrderLogService backOrderLogService;

    @Override
    public void importSaleOrder(String filePath) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));
        //销售列表
        XSSFSheet sheetSaleOrder = wb.getSheetAt(0);
        if (!sheetSaleOrder.getSheetName().equals("销售记录")) {
            throw new Exception("99999:sheet名称不正确,应为<销售记录>，实际为<" + sheetSaleOrder.getSheetName() + ">");
        }
        Iterator rows = sheetSaleOrder.rowIterator();
        titleMap = getTitleMap((XSSFRow) rows.next());

        XSSFRow row;
        List saleOrders = new ArrayList();
        //行循环
        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            TbSaleOrder saleOrder = getSaleOrder(row);
            if (saleOrder != null) {
                saleOrders.add(saleOrder);
            }
        }
        saleOrderService.insertBatch(saleOrders, 1000);
    }


    @Override
    public void importBackOrder(String filePath) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));
        //销售列表
        XSSFSheet sheetSaleOrder = wb.getSheetAt(1);
        Iterator rows = sheetSaleOrder.rowIterator();
        titleMap = getTitleMap((XSSFRow) rows.next());

        List backOrders = new ArrayList();
        XSSFRow row;
        //行循环
        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            TbBackOrder backOrder = getBackOrder(row);
            if (backOrder != null) {
                backOrders.add(backOrder);
            }
        }
        backOrderService.insertBatch(backOrders, 1000);
    }

    @Override
    public void importBackOrderLog(String filePath) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));
        //销售列表
        XSSFSheet sheetSaleOrder = wb.getSheetAt(2);
        Iterator rows = sheetSaleOrder.rowIterator();
        titleMap = getTitleMap((XSSFRow) rows.next());

        List backOrders = new ArrayList();
        XSSFRow row;
        //行循环
        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            TbBackOrderLog backOrderLog = getBackOrderLog(row);
            if (backOrderLog != null) {
                backOrders.add(backOrderLog);
            }
        }
        backOrderLogService.insertBatch(backOrders, 1000);
    }

    private TbSaleOrder getSaleOrder(XSSFRow row) throws Exception {
        JSONObject jsonEntity = getJsonEntityFromRow(row, saleOrderMap);
        TbSaleOrder tbSaleOrder = JSON.parseObject(jsonEntity.toJSONString(), TbSaleOrder.class);
        if (StringUtils.isNotBlank(tbSaleOrder.getProductCode())) {
            String productCode = tbSaleOrder.getProductCode();
            if (productCode.contains(".")) {
                productCode = productCode.split("\\.")[0];
                tbSaleOrder.setProductCode(productCode);
            }
        } else {
            tbSaleOrder = null;
        }
        if (tbSaleOrder != null && StringUtils.isNotBlank(tbSaleOrder.getOrderType())) {
            tbSaleOrder.setOrderType(new Integer(Double.valueOf(tbSaleOrder.getOrderType()).intValue()).toString());
        }
        return tbSaleOrder;
    }

    private TbBackOrder getBackOrder(XSSFRow row) throws Exception {
        JSONObject jsonEntity = getJsonEntityFromRow(row, backOrderMap);
        TbBackOrder backOrder = JSON.parseObject(jsonEntity.toJSONString(), TbBackOrder.class);
        if (StringUtils.isBlank(backOrder.getBackOrderId())) {
            backOrder = null;
        }
        if (backOrder != null && StringUtils.isNotBlank(backOrder.getBackOrderStatus())) {
            backOrder.setBackOrderStatus(new Integer(Double.valueOf(backOrder.getBackOrderStatus()).intValue()).toString());
        }
        return backOrder;
    }


    private TbBackOrderLog getBackOrderLog(XSSFRow row) throws Exception {
        JSONObject jsonEntity = getJsonEntityFromRow(row, backOrderLogMap);
        TbBackOrderLog backOrderLog = JSON.parseObject(jsonEntity.toJSONString(), TbBackOrderLog.class);
        if (backOrderLog.getBackAmount() == null) {
            backOrderLog = null;
        }
        return backOrderLog;
    }

    private JSONObject getJsonEntityFromRow(XSSFRow row, Map<String, String> entityKeyMap) {
        Iterator cells = row.cellIterator();
        XSSFCell cell;
        JSONObject jsonEntity = new JSONObject();
        //每行的字段值读取
        while (cells.hasNext()) {
            cell = (XSSFCell) cells.next();
            int colIndex = cell.getColumnIndex();
            String title = titleMap.get(colIndex);
            String saleOrderKey = entityKeyMap.get(title);
            Object cellValue = null;

            if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                cellValue = cell.getStringCellValue().trim();
            } else if (HSSFDateUtil.isCellDateFormatted(cell)) {
                cellValue = cell.getDateCellValue();
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    cellValue = sdf.format(cellValue);
                } catch (Exception e) {
                    System.out.println("日期解析异常，rowNum<" + row.getRowNum() + ">,cellValue<" + cellValue + ">");
                }
            } else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
                cellValue = cell.getNumericCellValue();
            }
            if (cellValue != null) {
                jsonEntity.put(saleOrderKey, cellValue);
            }
        }
        return jsonEntity;
    }


    private Map getTitleMap(XSSFRow titleRow) {
        Iterator titleCells = titleRow.cellIterator();
        while (titleCells.hasNext()) {
            XSSFCell cell = (XSSFCell) titleCells.next();
            if (StringUtils.isNotBlank(cell.getStringCellValue())) {
                titleMap.put(cell.getColumnIndex(), cell.getStringCellValue().trim());
            }
        }
        return titleMap;
    }
}
