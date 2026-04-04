[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=22088203)
# CACBA - Final Project

Repository to keep all files related to Final Project

Content:

1. **application** - put your application source code in this folder
1. **infrastructure** - put all scripts used to build artifacts, eg. Dockerfiles, Shell scripts, Terraform files etc.


## Manual Deployment Documentation

This project was deployed manually using Docker, Amazon ECR, Amazon ECS (Fargate), and an Application Load Balancer.

### 0. Build JAR File

Before building the Docker image, the Java application was packaged into a JAR file:

mvn clean package

After the build finished, the generated JAR file was renamed to `app.jar` and placed into the `target/` directory so it could be copied correctly by the Dockerfile.

### 1. Build Docker Image

The Docker image was built locally using the provided Dockerfile:

docker build -f infrastructure/Dockerfile -t recipesite-app:v3 application

### 2. Authenticate Docker to Amazon ECR

Docker was authenticated against Amazon ECR using AWS CLI credentials:

aws ecr get-login-password --region <REGION> | docker login --username AWS --password-stdin <AWS_ACCOUNT_ID>.dkr.ecr.<REGION>.amazonaws.com

### 3. Tag and Push Image to ECR

The built image was tagged and pushed to the ECR repository:

docker tag recipesite-app:v3 <AWS_ACCOUNT_ID>.dkr.ecr.<REGION>.amazonaws.com/recipesite-app:v3
docker push <AWS_ACCOUNT_ID>.dkr.ecr.<REGION>.amazonaws.com/recipesite-app:v3

### 4. Create New ECS Task Definition Revision

In AWS Console:
- Go to Amazon ECS → Task Definitions
- Select existing task definition (recipesite-task)
- Click "Create new revision"
- Update container image to:
  <AWS_ACCOUNT_ID>.dkr.ecr.<REGION>.amazonaws.com/recipesite-app:v3
- Configure environment variables (database credentials and AWS temporary credentials)
- Save the new revision

### 5. Update ECS Service

- Go to Amazon ECS → Clusters → Services
- Select recipesite-task-service
- Click Update
- Select latest task definition revision
- Enable "Force new deployment"
- Deploy the service

### 6. Networking and Load Balancer Configuration

- Launch type: Fargate
- Network mode: awsvpc
- Auto-assign public IP for ECS tasks: Enabled
- Application Load Balancer:
  - Type: Application Load Balancer
  - Scheme: Internet-facing
  - Listener: HTTP on port 8080
  - Target group:
    - Target type: IP
    - Protocol: HTTP
    - Port: 8080
    - Health check path: /

### 7. Access Application

After deployment and successful health checks, the application is accessible at:

http://<APPLICATION_LOAD_BALANCER_DNS>:8080

## Reflections About the Project

During this project, I learned how Docker images are built, versioned, and deployed using Amazon ECR and ECS with Fargate. I also gained hands-on experience configuring ECS services, task definitions, networking, and load balancing in AWS.

The main obstacles were related to networking, security groups, load balancer configuration, and understanding how ECS tasks receive traffic. These issues were solved by correctly configuring the Application Load Balancer, target groups, public IP assignment, and security group rules.

What surprised me most was how many small configuration details (ports, protocols, public IPs, health checks) must be aligned correctly for the application to become reachable, even when the container itself is working properly.
