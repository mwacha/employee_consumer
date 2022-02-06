package tk.mwacha.http.notification;

public interface EventSender<E> {

    void send(E event);
}