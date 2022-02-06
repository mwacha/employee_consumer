package tk.mwacha.interactions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mwacha.exceptions.EntityNotFoundException;
import tk.mwacha.repositories.EmployeeRepository;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeRemoval {

    private final EmployeeRepository repository;

    @Transactional
    public void remove(UUID id) {
        log.info("REMOVING EMPLOYEE ID {}", id);
        var optional = repository.findById(id);

        if (optional.isEmpty()) {
            log.warn("EMPLOYEE NOT FOUND ID {} TO REMOVE", id);
            throw new EntityNotFoundException();
        }
        var employee = optional.get();

        employee.disable();
        repository.save(employee);

        log.info("EMPLOYEE HAS BEEN REMOVED ID {} ", id);
    }
}
