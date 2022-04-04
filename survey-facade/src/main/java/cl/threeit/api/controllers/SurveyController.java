package cl.threeit.api.controllers;

import cl.threeit.api.business.service.SurveyService;
import cl.threeit.api.entity.CreateSurveyRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SurveyController implements ISurveyController {

    final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @Override
    public ResponseEntity<Object> createRecord(@Valid CreateSurveyRequest request) {
        return new ResponseEntity<>(surveyService.createRecord(request), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getResume() {
        return new ResponseEntity<>(surveyService.getResume(), HttpStatus.OK);
    }

}
