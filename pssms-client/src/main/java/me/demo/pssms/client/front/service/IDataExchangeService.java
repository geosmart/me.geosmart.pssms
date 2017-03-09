package me.demo.pssms.client.front.service;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p>
 * 数据交换：excel数据导入导出
 * </p>
 *
 * @author geosmart
 * @since 2017-03-09
 */
public interface IDataExchangeService {

    void importSaleOrder(String filePath) throws IOException;
}
