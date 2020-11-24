package com.dstanzimkabir.coronavirustracker.controllers;

import java.util.List;

import com.dstanzimkabir.coronavirustracker.models.LocationStats;
import com.dstanzimkabir.coronavirustracker.services.CoronavirusDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/abc")
public class HomeController {

    @Autowired
    CoronavirusDataService coronavirusDataService;
    
    @GetMapping("/corona")
    public String home(Model model){
        List<LocationStats> allStats = coronavirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPreviousDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
    
}
