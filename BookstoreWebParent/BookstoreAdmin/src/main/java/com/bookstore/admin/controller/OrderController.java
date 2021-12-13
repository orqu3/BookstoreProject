package com.bookstore.admin.controller;

import com.bookstore.admin.exception.OrderNotFoundException;
import com.bookstore.admin.service.OrderService;
import com.bookstore.admin.service.SettingService;
import com.bookstore.common.entity.order.Order;
import com.bookstore.common.entity.setting.Setting;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private String defaultRedirectURL = "redirect:/orders/page/1?sortField=orderTime&sortDir=desc";
    private final OrderService orderService;
    private final SettingService settingService;

    @GetMapping("/orders")
    public String listFirstPage(Model model){
        return defaultRedirectURL;
    }

    @GetMapping("orders/page/{pageNum}")
    public String listByPage(Model model,
                             @PathVariable(name = "pageNum") int pageNum,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword,
                             HttpServletRequest request){
        Page<Order> page = orderService.listByPage(pageNum, sortField, sortDir, keyword);
        List<Order> listOrders = page.getContent();

        long startCount = (pageNum - 1) * OrderService.ORDERS_PER_PAGE + 1;
        model.addAttribute("startCount", startCount);

        long endCount = startCount + OrderService.ORDERS_PER_PAGE - 1;

        if (endCount > page.getTotalElements()) {
            endCount = page.getTotalElements();
        }

        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("listOrders", listOrders);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("keyword", keyword);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("endCount", endCount);

        loadCurrencySetting(request);

        return "orders/orders";
    }

    private void loadCurrencySetting(HttpServletRequest request){
        List<Setting> currencySettings = settingService.getCurrencySettings();

        for(Setting setting: currencySettings){
            request.setAttribute(setting.getKey(), setting.getValue());
        }
    }

    @GetMapping("/orders/detail/{id}")
    public String viewOrderDetails(@PathVariable("id") Integer id, Model model,
                                   RedirectAttributes ra, HttpServletRequest request){
        try{
            Order order = orderService.get(id);
            loadCurrencySetting(request);
            model.addAttribute("order", order);

            return "orders/order_details_modal";
        } catch (OrderNotFoundException ex){
            ra.addFlashAttribute("message", ex.getMessage());
            return defaultRedirectURL;
        }
    }

    @GetMapping("/orders/delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try{
            orderService.delete(id);
            ra.addFlashAttribute("message", "The order ID " + id + " has been deleted.");
        }catch (OrderNotFoundException ex){
            ra.addFlashAttribute("message", ex.getMessage());
        }

        return defaultRedirectURL;
    }

}
