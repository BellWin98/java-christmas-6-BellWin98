package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Order;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private static final DecimalFormat decimalFormat = new DecimalFormat("###,###");

    public static void printBenefits(Order order) {
        System.out.println("12월 " + order.getReservationDate() + "일에 " + "우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
        printOrderMenu(order);
        printTotalAmountBeforeDiscount(order);
        printBenefitDetails(order);
        printTotalBenefitAmount(order);
        printExpectedPaymentAmount(order);
        printBadge(order);
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printOrderMenu(Order order) {
        System.out.println("<주문 메뉴>");
        for (Map.Entry<String, Integer> orderSheet : order.getOrder().entrySet()) {
            System.out.println(orderSheet.getKey() + " " + orderSheet.getValue() + "개");
        }
        System.out.println();
    }

    public static void printTotalAmountBeforeDiscount(Order order) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(decimalFormat.format(order.getOrderAmount()) + "원\n");
        System.out.println("<증정 메뉴>");
        if (order.canGetGift()) {
            System.out.println("샴페인 1개\n");
            return;
        }
        System.out.println("없음\n");
    }

    public static void printBenefitDetails(Order order) {
        System.out.println("<혜택 내역>");
        if (order.getBenefitsInfo().size() > 0) {
            Map<String, Integer> benefitsInfo = order.getBenefitsInfo();
            for (Map.Entry<String, Integer> benefit : benefitsInfo.entrySet()) {
                System.out.println(benefit.getKey() + ": " + "-" + decimalFormat.format(benefit.getValue()) + "원");
            }
            System.out.println();
            return;
        }
        System.out.println("없음\n");
    }

    public static void printTotalBenefitAmount(Order order) {
        System.out.println("<총혜택 금액>");
        if (order.calculateTotalDiscount() == 0) {
            System.out.println("0원\n");
            return;
        }
        System.out.println("-" + decimalFormat.format(order.calculateTotalDiscount()) + "원\n");
    }

    public static void printExpectedPaymentAmount(Order order) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(decimalFormat.format(order.getTotalAmount()) + "원\n");
    }

    public static void printBadge(Order order) {
        System.out.println("<12월 이벤트 배지>");
        String badge = Badge.findBadge(order.calculateTotalDiscount());
        System.out.println(badge);
    }
}