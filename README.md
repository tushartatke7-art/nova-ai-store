# 🛍️ NOVA AI Store

> **A premium AI-powered e-commerce store with full DevOps pipeline — built from scratch by Tushar Tatke**

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-green?style=for-the-badge&logo=springboot)
![Claude AI](https://img.shields.io/badge/Claude_AI-Anthropic-blueviolet?style=for-the-badge)
![Docker](https://img.shields.io/badge/Docker-Containerised-blue?style=for-the-badge&logo=docker)
![Kubernetes](https://img.shields.io/badge/Kubernetes-k3s-326CE5?style=for-the-badge&logo=kubernetes)
![Jenkins](https://img.shields.io/badge/Jenkins-CD_Pipeline-D24939?style=for-the-badge&logo=jenkins)
![Ansible](https://img.shields.io/badge/Ansible-Automation-EE0000?style=for-the-badge&logo=ansible)
![Prometheus](https://img.shields.io/badge/Prometheus-Monitoring-E6522C?style=for-the-badge&logo=prometheus)
![Grafana](https://img.shields.io/badge/Grafana-Dashboards-F46800?style=for-the-badge&logo=grafana)

---

## 🎯 What is NOVA AI Store?

NOVA AI Store is a **premium e-commerce web application** that uses **Claude AI (Anthropic)** to give customers personalised product recommendations. Instead of manually searching through products, customers simply tell NOVA their style, occasion, budget, and mood — and Claude AI recommends the perfect product with a full style story.

### The Big Idea
```
Customer says:  "I want something minimal, for office, under $500, feeling focused"
Claude AI says: "Urban Pack 🎒 — versatile, clean lines, perfect for the focused professional"
```

This project is not just an app — it is a **complete DevOps learning project** covering every stage of the modern software delivery lifecycle.

---

## 🏗️ Architecture Overview
```
┌─────────────────────────────────────────────────────────────────┐
│                        NOVA AI Store                            │
│                                                                 │
│  Browser ──► Spring Boot (Java 17) ──► Claude API (Anthropic)  │
│                    │                                            │
│              Static Frontend                                    │
│         (HTML + CSS + Vanilla JS)                               │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                     DevOps Pipeline                             │
│                                                                 │
│  Git Push                                                       │
│     │                                                           │
│     ▼                                                           │
│  GitHub Actions (CI)                                            │
│  ✅ Build  ✅ Test  ✅ Docker Build  ✅ Smoke Test              │
│     │                                                           │
│     ▼                                                           │
│  Jenkins (CD)                                                   │
│  ✅ Checkout  ✅ Build  ✅ Docker  ✅ Deploy  ✅ Health Check   │
│     │                                                           │
│     ▼                                                           │
│  Kubernetes / k3s                                               │
│  ✅ 2 Pods Running  ✅ NodePort Service  ✅ Helm Chart         │
│     │                                                           │
│     ▼                                                           │
│  Prometheus + Grafana                                           │
│  ✅ Metrics Scraping  ✅ Live Dashboards                       │
└─────────────────────────────────────────────────────────────────┘
```

---

## 🗂️ Project Structure
```
nova-ai-store/
│
├── src/main/java/com/nova/
│   ├── app/NOVAApplication.java              # Spring Boot entry point
│   ├── controller/HealthController.java       # GET /api/health
│   ├── controller/RecommendationController.java  # POST /api/recommend
│   ├── model/ShoppingRequest.java            # Input model
│   ├── model/ProductRecommendation.java      # Output model
│   ├── service/ClaudeService.java            # Claude AI integration
│   └── config/WebClientConfig.java           # HTTP client
│
├── src/main/resources/static/index.html      # Full NOVA frontend
│
├── ansible/
│   ├── inventory.ini                         # Server list
│   ├── install-docker.yml                    # Playbook: install Docker
│   └── deploy-nova.yml                       # Playbook: deploy NOVA
│
├── k8s/
│   ├── deployment.yaml                       # K8s Deployment (2 replicas)
│   ├── service.yaml                          # NodePort Service
│   ├── servicemonitor.yaml                   # Prometheus scraping
│   ├── blue-deployment.yaml                  # Blue-Green: blue
│   ├── green-deployment.yaml                 # Blue-Green: green
│   └── blue-green-service.yaml               # Blue-Green: service
│
├── helm-chart/
│   ├── Chart.yaml                            # Helm chart metadata
│   ├── values.yaml                           # Default values
│   └── templates/
│       ├── deployment.yaml                   # Helm deployment template
│       └── service.yaml                      # Helm service template
│
├── Dockerfile                                # Multi-stage Docker build
├── docker-compose.yml                        # App compose file
├── docker-compose-monitoring.yml             # Prometheus + Grafana
├── prometheus.yml                            # Prometheus scrape config
├── Jenkinsfile                               # Jenkins CD pipeline
└── .github/workflows/ci.yml                  # GitHub Actions CI
```

---

## 🧰 Tech Stack

| Layer | Technology | Purpose |
|-------|-----------|---------|
| **Language** | Java 17 | Application code |
| **Framework** | Spring Boot 3 | REST API + Web server |
| **AI** | Claude API (Anthropic) | Product recommendations |
| **Build Tool** | Maven | Compile, test, package |
| **Frontend** | HTML + CSS + Vanilla JS | Premium NOVA UI |
| **Container** | Docker | Package the app |
| **Orchestration** | k3s (Kubernetes) | Run containers at scale |
| **Helm** | Helm Charts | Kubernetes package manager |
| **CI** | GitHub Actions | Auto build + test on push |
| **CD** | Jenkins | Auto deploy on merge |
| **Automation** | Ansible | Server setup + deployment |
| **Monitoring** | Prometheus + Grafana | Metrics and dashboards |

---

## 🚀 The Complete Journey — Step by Step

### Step 1 — Fix the Build

The project was copied from BrewMind AI. Several files had old class names inside them. Java requires the class name inside a file to exactly match the filename.

**The Problem:**
```
File: ProductRecommendation.java
Inside: public class CoffeeRecommendation { }  ← WRONG!
```

**The Fix:**
```bash
sed -i 's/class CoffeeRecommendation/class ProductRecommendation/g' \
  src/main/java/com/nova/model/ProductRecommendation.java

mvn package -DskipTests
# BUILD SUCCESS ✅
```

---

### Step 2 — Add AI Recommendation Form to Frontend

Injected an AI Stylist section into `index.html` with 4 inputs (Style, Occasion, Budget, Mood), a yellow CTA button, and a result card showing Claude's recommendation live on the page.

**Design System:**
```css
--bg: #0a0a0a        /* Pure black */
--accent: #e8ff47    /* NOVA yellow */
Font: Bebas Neue + DM Sans + DM Mono
```

---

### Step 3 — Test Claude AI
```bash
curl -X POST http://localhost:8080/api/recommend \
  -H "Content-Type: application/json" \
  -d '{"style":"minimal","occasion":"office","budget":"under 500","mood":"focused"}'

# Response: {"productName":"Urban Pack","emoji":"🎒","priceRange":"$49-$99"...}
```

---

### Step 4 — Dockerise the Application

Used a **multi-stage Dockerfile** — builder stage uses heavy Maven image, final stage uses tiny Alpine JRE (~180MB vs ~600MB).
```bash
sudo docker build -t nova-ai-store:latest .
sudo docker run -d -p 8080:8080 -e ANTHROPIC_API_KEY=your_key nova-ai-store:latest
```

---

### Step 5 — Push to GitHub
```bash
git init
git add .
git commit -m "feat: NOVA AI Store — Spring Boot + Claude AI + Docker + CI/CD"
git remote add origin https://github.com/tushartatke7-art/nova-ai-store.git
git push -u origin main
```

---

### Step 6 — GitHub Actions CI Pipeline

Every push to `main` triggers automatic build, test, Docker build, and smoke test on GitHub's servers.
```yaml
on:
  push:
    branches: [ main ]
jobs:
  build-and-test:
    steps:
      - Build with Maven
      - Run tests
      - Build Docker image
      - Smoke test /api/health
```

---

### Step 7 — Jenkins CD Pipeline

Jenkins runs on Docker at `localhost:8081`. Handles the full deploy cycle after CI passes.

**Key fix:** Used `agent any` instead of Docker agent so Jenkins can access the Docker socket directly.
```groovy
pipeline {
    agent any
    stages {
        stage('Deploy') {
            withCredentials([string(credentialsId: 'ANTHROPIC_API_KEY')]) {
                sh 'docker run -d --name nova-app -p 8080:8080 nova-ai-store:1.0.0'
            }
        }
        stage('Health Check') {
            sh 'APP_IP=$(docker inspect -f "{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}" nova-app)'
            sh 'curl -f http://${APP_IP}:8080/api/health'
        }
    }
}
```

---

### Step 8 — Ansible (New Skill!)

Ansible automates server setup. Write once, run on 1 or 100 servers.

**Playbook 1 — Install Docker:**
```bash
ansible-playbook -i inventory.ini install-docker.yml
# ok=10   changed=4   failed=0 ✅
```

**Playbook 2 — Deploy NOVA:**
```bash
ansible-playbook -i inventory.ini deploy-nova.yml \
  -e "anthropic_api_key=your_key"
# ✅ NOVA AI Store deployed and healthy at port 8080!
```

---

### Step 9 — Kubernetes with k3s
```bash
# Create API key secret
sudo k3s kubectl create secret generic nova-secret \
  --from-literal=anthropic-api-key=your_key

# Import image into k3s containerd
sudo docker save nova-ai-store:1.0.0 | sudo k3s ctr images import -

# Deploy 2 replicas
sudo k3s kubectl apply -f k8s/deployment.yaml
sudo k3s kubectl apply -f k8s/service.yaml
```

**Result:**
```
nova-ai-store-7d7bcc555d-f8qpt   1/1   Running ✅
nova-ai-store-7d7bcc555d-xmlgm   1/1   Running ✅
```

Traffic flows: `Browser → NodePort:30080 → Service → Pod 1 or Pod 2`

---

### Step 10 — Helm Charts
```bash
sudo helm install nova-ai-store helm-chart/ \
  --kubeconfig /etc/rancher/k3s/k3s.yaml \
  --set env.anthropicApiKey=your_key

# STATUS: deployed ✅
```

---

### Step 11 — Prometheus + Grafana

Spring Boot Actuator exposes metrics at `/actuator/prometheus`. Prometheus scrapes every 15s. Grafana visualises everything.
```bash
sudo docker compose -f docker-compose-monitoring.yml up -d
```

- Prometheus: http://localhost:9090
- Grafana: http://localhost:3000 (admin / nova123)

**Key metrics:**
```
jvm_memory_used_bytes         # RAM usage
http_server_requests_seconds  # API response times
process_cpu_usage             # CPU usage
```

---

## ⚡ Quick Start
```bash
git clone https://github.com/tushartatke7-art/nova-ai-store.git
cd nova-ai-store
ANTHROPIC_API_KEY=your_key mvn spring-boot:run
# Visit: http://localhost:8080
```

---

## 🌐 API Reference

### Health Check
```
GET /api/health
→ {"status":"UP","app":"NOVA AI Store","version":"1.0.0"}
```

### AI Recommendation
```
POST /api/recommend
Body: {"style":"minimal","occasion":"office","budget":"under 500","mood":"focused"}
→ {"productName":"Laptop Pro","emoji":"💻","priceRange":"$899-$999"...}
```

---

## 🧠 Key Lessons Learned

| Problem | Root Cause | Solution |
|---------|-----------|----------|
| `BUILD FAILURE: return type required` | Constructor name didn't match class name | `sed` to rename constructor |
| `docker: not found` in Jenkins | Jenkins container had no Docker CLI | `apt install docker.io` inside container |
| `permission denied` on Docker socket | Jenkins user not in docker group | `chmod 666 /var/run/docker.sock` |
| `ANTHROPIC_API_KEY=` empty in Jenkins | Credential not wired correctly | `withCredentials` block in Jenkinsfile |
| Helm `cluster unreachable` | Helm didn't know about k3s config | `--kubeconfig /etc/rancher/k3s/k3s.yaml` |
| Prometheus `host.docker.internal` fails | Linux doesn't support that hostname | Use actual host IP from `hostname -I` |

---

## 📊 Complete Pipeline Flow
```
Developer pushes code
        │
        ▼
GitHub Actions (CI)
✅ Build → Test → Docker → Smoke Test
        │
        ▼
Jenkins (CD)
✅ Checkout → Build → Docker → Deploy → Health Check
        │
        ▼
k3s Kubernetes (2 Pods via Helm)
✅ Load balanced on NodePort :30080
        │
        ▼
Prometheus + Grafana
✅ Metrics scraped every 15s → Live dashboards
```

---

## 👨‍💻 Author

**Tushar Tatke** — DevOps Engineer
- GitHub: [@tushartatke7-art](https://github.com/tushartatke7-art)

---

> *Built with ❤️ and a lot of `BUILD SUCCESS` messages*
