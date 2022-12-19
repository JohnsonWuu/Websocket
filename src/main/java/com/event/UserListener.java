package com.event;

import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserListener {

    @Autowired
    UserService userService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT, classes = UserRegisterEvent.class)
    //@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = UserRegisterEvent.class)
    public void onUserRegisterEvent(UserRegisterEvent event) {

        System.out.println("[BEFORE_COMMIT]onUserRegisterEvent,sendActivationCode!!");
        //userService.sendActivationCode(event.getRegisterDate());
    }

   //@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT, classes = UserRegisterEvent.class)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = UserRegisterEvent.class)
    public void onUserRegisterEvent2(UserRegisterEvent event) {

        System.out.println("[AFTER_COMMIT]onUserRegisterEvent,sendActivationCode!!");

        //userService.sendActivationCode(event.getRegisterDate());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void onUserRegisterEvent3(UserRegisterEvent event) {
        //获取事件传递的数据
        System.out.println("[AFTER_COMPLETION]onUserRegisterEvent,sendActivationCode!!");

    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void onUserRegisterEvent4(UserRegisterEvent event) {
        //获取事件传递的数据
        System.out.println("[AFTER_ROLLBACK]onUserRegisterEvent,sendActivationCode!!");
    }
}
