package me.demo.pssms.client.front.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import me.demo.pssms.client.front.service.IDataExchangeService;
import me.geosmart.pssms.rpcs.entity.TbSaleOrder;
import me.geosmart.pssms.rpcs.service.ITbSaleOrderService;
import me.geosmart.pssms.rpcs.service.impl.TbSaleOrderServiceImpl;

/**
 * <p>
 * 数据交换实现：excel数据导入导出
 * </p>
 *
 * @author geosmart
 * @since 2017-03-09
 */
@Service("dataExchangeService")
public class DataExchangeServiceImpl implements IDataExchangeService {

    static Map<String, String> saleOrderMap = new HashMap<String, String>();

    static Map<Integer, String> titleMap = new HashMap();

    @Autowired
    private ITbSaleOrderService saleOrderService;

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

    public void importSaleOrder(String filePath) throws IOException {
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
            saleOrderService.insert(saleOrder);
            System.out.println(saleOrder.toString());
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

    private TbSaleOrder getSaleOrder(XSSFRow row) {
        Iterator cells = row.cellIterator();
        XSSFCell cell;
        JSONObject jsonEntity = new JSONObject();
        //每行的字段值读取
        while (cells.hasNext()) {
            cell = (XSSFCell) cells.next();
            int colIndex = cell.getColumnIndex();
            String title = titleMap.get(colIndex);
            String saleOrderKey = saleOrderMap.get(title);
            Object cellValue;
            if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
                cellValue = cell.getNumericCellValue();
            } else {
                cellValue = cell.getStringCellValue().trim();
            }
            jsonEntity.put(saleOrderKey, cellValue);
        }
        TbSaleOrder tbSaleOrder = JSON.parseObject(jsonEntity.toJSONString(), TbSaleOrder.class);
        return tbSaleOrder;
    }
}
