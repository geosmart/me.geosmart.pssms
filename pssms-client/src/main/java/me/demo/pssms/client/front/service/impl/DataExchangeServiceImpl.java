package me.demo.pssms.client.front.service.impl;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import me.demo.pssms.client.front.service.IDataExchangeService;

/**
 * <p>
 * 数据交换实现：excel数据导入导出
 * </p>
 *
 * @author geosmart
 * @since 2017-03-09
 */
public class DataExchangeServiceImpl implements IDataExchangeService {

    public void importSaleOrder(String filePath) throws IOException {
        InputStream ExcelFileToRead = new FileInputStream(filePath);
        XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
        XSSFWorkbook test = new XSSFWorkbook();

        XSSFSheet sheetSaleOrder = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;

        Iterator rows = sheetSaleOrder.rowIterator();
        //跳过第一个行title
        rows.next();
        //行循环
        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            Iterator cells = row.cellIterator();
            //每行的字段值读取
            while (cells.hasNext()) {
                cell = (XSSFCell) cells.next();
                int colIndex = cell.getColumnIndex();
                //根据列索引获取值
                if (colIndex == 0) {

                }
            }
            System.out.println();
        }
    }
}
