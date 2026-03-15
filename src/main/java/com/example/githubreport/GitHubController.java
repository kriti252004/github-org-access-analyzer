package com.example.githubreport;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/report")
    
    public Map<String, List<Map<String, String>>> getReport(
            @RequestParam String org, 
            @RequestParam String token) {
        return gitHubService.generateAccessReport(org, token);
    }
}