package com.cheatbook.amazon_balance_calculation.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cheatbook.amazon_balance_calculation.model.SettlementInfo;
import com.cheatbook.amazon_balance_calculation.model.SettlementInfoDetail;
import com.cheatbook.amazon_balance_calculation.model.SettlementInfoDetailId;
import com.cheatbook.amazon_balance_calculation.repository.SettlementInfoDetailRepository;
import com.cheatbook.amazon_balance_calculation.repository.SettlementInfoRepository;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.transaction.Transactional;

@Service
public class SettlementInfoService {

  @Autowired
  private SettlementInfoRepository settlementInfoRepository;

  @Autowired
  private SettlementInfoDetailRepository settlementInfoDetailRepository;

  public void readSettlementInfoDataTsv(String filePath) {

    // ObjectMapper を作成し、JavaTimeModule を登録
    CsvMapper mapper = new CsvMapper();
    mapper.registerModule(new JavaTimeModule());

    CsvSchema schema = CsvSchema.builder()
                                .addColumn("settlementId")
                                .addColumn("settlementStartDate")
                                .addColumn("settlementEndDate")
                                .addColumn("depositDate")
                                .addColumn("totalAmount")
                                .addColumn("currency")
                                .addColumn("transactionType")
                                .addColumn("orderId")
                                .addColumn("merchantOrderId")
                                .addColumn("adjustmentId")
                                .addColumn("shipmentId")
                                .addColumn("marketplaceName")
                                .addColumn("amountType")
                                .addColumn("amountDescription")
                                .addColumn("amount")
                                .addColumn("fulfillmentId")
                                .addColumn("postedDate")
                                .addColumn("postedDateTime")
                                .addColumn("orderItemCode")
                                .addColumn("merchantOrderItemId")
                                .addColumn("merchantAdjustmentItemId")
                                .addColumn("sku")
                                .addColumn("quantityPurchased")
                                .addColumn("promotionId")
                                .build()
                                .withColumnSeparator('\t') // TSVの場合。CSVの場合は変更
                                .withHeader(); // ヘッダーを考慮

    List<SettlementInfoTsvData> dataList = null;
    try {
      MappingIterator<SettlementInfoTsvData> it = mapper.readerFor(SettlementInfoTsvData.class)
                                                        .with(schema)
                                                        .readValues(new File(filePath));
      dataList = it.readAll();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // データベースに保存
    if (dataList != null) {
      saveDataToDatabase(dataList);
    }

  }

  @Transactional
  public void saveDataToDatabase(List<SettlementInfoTsvData> dataList) {
    for (SettlementInfoTsvData data : dataList) {
      if (Objects.nonNull(data.getSettlementStartDate())) {
        SettlementInfo settlementInfo = new SettlementInfo();
        settlementInfo.setSettlementId(data.getSettlementId());
        settlementInfo.setSettlementStartDate(data.getSettlementStartDate());
        settlementInfo.setSettlementEndDate(data.getSettlementEndDate());
        settlementInfo.setDepositDate(data.getDepositDate());
        settlementInfo.setTotalAmount(data.getTotalAmount());
        settlementInfo.setCurrency(data.getCurrency());
        settlementInfoRepository.save(settlementInfo);
      } else {
        SettlementInfoDetail detail = new SettlementInfoDetail();
        SettlementInfoDetailId detailId = new SettlementInfoDetailId();
        detailId.setSettlementId(data.getSettlementId());
        detailId.setOrderId(data.getOrderId());
        detailId.setAmountDescription(data.getAmountDescription());
        detailId.setTransactionType(data.getTransactionType());
        detail.setId(detailId);

        detail.setMerchantOrderId(data.getMerchantOrderId());
        detail.setAdjustmentId(data.getAdjustmentId());
        detail.setShipmentId(data.getShipmentId());
        detail.setMarketplaceName(data.getMarketplaceName());
        detail.setAmountType(data.getAmountType());
        detail.setAmount(data.getAmount());
        detail.setFulfillmentId(data.getFulfillmentId());
        detail.setPostedDate(data.getPostedDate());
        detail.setPostedDateTime(data.getPostedDateTime());
        detail.setOrderItemCode(data.getOrderItemCode());
        detail.setMerchantOrderItemId(data.getMerchantOrderItemId());
        detail.setMerchantAdjustmentItemId(data.getMerchantAdjustmentItemId());
        detail.setSku(data.getSku());
        detail.setQuantityPurchased(data.getQuantityPurchased());
        detail.setPromotionId(data.getPromotionId());

        settlementInfoDetailRepository.save(detail);
      }
    }
  }
}
