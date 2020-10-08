package com.itechart.agency.service.impl;

import com.itechart.agency.entity.Agency;
import com.itechart.agency.exception.NotFoundException;
import com.itechart.agency.repository.AgencyRepository;
import com.itechart.agency.repository.AgencyTransactionRepository;
import com.itechart.agency.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private AgencyRepository agencyRepository;
    @Autowired
    private AgencyTransactionRepository agencyTransactionRepository;
    private static final Integer FIXED_PAYMENT_TERM = 30;

//        @Scheduled(cron = "*/10 * * * * *")
    /* 8 o'clock of every day.*/
//    @Scheduled(cron = "0 0 8 * * *")
    public void trackAgencyPayment() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();


        agencyTransactionRepository.findAll().forEach(agencyTrans -> {
            long milliseconds = date.getTime() - agencyTrans.getDate().getTime();
            int days = (int) (milliseconds / (24 * 60 * 60 * 1000));

            System.out.println(days);
            if (days > FIXED_PAYMENT_TERM) {

                Agency agency = agencyRepository.findById(agencyTrans.getAgency().getId()).orElseThrow(() -> new NotFoundException("Not found agency with id:" + agencyTrans.getAgency().getId()));
                if (agency.getDeposit() < 0) {
                    /*setAgency deActive*/
                }
                System.out.println("Agency "+ agency.getName()+" has deposit after payment "+agency.getDeposit());
//                agency.setDeposit(agency.getDeposit() - agency.getRegularPayment());
                agencyRepository.save(agency);
            }
        });


        System.out.println("1");

    }

}
