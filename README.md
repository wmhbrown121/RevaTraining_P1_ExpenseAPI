# RevaTraining_P1_ExpenseAPI

## Project Description
This is an Expense Reimbursement REST API that allows employees of an organization to submit reimbursement requests that can be approved or denied by management. 
## Technologies Used
- Hibernate - Version 5.4.28
- Javalin - Version 3.13.3
- Mockito - Version 3.7.7
- PostgreSQL - Version 42.2.18
- Auth0/Java-JWT - Version 3.12.1
- Log4j - Version 1.2.17
- JS/HTML/CSS
## Features
Login credentials with JWT authorization for employees and managers.
Displays a table of expense reimbursement requests depending upon the users JWT credentials.
#### Employees 
  - Can submit new expense reimbursement requests.
  - Can view their past expense submissions by status or id.
#### Managers
  - Can view all reimbursement requests for every employee.
  - Can search for requests by their status.
### Future Improvements
  - Add statistics for reimbursement approval amounts for managerial review.
  - Add more fields to employee reimbursement request form allowing employees to attach receipts/invoices
## Getting Started
### 1. Create a new project on Google Cloud Platform
### 2. Create instance of SQL database
#### A. Create a **PostgreSQL** instance with the following minimum requirements:
    - 1 vCPUs
    - 3.75 GB Memory
    - 10 GB SSD storage
#### B. **Users** side menu tab: Create your credentials for the database
    - ADD USER ACCOUNT
      - Set a User name
      - Set a password
      - Set type to PostgreSQL
#### C. **Databases** side menu tab: Create the database that will store the expense and user information
    - CREATE DATABASE
      - Set database name
#### D. **Connections** side menu tab: Enter the IP addresses of the machine that will be accessing the database
    - Select Public IP
    - Under **Authorized networks** -> ADD NETWORK -> New network
      - Set **Name** for the approved netowrk
      - Enter the IP address for the approved network (CIDR notation)
#### E. Information you will need from the SQL instance:
    - Public IP from the **Connect to this instance** section of the SQL **Overview** tab
    - Database name
    - Database User username and password
### 3. Create a **Compute Engine** instance
#### A. Create VM instance with the following minimum configuration settings:
    - Name: create a globally unique name for the compute engine
    - Region: select a region close to your area for minimal latency
    - Machine configuration/series: E2
    - Machine configuration/Machine type: small
    - Boot disk: **Change** to Cent OS Operating system
    - Identity and API access/Access scopes: select **Allow full access to all Cloud APIs**
    - Firewall: Allow both **HTTP and HTTPS traffic**
    - select CREATE
#### B. Setup the VM through SSH
##### i. Click the SSH button for the server under the Connect column of your list of VM instances
##### ii. Install Maven with the following command:
  - sudo yum install maven
##### iii. Upload project .jar file
  - Click the Settings gear icon at the top-right of the SSH window
  - Click **UPLOAD**
  - Navigate to the project folder (/build/libs/ExpenseReimbursementAPI-all-1.0.jar) and select the .jar file to upload to the VM.
  - NOTE: If the .jar file is not there, or is deleted, you can create a new one by running the following command in the command prompt in the project folder:
    - gradlew fatjar
##### iv. Add the environment variable to the VM with the following command:
  - export P1_CONN_DETAILS=jdbc:postgresql://**POSTGRESQL DB IP ADDRESS**:5432/**DB NAME**?user\=**DB USERNAME**&password\=**DB PASSWORD**
### 4. Setup Firewall Rules
#### A. From the **Compute Engine** dashboard, under the **Related Actions** footer, select **Setup Firewall Rules**
#### B. At the top of the dash, select **CREATE FIREWALL RULE** and use the following settings:
##### i. Name: Set a name for the rule (i.e. javalin-server)
##### ii. Description: allow traffic on port 7000
##### iii. Targets/Target tags: **http-server**
##### iv. Source filters/IP ranges: **0.0.0.0/0**
##### v. Protocols and ports: tcp:7000
### 5. Deploy on Storage Bucket
#### A. Under side menu, under Storage, select Storage Browser
#### B. **CREATE BUCKET**
##### i. Give the bucket a globally unique name and press CREATE. No other configuration required.
#### C. Go to the newly created storage bucket
#### D. Select **UPLOAD FILES**
##### i. Find the HTML files in this repo and upload them
#### E. For all 3 HTML files, select the 3 verticle dots to access the **Object Overflow Menu**
##### i. Select **Edit permissions**
##### ii. In the new window, select **+ADD ENTRY** and set entity to **public**
### 6. Start up the server and run.
#### A. All routes in the HTML files will have to be changed to the current IP address of the VM.
#### B. Go the SSH of the VM and run the following command:
##### i. SSH: java -jar ExpenseReimbursementAPI-all-1.0.jar
#### C. Copy the IP of the Compute VM and go to the following path in the browser: http://(IP ADDRESS):7000/
## Contributors 
Adam Reneiri (Revature Trainer)
## License
None


