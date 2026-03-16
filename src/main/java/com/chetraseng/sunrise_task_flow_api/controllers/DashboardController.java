package com.chetraseng.sunrise_task_flow_api.controllers;


import com.chetraseng.sunrise_task_flow_api.dto.DashboardResponse;
import com.chetraseng.sunrise_task_flow_api.Services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public ResponseEntity<DashboardResponse> getSummary() {

        DashboardResponse summary = dashboardService.getSummary();

        return ResponseEntity.ok(summary);
    }
}
