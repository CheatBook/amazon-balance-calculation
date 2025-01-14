package com.cheatbook.amazon_balance_calculation.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.cheatbook.amazon_balance_calculation.model.SettlementInfo;
import com.cheatbook.amazon_balance_calculation.model.SettlementInfoDetail;
import com.cheatbook.amazon_balance_calculation.repository.SettlementInfoDetailRepository;
import com.cheatbook.amazon_balance_calculation.repository.SettlementInfoRepository;
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

  public void readSettlementInfoDataTsv(MultipartFile file) {

    // ObjectMapper を作成し、JavaTimeModule を登録
    CsvMapper csvMapper = new CsvMapper();
    csvMapper.registerModule(new JavaTimeModule());

    CsvSchema csvSchema = CsvSchema.builder()
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
    try (InputStream inputStream = file.getInputStream()) {
      dataList = csvMapper.readerFor(SettlementInfoTsvData.class)
                          .with(csvSchema)
                          .<SettlementInfoTsvData>readValues(inputStream) // 型安全な方法で値を読み取る
                          .readAll();

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
        detail.setSettlementId(data.getSettlementId());
        detail.setOrderId(data.getOrderId());
        detail.setAmountDescription(data.getAmountDescription());
        detail.setTransactionType(data.getTransactionType());
        detail.setAmountType(data.getAmountType());

        detail.setMerchantOrderId(data.getMerchantOrderId());
        detail.setAdjustmentId(data.getAdjustmentId());
        detail.setShipmentId(data.getShipmentId());
        detail.setMarketplaceName(data.getMarketplaceName());
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
