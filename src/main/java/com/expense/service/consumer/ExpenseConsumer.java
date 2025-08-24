package com.expense.service.consumer;
import com.expense.service.dto.ExpenseDto;
import com.expense.service.service.ExpenseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExpenseConsumer {
    private ExpenseService expenseService;

    @Autowired
    ExpenseConsumer(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    @Transactional
    public void listen(ExpenseDto eventData) {
        try{
            expenseService.createExpense(eventData);
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("AuthServiceConsumer: Exception is thrown while consuming kafka event");
        }
    }
}
