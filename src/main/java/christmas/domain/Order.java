package christmas.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.domain.Discount.*;

public class Order {
    private static final int MINIMUM_ORDER_AMOUNT = 10000;
    private static final int ORDER_AMOUNT_FOR_GIFT = 120000;
    private static final int DISCOUNT_AMOUNT_INCREASE = 100;
    private final int reservationDate;
    private final int totalOrderAmountBeforeDiscount;
    private final Map<String, Integer> order;
    private final Map<String, Integer> benefitsInfo;
    private int totalOrderAmountAfterDiscount;

    public Order(int reservationDate, Map<String, Integer> order){
        this.reservationDate = reservationDate;
        this.order = order;
        totalOrderAmountBeforeDiscount = calculateOrderAmountBeforeDiscount();
        totalOrderAmountAfterDiscount = totalOrderAmountBeforeDiscount;
        benefitsInfo = new HashMap<>();
    }

    public void applyBenefits(List<String> events){
        if (canGetGift()){
            benefitsInfo.put(GIFT.getContent(), GIFT.getAmount());
        }
        for (String event : events){
            if (event.equals(CHRISTMAS_D_DAY.getContent())){
                benefitsInfo.put(CHRISTMAS_D_DAY.getContent(), getChristmasD_DayDiscount(CHRISTMAS_D_DAY.getAmount()));
            }
            if (event.equals(SPECIAL.getContent())){
                benefitsInfo.put(SPECIAL.getContent(), getSpecialDiscount(SPECIAL.getAmount()));
            }
            if (event.equals(WEEKDAY.getContent())){
                benefitsInfo.put(WEEKDAY.getContent(), getWeekDayDiscount(WEEKDAY.getAmount()));
                return;
            }
        }
        benefitsInfo.put(WEEKEND.getContent(), getWeekEndDiscount(WEEKEND.getAmount()));
    }

    public int getChristmasD_DayDiscount(int discountAmount){
        discountAmount = discountAmount + DISCOUNT_AMOUNT_INCREASE * (reservationDate - 1);
        totalOrderAmountAfterDiscount -= discountAmount;
        return discountAmount;
    }

    public int getSpecialDiscount(int discountAmount){
        totalOrderAmountAfterDiscount -= discountAmount;
        return discountAmount;
    }

    public int getWeekDayDiscount(int discountAmount){
        int count = 0;
        for (Map.Entry<String, Integer> menu : order.entrySet()){
            if (Menu.isMenuCategoryDessert(menu.getKey())){
                count += menu.getValue();
            }
        }
        discountAmount = discountAmount * count;
        totalOrderAmountAfterDiscount -= discountAmount;
        return discountAmount;
    }

    public int getWeekEndDiscount(int discountAmount){
        int count = 0;
        for (Map.Entry<String, Integer> menu : order.entrySet()){
            if (Menu.isMenuCategoryMain(menu.getKey())){
                count += menu.getValue();
            }
        }
        discountAmount = discountAmount * count;
        totalOrderAmountAfterDiscount -= discountAmount;
        return discountAmount;
    }

    public boolean isSumOverMinimumOrderAmount(){
        int sum = 0;
        for (Map.Entry<String, Integer> orderSheet : order.entrySet()){
            sum += Menu.calculateSumOfPrice(orderSheet.getKey(), orderSheet.getValue());
        }
        return (sum > MINIMUM_ORDER_AMOUNT);
    }

    public boolean canGetGift(){
        return totalOrderAmountBeforeDiscount > ORDER_AMOUNT_FOR_GIFT;
    }

    public int calculateOrderAmountBeforeDiscount(){
        int sum = 0;
        for (Map.Entry<String, Integer> orderSheet : order.entrySet()){
            sum += Menu.calculateSumOfPrice(orderSheet.getKey(), orderSheet.getValue());
        }
        return sum;
    }

    public int calculateTotalBenefitAmount(){
        int sum = 0;
        for (Integer discountAmount : benefitsInfo.values()){
            sum += discountAmount;
        }
        return sum;
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
    public int getTotalOrderAmountBeforeDiscount(){
        return totalOrderAmountBeforeDiscount;
    }
    public int getTotalOrderAmountAfterDiscount(){
        return totalOrderAmountAfterDiscount;
    }
}