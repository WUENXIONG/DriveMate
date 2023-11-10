package com.map.controller;

import com.common.util.ResponseCodeMap;
import com.map.controller.form.CalculateDriveLineForm;
import com.map.controller.form.EstimateOrderMileageAndMinuteForm;
import com.map.service.MapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/map")
@Tag(name = "MapController", description = "地图Web接口")
public class MapController {
    @Resource
    private MapService mapService;

    @PostMapping("/estimateOrderMileageAndMinute")
    @Operation(summary = "估算里程和时间")
    public ResponseCodeMap estimateOrderMileageAndMinute(@RequestBody @Valid EstimateOrderMileageAndMinuteForm form) {
        HashMap map = mapService.estimateOrderMileageAndMinute(form.getMode(),
                form.getStartPlaceLatitude(), form.getStartPlaceLongitude(),
                form.getEndPlaceLatitude(), form.getEndPlaceLongitude());
        return ResponseCodeMap.ok().put("result", map);
    }

    @PostMapping("/calculateDriveLine")
    @Operation(summary = "计算行驶路线")
    public ResponseCodeMap calculateDriveLine(@RequestBody @Valid CalculateDriveLineForm form) {
        HashMap map = mapService.calculateDriveLine(form.getStartPlaceLatitude(), form.getStartPlaceLongitude(), form.getEndPlaceLatitude(), form.getEndPlaceLongitude());
        return ResponseCodeMap.ok().put("result", map);
    }

}

