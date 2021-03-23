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
- HTML/CSS
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
##### Name: create a globally unique name for the compute engine
##### Region: select a region close to your area for minimal latency
##### Machine configuration/series: E2
##### Machine configuration/Machine type: small
##### Boot disk: **Change** to Cent OS Operating system
##### Identity and API access/Access scopes: select **Allow full access to all Cloud APIs**
##### Firewall: Allow both **HTTP and HTTPS traffic**
##### select CREATE
#### B. Setup the VM through SSH
##### 1. Click the SSH button for the server under the Connect column of your list of VM instances
##### 2. Install Maven with the following command:
 - sudo yum install maven
##### 3. Upload project .jar file
 - Click the Settings gear icon at the top-right of the SSH window
 - Click **UPLOAD**
 - Navigate to the project folder (/build/libs/ExpenseReimbursementAPI-all-1.0.jar) and select the .jar file to upload to the VM.
 - Add the environment variable to the VM wiht the following command:
    - export P1_CONN_DETAILS=jdbc:postgresql://**POSTGRESQL DB IP ADDRESS**:5432/**DB NAME**?user\=**DB USERNAME**&password\=**DB PASSWORD**


## Usage

## Contributors 

## License


