package christmas.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.domain.Discount.*;

public class Order {
    private static final int MINIMUM_ORDER_AMOUNT = 10_000;
    private static final int ORDER_AMOUNT_FOR_GIFT = 120_000;
    private static final int DISCOUNT_AMOUNT_INCREASE = 100;
    private final int reservationDate;
    private final Map<String, Integer> order;
    private final Map<String, Integer> benefitsInfo = new HashMap<>();
    private final int orderAmount; // 주문 금액
    private int totalAmount; // 총 결제금액

    public Order(int reservationDate, Map<String, Integer> order) {
        this.reservationDate = reservationDate;
        this.order = order;
        orderAmount = calculateOrderAmount();
        totalAmount = orderAmount;
    }

    public void applyBenefits(List<String> events) {
        if (canGetGift()) {
            benefitsInfo.put(GIFT.getContent(), GIFT.getAmount());
        }
        applyDiscount(events);
    }

    public boolean isSumOverMinimumOrderAmount() {
        return order.entrySet()
                .stream()
                .mapToInt(orderSheet -> Menu.calculateSumOfMenuPrice(orderSheet.getKey(), orderSheet.getValue()))
                .sum() > MINIMUM_ORDER_AMOUNT;
    }

    public boolean canGetGift() {
        return orderAmount > ORDER_AMOUNT_FOR_GIFT;
    }

    public int calculateTotalDiscount() {
        return benefitsInfo.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<String, Integer> getOrder() {
        return order;
    }

    public int getReservationDate() {
        return reservationDate;
    }

    public Map<String, Integer> getBenefitsInfo() {
        return benefitsInfo;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    private void applyDiscount(List<String> events) {
        for (String event : events) {
            if (event.equals(CHRISTMAS_D_DAY.getContent())) {
                benefitsInfo.put(CHRISTMAS_D_DAY.getContent(), getChristmasD_DayDiscount(CHRISTMAS_D_DAY.getAmount()));
            }
            if (event.equals(SPECIAL.getContent())) {
                benefitsInfo.put(SPECIAL.getContent(), getSpecialDiscount(SPECIAL.getAmount()));
            }
            if (event.equals(WEEKDAY.getContent())) {
                benefitsInfo.put(WEEKDAY.getContent(), getWeekDayDiscount(WEEKDAY.getAmount()));
                return;
            }
        }
        benefitsInfo.put(WEEKEND.getContent(), getWeekEndDiscount(WEEKEND.getAmount()));
    }

    private int getChristmasD_DayDiscount(int discountAmount) {
        discountAmount = discountAmount + DISCOUNT_AMOUNT_INCREASE * (reservationDate - 1);
        totalAmount -= discountAmount;
        return discountAmount;
    }

    private int getSpecialDiscount(int discountAmount) {
        totalAmount -= discountAmount;
        return discountAmount;
    }

    private int getWeekDayDiscount(int discountAmount) {
        int count = order.entrySet()
                .stream()
                .filter(menu -> Menu.isMenuCategoryDessert(menu.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
        int calculatedDiscount = discountAmount * count;
        totalAmount -= calculatedDiscount;
        return calculatedDiscount;
    }

    private int getWeekEndDiscount(int discountAmount) {
        int count = order.entrySet()
                .stream()
                .filter(menu -> Menu.isMenuCategoryMain(menu.getKey()))
                .mapToInt(Map.Entry::getValue)
                .sum();
        int calculatedDiscount = discountAmount * count;
        totalAmount -= calculatedDiscount;
        return calculatedDiscount;
    }

    private int calculateOrderAmount() {
        return order.entrySet()
                .stream()
                .mapToInt(orderSheet -> Menu.calculateSumOfMenuPrice(orderSheet.getKey(), orderSheet.getValue()))
                .sum();
    }
}