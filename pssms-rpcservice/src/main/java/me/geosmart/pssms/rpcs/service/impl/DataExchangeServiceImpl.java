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
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import me.geosmart.pssms.rpcs.entity.TbSaleOrder;
import me.geosmart.pssms.rpcs.service.IDataExchangeService;
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
    }

    @Autowired
    private ITbSaleOrderService saleOrderService;

    public void importSaleOrder(String filePath) throws Exception {
        InputStream ExcelFileToRead = new FileInputStream(filePath);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
        XSSFWorkbook test = new XSSFWorkbook();

        //销售列表
        XSSFSheet sheetSaleOrder = wb.getSheetAt(0);
        Iterator rows = sheetSaleOrder.rowIterator();
        titleMap = getTitleMap((XSSFRow) rows.next());


        XSSFRow row;
        //行循环
        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            TbSaleOrder saleOrder = getSaleOrder(row);
            if (saleOrder != null) {
                System.out.println(JSON.toJSONString(saleOrder, true));
                saleOrderService.insert(saleOrder);
            }
        }
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

    private TbSaleOrder getSaleOrder(XSSFRow row) throws Exception {
        Iterator cells = row.cellIterator();
        XSSFCell cell;
        JSONObject jsonEntity = new JSONObject();
        //每行的字段值读取
        while (cells.hasNext()) {
            cell = (XSSFCell) cells.next();
            int colIndex = cell.getColumnIndex();
            String title = titleMap.get(colIndex);
            String saleOrderKey = saleOrderMap.get(title);
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
        TbSaleOrder tbSaleOrder = JSON.parseObject(jsonEntity.toJSONString(), TbSaleOrder.class);
        tbSaleOrder.setSerialId(SerialService.newSerialId().toString());
        if (StringUtils.isBlank(tbSaleOrder.getProductCode())) {
            tbSaleOrder = null;
        } else {
            if (StringUtils.isBlank(tbSaleOrder.getSaleOrderId())) {
                tbSaleOrder.setSaleOrderId(" ");
            }
        }
        return tbSaleOrder;
    }
}
