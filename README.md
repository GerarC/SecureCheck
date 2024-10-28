# SecureCheck
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=GerarC_SecureCheck-Back&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=GerarC_SecureCheck-Back)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=GerarC_SecureCheck-Back&metric=bugs)](https://sonarcloud.io/summary/new_code?id=GerarC_SecureCheck-Back)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=GerarC_SecureCheck-Back&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=GerarC_SecureCheck-Back)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=GerarC_SecureCheck-Back&metric=coverage)](https://sonarcloud.io/summary/new_code?id=GerarC_SecureCheck-Back)

Backend of SecureCheck. A web that helps you to audit companies to fulfill ISO 27001

### Architecture
This API is based on [Ports and Adapters](https://8thlight.com/insights/a-color-coded-guide-to-ports-and-adapters) clean
Architecture, Main modules are:

1. **Domain:**
   it's the center of the application, clean of every external library, contains models, application ports,
   service ports and any util you need inside of it. Principal submodules are:
    - *Model:* Entities of the project (in this case, user, role, company, and so on and so forth).
    - *SPI:* Service Port Interfaces declare which information must receive and send to provider services
      like ORMs or messaging brokers.
    - *API:* Application Port Interfaces declare which information must send and receive components which use Domain
      like Rest or Soap controllers.
2. **Adapters:**
   These adapters make use of ports, submodules are:
    - **Driven:** These adapters provide functionalities to Domain through SPI ports. In this project we are going to
      use JPA mainly
    - **Driving:** These adapters use 'Use cases' to achieve something, in this project we are going to use Rest
      Controllers to make an API.
3. **Configuration:** Here is stored any configuration the project need

### Endpoints
Available endpoints are:
- **Authentication:**
  - `/v1/auth/register/auditor`

