package com.laz.knowledge.twelve;

/**
 * ClassName: History
 * Description:
 *
 * @author chenmiaomiao
 * @version 1.0
 * @date: 2020/4/20
 */
public class History {
    private String TxId;
    private CodesResponse BlockDataEntity;

    public String getTxId() {
        return TxId;
    }

    public void setTxId(String txId) {
        TxId = txId;
    }

    public CodesResponse getBlockDataEntity() {
        return BlockDataEntity;
    }

    public void setBlockDataEntity(CodesResponse blockDataEntity) {
        BlockDataEntity = blockDataEntity;
    }
}
