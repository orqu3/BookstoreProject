package com.bookstore.shopping.service;

import com.bookstore.common.entity.Address;
import com.bookstore.common.entity.CartItem;
import com.bookstore.common.entity.Customer;
import com.bookstore.common.entity.order.*;
import com.bookstore.common.entity.product.Product;
import com.bookstore.common.exception.OrderNotFoundException;
import com.bookstore.shopping.repository.OrderRepository;
import com.bookstore.shopping.util.order.OrderReturnRequest;
import com.bookstore.shopping.util.checkout.CheckoutInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService {

    public static final int ORDER_PER_PAGE = 10;
    private final OrderRepository orderRepository;

    public Order createOrder(Customer customer, Address address, List<CartItem> cartItems, PaymentMethod paymentMethod,
                             CheckoutInfo checkoutInfo) {

        Order newOrder = new Order();
        newOrder.setOrderTime(new Date());

        if (paymentMethod.equals(PaymentMethod.PAYPAL)) {
            newOrder.setStatus(OrderStatus.PAID);
        } else {
            newOrder.setStatus(OrderStatus.NEW);
        }

        newOrder.setCustomer(customer);
        newOrder.setProductCost(checkoutInfo.getProductCost());
        newOrder.setSubtotal(checkoutInfo.getProductTotal());
        newOrder.setShippingCost(checkoutInfo.getShippingCostTotal());
        newOrder.setTax(0.0f);
        newOrder.setTotal(checkoutInfo.getPaymentTotal());
        newOrder.setPaymentMethod(paymentMethod);
        newOrder.setDeliverDays(checkoutInfo.getDeliverDays());
        newOrder.setDeliverDate(checkoutInfo.getDeliverDate());

        if (address == null) {
            newOrder.copyAddressFromCustomer();
        } else {
            newOrder.copyShippingAddress(address);
        }

        Set<OrderDetail> orderDetails = newOrder.getOrderDetails();

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(newOrder);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setUnitPrice(product.getDiscountPrice());
            orderDetail.setProductCost(product.getCost() * cartItem.getQuantity());
            orderDetail.setSubtotal(cartItem.getSubTotal());
            orderDetail.setShippingCost(cartItem.getShippingCost());

            orderDetails.add(orderDetail);
        }

        OrderTrack track = new OrderTrack();
        track.setOrder(newOrder);
        track.setStatus(OrderStatus.NEW);
        track.setNotes(OrderStatus.NEW.defaultDescription());
        track.setUpdatedTime(new Date());

        newOrder.getOrderTracks().add(track);

        return orderRepository.save(newOrder);
    }

    public Page<Order> listForCustomerByPage(Customer customer, int pageNum,
                                             String sortField, String sortDir, String keyword) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(pageNum - 1, ORDER_PER_PAGE, sort);

        if (keyword != null) {
            return orderRepository.findAll(keyword, customer.getId(), pageable);
        }

        return orderRepository.findAll(customer.getId(), pageable);
    }

    public Order getOrder(Integer id, Customer customer) {
        return orderRepository.findByIdAndCustomer(id, customer);
    }

    public void setOrderReturnRequested(OrderReturnRequest request, Customer customer) throws OrderNotFoundException {
        Order order = orderRepository.findByIdAndCustomer(request.getOrderId(), customer);

        if (order == null) {
            throw new OrderNotFoundException("Order ID " + request.getOrderId() + " not found");
        }

        if (order.isReturnRequested()) return;

        OrderTrack track = new OrderTrack();
        track.setOrder(order);
        track.setUpdatedTime(new Date());
        track.setStatus(OrderStatus.RETURN_REQUESTED);

        String notes = "Reason: " + request.getReason();
        
        if (!"".equals(request.getNote())) {
            notes += ". " + request.getNote();
        }

        track.setNotes(notes);

        order.getOrderTracks().add(track);
        order.setStatus(OrderStatus.RETURN_REQUESTED);

        orderRepository.save(order);
    }
}
