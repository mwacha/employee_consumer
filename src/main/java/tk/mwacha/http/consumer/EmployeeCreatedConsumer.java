package tk.mwacha.http.consumer;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import tk.mwacha.entities.Employee;
import tk.mwacha.events.sender.EmployeeCreatedEvent;
import tk.mwacha.http.notification.Notification;
import tk.mwacha.interactions.EmployeeCreation;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeCreatedConsumer {

    private static final String VERSION_CONTRACT = "1.0";
    private final Gson gson = new Gson();
    private final EmployeeCreation employeeCreation;

    @JmsListener(
            destination = "${queue.employee.created.name}",
            concurrency = "${queue.employee.created.pool-size}")
    public void consumerQueue(String snsNotification) {


            var notification = gson.fromJson(
                    snsNotification,
                    Notification.class);
            var event = gson.fromJson(
                    notification.getMessage(),
                    EmployeeCreatedEvent.class); ;

            log.info("CONSUMER EVENT ID {}", event.getId());

            employeeCreation.create(Employee.fromEmployeeContent(event.getId(), event.getContent()));

    }
}
