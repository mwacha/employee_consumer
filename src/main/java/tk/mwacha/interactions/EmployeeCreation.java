package tk.mwacha.interactions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.entities.Employee;
import tk.mwacha.entities.NotificationReceived;
import tk.mwacha.exceptions.EntityNotFoundException;
import tk.mwacha.mapper.MessageMapper;
import tk.mwacha.repositories.EmployeeRepository;
import tk.mwacha.repositories.NotificationReceivedRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeCreation {

    private final EmployeeRepository repository;
    private final NotificationReceivedRepository notificationReceivedRepository;
    private final MessageMapper mapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public void create(Employee entity) {
       log.info("CREATING EMPLOYEE");

        var received = NotificationReceived.builder().body(mapper.toJson(entity)).build();
        saveNotificationReceived(received);
      repository.saveAndFlush(entity);
//       var employee = repository.findById(generateId(entity).getId()).orElseThrow(
//           EntityNotFoundException::new);
        try {
          this.synchronize();
          throw new RuntimeException();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        received.setStatus("DONE");

        saveNotificationReceived(received);

        log.info("EMPLOYEE HAS BEEN CREATED ID {} ", entity.getId());
    }

    private void synchronize() throws InterruptedException {
      Thread.sleep(10000);
    }
    private void saveNotificationReceived(NotificationReceived received) {
        notificationReceivedRepository.saveAndFlush(received);
    }

}
