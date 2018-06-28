package com.duckduckgogogo.controller;

import com.duckduckgogogo.services.CameraService;
import com.duckduckgogogo.services.TraceService;
import com.duckduckgogogo.utils.JSONHandler;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/console/trace/")
public class TraceController {
    @Autowired
    private TraceService traceService;

    @Autowired
    private CameraService cameraService;

    @RequestMapping("/search")
    @ResponseBody
    public Map<String, Object> search(
            @RequestParam("startDate") String startTime,
            @RequestParam("endDate") String endTime,
            @RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();

        String result_trace = traceService.search(startTime,endTime,id);
        String result_all = cameraService.searchCamera("","0","10000");

        System.out.println("查询成功："+result_trace+"\n"+result_all);

        JSONArray array_trace = JSONArray.fromObject(result_trace);
        JSONObject array_all = JSONObject.fromObject(result_all);

        JSONObject object = new JSONObject();
        object.put("traceCamera",array_trace);
        object.put("allCamera",array_all.getJSONArray("rows"));

        map.put("cameras", object);
        map.put("status", "SUCCEED");

        return map;
    }
}
