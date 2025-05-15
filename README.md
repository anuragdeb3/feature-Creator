# feature-Creator
It would fetch stories from JIRA and then create cucumber feature file and step definition for the same.

Here's a high-level plan to build a local AI-based model that:

Fetches stories from JIRA (via API)  
Parses their descriptions  
Converts them into Cucumber .feature files  
Generates Java step definition placeholders  
All processing (except fetching from JIRA) happens locally — no external API calls like OpenAI.  

## Phase 1: Requirements & Setup
 ### Tools & Libraries
**Language:** Java or Python  
**ML/NLP:** SpaCy (Python), OpenNLP (Java), or a fine-tuned local model (like TinyBERT or MiniLM)  
**Cucumber:** For .feature file structure  
**Maven/Gradle:** For dependency management (if using Java)  
**JIRA Integration:** REST API using Basic Auth or OAuth2  
**Storage:** Local file system  

## Phase 2: Functional Components
### 1. Fetch Stories from JIRA
Use JIRA's REST API:
_GET /rest/api/2/search?jql=project=ABC AND issuetype=Story_

**Extract fields:**
```
summary
description
key (e.g., ABC-123)
```
Ensure credentials and base URL are configured via a config.yaml or .env file.

### 2. Parse Story Description Locally
Use rule-based NLP (or small local ML model) to:

Extract actions (Given/When/Then)

Identify test intent

Example mapping:
```
"As a user, I should be able to reset password when I click forgot password."

Feature: Password Reset
  Scenario: Reset password via Forgot Password
    Given I am on the login page
    When I click "Forgot Password"
    Then I should receive a password reset link
```
Use pattern matching, keywords, or dependency parsing.

### 3. Generate Feature File
Create .feature file per JIRA story
Filename: CBDDD-123.feature
Folder: features/generated/

Include:
```
Feature: <Story summary>

Scenario: <Generated scenario>

@<JIRA-key> tag
```
### 4. Create Java Step Definition Placeholder
Auto-create Java file: **ABC_123StepDefs.java**

Folder: **src/test/java/stepdefinitions/generated/**

Include method stubs for each Given/When/Then using annotations:

```
@Given("I am on the login page")
public void iAmOnLoginPage() {
    // TODO: implement step
}
```

## Phase 3: Automation Script (CLI or Pipeline)
Build a command-line tool or GitLab job that:

Connects to JIRA and fetches recent/unprocessed stories  
Parses and generates .feature files  
Generates or updates Java step files  
(Optional) Flags completed stories in JIRA with a label/comment  

## Phase 4: Configuration & Constraints
Security / Offline Constraints  
All NLP is done via local models (SpaCy, OpenNLP, or fine-tuned LLM)  
No external internet access except to JIRA (intranet-hosted or whitelisted domain)  

### Configurable via:

**config.yaml:**

```
jira_base_url: https://your-jira-instance
jira_username: user
jira_token: token
feature_output_dir: ./features/generated
step_output_dir: ./src/test/java/stepdefinitions/generated
```

## Optional: Enhancements
Mark stories as **"Test Automation Ready"** in JIRA after processing
GUI frontend for non-technical users
Schedule periodic polling (using cron or GitLab CI/CD)

## Deliverables
jira-to-feature CLI tool  
Local NLP parser module  
.feature file generator  
Java step definition stub generator  
Sample stories + output files  
Documentation (README.md)  
