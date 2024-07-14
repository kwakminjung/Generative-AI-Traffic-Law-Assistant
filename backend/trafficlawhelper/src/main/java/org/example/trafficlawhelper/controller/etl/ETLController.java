package org.example.trafficlawhelper.controller.etl;

import lombok.RequiredArgsConstructor;
import org.example.trafficlawhelper.service.etl.ETLService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai/etl")
public class ETLController {
    private final ETLService etlService;

    @GetMapping("/load")
    public ResponseEntity<Void> load(@RequestParam String path) {
        etlService.load(path);
        return ResponseEntity.ok().build();
    }
}
