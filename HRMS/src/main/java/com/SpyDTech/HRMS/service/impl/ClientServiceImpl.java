package com.SpyDTech.HRMS.service.impl;

import com.SpyDTech.HRMS.dto.*;
import com.SpyDTech.HRMS.entities.*;
import com.SpyDTech.HRMS.repository.*;
import com.SpyDTech.HRMS.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ModulePermissionRepository modulePermissionRepository;
    @Autowired
    ClientListRepository clientListRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TeamClassRepository teamClassRepository;

    @Override
    public ResponseEntity saveClient(ClientRequest clientRequest) {
        Client client=new Client();
        client.setFirstName(clientRequest.getFirstName());
        if(clientRequest.getLastName()!=null) {
            client.setLastName(clientRequest.getLastName());
        }
        client.setEmailId(clientRequest.getEmailId());
        client.setPassword(clientRequest.getPassword());
        if(clientRequest.getConformPassword().equals(clientRequest.getPassword())) {
            client.setConformPassword(clientRequest.getConformPassword());
        }else{
            ErrorResponse errorResponse = new ErrorResponse("Client Password and Conform Password must be same.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        if(clientRequest.getMobileNo()!=null) {
            client.setMobileNo(clientRequest.getMobileNo());
        }
        client.setUserName(clientRequest.getUserName());
        if(clientRequest.getAddress()!=null) {
            client.setAddress(clientRequest.getAddress());
        }
        Optional<Client> optionalClient=clientRepository.findByClientId(clientRequest.getClientId());
        if(optionalClient.isEmpty()) {
            client.setClientId(clientRequest.getClientId());
        }else{
            ErrorResponse errorResponse = new ErrorResponse("Client with the same ClientId already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

        List<ModulePermission> permissions = new ArrayList<>();
        if (clientRequest.getModulePermissionDto() != null) {
            for (ModulePermissionDto permissionDto : clientRequest.getModulePermissionDto()) {
                ModulePermission permission = new ModulePermission();
                permission.setModuleName(permissionDto.getModuleName());
                permission.setReadAccess(permissionDto.getReadAccess());
                permission.setWriteAccess(permissionDto.getWriteAccess());
                permission.setDeleteAccess(permissionDto.getDeleteAccess());
                permission.setClient(client);
                permissions.add(permission);
            }
        }
        client.setModulePermissions(permissions);
        clientRepository.save(client);
        modulePermissionRepository.saveAll(permissions);
        clientRepository.save(client);
        return ResponseEntity.ok(convertEntityToDto(client));
    }

    @Override
    public ResponseEntity saveClientList(ClientListRequest clientListRequest) {
        ClientList clientList=new ClientList();
        Optional<ClientList> optional=clientListRepository.findByCompanyNameAndRole(clientListRequest.getCompanyName(), clientListRequest.getRole());
        if(optional.isPresent()){
            return new ResponseEntity<>("Client already exists with this Role And CompanyName ", HttpStatus.NOT_FOUND);
        }
        Optional<Client> client=clientRepository.findByClientId(clientListRequest.getClientId());
        if(client.isEmpty()){
            return new ResponseEntity<>("Client not found with this ClientId ", HttpStatus.NOT_FOUND);
        }
        clientList.setClientName(client.get().getFirstName());
        clientList.setRole(clientListRequest.getRole());
        clientList.setCompanyName(clientListRequest.getCompanyName());
        clientListRepository.save(clientList);
        return ResponseEntity.ok(convertClientListToRequest(clientList));
    }

    @Override
    public ResponseEntity getclientList() {
       List<ClientList> clientLists=clientListRepository.findAll();
       List<ClientListRequest> clientListRequests=new ArrayList<>();
       for(ClientList clientList:clientLists){
           clientListRequests.add(convertClientListToRequest(clientList));
       }
       return ResponseEntity.ok(clientLists);
    }

    @Override
    public ResponseEntity getClientDetails(String companyName, String role) {
        ClientDetailsDto clientDetailsDto = new ClientDetailsDto();
        Optional<ClientList> optional = clientListRepository.findByCompanyNameAndRole(companyName, role);
        if (optional.isPresent()) {
            clientDetailsDto.setClientListRequest(convertClientListToRequest(optional.get()));
            List<AddProject> projectDetails = projectRepository.findAllByClientName(optional.get().getCompanyName());
            if (projectDetails != null && !projectDetails.isEmpty()) {
                Map<Team, List<AddProject>> projectsByTeam = projectDetails.stream()
                        .collect(Collectors.groupingBy(AddProject::getTeam));
                List<TeamDetailsDto> teamDetails = new ArrayList<>();
                for (Map.Entry<Team, List<AddProject>> entry : projectsByTeam.entrySet()) {
                    Team team = entry.getKey();
                    List<AddProject> projects = entry.getValue();
                    Optional<TeamClass> teamClassOptional = teamClassRepository.findByTeamName(team.name());
                    Integer employeeCount = teamClassOptional.map(TeamClass::getEmployeesCount).orElse(0);
                    BigDecimal totalBudget = BigDecimal.ZERO;
                    for (AddProject project : projects) {
                        BigDecimal rate = new BigDecimal(project.getRateInDollar());
                        totalBudget = totalBudget.add(rate);
                    }

                    TeamDetailsDto teamDetail = new TeamDetailsDto(
                            team.name(),
                            (long) projects.size(),
                            employeeCount,
                            totalBudget
                    );
                    System.out.println("Adding team details: " + teamDetail);
                    teamDetails.add(teamDetail);
                }

                clientDetailsDto.setTeamDetailsDtoList(teamDetails);
            }
        }else{
            return new ResponseEntity<>("Client not found with this CompanyName and Role ", HttpStatus.NOT_FOUND);

        }

        return ResponseEntity.ok(clientDetailsDto);
    }

    private ClientRequest convertEntityToDto(Client client) {
        ClientRequest clientResponse = new ClientRequest();
        clientResponse.setFirstName(client.getFirstName());
        clientResponse.setLastName(client.getLastName());
        clientResponse.setEmailId(client.getEmailId());
        clientResponse.setUserName(client.getUserName());
        clientResponse.setMobileNo(client.getMobileNo());
        clientResponse.setClientId(client.getClientId());
        clientResponse.setAddress(client.getAddress());
        clientResponse.setModulePermissionDto(convertToDtoList(client.getModulePermissions()));
        return clientResponse;
    }

    private List<ModulePermissionDto> convertToDtoList(List<ModulePermission> permissions) {
        List<ModulePermissionDto> permissionDtos = new ArrayList<>();
        for (ModulePermission permission : permissions) {
            ModulePermissionDto permissionDto = new ModulePermissionDto();
            permissionDto.setId(permission.getId());
            permissionDto.setModuleName(permission.getModuleName());
            permissionDto.setReadAccess(permission.getReadAccess());
            permissionDto.setWriteAccess(permission.getWriteAccess());
            permissionDto.setDeleteAccess(permission.getDeleteAccess());
            permissionDtos.add(permissionDto);
        }
        return permissionDtos;
    }

    public ClientListRequest convertClientListToRequest(ClientList clientList){
        ClientListRequest clientListRequest=new ClientListRequest();
        clientListRequest.setId(clientList.getId());
        clientListRequest.setCompanyName(clientList.getCompanyName());
        clientListRequest.setRole(clientList.getRole());
        clientListRequest.setClientName(clientList.getClientName());
        return clientListRequest;
    }


}
