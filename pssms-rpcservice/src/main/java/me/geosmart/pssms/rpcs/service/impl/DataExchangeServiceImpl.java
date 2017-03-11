package me.geosmart.pssms.rpcs.service.impl;

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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import me.geosmart.pssms.rpcs.entity.TbBackOrder;
import me.geosmart.pssms.rpcs.entity.TbBackOrderLog;
import me.geosmart.pssms.rpcs.entity.TbBackProduct;
import me.geosmart.pssms.rpcs.entity.TbSaleOrder;
import me.geosmart.pssms.rpcs.service.IDataExchangeService;
import me.geosmart.pssms.rpcs.service.ITbBackOrderLogService;
import me.geosmart.pssms.rpcs.service.ITbBackOrderService;
import me.geosmart.pssms.rpcs.service.ITbBackProductService;
import me.geosmart.pssms.rpcs.service.ITbSaleOrderService;
import me.geosmart.pssms.rpcs.util.SerialService;

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
    static Map<String, String> backProductMap = new HashMap<String, String>();
    static Map<String, String> backOrderMap = new HashMap<String, String>();
    static Map<String, String> backOrderLogMap = new HashMap<String, String>();

    static Map<Integer, String> titleMap = new HashMap();

    static {
        saleOrderMap.put("日期", "order_date");
        saleOrderMap.put("销售单号", "sale_order_id");
        saleOrderMap.put("客户", "customer_code");
        saleOrderMap.put("货号", "product_code");
        saleOrderMap.put("款号", "product_code");
        saleOrderMap.put("数量", "number");
        saleOrderMap.put("价格", "price");
        saleOrderMap.put("单价", "price");
        saleOrderMap.put("金额", "amount");
        saleOrderMap.put("备注", "memo");

        backProductMap.put("日期", "order_date");
        backProductMap.put("退单编号", "back_order_id");
        backProductMap.put("客户", "customer_code");
        backProductMap.put("货号", "product_code");
        backProductMap.put("款号", "product_code");
        backProductMap.put("数量", "number");
        backProductMap.put("价格", "price");
        backProductMap.put("单价", "price");
        backProductMap.put("退货金额", "amount");
        backProductMap.put("备注", "memo");

        backOrderMap.put("日期", "order_date");
        backOrderMap.put("退单编号", "back_order_id");
        backOrderMap.put("客户", "customer_code");
        backOrderMap.put("退单金额", "amount");
        backOrderMap.put("父退单号", "parent_back_order_id");
        backOrderMap.put("使用情况", "back_order_status");
        backOrderMap.put("备注", "memo");

        backOrderLogMap.put("日期", "order_date");
        saleOrderMap.put("销售单号", "sale_order_id");
        backOrderLogMap.put("退单编号", "back_order_id");
        backOrderLogMap.put("客户", "customer_code");
        backOrderLogMap.put("货号", "product_code");
        backOrderLogMap.put("款号", "product_code");
        backOrderLogMap.put("数量", "number");
        backOrderLogMap.put("价格", "price");
        backOrderLogMap.put("单价", "price");
        backOrderLogMap.put("金额", "amount");
        backOrderLogMap.put("退单使用金额", "back_amount");
        backOrderLogMap.put("备注", "memo");
    }

    @Autowired
    private ITbSaleOrderService saleOrderService;

    @Autowired
    private ITbBackProductService backProductService;

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
            throw new Exception("99999:sheet名称不正确");
        }
        Iterator rows = sheetSaleOrder.rowIterator();
        titleMap = getTitleMap((XSSFRow) rows.next());

        XSSFRow row;
        //行循环
        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            TbSaleOrder saleOrder = getSaleOrder(row);
            if (saleOrder != null) {
                saleOrderService.insert(saleOrder);
            }
        }
    }

    @Override
    public void importBackProduct(String filePath) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));
        //销售列表
        XSSFSheet sheetSaleOrder = wb.getSheetAt(1);
        Iterator rows = sheetSaleOrder.rowIterator();
        titleMap = getTitleMap((XSSFRow) rows.next());

        XSSFRow row;
        //行循环
        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            TbBackProduct backProduct = getBackProduct(row);
            if (backProduct != null) {
                backProductService.insert(backProduct);
            }
        }
    }

    @Override
    public void importBackOrder(String filePath) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));
        //销售列表
        XSSFSheet sheetSaleOrder = wb.getSheetAt(2);
        Iterator rows = sheetSaleOrder.rowIterator();
        titleMap = getTitleMap((XSSFRow) rows.next());

        XSSFRow row;
        //行循环
        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            TbBackOrder backOrder = getBackOrder(row);
            if (backOrder != null) {
                backOrderService.insert(backOrder);
            }
        }
    }

    @Override
    public void importBackOrderLog(String filePath) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));
        //销售列表
        XSSFSheet sheetSaleOrder = wb.getSheetAt(3);
        Iterator rows = sheetSaleOrder.rowIterator();
        titleMap = getTitleMap((XSSFRow) rows.next());

        XSSFRow row;
        //行循环
        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            TbBackOrderLog backOrderLog = getBackOrderLog(row);
            if (backOrderLog != null) {
                backOrderLogService.insert(backOrderLog);
            }
        }
    }

    private TbSaleOrder getSaleOrder(XSSFRow row) throws Exception {
        JSONObject jsonEntity = getJsonEntityFromRow(row, saleOrderMap);
        TbSaleOrder tbSaleOrder = JSON.parseObject(jsonEntity.toJSONString(), TbSaleOrder.class);
        tbSaleOrder.setSerialId(SerialService.newSerialId().toString());
        if (StringUtils.isBlank(tbSaleOrder.getProductCode())) {
            tbSaleOrder = null;
        }
        return tbSaleOrder;
    }

    private TbBackProduct getBackProduct(XSSFRow row) throws Exception {
        JSONObject jsonEntity = getJsonEntityFromRow(row, backProductMap);
        TbBackProduct backProduct = JSON.parseObject(jsonEntity.toJSONString(), TbBackProduct.class);
        backProduct.setSerialId(SerialService.newSerialId().toString());
        if (StringUtils.isBlank(backProduct.getProductCode())) {
            backProduct = null;
        }
        return backProduct;
    }

    private TbBackOrder getBackOrder(XSSFRow row) throws Exception {
        JSONObject jsonEntity = getJsonEntityFromRow(row, backOrderMap);
        TbBackOrder backOrder = JSON.parseObject(jsonEntity.toJSONString(), TbBackOrder.class);
        if (StringUtils.isBlank(backOrder.getBackOrderId())) {
            backOrder = null;
        }
        return backOrder;
    }


    private TbBackOrderLog getBackOrderLog(XSSFRow row) throws Exception {
        JSONObject jsonEntity = getJsonEntityFromRow(row, backOrderLogMap);
        TbBackOrderLog backOrderLog = JSON.parseObject(jsonEntity.toJSONString(), TbBackOrderLog.class);
        backOrderLog.setSerialId(SerialService.newSerialId().toString());
        if (StringUtils.isBlank(backOrderLog.getBackOrderId())) {
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
                cellValue = cell.getStringCellValue();
            } else if (HSSFDateUtil.isCellDateFormatted(cell)) {
                cellValue = cell.getDateCellValue();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                cellValue = sdf.format(cellValue);
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
                titleMap.put(cell.getColumnIndex(), cell.getStringCellValue());
            }
        }
        return titleMap;
    }
}
