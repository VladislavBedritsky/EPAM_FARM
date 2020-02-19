package org.example.EPAM_FARM.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping
    public List<Map<String, String>> maps () {
        Map<String, String> map = new HashMap<>();
        map.put("id","1");
        map.put("name","mike");
        List<Map<String, String>> list = new ArrayList<>();
        list.add(map);

        return list;
    }

}
