package com.example.anyanghome.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.anyanghome.common.Result;
import com.example.anyanghome.pojo.dto.BusBookingDTO;
import com.example.anyanghome.pojo.dto.MessageCreateDTO;
import com.example.anyanghome.pojo.entity.BusRoute;
import com.example.anyanghome.pojo.entity.Order;
import com.example.anyanghome.service.BusRouteService;
import com.example.anyanghome.service.MessageService;
import com.example.anyanghome.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

/**
 * 约车服务控制器
 */
@RestController
@RequestMapping("/api/bus")
@RequiredArgsConstructor
@Tag(name = "约车服务", description = "约车服务相关接口")
public class BusController {

    private final BusRouteService busRouteService;
    private final OrderService orderService;
    private final MessageService messageService;

    @GetMapping("/routes")
    @Operation(summary = "获取可预约的客车路线列表")
    public Result<List<BusRoute>> getAvailableRoutes() {
        List<BusRoute> routes = busRouteService.getAvailableRoutes();
        return Result.success(routes);
    }

    @GetMapping("/routes/{id}")
    @Operation(summary = "获取客车路线详情")
    public Result<BusRoute> getRouteDetail(@PathVariable Long id) {
        BusRoute route = busRouteService.getRouteById(id);
        if (route == null) {
            return Result.error("路线不存在");
        }
        return Result.success(route);
    }

    @PostMapping("/create-booking")
    @Operation(summary = "创建客车预约")
    public Result<Void> createBusBooking(@Valid @RequestBody BusBookingDTO busBookingDTO) {
        try {
            Long userId = StpUtil.getLoginIdAsLong();

            // 创建统一订单
            Order order = new Order();
            order.setUserId(userId);
            order.setType("booking");
            order.setTitle(busBookingDTO.getFromLocation() + " → " + busBookingDTO.getToLocation());
            order.setDescription(busBookingDTO.getBusType() + "，发车时间：" + busBookingDTO.getDepartureTime());
            order.setPrice(busBookingDTO.getPrice());
            order.setStatus("pending");

            // 创建订单详情JSON
            String details = String.format(
                    "{\"from\":\"%s\",\"to\":\"%s\",\"departureTime\":\"%s\",\"arrivalTime\":\"%s\",\"busType\":\"%s\",\"company\":\"%s\",\"seatNumber\":\"%s\",\"passengerName\":\"%s\",\"passengerPhone\":\"%s\",\"passengerIdCard\":\"%s\"}", busBookingDTO.getFromLocation(),
                    busBookingDTO.getToLocation(),
                    busBookingDTO.getDepartureTime(),
                    busBookingDTO.getArrivalTime(),
                    busBookingDTO.getBusType(),
                    busBookingDTO.getCompany(),
                    busBookingDTO.getSeatNumber(),
                    busBookingDTO.getPassengerName(),
                    busBookingDTO.getPassengerPhone(),
                    busBookingDTO.getPassengerIdCard());
            order.setDetails(details);

            orderService.createOrder(order);
            // 发送预约创建成功消息
            MessageCreateDTO dto = new MessageCreateDTO();
            dto.setTitle("预约成功通知");
            dto.setContent("您的客车预约已成功，发车时间于今日 " + busBookingDTO.getDepartureTime() + "，请提前30分钟到达车站。");
            dto.setType("booking");
            messageService.createMessage(dto);
            return Result.success();
        } catch (Exception e) {
            return Result.error("创建预约失败：" + e.getMessage());
        }
    }

}
