package com.bookmyhotel.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SMSService {

    @Value("${sid}")
    private String accountSid;
    @Value("${token}")
    private String authToken;
    @Value("${number}")
    private String fromPhoneNumber;

    public void sendSms(String toNumber, String message) {
        Twilio.init(accountSid,authToken);
        Message messageObject = Message.creator(
                        new PhoneNumber(toNumber),
                        new PhoneNumber(fromPhoneNumber),
                        message)
                .create();
    }
}
