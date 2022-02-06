package tk.mwacha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.mwacha.entities.NotificationReceived;

import java.util.UUID;

public interface NotificationReceivedRepository extends JpaRepository<NotificationReceived, UUID> {
}
