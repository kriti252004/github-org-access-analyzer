# github-org-access-analyzer
# GitHub Organization Access Report Service

A high-performance Spring Boot service that generates a structured report mapping users to the repositories they can access within a GitHub organization.

## 🚀 How to Run the Project
1. **Prerequisites:** Ensure you have Java 17+ and Maven installed.
2. **Clone/Download:** Download the source code to your local machine.
3. **Run:** Open the project in your IDE (IntelliJ/VS Code) and run `GithubreportApplication.java`.
4. **Access:** The server will start on `http://localhost:8080`.

## 🔑 Authentication Configuration
This project uses **GitHub Personal Access Tokens (PAT)** for secure authentication. 
- You do not need to configure anything in the code.
- Pass your token as a request parameter when calling the API. 
- Ensure your token has `repo` and `read:org` scopes.

## 📡 How to Call the API
**Endpoint:** `GET /api/report`

**Example Request:**
`http://localhost:8080/api/report?org=[ORG_NAME]&token=[YOUR_GITHUB_TOKEN]`

**Example Response:**
```json
{
  "username123": [
    {
      "repository": "project-alpha",
      "role": "admin"
    }
  ]
}
