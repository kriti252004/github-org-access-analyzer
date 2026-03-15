package com.example.githubreport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GitHubService {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.github.com")
            .build();

    public Map<String, List<Map<String, String>>> generateAccessReport(String orgName, String token) {
      
        Map<String, List<Map<String, String>>> userReport = new HashMap<>();

        try {
            // 1. Retrieve the repositories 
            List<Map> repositories = webClient.get()
                    .uri("/orgs/{org}/repos", orgName)
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .bodyToFlux(Map.class)
                    .collectList()
                    .block();

            if (repositories != null) {
                for (Map repo : repositories) {
                    String repoName = (String) repo.get("name");

                    // 2. access to user
                    List<Map> collaborators = webClient.get()
                            .uri("/repos/{org}/{repo}/collaborators", orgName, repoName)
                            .header("Authorization", "Bearer " + token)
                            .retrieve()
                            .bodyToFlux(Map.class)
                            .collectList()
                            .block();

                    if (collaborators != null) {
                        for (Map user : collaborators) {
                            String username = (String) user.get("login");
                           
                            String role = (String) user.get("role_name"); 

                            Map<String, String> repoDetail = new HashMap<>();
                            repoDetail.put("repository", repoName);
                            repoDetail.put("role", role);

                            
                            userReport.putIfAbsent(username, new ArrayList<>());
                            userReport.get(username).add(repoDetail);
                        }
                    }
                }
            }
        } catch (Exception e) {
          
            System.err.println("GitHub API Error: " + e.getMessage());
        }
        return userReport;
    }
}
