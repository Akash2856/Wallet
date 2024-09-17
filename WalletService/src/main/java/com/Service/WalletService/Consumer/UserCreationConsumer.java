package com.Service.WalletService.Consumer;

import com.Service.WalletService.Model.wallet;
import com.Service.WalletService.Repository.walletRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.Service.WalletService.Model.wallet;
import static com.Service.WalletService.Constant.KafkaConstant.USER_CREATION_TOPIC;
import static com.Service.WalletService.Constant.UserCreationTopicConstant.PHONENO;
import static com.Service.WalletService.Constant.UserCreationTopicConstant.USERID;

@Service
@Slf4j
public class UserCreationConsumer {

    @Autowired
    ObjectMapper objectMapper;
    @Value("${wallet.initial.amount}")
    Double walletAmount;
    @Autowired
    walletRepository walletRepository;

    @KafkaListener(topics = USER_CREATION_TOPIC, groupId = "wallet-group")
    public void userCreated(String message) throws JsonProcessingException {
        ObjectNode node= objectMapper.readValue(message, ObjectNode.class);
        String phoneNo= node.get(PHONENO).textValue();
        Integer userId= node.get(USERID).intValue();
        wallet wallet1= wallet.builder()
                .phoneNo(phoneNo)
                .userid(userId)
                .balance(walletAmount).build();
        walletRepository.save(wallet1);
        log.info("wallet save for user:{}",userId);
    }
}
