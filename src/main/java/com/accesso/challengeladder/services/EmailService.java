package com.accesso.challengeladder.services;

import java.io.IOException;

import com.sendgrid.*;
import org.apache.log4j.Logger;

public class EmailService
{

    private static final Logger logger = Logger.getLogger(UserService.class.getCanonicalName());
    private static final String api_key = "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";

    public EmailService() throws IOException
    {
    }

    public static boolean sendEmail(String subject, String toEmail, String message)
    {
        Email from = new Email("tabletennis@accesso.com", "Accesso Table Tennis");
        Email to = new Email(toEmail);
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(api_key);
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
            logger.debug(response.statusCode);
            logger.debug(response.body);
            logger.debug(response.headers);

            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
