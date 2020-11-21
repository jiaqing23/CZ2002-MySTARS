public interface RegisterNotification {

    /**
     * Abstract method that sends successful course registration notifications to Student objects
     * @param student Student to be sent with notifications.
     * @param index Index number registered by Student object.
     */
    public abstract void sendRegisterNotification(Student student, Index index);
    
}
