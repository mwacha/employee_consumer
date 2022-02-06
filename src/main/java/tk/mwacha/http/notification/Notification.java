package tk.mwacha.http.notification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notification {

        private String Type;
        private String MessageId;
        private String TopicArn;
        private String Message;
        private String Timestamp;
        private String SignatureVersion;
        private String Signature;
        private String SigningCertURL;
        private String UnsubscribeURL;
}
