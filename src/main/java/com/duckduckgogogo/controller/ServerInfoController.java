package com.duckduckgogogo.controller;

import com.duckduckgogogo.services.ServerService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/console/server_info")

public class ServerInfoController {
    @Autowired
    private ServerService serverService;

    @RequestMapping("/getStatus")
    private Map<String, Object> getStatus(@RequestParam("serverId") int id){
        Map<String, Object> map = new HashMap<>();

        String info = serverService.getServerById(id);
        JSONObject object_info = JSONObject.fromObject(info);

        String status  = serverService.getServerInfoById(id,object_info.getString("serverIP"));
        JSONArray array = JSONArray.fromObject(status);

        map.put("serverInfo",object_info);
        map.put("status",array);

        return map;
    }
}
