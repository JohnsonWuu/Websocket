package com.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Date;

@Setter
@Getter
public class UserRegisterEvent extends ApplicationEvent {

    private Date registerDate;

    public UserRegisterEvent(Date registerDate) {

        super(registerDate);
        this.registerDate = registerDate;

        System.out.println("UserRegisterEvent,registerDate: " + registerDate);
    }

}
