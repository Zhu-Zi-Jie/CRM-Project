package com.zzj.crm.workbench.mapper;

import com.zzj.crm.workbench.domain.TransactionHistory;

import java.util.List;

public interface TransactionHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_history
     *
     * @mbggenerated Sun Jul 24 15:16:49 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_history
     *
     * @mbggenerated Sun Jul 24 15:16:49 CST 2022
     */
    int insert(TransactionHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_history
     *
     * @mbggenerated Sun Jul 24 15:16:49 CST 2022
     */
    int insertSelective(TransactionHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_history
     *
     * @mbggenerated Sun Jul 24 15:16:49 CST 2022
     */
    TransactionHistory selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_history
     *
     * @mbggenerated Sun Jul 24 15:16:49 CST 2022
     */
    int updateByPrimaryKeySelective(TransactionHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_tran_history
     *
     * @mbggenerated Sun Jul 24 15:16:49 CST 2022
     */
    int updateByPrimaryKey(TransactionHistory record);

    //保存创建的交易历史
    int insertTranHistory(TransactionHistory tranHistory);

    //根据tranId查询该交易下所有历史的明细信息
    List<TransactionHistory> selectTranHistoryForDetailByTranId(String tranId);

}