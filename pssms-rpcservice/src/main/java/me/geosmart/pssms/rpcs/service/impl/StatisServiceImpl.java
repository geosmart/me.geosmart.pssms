package me.geosmart.pssms.rpcs.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.geosmart.pssms.rpcs.entity.TbBackOrder;
import me.geosmart.pssms.rpcs.service.IStatisService;
import me.geosmart.pssms.rpcs.service.ITbBackOrderLogService;
import me.geosmart.pssms.rpcs.service.ITbBackOrderService;
import me.geosmart.pssms.rpcs.service.ITbSaleOrderService;

/**
 * <p>
 * 数据统计服务
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
@Service
public class StatisServiceImpl implements IStatisService {

    @Autowired
    private ITbSaleOrderService saleOrderService;


    @Autowired
    private ITbBackOrderService backOrderService;

    @Autowired
    private ITbBackOrderLogService backOrderLogService;


    @Override
    public void exportStatisResult(Date orderDateBegin, Date orderDateEnd, String templatePath, String exportPath) throws Exception {
        FileInputStream fis = new FileInputStream(new java.io.File(templatePath));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet saleAmountSheet = workbook.getSheetAt(0);
        if (saleAmountSheet.getSheetName().equals("月销售额")) {
            JSONObject saleOrderDate = statisSaleAmount(orderDateBegin, orderDateEnd, "1");
            saleOrderDate.forEach((k, v) -> {
                if (k.equals("productSaleAmount")) {
                    JSONObject productSaleAmount = (JSONObject) v;
                    // 款号	金额	  总销售额	退单使用金额	实际销售额
                    final int[] i = {1};
                    productSaleAmount.forEach((k1, v1) -> {
                        XSSFRow row = saleAmountSheet.createRow(i[0]);
                        if (i[0] == 1) {
                            row.createCell(2).setCellValue(saleOrderDate.getDoubleValue("saleOrderSumAmount"));
                            row.createCell(3).setCellValue(saleOrderDate.getDoubleValue("backOrderLogSumAmount"));
                            row.createCell(4).setCellValue(saleOrderDate.getDoubleValue("netSaleAmount"));
                        }
                        row.createCell(0).setCellValue(k1);
                        row.createCell(1).setCellValue((Double) v1);
                        i[0]++;
                    });
                }
            });
        }

        XSSFSheet backRateSheet = workbook.getSheetAt(1);
        if (backRateSheet.getSheetName().equals("月退货率")) {
            JSONObject backRate = statisBackRate(orderDateBegin, orderDateEnd);
            final int[] i = {1};
            //货号	销售量	退货量	退货率
            backRate.forEach((k1, v1) -> {
                XSSFRow row = backRateSheet.createRow(i[0]);
                row.createCell(0).setCellValue(k1);
                JSONObject bakeRateJson = (JSONObject) backRate.get(k1);
                row.createCell(1).setCellValue(bakeRateJson.getIntValue("销售量"));
                row.createCell(2).setCellValue(bakeRateJson.getIntValue("退货量"));
                row.createCell(3).setCellValue(bakeRateJson.getDoubleValue("退货率"));
                i[0]++;
            });
        }

        XSSFSheet usedOrderSheet = workbook.getSheetAt(2);
        if (usedOrderSheet.getSheetName().equals("本月已用退单")) {
            JSONArray backOrder = statisBackOrder(orderDateBegin, orderDateEnd, "1");
            final int[] i = {1};
            //日期	客户	退单编号	退单金额
            backOrder.forEach(item -> {
                XSSFRow row = usedOrderSheet.createRow(i[0]);
                JSONObject backOrderJson = (JSONObject) item;
                row.createCell(0).setCellValue(backOrderJson.getString("日期"));
                row.createCell(1).setCellValue(backOrderJson.getString("客户"));
                row.createCell(2).setCellValue(backOrderJson.getString("退单编号"));
                row.createCell(3).setCellValue(backOrderJson.getDoubleValue("退单金额"));
                i[0]++;
            });
        }

        XSSFSheet unUseOrderSheet = workbook.getSheetAt(3);
        if (unUseOrderSheet.getSheetName().equals("本月未用退单")) {
            JSONArray backOrder = statisBackOrder(orderDateBegin, orderDateEnd, "0");
            final int[] i = {1};
            //日期	客户	退单编号	退单金额
            backOrder.forEach(item -> {
                XSSFRow row = unUseOrderSheet.createRow(i[0]);
                JSONObject backOrderJson = (JSONObject) item;
                row.createCell(0).setCellValue(backOrderJson.getString("日期"));
                row.createCell(1).setCellValue(backOrderJson.getString("客户"));
                row.createCell(2).setCellValue(backOrderJson.getString("退单编号"));
                row.createCell(3).setCellValue(backOrderJson.getDoubleValue("退单金额"));
                i[0]++;
            });
        }

        fis.close();

        //删除现有导出结果
        if (new java.io.File(exportPath).exists()) {
            new java.io.File(exportPath).delete();
        }

        FileOutputStream fos = new FileOutputStream(new java.io.File(exportPath));
        workbook.write(fos);
        fos.flush();
        fos.close();
    }


    @Override
    public JSONObject statisSaleAmount(Date orderDateBegin, Date orderDateEnd, String orderType) throws Exception {
        JSONObject statisData = new JSONObject();

        JSONObject productSaleAmountData = new JSONObject();
        //统计每个款号销售额
        List<Map> resultMap = saleOrderService.groupAmountByProduct(orderDateBegin, orderDateEnd, orderType);
        resultMap.forEach(item -> {
            String key = null;
            Object value = null;
            Iterator it = item.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (entry.getKey().equals("product_code")) {
                    key = (String) entry.getValue();
                } else {
                    value = entry.getValue();
                }
            }
            productSaleAmountData.put(key, value);
        });
        statisData.put("productSaleAmount", productSaleAmountData);
        //统计总销售额
        Double saleOrderSumAmount = saleOrderService.selectSumSaleAmount(orderDateBegin, orderDateEnd, "1");
        //统计退货单使用金额
        Double backOrderLogSumAmount = backOrderLogService.selectSumBackAmount(orderDateBegin, orderDateEnd);
        //纯销售额
        Double netSaleAmount = 0D;
        if (saleOrderSumAmount != null) {
            netSaleAmount = saleOrderSumAmount;
            if (backOrderLogSumAmount != null) {
                netSaleAmount = saleOrderSumAmount - backOrderLogSumAmount;
            }
        }
        statisData.put("saleOrderSumAmount", saleOrderSumAmount);
        statisData.put("backOrderLogSumAmount", backOrderLogSumAmount);
        statisData.put("netSaleAmount", netSaleAmount);
        return statisData;
    }

    @Override
    public JSONObject statisBackRate(Date orderDateBegin, Date orderDateEnd) throws Exception {
        //退货件数统计/总销售件数
        JSONObject rateJson = new JSONObject();
        List<Map> backProductMapList = saleOrderService.groupNumberByProduct(orderDateBegin, orderDateEnd, null);
        backProductMapList.forEach(item -> {
            String productKey = null;
            String orderTypeKey = null;
            int orderNum = 0;
            Iterator it = item.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (entry.getKey().equals("product_code")) {
                    productKey = (String) entry.getValue();
                } else {
                    if (entry.getKey().equals("order_type")) {
                        orderTypeKey = (String) entry.getValue();
                        orderTypeKey = orderTypeKey.equals("-1") ? "退货量" : orderTypeKey;
                        orderTypeKey = orderTypeKey.equals("1") ? "销售量" : orderTypeKey;
                    } else {
                        orderNum = ((BigDecimal) entry.getValue()).intValue();
                        JSONObject orderNumJson = new JSONObject();
                        if (rateJson.containsKey(productKey)) {
                            rateJson.getJSONObject(productKey).put(orderTypeKey, orderNum);
                            int backNum = (int) rateJson.getJSONObject(productKey).get("退货量");
                            int saleNum = (int) rateJson.getJSONObject(productKey).get("销售量");
                            String rate = new DecimalFormat("0.00").format((double) backNum / saleNum);
                            rateJson.getJSONObject(productKey).put("退货率", rate);
                        } else {
                            orderNumJson.put(orderTypeKey, orderNum);
                            rateJson.put(productKey, orderNumJson);
                        }
                    }
                }
            }
        });
        //设置默认值
        rateJson.forEach((k, v) -> {
            if (!rateJson.getJSONObject(k).containsKey("销售量")) {
                rateJson.getJSONObject(k).put("销售量", 0);
            }
            if (!rateJson.getJSONObject(k).containsKey("销售量")) {
                rateJson.getJSONObject(k).put("销售量", 0);
            }
            if (!rateJson.getJSONObject(k).containsKey("退货量")) {
                rateJson.getJSONObject(k).put("退货量", 0);
            }
        });
        return rateJson;
    }

    @Override
    public JSONArray statisBackOrder(Date orderDateBegin, Date orderDateEnd, String orderStatus) throws Exception {
        JSONArray result = new JSONArray();
        List<TbBackOrder> resultList = backOrderService.selectBackOrderByStatus(orderDateBegin, orderDateEnd, orderStatus);
        resultList.forEach(item -> {
            JSONObject resultItem = new JSONObject();
            resultItem.put("日期", item.getOrderDate());
            resultItem.put("客户", item.getCustomerCode());
            resultItem.put("退单编号", item.getBackOrderId());
            resultItem.put("退单金额", item.getAmount());
            resultItem.put("备注", item.getMemo());
            result.add(resultItem);
        });
        return result;
    }
}
