package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Discount;
import christmas.domain.Menu;
import christmas.domain.Order;

import java.util.List;

public class OutputView {
    public void printBenefits(int date, Order order){
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", date);
        printOrderMenu(order.getOrderMenu(), order.getMenuCount());
        printTotalAmountBeforeDiscount(order.getTotalAmountBeforeDiscount());
        printDiscountDetails(order.getDiscounts(), order.getMenuCount());
        printExpectedPaymentAmount(order.getTotalAmountAfterDiscount());
        printBadge(order.getBadge());
    }

    public void printOrderMenu(List<Menu> orderMenu, int count){
        System.out.println("<주문 메뉴>");
    }

    public void printTotalAmountBeforeDiscount(int amount){
        System.out.println("<할인 전 총주문 금액>");
        // 천원 단위 쉼표 출력


        System.out.println("<증정 메뉴>");
        // 샴페인 1개 또는 없음
    }

    public void printDiscountDetails(List<Discount> discounts, int count){
        System.out.println("<혜택 내역>");
        // 천원 단위 쉼표 구분 및 마이너스 출력
        System.out.println("<총혜택 금액>");
        // 천원 단위 쉼표 구분하여 출력
    }

    public void printExpectedPaymentAmount(int amount){
        System.out.println("<할인 후 예상 결제 금액>");
        // 천원 단위 쉼표 구분하여 출력
        System.out.println(amount);
    }

    public void printBadge(Badge badge){
        System.out.println("<12월 이벤트 배지>");
        // 없으면 없음 출력
    }
}