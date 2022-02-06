package tk.mwacha.http.consumer;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import tk.mwacha.events.sender.EmployeeRemovedEvent;
import tk.mwacha.http.notification.Notification;
import tk.mwacha.interactions.EmployeeRemoval;


@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeRemovedConsumer {

    private static final String VERSION_CONTRACT = "1.0";
    private final Gson gson = new Gson();
    private final EmployeeRemoval removal;

    @JmsListener(
            destination = "${queue.employee.removed.name}",
            concurrency = "${queue.employee.removed.pool-size}")
    public void consumerQueue(String snsNotification) {

            var notification = gson.fromJson(
                    snsNotification,
                    Notification.class);
            var event = gson.fromJson(
                    notification.getMessage(),
                    EmployeeRemovedEvent.class); ;

            log.info("CONSUMER EVENT ID {}", event.getId());

            removal.remove(event.getId());

    }
}
