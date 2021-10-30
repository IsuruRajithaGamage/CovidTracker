package com.example.CovidTracker.Controllers;

import com.example.CovidTracker.CovidDataService.CoronaVirusDataService;
import com.example.CovidTracker.models.LocationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStatus> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases() ).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getComparetoLastDay() ).sum();
        model.addAttribute("locationStatus",coronaVirusDataService.getAllStats());
        model.addAttribute("totalReportedCases",totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);


        return "home";
    }
}
