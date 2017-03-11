package me.geosmart.pssms.rpcs.service;

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

    void importSaleOrder(String filePath) throws Exception;

    void importBackProduct(String filePath) throws Exception;

    void importBackOrder(String filePath) throws Exception;

    void importBackOrderLog(String filePath) throws Exception;
}
