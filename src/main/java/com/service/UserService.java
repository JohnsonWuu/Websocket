package com.service;

import com.controller.ExceptionRecordNumberGenerator;
import com.entity.User;
import com.event.UserRegisterEvent;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Transactional
    public User userRegister(String name, int age) throws FileNotFoundException {

        User user = User.builder().name(name).age(age).build();

        userRepository.save(user);
        System.out.println("After save [Service]Register user,id: " + user.getId());

        //sendActivationCode(new Date(),user);
        eventPublisher.publishEvent(new UserRegisterEvent(new Date()));
        System.out.println("[Service]eventPublisher.publishEvent");


        //Test throw RuntimeException (Unchecked exception)
        //throw new RuntimeException();

        //Test throw ArithmeticException/NullPointerException
        int i = 1/0;

        return user;



        //Test throw RuntimeException File exception(Checked exception)
        //throw new FileNotFoundException();
    }

    public void sendActivationCode(Date registerDate,User user)
    {
        Random rand = new Random(); //instance of random class
        int upperbound = 25;
        //generate random values from 0-24
        int int_random = rand.nextInt(upperbound);

        System.out.println("sendActivationCode,code: " + int_random + " ,registerDate: " + registerDate + " ,user id: " + user.getId());

        //throw new RuntimeException();
    }

    public List<String> getNo(int count)
    {
        List<String> returnToteRecordNo = ExceptionRecordNumberGenerator.RETURN_TO_TOTE_RECORD.getRecordNumbers(count);

        System.out.println("[RETURN_TO_TOTE_RECORD]return tote record number:" + returnToteRecordNo);

        return returnToteRecordNo;

    }
}
