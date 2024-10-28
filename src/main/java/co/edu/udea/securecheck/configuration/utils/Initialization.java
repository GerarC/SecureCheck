package co.edu.udea.securecheck.configuration.utils;


import co.edu.udea.securecheck.adapter.driven.jpa.entity.*;
import co.edu.udea.securecheck.adapter.driven.jpa.repository.*;
import co.edu.udea.securecheck.domain.api.AuditServicePort;
import co.edu.udea.securecheck.domain.utils.annotation.Generated;
import co.edu.udea.securecheck.domain.utils.enums.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

@Generated
@Configuration
@RequiredArgsConstructor
public class Initialization {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DomainRepository domainRepository;
    private final ControlRepository controlRepository;
    private final CompanyRepository companyRepository;
    private final DefaultQuestionRepository defaultQuestionRepository;
    private final CustomQuestionRepository customQuestionRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuditServicePort auditServicePort;

    private static final String DEFAULT_QUESTION_TEMPLATE = "How is the control for %s being enforced?";

    @Bean
    public CommandLineRunner init() {
        return args -> {

            if (userRepository.findByEmail("admin@admin.com").isPresent()) return;
            // Roles
            List<RoleEntity> roles = List.of(
                    new RoleEntity(null, RoleName.ADMIN),
                    new RoleEntity(null, RoleName.AUDITOR),
                    new RoleEntity(null, RoleName.AUDITED)
            );
            roleRepository.saveAll(roles);

            // Users
            List<UserEntity> users = List.of(
                    new UserEntity(
                            null,
                            "Admin",
                            "Admin",
                            "0000000001",
                            LocalDateTime.of(2000, 1, 1, 1, 1),
                            "+573013424656",
                            "admin@admin.com",
                            passwordEncoder.encode("#P4ssw0rd*"),
                            roles.get(0),
                            null
                    ),
                    new UserEntity(
                            null,
                            "Gerardo",
                            "Castillo",
                            "0000000002",
                            LocalDateTime.of(1995, 2, 15, 10, 0),
                            "+573123456789",
                            "gerardoe.castillo@udea.edu.co",
                            passwordEncoder.encode("password"),
                            roles.get(1),
                            null
                    )
            );
            userRepository.saveAll(users);

            // Example Companies
            List<CompanyEntity> companies = List.of(
                    CompanyEntity.builder()
                            .nit("9009876543")
                            .name("GreenTech Innovations")
                            .address("101 Green Park, Cartagena")
                            .contactEmail("contact@greentech.com")
                            .contactPhone("+57 3217654321")
                            .createdAt(LocalDateTime.now())
                            .user(users.get(1))  // John Doe user
                            .build(),
                    CompanyEntity.builder()
                            .nit("9005432189")
                            .name("Global Solutions Ltd.")
                            .address("22 Enterprise Street, Barranquilla")
                            .contactEmail("info@globalsol.com")
                            .contactPhone("+57 3149876543")
                            .createdAt(LocalDateTime.now())
                            .user(users.get(0))  // Admin user
                            .build(),
                    CompanyEntity.builder()
                            .nit("9006789123")
                            .name("Skyline Enterprises")
                            .address("34 Horizon Avenue, Medellín")
                            .contactEmail("hello@skyline.com")
                            .contactPhone("+57 3023456789")
                            .createdAt(LocalDateTime.now())
                            .user(users.get(0))  // Admin user
                            .build(),
                    CompanyEntity.builder()
                            .nit("9001239876")
                            .name("TechFuture Co.")
                            .address("76 Tech Plaza, Cali")
                            .contactEmail("future@techfuture.com")
                            .contactPhone("+57 3187654321")
                            .createdAt(LocalDateTime.now())
                            .user(users.get(1))  // John Doe user
                            .build()
            );
            List<CompanyEntity> savedCompanies = companyRepository.saveAll(companies);

            DomainEntity domain = DomainEntity.builder()
                    .index(5)
                    .name("Organizational Controls")
                    .description("Controls related to the governance framework of the organization for information security")
                    .build();
            domainRepository.save(domain);

            // Controls for Organizational Controls Domain (A.5)
            List<ControlEntity> controlFive = List.of(
                    ControlEntity.builder()
                            .index(1)
                            .name("Information Security Policies")
                            .description("The information security policy and topic-specific policies must be defined, approved by management, published, communicated, and acknowledged by relevant personnel and stakeholders, and reviewed at planned intervals or when significant changes occur.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(2)
                            .name("Information Security Roles and Responsibilities")
                            .description("Information security roles and responsibilities must be defined and assigned according to the organization's needs.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(3)
                            .name("Segregation of Duties")
                            .description("Conflicting duties and areas of conflicting responsibility must be separated.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(4)
                            .name("Management Responsibilities")
                            .description("Management must require all personnel to apply information security in accordance with the established information security policy and the organization's specific policies and procedures.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(5)
                            .name("Contact with Authorities")
                            .description("The organization must establish and maintain contact with relevant authorities.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(6)
                            .name("Contact with Special Interest Groups")
                            .description("The organization must establish and maintain contact with special interest groups or other specialist security forums and professional associations.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(7)
                            .name("Threat Intelligence")
                            .description("Information related to information security threats must be collected and analyzed to generate threat intelligence.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(8)
                            .name("Information Security in Project Management")
                            .description("Information security must be integrated into project management.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(9)
                            .name("Inventory of Information and Associated Assets")
                            .description("An inventory of information and associated assets, including owners, must be developed and maintained.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(10)
                            .name("Acceptable Use of Information and Associated Assets")
                            .description("Rules for the acceptable use and procedures for the handling of information and associated assets must be identified, documented, and implemented.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(11)
                            .name("Return of Assets")
                            .description("Personnel and other relevant stakeholders must return all organizational assets in their possession when changing or terminating their employment, contract, or agreement.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(12)
                            .name("Information Classification")
                            .description("Information must be classified according to the organization's information security needs based on confidentiality, integrity, availability, and relevant stakeholder requirements.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(13)
                            .name("Information Labeling")
                            .description("An appropriate set of procedures for labeling information according to the information classification scheme adopted by the organization must be developed and implemented.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(14)
                            .name("Information Transfer")
                            .description("Rules, procedures, or agreements for information transfer must exist for all types of transfer facilities within the organization and between the organization and other parties.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(15)
                            .name("Access Control")
                            .description("Rules for controlling physical and logical access to information and associated assets must be established and implemented based on information security and business requirements.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(16)
                            .name("Identity Management")
                            .description("The complete lifecycle of identities must be managed.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(17)
                            .name("Authentication Information")
                            .description("The allocation and management of authentication information must be controlled through a management process, including advising personnel on the proper handling of authentication information.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(18)
                            .name("Access Rights")
                            .description("Access rights to information and other associated assets must be provided, reviewed, modified, and removed according to the organization's access control policy and specific rules.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(19)
                            .name("Information Security in Supplier Relationships")
                            .description("Processes and procedures for managing information security risks associated with the use of supplier products or services must be defined and implemented.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(20)
                            .name("Addressing Information Security in Supplier Agreements")
                            .description("Relevant information security requirements must be established and agreed upon with each supplier based on the type of relationship with the supplier.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(21)
                            .name("Information Security Management in ICT Supply Chain")
                            .description("Processes and procedures for managing information security risks associated with the ICT supply chain must be defined and implemented.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(22)
                            .name("Monitoring, Review, and Management of Supplier Services Changes")
                            .description("The organization must monitor, review, evaluate, and manage changes in supplier information security practices and service delivery periodically.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(23)
                            .name("Information Security for Cloud Services")
                            .description("Processes for the acquisition, use, management, and exit of cloud services must be established according to the organization's information security requirements.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(24)
                            .name("Incident Management Planning and Preparation")
                            .description("The organization must plan and prepare for managing information security incidents by defining, establishing, and communicating incident management processes, roles, and responsibilities.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(25)
                            .name("Assessment and Decision on Information Security Events")
                            .description("The organization must assess information security events and decide whether they should be classified as information security incidents.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(26)
                            .name("Response to Information Security Incidents")
                            .description("Information security incidents must be responded to according to documented procedures.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(27)
                            .name("Learning from Information Security Incidents")
                            .description("Knowledge gained from information security incidents must be used to strengthen and improve information security controls.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(28)
                            .name("Evidence Collection")
                            .description("Procedures for identifying, collecting, acquiring, and preserving evidence related to information security events must be established and implemented.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(29)
                            .name("Information Security during Disruptions")
                            .description("The organization must plan how to maintain information security at an appropriate level during disruptions.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(30)
                            .name("ICT Preparation for Business Continuity")
                            .description("ICT preparation must be planned, implemented, maintained, and tested based on business continuity objectives and ICT continuity requirements.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(31)
                            .name("Legal, Statutory, Regulatory, and Contractual Requirements")
                            .description("Relevant legal, statutory, regulatory, and contractual requirements for information security and the organization's approach to complying with these requirements must be identified, documented, and kept up to date.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(32)
                            .name("Intellectual Property Rights")
                            .description("The organization must implement appropriate procedures to protect intellectual property rights.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(33)
                            .name("Protection of Records")
                            .description("Records must be protected against loss, destruction, falsification, unauthorized access, and unauthorized disclosure.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(34)
                            .name("Privacy and Protection of Personally Identifiable Information (PII)")
                            .description("The organization must identify and comply with requirements related to the preservation of privacy and protection of PII according to applicable laws, regulations, and contractual requirements.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(35)
                            .name("Independent Review of Information Security")
                            .description("The organization's approach to managing information security and its implementation, including people, processes, and technologies, must be reviewed independently at planned intervals or when significant changes occur.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(36)
                            .name("Compliance with Information Security Policies, Standards, and Guidelines")
                            .description("Compliance with the organization's information security policy, policies, rules, and topic-specific standards must be reviewed periodically.")
                            .domain(domain)
                            .build(),
                    ControlEntity.builder()
                            .index(37)
                            .name("Documented Operating Procedures")
                            .description("Operating procedures for information processing facilities must be documented and made available to the staff who need them.")
                            .domain(domain)
                            .build()
            );
            controlRepository.saveAll(controlFive);

            List<DefaultQuestionEntity> defaultFiveQuestions = controlFive.stream()
                    .map(control -> DefaultQuestionEntity.builder()
                            .body(String.format(DEFAULT_QUESTION_TEMPLATE, control.getName()))
                            .control(control)
                            .build()
                    ).toList();
            defaultQuestionRepository.saveAll(defaultFiveQuestions);

            // Domains
            DomainEntity personnelDomain = DomainEntity.builder()
                    .index(6)
                    .name("Personnel Controls")
                    .description("Controls related to personnel security within the organization")
                    .build();
            domainRepository.save(personnelDomain);

            // Controls for Personnel Controls Domain (A.6)
            List<ControlEntity> controlSix = List.of(
                    ControlEntity.builder()
                            .index(1)
                            .name("Background Verification Checks")
                            .description("Background verification checks on all candidates for employment must be carried out prior to joining the organization and continuously thereafter, considering applicable laws, regulations, and ethics, proportional to business requirements, information classification, and perceived risks.")
                            .domain(personnelDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(2)
                            .name("Terms and Conditions of Employment")
                            .description("Employment contracts must define the responsibilities of personnel and the organization concerning information security.")
                            .domain(personnelDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(3)
                            .name("Information Security Awareness, Education, and Training")
                            .description("All personnel and relevant stakeholders must receive appropriate information security awareness, education, and training, including periodic updates on the organization's information security policy, and relevant specific policies and procedures based on their job function.")
                            .domain(personnelDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(4)
                            .name("Disciplinary Process")
                            .description("A formal disciplinary process must be established and communicated to take action against personnel and relevant stakeholders who violate the information security policy.")
                            .domain(personnelDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(5)
                            .name("Responsibilities after Termination or Change of Employment")
                            .description("Responsibilities and duties concerning information security that remain valid after employment termination or change must be defined, applied, and communicated to relevant personnel and stakeholders.")
                            .domain(personnelDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(6)
                            .name("Confidentiality or Non-Disclosure Agreements")
                            .description("Confidentiality or non-disclosure agreements reflecting the organization's needs for protecting information must be identified, documented, regularly reviewed, and signed by personnel and relevant stakeholders.")
                            .domain(personnelDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(7)
                            .name("Remote Working")
                            .description("Security measures must be implemented when personnel work remotely to protect information accessed, processed, or stored outside the organization's facilities.")
                            .domain(personnelDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(8)
                            .name("Information Security Event Reporting")
                            .description("The organization must provide a mechanism for personnel to report observed or suspected information security events through appropriate channels in a timely manner.")
                            .domain(personnelDomain)
                            .build()
            );
            controlRepository.saveAll(controlSix);

            List<DefaultQuestionEntity> defaultSixQuestions = controlSix.stream()
                    .map(control -> DefaultQuestionEntity.builder()
                            .body(String.format(DEFAULT_QUESTION_TEMPLATE, control.getName()))
                            .control(control)
                            .build()
                    ).toList();
            defaultQuestionRepository.saveAll(defaultSixQuestions);

            DomainEntity physicalDomain = DomainEntity.builder()
                    .index(7)
                    .name("Physical Controls")
                    .description("Controls related to physical security and protection of information systems and facilities")
                    .build();
            domainRepository.save(physicalDomain);

            // Controls for Physical Controls Domain (A.7)
            List<ControlEntity> controlSeven = List.of(
                    ControlEntity.builder()
                            .index(1)
                            .name("Physical Security Perimeters")
                            .description("Security perimeters will be defined and used to protect areas containing information and other associated assets.")
                            .domain(physicalDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(2)
                            .name("Physical Entry Control")
                            .description("Secure areas must be protected by appropriate entry controls and access points.")
                            .domain(physicalDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(3)
                            .name("Securing Offices, Rooms, and Facilities")
                            .description("Physical security for offices, rooms, and facilities must be designed and implemented.")
                            .domain(physicalDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(4)
                            .name("Physical Security Monitoring")
                            .description("Premises must be continuously monitored for unauthorized physical access.")
                            .domain(physicalDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(5)
                            .name("Protection Against Physical and Environmental Threats")
                            .description("Protection against physical and environmental threats, such as natural disasters and other intentional or unintentional threats to infrastructure, must be designed and implemented.")
                            .domain(physicalDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(6)
                            .name("Working in Secure Areas")
                            .description("Security measures for working in secure areas must be designed and implemented.")
                            .domain(physicalDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(7)
                            .name("Clear Desk and Clear Screen Policy")
                            .description("Clear desk rules for documents and removable storage media and clear screen rules for information processing facilities must be defined and enforced.")
                            .domain(physicalDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(8)
                            .name("Equipment Placement and Protection")
                            .description("Equipment must be securely placed and protected.")
                            .domain(physicalDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(9)
                            .name("Off-site Asset Protection")
                            .description("Assets off-site must be protected.")
                            .domain(physicalDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(10)
                            .name("Media Handling")
                            .description("Media must be managed throughout its lifecycle from acquisition, use, transport, and disposal according to the organization's classification and handling requirements.")
                            .domain(physicalDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(11)
                            .name("Support Utilities")
                            .description("Information processing facilities must be protected against power outages and other interruptions caused by support utility failures.")
                            .domain(physicalDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(12)
                            .name("Cabling Security")
                            .description("Cables carrying power, data, or support information services must be protected against interception, interference, or damage.")
                            .domain(physicalDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(13)
                            .name("Equipment Maintenance")
                            .description("Equipment must be properly maintained to ensure the availability, integrity, and confidentiality of information.")
                            .domain(physicalDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(14)
                            .name("Secure Disposal or Reuse of Equipment")
                            .description("Equipment items containing storage media must be verified to ensure all confidential data and licensed software have been securely removed or overwritten before disposal or reuse.")
                            .domain(physicalDomain)
                            .build()
            );
            controlRepository.saveAll(controlSeven);

            List<DefaultQuestionEntity> defaultSevenQuestions = controlSeven.stream()
                    .map(control -> DefaultQuestionEntity.builder()
                            .body(String.format(DEFAULT_QUESTION_TEMPLATE, control.getName()))
                            .control(control)
                            .build()
                    ).toList();
            defaultQuestionRepository.saveAll(defaultSevenQuestions);


            DomainEntity techDomain = DomainEntity.builder()
                    .index(8)
                    .name("Technological Controls")
                    .description("Controls related to technological and IT security measures for protecting information systems and data.")
                    .build();
            domainRepository.save(techDomain);

            // Controls for Technological Controls Domain (A.8)
            List<ControlEntity> controlsEight = List.of(
                    ControlEntity.builder()
                            .index(1)
                            .name("End-User Device Protection")
                            .description("Information stored, processed, or accessible through end-user devices will be protected.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(2)
                            .name("Privileged Access Rights")
                            .description("The allocation and use of privileged access rights will be restricted and managed.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(3)
                            .name("Restriction of Information Access")
                            .description("Access to information and other associated assets will be restricted according to the specific access control policy established.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(4)
                            .name("Source Code Access")
                            .description("Read and write access to source code, development tools, and software libraries will be managed appropriately.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(5)
                            .name("Secure Authentication")
                            .description("Secure authentication technologies and procedures will be implemented based on access control restrictions and the specific access control policy.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(6)
                            .name("Capacity Management")
                            .description("Resource usage will be controlled and adjusted according to current and anticipated capacity requirements.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(7)
                            .name("Malware Protection")
                            .description("Malware protection will be implemented and supported through adequate user awareness.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(8)
                            .name("Technical Vulnerability Management")
                            .description("Information about technical vulnerabilities of information systems in use will be obtained, the organization's exposure to such vulnerabilities will be assessed, and appropriate measures will be taken.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(9)
                            .name("Configuration Management")
                            .description("Configurations, including security configurations, for hardware, software, services, and networks must be established, documented, implemented, monitored, and reviewed.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(10)
                            .name("Information Deletion")
                            .description("Information stored in information systems, devices, or any other storage media will be deleted when no longer required.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(11)
                            .name("Data Masking")
                            .description("Data masking will be used according to the organization's specific access control policy and other related policies, considering applicable legislation.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(12)
                            .name("Data Leakage Prevention")
                            .description("Data leakage prevention measures will be applied to systems, networks, and any other devices that process, store, or transmit sensitive information.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(13)
                            .name("Information Backup")
                            .description("Information, software, and system backups will be maintained and tested periodically according to the agreed backup policy.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(14)
                            .name("Redundancy of Information Processing Facilities")
                            .description("Information processing facilities will be implemented with sufficient redundancy to meet availability requirements.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(15)
                            .name("Logging")
                            .description("Logs recording activities, exceptions, failures, and other relevant events will be produced, stored, protected, and analyzed.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(16)
                            .name("Monitoring Activities")
                            .description("Networks, systems, and applications will be monitored for anomalous behavior, and appropriate actions will be taken to assess potential information security incidents.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(17)
                            .name("Clock Synchronization")
                            .description("The clocks of information processing systems used by the organization must be synchronized with approved time sources.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(18)
                            .name("Use of Privileged Utility Programs")
                            .description("The use of utility programs that can override system and application controls must be restricted and strictly controlled.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(19)
                            .name("Software Installation on Operating Systems")
                            .description("Procedures and measures for securely managing software installation on operating systems will be implemented.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(20)
                            .name("Network Security")
                            .description("Networks and network devices will be secured, managed, and controlled to protect information in systems and applications.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(21)
                            .name("Network Services Security")
                            .description("Security mechanisms, service levels, and service requirements for network services will be identified, implemented, and controlled.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(22)
                            .name("Network Segregation")
                            .description("Groups of information services, users, and information systems must be segregated in the organization's networks.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(23)
                            .name("Web Filtering")
                            .description("Access to external websites will be managed to reduce exposure to malicious content.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(24)
                            .name("Cryptography Usage")
                            .description("Rules for effective use of cryptography, including cryptographic key management, will be defined and implemented.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(25)
                            .name("Secure Development Lifecycle")
                            .description("Rules for secure software and system development will be established and applied.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(26)
                            .name("Application Security Requirements")
                            .description("Information security requirements must be identified, specified, and approved when developing or acquiring applications.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(27)
                            .name("Secure System Architecture and Engineering Principles")
                            .description("Principles for secure system engineering must be established, documented, maintained, and applied to any information system development activity.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(28)
                            .name("Secure Coding")
                            .description("Secure coding principles will be applied during software development.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(29)
                            .name("Security Testing During Development and Acceptance")
                            .description("Security testing processes will be defined and implemented throughout the development lifecycle.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(30)
                            .name("Outsourced Development")
                            .description("The organization must direct, monitor, and review activities related to outsourced system development.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(31)
                            .name("Separation of Development, Testing, and Production Environments")
                            .description("Development, testing, and production environments must be separated and protected.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(32)
                            .name("Change Management")
                            .description("Changes to information processing facilities and information systems will be subject to change management procedures.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(33)
                            .name("Test Information")
                            .description("Test information will be selected, protected, and managed appropriately.")
                            .domain(techDomain)
                            .build(),
                    ControlEntity.builder()
                            .index(34)
                            .name("Protection of Information Systems During Audits")
                            .description("AuditEntity tests and other assurance activities involving information system evaluation must be planned and agreed upon between the evaluator and management.")
                            .domain(techDomain)
                            .build()
            );
            controlRepository.saveAll(controlsEight);

            List<DefaultQuestionEntity> defaultEightQuestions = controlsEight.stream()
                    .map(control -> DefaultQuestionEntity.builder()
                            .body(String.format(DEFAULT_QUESTION_TEMPLATE, control.getName()))
                            .control(control)
                            .build()
                    ).toList();
            defaultQuestionRepository.saveAll(defaultEightQuestions);

            List<DefaultQuestionEntity> defaultQuestions = defaultQuestionRepository.findAll();
            savedCompanies.forEach(company -> {
                List<CustomQuestionEntity> customQuestions = defaultQuestions.stream()
                        .map(question ->
                                CustomQuestionEntity.builder()
                                        .company(company)
                                        .control(question.getControl())
                                        .body(question.getBody())
                                        .build()
                        ).toList();
                customQuestionRepository.saveAll(customQuestions);
            });

            savedCompanies.forEach(company -> auditServicePort.createAudit(company.getId()));
        };
    }
}
