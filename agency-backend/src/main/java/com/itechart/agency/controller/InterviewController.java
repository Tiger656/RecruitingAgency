package com.itechart.agency.controller;

import com.itechart.agency.dto.*;
import com.itechart.agency.dto.converter.InterviewGetConverter;
import com.itechart.agency.dto.converter.InterviewSaveConverter;
import com.itechart.agency.entity.Agency;
import com.itechart.agency.entity.Interview;
import com.itechart.agency.repository.ManagerRepository;
import com.itechart.agency.service.ManagerService;
import com.itechart.agency.service.impl.AgencyServiceImpl;
import com.itechart.agency.service.impl.InterviewServiceImpl;
import com.itechart.agency.service.impl.ManagerServiceImpl;
import com.itechart.agency.service.impl.QuestionServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/interview")
public class InterviewController {
    private InterviewServiceImpl interviewService;
    private AgencyServiceImpl agencyService;
    private ManagerServiceImpl managerService;
    private QuestionServiceImpl questionService;
    private ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployerContractController.class);

    @Autowired
    public InterviewController(InterviewServiceImpl interviewService, ModelMapper modelMapper, AgencyServiceImpl agencyService, ManagerServiceImpl managerService, QuestionServiceImpl questionService) {
        this.interviewService = interviewService;
        this.modelMapper = modelMapper;
        this.agencyService = agencyService;
        this.managerService = managerService;
        this.questionService = questionService;
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PostMapping
    public ResponseEntity<?> createInterview(@RequestBody InterviewSaveDto interviewSaveDto) {

        Interview interview = InterviewSaveConverter.toEntity(interviewSaveDto);
        interview = interviewService.create(interview);
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/get-busytime-expert/{agencyId}/{expertId}/{year}/{month}/{day}")
    public ResponseEntity<?> getBusyTimeExpertForManager(@PathVariable("agencyId") Long agencyId,
                                                   @PathVariable("expertId") Long expertId,
                                                   @PathVariable("year") Integer year,
                                                   @PathVariable("month") Integer month,
                                                   @PathVariable("day") Integer day) {
        LOGGER.info("REST request. Path:/interview/get-busytime-expert method: GET.getBusyTimeExpertForManager");
        BusyHoursDto busyHours = interviewService.getExpertsBusyHours(agencyId, expertId, year, month, day);
        return new ResponseEntity<BusyHoursDto>(busyHours, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/get-busytime-manager/{agencyId}/{managerUserId}/{year}/{month}/{day}")
    public ResponseEntity<?> getBusyTimeManagerForManager(@PathVariable("agencyId") Long agencyId,
                                                   @PathVariable("managerUserId") Long managerUserId,
                                                   @PathVariable("year") Integer year,
                                                   @PathVariable("month") Integer month,
                                                   @PathVariable("day") Integer day) {
        LOGGER.info("REST request. Path:/interview/get-busytime-manager method: GET.getBusyTimeManagerForManager");
        BusyHoursDto busyHours = interviewService.getManagersBusyHours(agencyId, managerUserId, year, month, day);
        return new ResponseEntity<BusyHoursDto>(busyHours, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/get-busytime-employee/{agencyId}/{employeeContractId}/{year}/{month}/{day}")
    public ResponseEntity<?> getBusyTimeEmployeeApplicationForManager(@PathVariable("agencyId") Long agencyId,
                                                          @PathVariable("employeeContractId") Long employeeContractId,
                                                          @PathVariable("year") Integer year,
                                                          @PathVariable("month") Integer month,
                                                          @PathVariable("day") Integer day) {
        LOGGER.info("REST request. Path:/interview/get-busytime-manager method: GET.getBusyTimeManagerForManager");
        BusyHoursDto busyHours = interviewService.getEmployeeAppBusyHours(agencyId, employeeContractId, year, month, day);
        return new ResponseEntity<BusyHoursDto>(busyHours, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{agencyId}/{managerUserId}")
    public ResponseEntity<List<InterviewGetDto>> getInterviewsForManager(@PathVariable("agencyId") Long agencyId,
                                                                         @PathVariable("managerUserId") Long managerUserId
    ) {
        LOGGER.info("REST request. Path:/interview method: GET.getInterviewsForManager");
        List<Interview> interviews = interviewService.findAllByAgencyAndManager(agencyId, managerUserId);
        //List<InterviewSaveDto> interviewSaveDtos = new ArrayList<>();
        //interviews.forEach(interview -> interviewSaveDtos.add(InterviewSaveConverter.convertEntityToDto(interview)));
        List<InterviewGetDto> interviewGetDtos = new ArrayList<>();
        interviews.forEach(interview -> interviewGetDtos.add(InterviewGetConverter.convertEntityToDto(interview)));
        return new ResponseEntity<>(interviewGetDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{agencyId}")
    public ResponseEntity<List<InterviewGetDto>> getAllAgencyInterviewsForManager(@PathVariable("agencyId") Long agencyId) {
        LOGGER.info("REST request. Path:/interview method: GET.getAllAgencyInterviewsForManager");
        List<Interview> interviews = interviewService.findAllByAgency(agencyId);
        List<InterviewGetDto> interviewGetDtos = new ArrayList<>();
        interviews.forEach(interview -> interviewGetDtos.add(InterviewGetConverter.convertEntityToDto(interview)));
        return new ResponseEntity<>(interviewGetDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('EXPERT')")
    @GetMapping("/get-interview-for-expert/{agencyId}/{expertUserId}/{statusId}")
    public ResponseEntity<List<InterviewGetDto>> getUnconfirmedInterviewForExpert(@PathVariable("agencyId") Long agencyId,
                                                                       @PathVariable("expertUserId") Long expertUserId,
                                                                       @PathVariable("statusId") Long statusId
    ) {
        LOGGER.info("REST request. Path:/interview method: GET.getInterviewForExpert");
        List<Interview> interviews = interviewService.findAllByAgencyAndExpertAndInterviewStatus(agencyId, expertUserId, statusId);
        List<InterviewGetDto> interviewGetDtos = new ArrayList<>();
        interviews.forEach(interview -> interviewGetDtos.add(InterviewGetConverter.convertEntityToDto(interview)));
        return new ResponseEntity<>(interviewGetDtos, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('EXPERT')")
    @PostMapping("/change-interview-status-expert")
    public ResponseEntity<?/*InterviewGetDto*/> updateInterviewStatusForExpert(@RequestBody InterviewUpdateByExpertDto interviewUpdateByExpertDto) {
        LOGGER.info("REST request. Path:/interview/change-interview-status-expert method: GET.updateInterviewStatusForExpert");

        Interview interview = interviewService.updateInterviewStatus(interviewUpdateByExpertDto.getInterviewId(), interviewUpdateByExpertDto.getInterviewStatusId());
        List<QuestionDto> questionDtos = interviewUpdateByExpertDto.getQuestions();
        questionDtos.forEach((questionDto) -> questionService.createQuestion(questionDto));

        //InterviewGetDto interviewGetDto = InterviewGetConverter.convertEntityToDto(interview);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PutMapping("/change-interview-status")
    public ResponseEntity<InterviewGetDto> updateInterviewStatusForManager(@RequestBody InterviewSaveDto interviewSaveDto
    ) {
        LOGGER.info("REST request. Path:/interview/change-interview-status method: PUT.updateInterviewStatusForManager");
        Interview interview = interviewService.updateInterviewStatus(interviewSaveDto.getId(), interviewSaveDto.getInterviewStatusId());
        InterviewGetDto interviewGetDto = InterviewGetConverter.convertEntityToDto(interview);
        return new ResponseEntity<>(interviewGetDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('EXPERT')or hasAuthority('MANAGER')or hasAuthority('EMPLOYEE')")
    @GetMapping("/get-interview-by-id/{interviewId}")
    public ResponseEntity<InterviewGetDto> getInterviewById(@PathVariable("interviewId") Long interviewId) {
        LOGGER.info("REST request. Path:/interview/get-interview-by-id method: GET.getInterviewById");
        Interview interview = interviewService.findById(interviewId);
        InterviewGetDto interviewGetDto = InterviewGetConverter.convertEntityToDto(interview);
        return new ResponseEntity<>(interviewGetDto, HttpStatus.OK);
    }
}
