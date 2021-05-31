import com.mayikt.api.pay.entity.PaymentInfoEntity;

import java.math.BigDecimal;

public class Test01 {
    public static void main(String[] args) {
        PaymentInfoEntity paymentInfoEntity = new PaymentInfoEntity();
        paymentInfoEntity.setPayAmount(new BigDecimal(20.00));
//        System.out.println(paymentInfoEntity.getPayAmount().equals());
    }
}
