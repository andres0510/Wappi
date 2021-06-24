package com.wappi.test.controller;

import com.wappi.test.dto.Coupon;
import com.wappi.test.dto.Order;
import com.wappi.test.helpers.Dictionary;
import com.wappi.test.helpers.Report;

import java.util.List;

public class DataController {

    private DataController(){}

    public static void compareOrders(Order tableOffer, Order modalOffer) {
        Report.reportInfo(String.format(
            Dictionary.Messages.COMPARE, tableOffer.getDescription(), modalOffer.getDescription()
        ));
        Report.reportInfo(String.format(
            Dictionary.Messages.COMPARE, tableOffer.getPrice(), modalOffer.getPrice()
        ));
        Report.reportInfo(String.format(
            Dictionary.Messages.COMPARE, tableOffer.getDate(), modalOffer.getDate()
        ));
        Report.reportInfo(String.format(
            Dictionary.Messages.COMPARE, tableOffer.getDelivery(), modalOffer.getDelivery()
        ));
        Report.reportInfo(String.format(
            Dictionary.Messages.COMPARE, tableOffer.getShop(), modalOffer.getShop()
        ));
        Report.reportInfo(String.format(
            Dictionary.Messages.COMPARE, tableOffer.getAddress(), modalOffer.getAddress()
        ));
        Report.reportInfo(String.format(
            Dictionary.Messages.COMPARE, tableOffer.isCouponUsed(), modalOffer.isCouponUsed()
        ));
        if (!tableOffer.equals(modalOffer)) {
            Report.reportFail("Difference(s) found for current orders");
        }
    }

    public static void compareOrdersList(List<Order> clientOrders, List<Order> historyOrders) {
        if (clientOrders.size() != historyOrders.size() || !historyOrders.containsAll(clientOrders)) {
            Report.reportFail("Orders count are different");
        }
    }

    public static void validateWelcomeCoupon(Coupon clientCoupon, List<Coupon> couponList) {
        if (couponList.size()>1) {
            Report.reportFail("There are more coupons than expected");
        }
        Coupon coupon = couponList.get(0);
        if (!clientCoupon.getCode().equalsIgnoreCase(coupon.getCode())) {
            Report.reportFail("Coupons codes are different");
        }
        if (clientCoupon.getUses() != coupon.getUses()) {
            Report.reportFail("Coupons uses are different");
        }
    }

    public static void updateCouponUses(Coupon coupon) {
        int currentUses = coupon.getUses();
        coupon.setUses(currentUses-1);
    }

    public static void validateCouponsUpdated(Coupon clientCoupon, List<Coupon> coupons) {
        if (coupons.size()>1) {
            Report.reportFail("There are more coupons than expected");
        }
        if (!coupons.isEmpty()) {
            if (clientCoupon.getUses()==0) {
                Report.reportFail("Welcome coupon expired but found available coupons");
            }
            if (clientCoupon.getUses() != coupons.get(0).getUses()) {
                Report.reportFail("Coupon uses are not the expected");
            }
        }
        if (clientCoupon.getUses()>0 && coupons.isEmpty()) {
            Report.reportFail("Coupon not found at coupons view");
        }
    }

}
